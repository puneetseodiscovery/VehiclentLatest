package com.vehiclent.carCareActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MProcessActivity implements IMProcessActivity {

    IPProcessActivity ipProcessActivity;

    public MProcessActivity(PProcessActivity pProcessActivity) {
        this.ipProcessActivity = pProcessActivity;
    }

    @Override
    public void omSubmitUserQueryRestCall(String get_userId, String service_id, String my_query, String placeCurrentLatitude, String placeCurrentLongitude) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitUserQuery(get_userId, service_id, my_query, placeCurrentLatitude, placeCurrentLongitude, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.SUBMIT_QUERY_SUCCESS:
                    SubmitQueryResponseModel submitQueryResponseModel = (SubmitQueryResponseModel) msg.obj;
                    ipProcessActivity.onSubmitUserQuerySucessFromModel(submitQueryResponseModel);

                    break;
                case APIInterface.SUBMIT_QUERY_FAILED:
                    ipProcessActivity.onSubmitUserQueryFailedFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
