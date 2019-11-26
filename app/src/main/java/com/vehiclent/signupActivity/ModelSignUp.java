package com.vehiclent.signupActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class ModelSignUp implements IModelSignUp {

    IPSignUp ipSignUp;

    public ModelSignUp(PSignUp pSignUp) {
        this.ipSignUp = pSignUp;
    }

    @Override
    public void signUpRestCall(String firstname, String lastname, String email, String password, String phonenumber, String address, String gender) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_register_Api(firstname, lastname, email, password, phonenumber, address, gender, mHandler);
    }

    @Override
    public void socialLoginRestCall(String email, String user_name, String device_token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.socail_Login_Api(email, user_name, device_token, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case APIInterface.SIGNUP_SUCCESS:
                    RegisterResponseModel registerResponseModel = (RegisterResponseModel) msg.obj;
                    ipSignUp.onSignUpSucess(registerResponseModel);
                    break;

                case APIInterface.SIGNUP_FAILED:
                    ipSignUp.onSignUpFailed(String.valueOf(msg.obj));
                    break;
                case APIInterface.SOCIAL_LOGIN_SUCCESS:
                    SocialLoginResponseModel socialLoginResponseModel = (SocialLoginResponseModel) msg.obj;
                    ipSignUp.doSocailLoginSucessResponseFromModel(socialLoginResponseModel);
                    break;

                case APIInterface.SOCIAL_LOGIN_FAILED:
                    ipSignUp.doSocailLoginFailedResponseFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
