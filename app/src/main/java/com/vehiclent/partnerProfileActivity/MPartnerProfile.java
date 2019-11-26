package com.vehiclent.partnerProfileActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MPartnerProfile implements IMPartnerProfileActivity {

    IPPartnerProfileActivity ipPartnerProfileActivity;

    public MPartnerProfile(PPartnerProfile pPartnerProfile) {
        this.ipPartnerProfileActivity = pPartnerProfile;
    }

    @Override
    public void getPartnerProfileRestCall(String jobid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.onGoingDetails(jobid, mHandler);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.ONGOING_DETAILS_SUCESS:
                    GetPartnerProfileResponseModel getPartnerProfileResponseModel = (GetPartnerProfileResponseModel) msg.obj;
                    ipPartnerProfileActivity.onGetPartnerProfileSucess(getPartnerProfileResponseModel);
                    break;

                case APIInterface.ONGOING_DETAILS_FAILED:
                    ipPartnerProfileActivity.onGetPartnerProfileFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
