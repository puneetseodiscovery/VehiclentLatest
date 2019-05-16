package com.vehiclent.signInActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.vehiclent.responseModel.UserLoginResponseModel;
import com.vehiclent.signupActivity.SignUpActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends BaseClass  {


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

    IPLogin iPresenterLogin;

    ProgressDialog progressDialog;
    int treatmentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        context = SignInActivity.this;
        ButterKnife.bind(this);

        iPresenterLogin = new PLogin(this);

        tv_forgotpassword = (TextView) findViewById(R.id.tv_forgotpassword);
        signUpClick = (TextView) findViewById(R.id.signUpClick);

        tv_forgotpassword.setTypeface(Utility.typeFaceForBoldText(this));

        tv_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        signUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


       /* btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isNetworkConnected(context)) {
                    if (edit_usereamil.getText().toString().length() > 0 && edit_userpassword.getText().toString().length() > 0) {


                        if (Utility.validEmail(edit_usereamil.getText().toString().trim())) {

                            progressDialog = Utility.showLoader(SignInActivity.this);
                            iPresenterLogin.doLogin(edit_usereamil.getText().toString().trim(),
                                    edit_userpassword.getText().toString().trim());

                        } else {
                            edit_usereamil.setError("Enter a valid email.");
                            edit_usereamil.requestFocus();
                        }
                    } else {
                        if (edit_usereamil.getText().toString().length() == 0 && edit_userpassword.getText().toString().length() == 0) {
                            edit_usereamil.setError("Enter a valid email.");
                            edit_userpassword.setError("Enter password");
                            edit_usereamil.requestFocus();
                        } else if (edit_userpassword.getText().toString().length() == 0) {
                            edit_userpassword.setError("Enter password");
                            edit_userpassword.requestFocus();
                        } else if (edit_usereamil.getText().toString().length() == 0) {
                            edit_usereamil.setError("Enter a valid email.");
                            edit_usereamil.requestFocus();
                        }
                        //Toast.makeText(this, "Enter correct login details.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }

    @Override
    public void onLoginResponseFromPresenter(int statusValue) {

        Toast.makeText(this, "" + statusValue, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);


    }

    @Override
    public void onLoginSuccessFromPresenter(UserLoginResponseModel userLoginResponseModel) {


        progressDialog.dismiss();
        if (treatmentActivity == 0) {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();


        } else {
            onBackPressed();
            finish();
        }

    }

    @Override
    public void onLoginFailedFromPresenter(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    //    progressDialog.dismiss();


    }*/
    }
}
