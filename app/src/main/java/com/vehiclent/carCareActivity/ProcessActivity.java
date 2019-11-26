package com.vehiclent.carCareActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessActivity extends BaseClass implements IProcessActivity {
    private static int LOADING_TIME_OUT = 5000;

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    String get_UserId = "", service_id = "", my_query = "", placeCurrentLatitude = "", placeCurrentLongitude = "";
    ProcessActivity context;
    ProgressDialog progressDialog;
    IPProcessActivity ipProcessActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_processing);
        ButterKnife.bind(this);
        context = ProcessActivity.this;
        progressDialog=new ProgressDialog(context);
        ipProcessActivity = new PProcessActivity(this);

        Init();

    }

    private void Init() {

        get_UserId = getIntent().getStringExtra("get_UserId");
        service_id = getIntent().getStringExtra("service_id");
        my_query = getIntent().getStringExtra("my_query");
        placeCurrentLatitude = getIntent().getStringExtra("placeCurrentLatitude");
        placeCurrentLongitude = getIntent().getStringExtra("placeCurrentLongitude");

        ipProcessActivity.doSubmitUserQuery(get_UserId, service_id, my_query, placeCurrentLatitude, placeCurrentLongitude);

    }

    @Override
    public void onSubmitUserQueryResponseFromPresenet(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onSubmitUserQuerySuccessResponseFromPresenter(SubmitQueryResponseModel submitQueryResponseModel) {
        progressDialog.dismiss();
    }

    @Override
    public void onSubmitUserQueryFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

}
