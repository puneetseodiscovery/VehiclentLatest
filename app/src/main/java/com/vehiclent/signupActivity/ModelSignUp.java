package com.vehiclent.signupActivity;

import android.os.Handler;
import android.os.Message;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class ModelSignUp implements IModelSignUp {

    IPSignUp ipSignUp;

    public ModelSignUp(PSignUp pSignUp) {
        this.ipSignUp = pSignUp;
    }

    @Override
    public void signUpRestCall(String firstname, String lastname, String email, String password, String phonenumber, String address) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_register_Api(firstname, lastname, email, password, phonenumber, address, mHandler);
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
            }
        }
    };
}
