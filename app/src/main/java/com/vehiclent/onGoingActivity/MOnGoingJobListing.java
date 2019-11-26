package com.vehiclent.onGoingActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.OnGoingResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MOnGoingJobListing  implements IMOnGoingJobListing{

    IPOnGoingJobListing ipOnGoingJobListing;

    public MOnGoingJobListing(IPOnGoingJobListing ipOnGoingJobListing) {
        this.ipOnGoingJobListing = ipOnGoingJobListing;
    }

    @Override
    public void getonGoingJobListingRestCall(String userid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.ongoing_api(userid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.ONGOING_SUCCESS:
                    OnGoingResponseModel onGoingResponseModel = ((OnGoingResponseModel) msg.obj);
                    ipOnGoingJobListing.ononGoingJobListingSuccessResponseFromModel(onGoingResponseModel);
                    break;

                case APIInterface.ONGOING_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipOnGoingJobListing.ononGoingJobListingFaildResponseFromModel(mesFailed);
                    break;
                case APIInterface.ONGOING_EMPTY:
                    String messsageEmpty = String.valueOf(msg.obj);
                    ipOnGoingJobListing.ononGoingJobListingEmptyResponseFromModel(messsageEmpty);
                    break;
            }
        }
    };
}
