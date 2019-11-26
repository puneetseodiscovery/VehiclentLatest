package com.vehiclent.paymnetSuccessful;

import com.vehiclent.responseModelClasses.PaymentSucessModel;

public class PPaymentSuccessfulActivity implements IPPaymentSuccessfulActivity {


    IPaymentSuccessfulActivity iPaymentSuccessfulActivity;
    IMPaymentSuccessfulActivity imPaymentSuccessfulActivity;

    public PPaymentSuccessfulActivity(PaymentSuccessfulActivity paymentSuccessfulActivity) {
        this.iPaymentSuccessfulActivity = paymentSuccessfulActivity;
    }

    @Override
    public void doPayment(String jobid) {
        imPaymentSuccessfulActivity = new MPaymentSuccessfulActivity(this);
        imPaymentSuccessfulActivity.paymentSucessRestCall(jobid);
    }

    @Override
    public void onPaymentResponseFromModel(int statusValue) {
        iPaymentSuccessfulActivity.onPaymentFromPresenter(statusValue);
    }

    @Override
    public void onPaymentSucess(PaymentSucessModel paymentSucessModel) {
        iPaymentSuccessfulActivity.onPaymentSuccessResponseFromPresenter(paymentSucessModel);
    }

    @Override
    public void onPaymentFailed(String message) {
        iPaymentSuccessfulActivity.onPaymentFailedResponseFromPresenter(message);
    }
}
