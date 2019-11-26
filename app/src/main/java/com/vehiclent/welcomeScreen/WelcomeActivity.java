package com.vehiclent.welcomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.signupActivity.SignUpActivity;
import com.vehiclent.utils.Utility;

public class WelcomeActivity extends BaseClass implements View.OnClickListener {

    Button btn_login, btn_sign_up;
    TextView tv_addmore,tv_welcome;
    WelcomeActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Initialization();
        EventListner();
    }

    private void Initialization() {
        context=this;
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        tv_addmore = (TextView) findViewById(R.id.tv_addmore);
        tv_addmore.setTypeface(Utility.typeFaceForBoldText(this));
        tv_welcome = (TextView) findViewById(R.id.tv_welcome);
        tv_welcome.setTypeface(Utility.typeFaceForBoldText(this));

    }

    private void EventListner() {
        btn_login.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_login:

                intent = new Intent(WelcomeActivity.this, SignInActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_sign_up:

                intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent);

                break;
        }

    }
}
