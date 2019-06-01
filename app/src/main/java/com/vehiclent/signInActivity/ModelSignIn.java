package com.vehiclent.signInActivity;

import android.os.Handler;
import android.os.Message;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class ModelSignIn implements IMSignIn {

    IPSignIn ipSignIn;

    public ModelSignIn(PSignIn pSignIn) {
        this.ipSignIn = pSignIn;
    }

    @Override
    public void signInRestCalls(String email, String password) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_Login_Api(email, password, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:

                    SignInResponseModel signInResponseModel = (SignInResponseModel) msg.obj;
                    ipSignIn.onSignInSucess(signInResponseModel);

                    break;
                case APIInterface.LOGIN_FAILED:
                    ipSignIn.onSignInFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
