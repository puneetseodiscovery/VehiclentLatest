package com.vehiclent.onGoingActivity;


import com.vehiclent.responseModelClasses.OnGoingResponseModel;

public class POnGoingJobListing implements IPOnGoingJobListing {

    IOnGoingJobListing iOnGoingJobListing;
    IMOnGoingJobListing imOnGoingJobListing;

    public POnGoingJobListing(OnGoingActivity onGoingActivity){
        this.iOnGoingJobListing=onGoingActivity;
    }

    @Override
    public void doonGoingJobListing(String userid) {
        imOnGoingJobListing=new MOnGoingJobListing(this);
        imOnGoingJobListing.getonGoingJobListingRestCall(userid);
    }

    @Override
    public void ononGoingJobListingResponseFromModel(int statusValue) {
        iOnGoingJobListing.onUpcomingJobListingFromPresenter(statusValue);
    }

    @Override
    public void ononGoingJobListingSuccessResponseFromModel(OnGoingResponseModel onGoingResponseModel) {
    iOnGoingJobListing.onUpcomingJobListingSuccessFromPresenter(onGoingResponseModel);
    }

    @Override
    public void ononGoingJobListingFaildResponseFromModel(String message) {
        iOnGoingJobListing.onUpcomingJobListingFailedFromPresenter(message);
    }

    @Override
    public void ononGoingJobListingEmptyResponseFromModel(String message) {
        iOnGoingJobListing.onUpcomingJobListingEmptyResponseFromPresenter(message);
    }
}
