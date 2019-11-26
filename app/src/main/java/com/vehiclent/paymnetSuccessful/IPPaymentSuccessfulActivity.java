package com.vehiclent.paymnetSuccessful;

import com.vehiclent.responseModelClasses.PaymentSucessModel;

public interface IPPaymentSuccessfulActivity {

    void doPayment(String jobid);
    void onPaymentResponseFromModel(int statusValue);
    void onPaymentSucess(PaymentSucessModel paymentSucessModel);
    void onPaymentFailed(String message);

}
