package com.vehiclent.mainActivity.bottomFragments.ICategoryFragment;

import com.vehiclent.responseModelClasses.ServicesResponseModel;

public interface IPCategoryFragment {
    void doGetServicesList(String id);
    void onServicesListResponseFromModel(int statusValue);
    void onServicesListSuccessResponseFromModel(ServicesResponseModel servicesResponseModel);
    void onServicesListFaildResponseFromModel(String message);
}
