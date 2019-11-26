package com.vehiclent.aboutusActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.utils.SavePref;
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
    SavePref savePref;
    AboutUsActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ButterKnife.bind(this);
        context=AboutUsActivity.this;
        savePref=new SavePref(this);
        progressDialog=new ProgressDialog(this);
        Initialization();

    }

    private void Initialization() {

        ipAboutUs=new PAboutUs(this);

        if (Utility.isNetworkConnected(context)){
            progressDialog = Utility.showLoader(this);
            ipAboutUs.doAboutUs(savePref.getid());

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

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
        progressDialog.dismiss();
    }

    @Override
    public void onAboutUsSuccessFromPresenter(AboutUsResponseModel aboutUsResponseModel) {
        progressDialog.dismiss();

        if (aboutUsResponseModel.getData()!=null){

            tv_aboutdec.setText(aboutUsResponseModel.getData().get(0).getDescription());

        }else {
            tv_aboutdec.setText("About us");

        }
    }

    @Override
    public void onAboutUsFailedFromPresenter(String message) {

        progressDialog.dismiss();

    }
}
