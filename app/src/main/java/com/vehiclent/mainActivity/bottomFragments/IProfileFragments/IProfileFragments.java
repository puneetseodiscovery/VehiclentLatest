package com.vehiclent.mainActivity.bottomFragments.IProfileFragments;

import com.vehiclent.responseModelClasses.UpdateProfileResponseModel;
import com.vehiclent.responseModelClasses.UserProfileResponseModel;

public interface IProfileFragments {

    void onProfileFromPresenter(int statusValue);
    void onProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel);
    void onProfileFailedFromPresenter(String message);

    void onupdateProfilefromPresenter(int statusValue);
    void onUpdateProfileSucessFromPresenter(UpdateProfileResponseModel updateProfileResponseModel);
    void onuUpdateProfileFaildFromPresenter(String message);
}
