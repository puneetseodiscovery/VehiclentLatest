package com.vehiclent.paymnetSuccessful;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.ReviewActivity.ReviewsActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessfulActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_payment_done)
    TextView tv_payment_done;

    @BindView(R.id.tv_congratulations)
    TextView tv_congratulations;

    @BindView(R.id.tv_ordersucessfully)
    TextView tv_ordersucessfully;

    @BindView(R.id.img_back_payment)
    ImageView img_back_payment;

    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {
        tv_payment_done.setTypeface(Utility.typeFaceForBoldText(this));
        tv_congratulations.setTypeface(Utility.typeFaceForBoldText(this));
        tv_ordersucessfully.setTypeface(Utility.typeFaceForBoldText(this));


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
                startActivity(intent);

                break;
        }
    }
}
