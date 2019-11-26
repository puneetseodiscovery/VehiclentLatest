package com.vehiclent.fogotPassword;
import com.vehiclent.responseModelClasses.ForgotPasswordResponse;
public class PForgotPassword implements  IPForgotPassword {

    IForgotPassword iForgotPassword;
    IMForgotPassword imForgotPassword;

    public PForgotPassword(IForgotPassword iForgotPassword) {
        this.iForgotPassword = iForgotPassword;
    }

    @Override
    public void doForgotPassword(String email) {
        imForgotPassword = new MForgotPassword(this);
        imForgotPassword.forgotPasswordRestCalls(email);
    }

    @Override
    public void onForgotPasswordFromModel(int statusValue) {
        iForgotPassword.onForgotPasswordResponseFromPresenter(statusValue);
    }

    @Override
    public void onForgotPasswordSucess(ForgotPasswordResponse forgotPasswordResponse) {
        iForgotPassword.onForgotPasswordSuccessResponseFromPresenter(forgotPasswordResponse);
    }

    @Override
    public void onForgotPasswordFailed(String message) {

        iForgotPassword.onForgotPasswordFailedResponseFromPresenter(message);

    }
}
