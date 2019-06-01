package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;

public interface ISignUpAcitivity {

    void onSignUpResponseFromPresenter(int statusValue);

    void onSignUpSucessFromPresenter(RegisterResponseModel registerResponseModel);

    void onSignUpFaildeFromPresenter(String message);
}
