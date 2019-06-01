package com.vehiclent.contactusActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsUsActivity extends BaseClass {

    @BindView(R.id.et_useremail)
    EditText et_useremail;

    @BindView(R.id.edit_carcare)
    EditText edit_carcare;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.img_back)
    ImageView img_back;

    // SavePref savePref;

    ContactsUsActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_us);

        ButterKnife.bind(this);
        context = ContactsUsActivity.this;
        //    savePref = new SavePref(this);

        //    et_useremail.setText(savePref.getuser_email());

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isNetworkConnected(context)) ;
            }
        });
    }
}
