package com.vehiclent.signupActivity;

import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;

public class PSignUp implements IPSignUp {

    ISignUpAcitivity iSignUpAcitivity;
    IModelSignUp iModelSignUp;

    public PSignUp(SignUpActivity signUpActivity) {
        this.iSignUpAcitivity = signUpActivity;
    }

    @Override
    public void doSignUp(String firstname, String lastname, String email, String password, String phonenumber, String address, String gender) {
        iModelSignUp = new ModelSignUp(this);
        iModelSignUp.signUpRestCall(firstname, lastname, email, password, phonenumber, address, gender);
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

    @Override
    public void doSocailLogin(String email, String user_name, String device_token) {
        iModelSignUp = new ModelSignUp(this);
        iModelSignUp.socialLoginRestCall(email, user_name, device_token);
    }

    @Override
    public void doSocailLoginResponseFromModel(int statusValue) {
        iSignUpAcitivity.onSocailLoginFromPresenter(statusValue);
    }

    @Override
    public void doSocailLoginSucessResponseFromModel(SocialLoginResponseModel socialLoginResponseModel) {
        iSignUpAcitivity.onSocailLoginSuccessFromPresenter(socialLoginResponseModel);

    }

    @Override
    public void doSocailLoginFailedResponseFromModel(String message) {
        iSignUpAcitivity.onSocailLoginFailedFromPresenter(message);

    }
}
