package com.vehiclent.partnerProfileActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.paymentActivity.PaymentActivity;
import com.vehiclent.trackActivity.TrackerMapsActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartnerProfileActivity extends AppCompatActivity implements View.OnClickListener {
    PartnerProfileActivity context;
    @BindView(R.id.tv_partner_profile)
    TextView tv_partner_profile;
    @BindView(R.id.tv_partnername)
    TextView tv_partnername;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.tv_payment)
    Button tv_payment;
    @BindView(R.id.tv_track)
    Button tv_track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_profile);

        ButterKnife.bind(this);
        Initialization();
        EventListner();


    }


    private void Initialization() {

        context=this;

        tv_partner_profile.setTypeface(Utility.typeFaceForBoldText(this));
        tv_partnername.setTypeface(Utility.typeFaceForBoldText(this));


    }

    private void EventListner() {
        img_back.setOnClickListener(this);
        tv_payment.setOnClickListener(this);
        tv_track.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_payment:
                intent = new Intent(PartnerProfileActivity.this, PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_track:
                /*intent = new Intent(PartnerProfileActivity.this, TrackerMapsActivity.class);
                startActivity(intent);*/


                Uri gmmIntentUri = Uri.parse("geo:30.7154,76.6913");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

                break;
        }
    }
}
