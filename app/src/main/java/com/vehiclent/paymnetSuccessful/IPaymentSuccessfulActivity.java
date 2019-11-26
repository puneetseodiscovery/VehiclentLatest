package com.vehiclent.paymnetSuccessful;

import com.vehiclent.responseModelClasses.PaymentSucessModel;

public interface IPaymentSuccessfulActivity {


    void onPaymentFromPresenter(int statusValue);

    void onPaymentSuccessResponseFromPresenter(PaymentSucessModel paymentSucessModel);

    void onPaymentFailedResponseFromPresenter(String message);

}
