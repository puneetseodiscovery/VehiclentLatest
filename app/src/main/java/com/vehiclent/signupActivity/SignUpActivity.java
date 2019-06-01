package com.vehiclent.signupActivity;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.utils.CommonMethods;
import com.vehiclent.utils.Utility;
import com.vehiclent.welcomeScreen.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseClass implements View.OnClickListener, ISignUpAcitivity {

    SignUpActivity context;

    @BindView(R.id.tv_or)
    TextView tv_or;

    @BindView(R.id.btn_login)
    Button btn_login;

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

    @BindView(R.id.btn_user_signup)
    Button btn_user_signup;

    IPSignUp ipSignUp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        context = SignUpActivity.this;
        ipSignUp = new PSignUp(this);
        btn_login.setTypeface(Utility.typeFaceForBoldText(this));
        tv_or.setTypeface(Utility.typeFaceForBoldText(this));

    }

    private void EventListner() {
        btn_login.setOnClickListener(this);
        btn_user_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

                break;

            case R.id.btn_user_signup:

                if (Utility.isNetworkConnected(context)) {

                    if (edit_firstname.getText().toString().trim().isEmpty()) {

                        edit_firstname.setError("Please enter first name");

                    } else if (edit_lastname.getText().toString().trim().isEmpty()) {

                        edit_lastname.setError("Please enter last name");

                    } else if (edit_email.getText().toString().trim().isEmpty()) {

                        edit_email.setError("Please enter email");

                    } else if (!CommonMethods.isValidEmail(edit_email.getText().toString())) {

                        edit_email.setError("Please enter valid email");

                    } else if (edit_password.getText().toString().trim().isEmpty()) {

                        edit_password.setError("Please enter password");

                    } else if (!CommonMethods.isValidPassword(edit_password.getText().toString().trim())) {

                        edit_password.setError("Password should contain 1 numeric or 1 character or 1 special character");

                    } else if (edit_password.getText().toString().trim().length() < 6) {

                        edit_password.setError("Password length should be 6 character or long");

                    } else if (edit_confirm_password.getText().toString().trim().isEmpty()) {

                        edit_confirm_password.setError("Password confirmed your password");

                    } else if (!edit_confirm_password.getText().toString().trim().matches(edit_password.getText().toString().trim())) {

                        edit_confirm_password.setError("Password does not match");

                    } else if (edit_mobileno.getText().toString().trim().isEmpty()) {

                        edit_mobileno.setError("Please enter phone number");

                    } else if (edit_address.getText().toString().trim().isEmpty()) {

                        edit_address.setError("Please enter address");

                    } else {

                        progressDialog = Utility.showLoader(SignUpActivity.this);
                        ipSignUp.doSignUp(edit_firstname.getText().toString().trim(), edit_lastname.getText().toString().trim()
                                , edit_email.getText().toString().trim(), edit_password.getText().toString().trim(),
                                edit_mobileno.getText().toString().trim(), edit_address.getText().toString().trim());
                    }

                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
        }

    }

    @Override
    public void onSignUpResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onSignUpSucessFromPresenter(RegisterResponseModel registerResponseModel) {

        progressDialog.dismiss();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "Registration Sucessfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignUpFaildeFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "Registration failed" +message, Toast.LENGTH_SHORT).show();


    }
}
