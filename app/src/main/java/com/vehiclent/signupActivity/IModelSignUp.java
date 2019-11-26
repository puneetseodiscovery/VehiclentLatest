package com.vehiclent.signupActivity;

public interface IModelSignUp {
    void signUpRestCall(String firstname, String lastname, String email, String password, String phonenumber, String address,String gender);
    void socialLoginRestCall(String email, String user_name, String device_token);
}
