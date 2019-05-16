package com.vehiclent.signInActivity;

import com.vehiclent.responseModel.UserLoginResponseModel;

public interface ILoginActivity {

    void onLoginResponseFromPresenter(int statusValue);

    void onLoginSuccessFromPresenter(UserLoginResponseModel userLoginResponseModel);

    void onLoginFailedFromPresenter(String message);

}
