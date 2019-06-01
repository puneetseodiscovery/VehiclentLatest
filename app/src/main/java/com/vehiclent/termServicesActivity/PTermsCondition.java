package com.vehiclent.termServicesActivity;


import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;

public class PTermsCondition implements  IPTermsCondition{

    ITermsCondition iTermsCondition;
    IMTermsConditions imTermsConditions;

    public PTermsCondition(TermConditionsActivity termConditionsActivity) {

        this.iTermsCondition=termConditionsActivity;
    }

    @Override
    public void doTermsCondition(String id) {


        imTermsConditions=new ModelTermCondition(this);
        imTermsConditions.termconditionRestCall(id);

    }

    @Override
    public void onTermsConditionResponseFromModel(int statusValue) {
        iTermsCondition.onTermsConditionResponseFromPresenter(statusValue);
    }

    @Override
    public void onTermsConditionSucess(TermsConditionsResponseModel termsConditionsResponseModel) {
        iTermsCondition.onTermsConditionSuccessFromPresenter(termsConditionsResponseModel);
    }

    @Override
    public void onTermsConditionFailed(String message) {
        iTermsCondition.onTermsConditionFailedFromPresenter(message);

    }
}
