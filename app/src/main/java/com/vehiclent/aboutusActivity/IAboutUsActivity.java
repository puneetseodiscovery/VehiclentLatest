package com.vehiclent.aboutusActivity;

import com.vehiclent.responseModelClasses.AboutUsResponseModel;

public interface IAboutUsActivity {

    void onAboutUsResponseFromPresenter(int statusValue);
    void onAboutUsSuccessFromPresenter(AboutUsResponseModel aboutUsResponseModel);
    void onAboutUsFailedFromPresenter(String message);
}
