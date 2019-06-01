package com.vehiclent.signInActivity;

import com.vehiclent.responseModelClasses.SignInResponseModel;

public interface IPSignIn {

    void doSignIn(String email,String password);
    void onSignInResponseFromModel(int statusValue);
    void onSignInSucess(SignInResponseModel signInResponseModel);
    void onSignInFailed(String message);

}
