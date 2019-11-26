package com.vehiclent.contactusActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsUsActivity extends BaseClass implements IContactsUs, View.OnClickListener {

    @BindView(R.id.et_useremail)
    EditText et_useremail;

    @BindView(R.id.edit_sendmail)
    EditText edit_sendmail;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.relative_layout)
    RelativeLayout relative_layout;

    @BindView(R.id.img_back)
    ImageView img_back;

    SavePref savePref;
    ContactsUsActivity context;
    IPContactsUs ipContactsUs;
    String get_id;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_us);

        ButterKnife.bind(this);
        context = ContactsUsActivity.this;
        savePref = new SavePref(this);
        ipContactsUs = new PContactsUs(this);


        Initialization();

    }

    private void Initialization() {

        progressDialog = new ProgressDialog(context);

        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(context);
            ipContactsUs.doGetContactsUs(savePref.getid());

        } else {
            Toast.makeText(ContactsUsActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();

        }


        EventListners();

    }

    private void EventListners() {

        img_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();

                break;
            case R.id.btn_submit:

                if (Utility.isNetworkConnected(context)) {

                    ValidationOnMessageField();

                } else {

                    Toast.makeText(context, "Please check your internet connection!", Toast.LENGTH_SHORT).show();

                }


                break;
        }

    }

    private void ValidationOnMessageField() {

        if (edit_sendmail.getText().toString().trim().isEmpty()) {

            Snackbar snackbar = Snackbar
                    .make(relative_layout, "Please write your message", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(relative_layout, "", Snackbar.LENGTH_SHORT);
                            snackbar1.dismiss();
                        }
                    });

            snackbar.show();
        } else {

            progressDialog = Utility.showLoader(ContactsUsActivity.this);
            ipContactsUs.doContactsUs(savePref.getid(), edit_sendmail.getText().toString().trim());
        }

    }

    @Override
    public void onContactsUsResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();

    }

    @Override
    public void onContactsUsSuccessResponseFromPresenter(ContactsUsResponseModel contactsUsResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(context, "Your message successfully submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onContactsUsFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetContactUsResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onGetContactUsSuccessResponseFromPresenter(GetContactsusResponseModel getContactsusResponseModel) {
        progressDialog.dismiss();
        if (getContactsusResponseModel.getData().get(0) != null) {
            et_useremail.setText(getContactsusResponseModel.getData().get(0).getEmail());
        } else {
            et_useremail.setText("");

        }
    }

    @Override
    public void onGetContactUsFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

}
