package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;

public interface ISignUpAcitivity {

    void onSignUpResponseFromPresenter(int statusValue);

    void onSignUpSucessFromPresenter(RegisterResponseModel registerResponseModel);

    void onSignUpFaildeFromPresenter(String message);


    void onSocailLoginFromPresenter(int statusValue);

    void onSocailLoginSuccessFromPresenter(SocialLoginResponseModel socialLoginResponseModel);

    void onSocailLoginFailedFromPresenter(String message);
}
