package com.vehiclent.congratulationsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.mainActivity.HomeActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CongratulationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_congo)
    TextView tv_congo;

    @BindView(R.id.tv_congratulations)
    TextView tv_congratulations;

    @BindView(R.id.tv_ordersucessfully)
    TextView tv_ordersucessfully;

    @BindView(R.id.btn_done)
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        tv_congo.setTypeface(Utility.typeFaceForBoldText(this));
        tv_ordersucessfully.setTypeface(Utility.typeFaceForBoldText(this));
        tv_congratulations.setTypeface(Utility.typeFaceForBoldText(this));

    }

    private void EventListner() {

        btn_done.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
                Intent intent = new Intent(CongratulationActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
