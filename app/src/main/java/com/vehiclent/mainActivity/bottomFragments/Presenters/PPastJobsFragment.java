package com.vehiclent.mainActivity.bottomFragments.Presenters;

import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IMPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IPPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.Models.MPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.PastJobsFragment;
import com.vehiclent.responseModelClasses.PastJobListResponseModel;

public class PPastJobsFragment implements IPPastJobsFragment {

    IPastJobsFragment ipPastJobsFragment;
    IMPastJobsFragment imPastJobsFragment;

    public PPastJobsFragment(PastJobsFragment pastJobsFragment) {
        this.ipPastJobsFragment = pastJobsFragment;
    }

    @Override
    public void doPastJobsList(String userid) {
        imPastJobsFragment = new MPastJobsFragment(this);
        imPastJobsFragment.getPastJobRestCall(userid);
    }

    @Override
    public void onPastJobsListResponseFromModel(int statusValue) {
        ipPastJobsFragment.onPastJobFromPresenter(statusValue);
    }

    @Override
    public void onPastJobsListSuccessResponseFromModel(PastJobListResponseModel pastJobListResponseModel) {
        ipPastJobsFragment.onPastJobSuccessFromPresenter(pastJobListResponseModel);
    }

    @Override
    public void onPastJobsListFaildResponseFromModel(String message) {
        ipPastJobsFragment.onPastJobFailedFromPresenter(message);
    }

    @Override
    public void onPastJobsListEmptyResponseFromModel(String message) {
        ipPastJobsFragment.onPastJobEmptyFromPresenter(message);
    }
}
