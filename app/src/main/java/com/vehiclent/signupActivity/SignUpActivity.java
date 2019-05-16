package com.vehiclent.signupActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.utils.Utility;
import com.vehiclent.welcomeScreen.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    SignUpActivity context;

    @BindView(R.id.tv_or)
    TextView tv_or;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.btn_user_signup)
    Button btn_user_signup;

    @BindView(R.id.edit_firstname)
    EditText edit_firstname;

    @BindView(R.id.edit_lastname)
    EditText edit_lastname;

    @BindView(R.id.edit_email)
    EditText edit_email;


    @BindView(R.id.edit_password)
    EditText edit_password;

    @BindView(R.id.edit_confirm_password)
    EditText edit_confirm_password;

    @BindView(R.id.edit_mobileno)
    EditText edit_mobileno;

    @BindView(R.id.edit_address)
    EditText edit_address;

    @BindView(R.id.checked)
    CheckBox checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        context = this;

        btn_login.setTypeface(Utility.typeFaceForBoldText(this));
        tv_or.setTypeface(Utility.typeFaceForBoldText(this));

    }

    private void EventListner() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

                break;
        }

    }

}
