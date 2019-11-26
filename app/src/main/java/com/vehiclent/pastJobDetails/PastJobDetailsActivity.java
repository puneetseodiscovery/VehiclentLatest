package com.vehiclent.pastJobDetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PastJobDetailsActivity extends BaseClass implements View.OnClickListener, IPastJobDetailsActivity {

    @BindView(R.id.tv_pastjob)
    TextView tv_pastjob;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_user_email)
    TextView tv_user_email;

    @BindView(R.id.img_back_pastjob)
    ImageView img_back_pastjob;

    @BindView(R.id.tv_user_mobile)
    TextView tv_user_mobile;

    @BindView(R.id.tv_payable_amount)
    TextView tv_payable_amount;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_address)
    TextView tv_user_address;

    @BindView(R.id.tv_jobdete)
    TextView tv_jobdete;

    @BindView(R.id.tv_services)
    TextView tv_services;

    @BindView(R.id.img_userprofile)
    CircleImageView img_userprofile;

    PastJobDetailsActivity context;
    String job_id = "", job_image = "";
    ProgressDialog progressDialog;
    IPPastJobDetails ipPastJobDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_details);

        ButterKnife.bind(this);
        context = PastJobDetailsActivity.this;
        progressDialog = new ProgressDialog(this);

        ipPastJobDetails = new PPastJobDetails(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        job_id = getIntent().getStringExtra("job_id");
        job_image = getIntent().getStringExtra("job_image");
        tv_pastjob.setTypeface(Utility.typeFaceForBoldText(this));


        if (job_image.isEmpty() || job_image.equals("")) {

            Glide.with(context).load(job_image).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(img_userprofile);

        } else {
            Glide.with(context).load(job_image).into(img_userprofile);

        }

        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(context);
            ipPastJobDetails.doPastJobDetails(job_id);

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void onPastJobDetailsFromPresenter(int status) {

        progressDialog.dismiss();
    }

    @Override
    public void onPastJobDetailsSucessFromPresenter(PastJobDetailsResponseModel pastJobDetailsResponseModel) {
        progressDialog.dismiss();

        if (pastJobDetailsResponseModel.getData() != null || pastJobDetailsResponseModel.getData().size() > 0) {

            tv_username.setText(pastJobDetailsResponseModel.getData().get(0).getFirstName() + " " + pastJobDetailsResponseModel.getData().get(0).getLastName());
            tv_user_name.setText(pastJobDetailsResponseModel.getData().get(0).getFirstName() + " " + pastJobDetailsResponseModel.getData().get(0).getLastName());
            tv_user_email.setText(pastJobDetailsResponseModel.getData().get(0).getEmail());
            tv_user_mobile.setText(pastJobDetailsResponseModel.getData().get(0).getPhoneNumber());
            tv_user_address.setText(pastJobDetailsResponseModel.getData().get(0).getAddress());
            tv_payable_amount.setText("\u20B9" + " " + pastJobDetailsResponseModel.getData().get(0).getPrice());
            tv_jobdete.setText(pastJobDetailsResponseModel.getData().get(0).getJobdate());
            tv_services.setText(pastJobDetailsResponseModel.getData().get(0).getServiceName());


        } else {

        }


    }

    @Override
    public void onPastJobDetailsFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
