package com.vehiclent.signInActivity;

import com.vehiclent.responseModel.UserLoginResponseModel;

public class PLogin implements IPLogin {

    ILoginActivity iLoginActivity;
    IModelLogin iModelLogin;

    public PLogin(SignInActivity signInActivity) {

        //this.iLoginActivity=signInActivity;

    }

    @Override
    public void doLogin(String email, String password) {

        iModelLogin = new ModelLogin(email, password, this);
        iModelLogin.loginRestCall();

    }

    @Override
    public void onLoginResponseFromMode(int statusValue) {
        iLoginActivity.onLoginResponseFromPresenter(statusValue);
    }

    @Override
    public void onLoginSucess(UserLoginResponseModel userLoginResponseModel) {

        iLoginActivity.onLoginSuccessFromPresenter(userLoginResponseModel);

    }

    @Override
    public void onLoginFailed(String message) {

        iLoginActivity.onLoginFailedFromPresenter(message);

    }
}
