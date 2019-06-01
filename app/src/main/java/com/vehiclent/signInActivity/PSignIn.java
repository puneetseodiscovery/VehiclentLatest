package com.vehiclent.signInActivity;
import com.vehiclent.responseModelClasses.SignInResponseModel;


public class PSignIn implements IPSignIn {

    ISignInActivity iSignInActivity;
    IMSignIn imSignIn;

    public PSignIn(ISignInActivity iSignInActivity) {
        this.iSignInActivity = iSignInActivity;
    }

    @Override
    public void doSignIn(String email, String password) {

        imSignIn = new ModelSignIn(this);
        imSignIn.signInRestCalls(email, password);
    }

    @Override
    public void onSignInResponseFromModel(int statusValue) {
        iSignInActivity.onSignInResponseFromPresenter(statusValue);

    }

    @Override
    public void onSignInSucess(SignInResponseModel signInResponseModel) {
        iSignInActivity.onSignInSuccessResponseFromPresenter(signInResponseModel);
    }

    @Override
    public void onSignInFailed(String message) {
        iSignInActivity.onSignInFailedResponseFromPresenter(message);
    }
}
