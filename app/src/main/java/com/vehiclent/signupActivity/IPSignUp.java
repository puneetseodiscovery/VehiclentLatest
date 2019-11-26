package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;

public interface IPSignUp {

    void doSignUp(String firstname, String lastname, String email, String password, String phonenumber, String address,String gender);

    void onSignUpResponseFromModel(int statusValue);

    void onSignUpSucess(RegisterResponseModel registerResponseModel);

    void onSignUpFailed(String message);



    void doSocailLogin(String email, String user_name, String device_token);
    void doSocailLoginResponseFromModel(int statusValue);
    void doSocailLoginSucessResponseFromModel(SocialLoginResponseModel socialLoginResponseModel);
    void doSocailLoginFailedResponseFromModel(String message);
}
