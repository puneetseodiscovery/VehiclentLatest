package com.vehiclent.termServicesActivity;

import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;

public interface ITermsCondition {

    void onTermsConditionResponseFromPresenter(int statusValue);
    void onTermsConditionSuccessFromPresenter(TermsConditionsResponseModel termsConditionsResponseModel);
    void onTermsConditionFailedFromPresenter(String message);

}
