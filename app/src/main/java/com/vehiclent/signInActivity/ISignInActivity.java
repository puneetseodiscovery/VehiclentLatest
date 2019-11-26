package com.vehiclent.signInActivity;

import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.UploadImageResponseModel;

public interface ISignInActivity {
    // for signin
    void onSignInResponseFromPresenter(int statusValue);

    void onSignInSuccessResponseFromPresenter(SignInResponseModel signInResponseModel);

    void onSignInFailedResponseFromPresenter(String message);

    // for upload image after signin
    void onUploadImageSuccessFromPresenter(UploadImageResponseModel uploadImageResponseModel);
    void onUploadImageFailedFromPresenter(String message);

}
