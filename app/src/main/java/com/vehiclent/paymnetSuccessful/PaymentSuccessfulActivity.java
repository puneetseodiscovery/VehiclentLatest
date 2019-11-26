package com.vehiclent.paymnetSuccessful;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.ReviewActivity.ReviewsActivity;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.PaymentSucessModel;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessfulActivity extends BaseClass implements View.OnClickListener, IPaymentSuccessfulActivity {

    @BindView(R.id.tv_payment_done)
    TextView tv_payment_done;

    @BindView(R.id.tv_congratulations)
    TextView tv_congratulations;

    @BindView(R.id.tv_ordersucessfully)
    TextView tv_ordersucessfully;

    @BindView(R.id.tv_paymentsuccessful)
    TextView tv_paymentsuccessful;

    @BindView(R.id.img_back_payment)
    ImageView img_back_payment;

    @BindView(R.id.btn_next)
    Button btn_next;

    String jobid = "", amount = "",firstname="",partner_id="",your_profile="";

    PaymentSuccessfulActivity context;
    ProgressDialog progressDialog;
    IPPaymentSuccessfulActivity ipPaymentSuccessfulActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        ButterKnife.bind(this);

        context = PaymentSuccessfulActivity.this;
        progressDialog = new ProgressDialog(this);
        ipPaymentSuccessfulActivity = new PPaymentSuccessfulActivity(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        jobid = getIntent().getStringExtra("jobid");
        amount = getIntent().getStringExtra("amount");
        firstname = getIntent().getStringExtra("firstname");
        partner_id = getIntent().getStringExtra("partner_id");
        your_profile = getIntent().getStringExtra("your_profile");

        tv_payment_done.setTypeface(Utility.typeFaceForBoldText(this));
        tv_congratulations.setTypeface(Utility.typeFaceForBoldText(this));
        tv_paymentsuccessful.setTypeface(Utility.typeFaceForBoldText(this));
        tv_ordersucessfully.setTypeface(Utility.typeFaceForBoldText(this));
        tv_paymentsuccessful.setText("\u20B9" + " " + amount);

        if (Utility.isNetworkConnected(context)) {

            progressDialog = Utility.showLoader(PaymentSuccessfulActivity.this);
            ipPaymentSuccessfulActivity.doPayment(jobid);

        } else {
            Toast.makeText(PaymentSuccessfulActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();

        }
    }

    private void EventListner() {
        img_back_payment.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_payment:
                finish();
                break;

            case R.id.btn_next:
                Intent intent = new Intent(PaymentSuccessfulActivity.this, ReviewsActivity.class);
                intent.putExtra("firstname",firstname);
                intent.putExtra("jobid",jobid);
                intent.putExtra("partner_id",partner_id);
                intent.putExtra("your_profile",your_profile);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onPaymentFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onPaymentSuccessResponseFromPresenter(PaymentSucessModel paymentSucessModel) {
        progressDialog.dismiss();

    }

    @Override
    public void onPaymentFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();

    }
}
