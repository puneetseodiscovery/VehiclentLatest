package com.vehiclent.carCareActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.partnerProfileActivity.PartnerProfileActivity;
import com.vehiclent.utils.Utility;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarCareActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_carcare)
    TextView tv_carcare;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.img_back_carcare)
    ImageView img_back_carcare;

    private static int LOADING_TIME_OUT = 1000;

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_care);

        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }

    private void Initialization() {

        tv_carcare.setTypeface(Utility.typeFaceForBoldText(this));

    }

    private void EventListner() {
        btn_submit.setOnClickListener(this);
        img_back_carcare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:

                startTimer();
                avi.setVisibility(View.VISIBLE);
                break;
            case R.id.img_back_carcare:
                finish();
                break;
        }
    }

    private void startTimer() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CarCareActivity.this, PartnerProfileActivity.class);
                startActivity(intent);
                avi.setVisibility(View.GONE);

            }
        }, LOADING_TIME_OUT);


    }


}
