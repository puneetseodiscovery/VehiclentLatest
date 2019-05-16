package com.vehiclent.ReviewActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.cardActivity.CardActivity;
import com.vehiclent.congratulationsActivity.CongratulationActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_reviews)
    TextView tv_reviews;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.img_back_reviews)
    ImageView img_back_reviews;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        tv_reviews.setTypeface(Utility.typeFaceForBoldText(this));
        tv_username.setTypeface(Utility.typeFaceForBoldText(this));


    }

    private void EventListner() {

        img_back_reviews.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back_reviews:
                finish();
                break;

            case R.id.btn_submit:
                Intent intent = new Intent(ReviewsActivity.this, CongratulationActivity.class);
                startActivity(intent);
                break;
        }

    }
}
