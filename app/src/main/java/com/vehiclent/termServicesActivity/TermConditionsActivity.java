package com.vehiclent.termServicesActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TermConditionsActivity extends BaseClass implements ITermsCondition{
    @BindView(R.id.tv_termconditiondec)
    TextView tv_termconditiondec;

    @BindView(R.id.img_back)
    ImageView img_back;

    IPTermsCondition ipTermsCondition;
    ProgressDialog progressDialog;
    SavePref savePref;
    String get_id;
    TermConditionsActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_conditions);


        ButterKnife.bind(this);
        context=TermConditionsActivity.this;
        progressDialog=new ProgressDialog(this);
        savePref=new SavePref(this);
        Initialization();
    }

    private void Initialization() {
        get_id=savePref.getid();
        ipTermsCondition=new PTermsCondition(this);


        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(this);
            ipTermsCondition.doTermsCondition("");

        } else {
            Toast.makeText(TermConditionsActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();

        }
        EventListeners();
    }

    private void EventListeners() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onTermsConditionResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onTermsConditionSuccessFromPresenter(TermsConditionsResponseModel termsConditionsResponseModel) {
        progressDialog.dismiss();
        tv_termconditiondec.setText(termsConditionsResponseModel.getData().get(0).getDescription());
    }

    @Override
    public void onTermsConditionFailedFromPresenter(String message) {
        progressDialog.dismiss();

    }
}
