package com.vehiclent.aboutusActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutUsActivity extends BaseClass implements IAboutUsActivity {

    @BindView(R.id.tv_aboutdec)
    TextView tv_aboutdec;

    @BindView(R.id.img_back)
    ImageView img_back;

    IPAboutUs ipAboutUs;
    ProgressDialog progressDialog;
  //  SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ButterKnife.bind(this);
    //    savePref=new SavePref(this);
        Initialization();

    }

    private void Initialization() {

        ipAboutUs=new PAboutUs(this);
        progressDialog = Utility.showLoader(this);

    //    ipAboutUs.doAboutUs(savePref.getid());
        EventListner();
    }

    private void EventListner() {

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onAboutUsResponseFromPresenter(int statusValue) {

    }

    @Override
    public void onAboutUsSuccessFromPresenter(AboutUsResponseModel aboutUsResponseModel) {
        progressDialog.dismiss();
        tv_aboutdec.setText(aboutUsResponseModel.getData().get(0).getDescription());
    }

    @Override
    public void onAboutUsFailedFromPresenter(String message) {

    }
}
