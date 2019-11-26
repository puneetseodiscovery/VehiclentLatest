package com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment;

import com.vehiclent.responseModelClasses.PastJobListResponseModel;

public interface IPastJobsFragment {

    void onPastJobFromPresenter(int statusValue);
    void onPastJobSuccessFromPresenter(PastJobListResponseModel pastJobListResponseModel);
    void onPastJobFailedFromPresenter(String messge);
    void onPastJobEmptyFromPresenter(String messge);
}
