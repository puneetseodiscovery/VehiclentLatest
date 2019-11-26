package com.vehiclent.mainActivity.bottomFragments.Models;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IMPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IPPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PPastJobsFragment;
import com.vehiclent.responseModelClasses.PastJobListResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MPastJobsFragment implements IMPastJobsFragment {

    IPPastJobsFragment ipPastJobsFragment;

    public MPastJobsFragment(PPastJobsFragment pPastJobsFragment) {
        this.ipPastJobsFragment = pPastJobsFragment;
    }

    @Override
    public void getPastJobRestCall(String userid) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.pastjobListing_Api(userid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.PASTJOB_SUCCESS:
                    PastJobListResponseModel pastJobListResponseModel = ((PastJobListResponseModel) msg.obj);
                    ipPastJobsFragment.onPastJobsListSuccessResponseFromModel(pastJobListResponseModel);
                    break;

                case APIInterface.PASTJOB_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipPastJobsFragment.onPastJobsListFaildResponseFromModel(mesFailed);
                    break;

                case APIInterface.PASTJOB_EMPTY:
                    String mesEmpty = String.valueOf(msg.obj);
                    ipPastJobsFragment.onPastJobsListEmptyResponseFromModel(mesEmpty);
                    break;
            }
        }
    };
}
