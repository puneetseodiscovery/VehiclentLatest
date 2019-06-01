package com.vehiclent.signInActivity;

import com.vehiclent.responseModelClasses.SignInResponseModel;

public interface ISignInActivity {

    void onSignInResponseFromPresenter(int statusValue);
    void onSignInSuccessResponseFromPresenter(SignInResponseModel signInResponseModel);
    void onSignInFailedResponseFromPresenter(String message);
}
