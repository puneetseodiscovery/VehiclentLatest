package com.vehiclent.pastJobDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastJobDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_pastjob)
    TextView tv_pastjob;

    @BindView(R.id.img_back_pastjob)
    ImageView img_back_pastjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_details);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {
        tv_pastjob.setTypeface(Utility.typeFaceForBoldText(this));


    }

    private void EventListner() {

        img_back_pastjob.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_pastjob:
                finish();
                break;
        }
    }
}
