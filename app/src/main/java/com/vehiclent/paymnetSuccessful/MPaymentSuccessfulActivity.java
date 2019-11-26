package com.vehiclent.paymnetSuccessful;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.PaymentSucessModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MPaymentSuccessfulActivity implements IMPaymentSuccessfulActivity {

    IPPaymentSuccessfulActivity ipPaymentSuccessfulActivity;


    public MPaymentSuccessfulActivity(PPaymentSuccessfulActivity pPaymentSuccessfulActivity) {
        this.ipPaymentSuccessfulActivity = pPaymentSuccessfulActivity;
    }

    @Override
    public void paymentSucessRestCall(String jobid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.paymnet_Sucessful(jobid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.PAYMENT_SUCCESS:
                    PaymentSucessModel paymentSucessModel = (PaymentSucessModel) msg.obj;
                    ipPaymentSuccessfulActivity.onPaymentSucess(paymentSucessModel);
                    break;

                case APIInterface.PAYMENT_FAILED:
                    ipPaymentSuccessfulActivity.onPaymentFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
