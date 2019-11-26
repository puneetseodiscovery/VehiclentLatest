package com.vehiclent.signInActivity;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.UploadImageResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class PSignIn implements IPSignIn {

    ISignInActivity iSignInActivity;
    IMSignIn imSignIn;

    public PSignIn(ISignInActivity iSignInActivity) {
        this.iSignInActivity = iSignInActivity;
    }

    @Override
    public void doSignIn(String email, String password,String device_token,String latitude,String longitude) {

        imSignIn = new ModelSignIn(this);
        imSignIn.signInRestCalls(email, password,device_token,latitude,longitude);
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


    @Override
    public void doUploadImage(String id, MultipartBody.Part body, RequestBody imgReq) {
        imSignIn = new ModelSignIn(this);
        imSignIn.uploadImageRestCall(id, body,imgReq);
    }

    @Override
    public void onUploadImageSuccess(UploadImageResponseModel uploadImageResponseModel) {
        iSignInActivity.onUploadImageSuccessFromPresenter(uploadImageResponseModel);

    }

    @Override
    public void onUploadImageFailed(String meaage) {
        iSignInActivity.onUploadImageFailedFromPresenter(meaage);

    }
}
