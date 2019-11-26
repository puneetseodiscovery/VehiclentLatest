package com.vehiclent.fogotPassword;

import com.vehiclent.responseModelClasses.ForgotPasswordResponse;
public interface IPForgotPassword {

    void doForgotPassword(String email);
    void onForgotPasswordFromModel(int statusValue);
    void onForgotPasswordSucess(ForgotPasswordResponse forgotPasswordResponse);
    void onForgotPasswordFailed(String message);

}
