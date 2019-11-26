package com.vehiclent.mainActivity.bottomFragments.Presenters;
import com.vehiclent.mainActivity.bottomFragments.IProfileFragments.IMProfileFragments;
import com.vehiclent.mainActivity.bottomFragments.IProfileFragments.IPProfileFragment;
import com.vehiclent.mainActivity.bottomFragments.IProfileFragments.IProfileFragments;
import com.vehiclent.mainActivity.bottomFragments.Models.MProfileFragment;
import com.vehiclent.mainActivity.bottomFragments.ProfileFragment;
import com.vehiclent.responseModelClasses.UpdateProfileResponseModel;
import com.vehiclent.responseModelClasses.UserProfileResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PProfileFragments implements IPProfileFragment {

    IProfileFragments iProfileFragments;
    IMProfileFragments imProfileFragments;

    public PProfileFragments(ProfileFragment profileFragment) {

        this.iProfileFragments=profileFragment;

    }

    @Override
    public void doGetProfile(String id) {

        imProfileFragments = new MProfileFragment( this);
        imProfileFragments.getProfileRestCall(id);
    }

    @Override
    public void onGetProfileResponseFromModel(int statusValue) {
        iProfileFragments.onProfileFromPresenter(statusValue);
    }

    @Override
    public void onGetProfileSucessResponseFromModel(UserProfileResponseModel userProfileResponseModel) {
        iProfileFragments.onProfileSuccessFromPresenter(userProfileResponseModel);
    }

    @Override
    public void onGetProfileFailedMessage(String message) {
        iProfileFragments.onProfileFailedFromPresenter(message);
    }


    @Override
    public void doUpdateProfile(String id, String first_name, String last_name, String gender, String email, String phone_number, String address, MultipartBody.Part body, RequestBody imgReq) {

        imProfileFragments = new MProfileFragment( this);
        imProfileFragments.updateProfileRestCall(id,first_name,last_name,gender,email,phone_number,address,body,imgReq);
    }

    @Override
    public void onUpdateProfileResponseFromModel(int statusValue) {
        iProfileFragments.onupdateProfilefromPresenter(statusValue);
    }

    @Override
    public void onUpdateProfileSucessResponseModel(UpdateProfileResponseModel updateProfileResponseModel) {
        iProfileFragments.onUpdateProfileSucessFromPresenter(updateProfileResponseModel);
    }

    @Override
    public void onUpdateProfileFailedMessage(String message) {

        iProfileFragments.onuUpdateProfileFaildFromPresenter(message);

    }
}
