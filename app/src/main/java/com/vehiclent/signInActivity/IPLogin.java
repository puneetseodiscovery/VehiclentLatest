package com.vehiclent.signInActivity;

import com.vehiclent.responseModel.UserLoginResponseModel;

public interface IPLogin {

    void doLogin(String email, String password);

    void onLoginResponseFromMode(int statusValue);

    void onLoginSucess(UserLoginResponseModel userLoginResponseModel);

    void onLoginFailed(String message);
}
