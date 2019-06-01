package com.vehiclent.signInActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.fogotPassword.ForgotPassword;
import com.vehiclent.mainActivity.HomeActivity;
import com.vehiclent.mainActivity.MainActivity;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.signupActivity.SignUpActivity;
import com.vehiclent.utils.CommonMethods;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends BaseClass implements ISignInActivity, View.OnClickListener {

    SignInActivity context;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.tv_forgotpassword)
    TextView tv_forgotpassword;

    @BindView(R.id.signUpClick)
    TextView signUpClick;

    @BindView(R.id.edit_usereamil)
    EditText edit_usereamil;

    @BindView(R.id.edit_userpassword)
    EditText edit_userpassword;

    ProgressDialog progressDialog;

    IPSignIn ipSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        context = SignInActivity.this;
        ButterKnife.bind(this);
        ipSignIn=new PSignIn(this);
        Initialization();


    }

    private void Initialization() {

        tv_forgotpassword.setTypeface(Utility.typeFaceForBoldText(this));
        EventListner();
    }

    private void EventListner() {

        tv_forgotpassword.setOnClickListener(this);
        signUpClick.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;

        switch (v.getId()) {
            case R.id.tv_forgotpassword:
                 intent = new Intent(SignInActivity.this, ForgotPassword.class);
                startActivity(intent);
                break;
            case R.id.signUpClick:
                 intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:

               ValidationOnSignIn(); // validation on user login cradintials

                break;

        }
    }

    private void ValidationOnSignIn() {

        if (Utility.isNetworkConnected(context)) {

             if (edit_usereamil.getText().toString().trim().isEmpty()) {

                 edit_usereamil.setError("Please enter email");

            } else if (!CommonMethods.isValidEmail(edit_usereamil.getText().toString())) {

                 edit_usereamil.setError("Please enter valid email");

            } else if (edit_userpassword.getText().toString().trim().isEmpty()) {

                 edit_userpassword.setError("Please enter password");

            } else {

                progressDialog = Utility.showLoader(SignInActivity.this);
                 ipSignIn.doSignIn(edit_usereamil.getText().toString().trim(), edit_userpassword.getText().toString().trim());
            }

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onSignInResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onSignInSuccessResponseFromPresenter(SignInResponseModel signInResponseModel) {
        progressDialog.dismiss();
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "Login Sucessfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignInFailedResponseFromPresenter(String message) {

    }


}
