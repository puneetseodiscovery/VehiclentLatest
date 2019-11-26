package com.vehiclent.fogotPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.ForgotPasswordResponse;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.utils.CommonMethods;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

public class ForgotPassword extends BaseClass implements View.OnClickListener, IForgotPassword {

    ImageView img_back;
    TextView tv_about_forgotpassword;
    Button btn_resetpassword;
    ForgotPassword context;
    EditText edit_usereamil;
    IPForgotPassword ipForgotPassword;
    SavePref savePref;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        context = ForgotPassword.this;
        ipForgotPassword = new PForgotPassword(this);
        savePref = new SavePref(this);
        progressDialog = new ProgressDialog(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        img_back = (ImageView) findViewById(R.id.img_back);
        edit_usereamil = (EditText) findViewById(R.id.edit_usereamil);
        btn_resetpassword = (Button) findViewById(R.id.btn_resetpassword);
        btn_resetpassword.setTypeface(Utility.typeFaceForBoldText(this));
        tv_about_forgotpassword = (TextView) findViewById(R.id.tv_about_forgotpassword);
        tv_about_forgotpassword.setTypeface(Utility.typeFaceForBoldText(this));
    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_resetpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_back:

                finish();
                break;
            case R.id.btn_resetpassword:

                if (Utility.isNetworkConnected(context)) {

                    validationonForgotPassword();

                } else {
                    Toast.makeText(context, getString(R.string.check_network_connection), Toast.LENGTH_SHORT).show();

                }

                break;
        }

    }

    private void validationonForgotPassword() {

        if (edit_usereamil.getText().toString().trim().isEmpty()) {
            edit_usereamil.setError("Please enter email address");
        } else if (!CommonMethods.isValidEmail(edit_usereamil.getText().toString())) {
            edit_usereamil.setError("Please enter valid email ");
        } else {
            progressDialog = Utility.showLoader(ForgotPassword.this);
            ipForgotPassword.doForgotPassword(edit_usereamil.getText().toString());
        }


    }

    @Override
    public void onForgotPasswordResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();

    }

    @Override
    public void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponse forgotPasswordResponse) {
        progressDialog.dismiss();
        Intent intent = new Intent(ForgotPassword.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "Password send on your email Sucessfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onForgotPasswordFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
      //  Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();

    }
}
