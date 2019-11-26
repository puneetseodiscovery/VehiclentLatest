package com.vehiclent.mainActivity.bottomFragments.ICategoryFragment;

import com.vehiclent.responseModelClasses.ServicesResponseModel;

public interface ICategoryFragment {
    void onServicesFromPresenter(int statusValue);
    void onServicesSuccessFromPresenter(ServicesResponseModel servicesResponseModel);
    void onServicesFailedFromPresenter(String messge);
}
