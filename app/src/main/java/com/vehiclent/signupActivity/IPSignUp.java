package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;

public interface IPSignUp {

    void doSignUp(String firstname, String lastname, String email, String password, String phonenumber, String address);

    void onSignUpResponseFromModel(int statusValue);

    void onSignUpSucess(RegisterResponseModel registerResponseModel);

    void onSignUpFailed(String message);
}
