package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;

public class PSignUp implements IPSignUp {

    ISignUpAcitivity iSignUpAcitivity;
    IModelSignUp iModelSignUp;

    public PSignUp(SignUpActivity signUpActivity) {
        this.iSignUpAcitivity = signUpActivity;
    }

    @Override
    public void doSignUp(String firstname, String lastname, String email, String password, String phonenumber, String address) {
        iModelSignUp = new ModelSignUp(this);
        iModelSignUp.signUpRestCall(firstname, lastname, email, password, phonenumber, address);
    }

    @Override
    public void onSignUpResponseFromModel(int statusValue) {
        iSignUpAcitivity.onSignUpResponseFromPresenter(statusValue);
    }

    @Override
    public void onSignUpSucess(RegisterResponseModel registerResponseModel) {
        iSignUpAcitivity.onSignUpSucessFromPresenter(registerResponseModel);

    }

    @Override
    public void onSignUpFailed(String message) {
        iSignUpAcitivity.onSignUpFaildeFromPresenter(message);

    }
}
