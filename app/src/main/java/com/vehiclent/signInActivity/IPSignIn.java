package com.vehiclent.signInActivity;

import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.UploadImageResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IPSignIn {

    void doSignIn(String email,String password,String device_token,String latitude,String longitude);
    void onSignInResponseFromModel(int statusValue);
    void onSignInSucess(SignInResponseModel signInResponseModel);
    void onSignInFailed(String message);


    void doUploadImage(String id, MultipartBody.Part body, RequestBody imgReq);
    void onUploadImageSuccess(UploadImageResponseModel uploadImageResponseModel);
    void onUploadImageFailed(String meaage);

}
