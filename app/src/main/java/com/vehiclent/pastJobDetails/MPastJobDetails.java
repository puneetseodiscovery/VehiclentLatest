package com.vehiclent.pastJobDetails;

import android.os.Handler;
import android.os.Message;
import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;


public class MPastJobDetails implements IMPastJobDetails {


    IPPastJobDetails ipPastJobDetails;


    public MPastJobDetails(PPastJobDetails pPastJobDetails) {
        this.ipPastJobDetails = pPastJobDetails;
    }

    @Override
    public void getPastJobDetailsRestCall(String jobid) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.pastjobDetails_Api(jobid, mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.PASTJOBDETAILS_SUCCESS:

                    PastJobDetailsResponseModel pastJobDetailsResponseModel = (PastJobDetailsResponseModel) msg.obj;
                    ipPastJobDetails.onPastJobDetailsSucessResponseFromModel(pastJobDetailsResponseModel);

                    break;
                case APIInterface.PASTJOBDETAILS_FAILED:
                    ipPastJobDetails.onPastJobDetailsFailedResponseFromModel(String.valueOf(msg.obj));
                    break;


            }
        }
    };
}
