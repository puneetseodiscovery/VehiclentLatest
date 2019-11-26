package com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment;

import com.vehiclent.responseModelClasses.PastJobListResponseModel;

public interface IPPastJobsFragment {

    void doPastJobsList(String userid);
    void onPastJobsListResponseFromModel(int statusValue);
    void onPastJobsListSuccessResponseFromModel(PastJobListResponseModel pastJobListResponseModel);
    void onPastJobsListFaildResponseFromModel(String message);
    void onPastJobsListEmptyResponseFromModel(String message);
}
