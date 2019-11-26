package com.vehiclent.onGoingActivity;

import com.vehiclent.responseModelClasses.OnGoingResponseModel;

public interface IOnGoingJobListing {

    void onUpcomingJobListingFromPresenter(int statusValue);
    void onUpcomingJobListingSuccessFromPresenter(OnGoingResponseModel onGoingResponseModel);
    void onUpcomingJobListingFailedFromPresenter(String messge);
    void onUpcomingJobListingEmptyResponseFromPresenter(String message);
}
