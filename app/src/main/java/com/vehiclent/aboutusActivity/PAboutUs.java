package com.vehiclent.aboutusActivity;


import com.vehiclent.responseModelClasses.AboutUsResponseModel;

public class PAboutUs implements IPAboutUs {

    IAboutUsActivity iAboutUsActivity;
    IModelAboutUs iModelAboutUs;

    public PAboutUs(AboutUsActivity aboutUsActivity) {

        this.iAboutUsActivity=aboutUsActivity;

    }

    @Override
    public void doAboutUs(String id) {

        iModelAboutUs=new ModelAboutUs(this);
        iModelAboutUs.aboutUsRestCall(id);

    }

    @Override
    public void onAboutUsResponseFromModel(int statusValue) {
        iAboutUsActivity.onAboutUsResponseFromPresenter(statusValue);
    }

    @Override
    public void onAboutUsSucess(AboutUsResponseModel aboutUsResponseModel) {
        iAboutUsActivity.onAboutUsSuccessFromPresenter(aboutUsResponseModel);


    }

    @Override
    public void onAboutUsFailed(String message) {
        iAboutUsActivity.onAboutUsFailedFromPresenter(message);


    }
}
