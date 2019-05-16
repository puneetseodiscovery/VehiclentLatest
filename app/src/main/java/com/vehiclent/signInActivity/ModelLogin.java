package com.vehiclent.signInActivity;

import android.os.Handler;
import android.os.Message;
import com.vehiclent.responseModel.UserLoginResponseModel;
import com.vehiclent.retrofit.APIInterface;
import com.vehiclent.retrofit.RetrofitCalls;

import java.util.logging.LogRecord;

public class ModelLogin implements IModelLogin {

    String email, password = "";
    IPLogin ipLogin;

    int value;


    public ModelLogin(String email, String password, PLogin pLogin) {
        this.email = email;
        this.password = password;
        this.ipLogin = pLogin;
    }

    @Override
    public void loginRestCall() {

        BeanLogin beanLogin = new BeanLogin(email, password);
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginUser(beanLogin, mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    UserLoginResponseModel userLoginResponseModel = (UserLoginResponseModel) msg.obj;
                    ipLogin.onLoginSucess(userLoginResponseModel);
                    break;

                case APIInterface.LOGIN_FAILED:
                    ipLogin.onLoginFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };

}
