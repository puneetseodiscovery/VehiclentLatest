package com.vehiclent.fogotPassword;

import com.vehiclent.responseModelClasses.ForgotPasswordResponse;

public interface IForgotPassword {

    void onForgotPasswordResponseFromPresenter(int statusValue);
    void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponse forgotPasswordResponse);
    void onForgotPasswordFailedResponseFromPresenter(String message);
}
