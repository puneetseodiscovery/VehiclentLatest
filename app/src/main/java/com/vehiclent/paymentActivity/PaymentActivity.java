package com.vehiclent.paymentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.startPayment.StartPaymentActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseClass implements View.OnClickListener {

    PaymentActivity context;

    @BindView(R.id.relative_payforu)
    RelativeLayout relative_payforu;


    @BindView(R.id.img_paymentback)
    ImageView img_paymentback;

    @BindView(R.id.tv_payment)
    TextView tv_payment;


    String jobid = "", service_price = "", user_name = "", phone_number = "",email="",partner_id="",your_profile="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        context = PaymentActivity.this;
        ButterKnife.bind(this);

        Initialization();
        EventListner();


    }

    private void Initialization() {

        tv_payment.setTypeface(Utility.typeFaceForBoldText(this));

        jobid = getIntent().getStringExtra("jobid");
        service_price = getIntent().getStringExtra("service_price");
        user_name = getIntent().getStringExtra("user_name");
        phone_number = getIntent().getStringExtra("phone_number");
        email = getIntent().getStringExtra("email");
        partner_id = getIntent().getStringExtra("partner_id");
        your_profile = getIntent().getStringExtra("your_profile");

    }

    private void EventListner() {

        img_paymentback.setOnClickListener(this);
        relative_payforu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_paymentback:
                finish();
                break;

            case R.id.relative_payforu:
                Intent intent = new Intent(context, StartPaymentActivity.class);

                intent.putExtra("jobid", jobid);
                intent.putExtra("service_price", service_price);
                intent.putExtra("user_name", user_name);
                intent.putExtra("phone_number", phone_number);
                intent.putExtra("email", email);
                intent.putExtra("partner_id", partner_id);
                intent.putExtra("your_profile", your_profile);
                startActivity(intent);

                break;
        }
    }
}
