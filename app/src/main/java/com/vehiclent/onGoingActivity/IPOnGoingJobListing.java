package com.vehiclent.onGoingActivity;

import com.vehiclent.responseModelClasses.OnGoingResponseModel;

public interface IPOnGoingJobListing {

    void doonGoingJobListing(String userid);
    void ononGoingJobListingResponseFromModel(int statusValue);
    void ononGoingJobListingSuccessResponseFromModel(OnGoingResponseModel onGoingResponseModel);
    void ononGoingJobListingFaildResponseFromModel(String message);
    void ononGoingJobListingEmptyResponseFromModel(String message);
}
