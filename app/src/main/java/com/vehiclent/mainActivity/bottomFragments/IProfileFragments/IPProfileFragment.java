package com.vehiclent.mainActivity.bottomFragments.IProfileFragments;

import com.vehiclent.responseModelClasses.UpdateProfileResponseModel;
import com.vehiclent.responseModelClasses.UserProfileResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IPProfileFragment {

    void doGetProfile(String id);

    void onGetProfileResponseFromModel(int statusValue);

    void onGetProfileSucessResponseFromModel(UserProfileResponseModel userProfileResponseModel);

    void onGetProfileFailedMessage(String message);


    void doUpdateProfile(String id, String first_name, String last_name, String gender, String email, String phone_number, String address, MultipartBody.Part body, RequestBody imgReq);

    void onUpdateProfileResponseFromModel(int statusValue);

    void onUpdateProfileSucessResponseModel(UpdateProfileResponseModel updateProfileResponseModel);

    void onUpdateProfileFailedMessage(String message);

}
