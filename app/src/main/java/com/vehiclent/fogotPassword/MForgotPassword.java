package com.vehiclent.fogotPassword;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.ForgotPasswordResponse;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MForgotPassword implements IMForgotPassword {


    IPForgotPassword imForgotPassword;

    public MForgotPassword(PForgotPassword pForgotPassword) {

        this.imForgotPassword=pForgotPassword;

    }

    @Override
    public void forgotPasswordRestCalls(String email) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.forgot_password_Api(email, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.FORGOT_SUCCESS:

                    ForgotPasswordResponse forgotPasswordResponse = (ForgotPasswordResponse) msg.obj;
                    imForgotPassword.onForgotPasswordSucess(forgotPasswordResponse);

                    break;
                case APIInterface.FORGOT_FAILED:
                    imForgotPassword.onForgotPasswordFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
