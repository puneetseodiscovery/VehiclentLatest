package com.vehiclent.mainActivity.bottomFragments.Presenters;

import com.vehiclent.mainActivity.bottomFragments.CategoriesFragment;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.ICategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.IMCategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.IPCategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.Models.MCategoryFragment;
import com.vehiclent.responseModelClasses.ServicesResponseModel;


public class PCategoryFragment implements IPCategoryFragment {

    ICategoryFragment iCategoryFragment;
    IMCategoryFragment imCategoryFragment;

    public PCategoryFragment(CategoriesFragment categoriesFragment) {
        this.iCategoryFragment=categoriesFragment;
    }


    @Override
    public void doGetServicesList(String id) {
        imCategoryFragment= new MCategoryFragment(this);
        imCategoryFragment.getServicesListRestCall(id);
    }

    @Override
    public void onServicesListResponseFromModel(int statusValue) {
        iCategoryFragment.onServicesFromPresenter(statusValue);
    }

    @Override
    public void onServicesListSuccessResponseFromModel(ServicesResponseModel servicesResponseModel) {
        iCategoryFragment.onServicesSuccessFromPresenter(servicesResponseModel);
    }

    @Override
    public void onServicesListFaildResponseFromModel(String message) {
        iCategoryFragment.onServicesFailedFromPresenter(message);

    }
}
