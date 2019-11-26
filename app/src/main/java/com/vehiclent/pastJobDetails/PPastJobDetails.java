package com.vehiclent.pastJobDetails;


import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;

public class PPastJobDetails implements IPPastJobDetails {

    IPastJobDetailsActivity iPastJobDetailsActivity;
    IMPastJobDetails imPastJobDetails;

    public PPastJobDetails(IPastJobDetailsActivity iPastJobDetailsActivity) {
        this.iPastJobDetailsActivity = iPastJobDetailsActivity;
    }

    @Override
    public void doPastJobDetails(String jobid) {
        imPastJobDetails = new MPastJobDetails(this);
        imPastJobDetails.getPastJobDetailsRestCall(jobid);
    }

    @Override
    public void onPastJobDetailsResponseFromModel(int statusValue) {
        iPastJobDetailsActivity.onPastJobDetailsFromPresenter(statusValue);
    }

    @Override
    public void onPastJobDetailsSucessResponseFromModel(PastJobDetailsResponseModel pastJobDetailsResponseModel) {
        iPastJobDetailsActivity.onPastJobDetailsSucessFromPresenter(pastJobDetailsResponseModel);
    }

    @Override
    public void onPastJobDetailsFailedResponseFromModel(String message) {
        iPastJobDetailsActivity.onPastJobDetailsFailedFromPresenter(message);
    }
}
