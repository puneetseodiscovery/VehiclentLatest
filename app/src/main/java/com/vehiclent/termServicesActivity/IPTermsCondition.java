package com.vehiclent.termServicesActivity;


import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;

public interface IPTermsCondition {

    void doTermsCondition(String id);
    void onTermsConditionResponseFromModel(int statusValue);
    void onTermsConditionSucess(TermsConditionsResponseModel termsConditionsResponseModel);
    void onTermsConditionFailed(String message);
}
