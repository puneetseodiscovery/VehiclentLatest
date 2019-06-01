package com.vehiclent.aboutusActivity;

import com.vehiclent.responseModelClasses.AboutUsResponseModel;

public interface IPAboutUs {
    void doAboutUs(String id);
    void onAboutUsResponseFromModel(int statusValue);
    void onAboutUsSucess(AboutUsResponseModel aboutUsResponseModel);
    void onAboutUsFailed(String message);
}
