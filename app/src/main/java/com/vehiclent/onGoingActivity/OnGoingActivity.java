package com.vehiclent.onGoingActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.onGoingActivity.adapters.OnGoingAdapter;
import com.vehiclent.responseModelClasses.OnGoingResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnGoingActivity extends BaseClass implements IOnGoingJobListing {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_nodata)
    ImageView img_nodata;

    @BindView(R.id.recycler_ongoing)
    RecyclerView recycler_ongoing;


    OnGoingAdapter onGoingAdapter;
    Context context;
    ProgressDialog progressDialog;
    SavePref savePref;

    IPOnGoingJobListing ipOnGoingJobListing;

    String user_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going);

        ButterKnife.bind(this);
        context = OnGoingActivity.this;
        progressDialog = new ProgressDialog(context);
        savePref = new SavePref(context);
        ipOnGoingJobListing = new POnGoingJobListing(this);
        user_id = savePref.getid();
        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(context);
            ipOnGoingJobListing.doonGoingJobListing(user_id);

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpcomingJobListingFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpcomingJobListingSuccessFromPresenter(OnGoingResponseModel onGoingResponseModel) {
        progressDialog.dismiss();

        if (onGoingResponseModel != null && onGoingResponseModel.getData().size() > 0) {
            img_nodata.setVisibility(View.GONE);
            onGoingAdapter = new OnGoingAdapter(context, onGoingResponseModel.getData());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recycler_ongoing.setLayoutManager(layoutManager);
            recycler_ongoing.setAdapter(onGoingAdapter);

        } else {

        }
    }

    @Override
    public void onUpcomingJobListingFailedFromPresenter(String messge) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpcomingJobListingEmptyResponseFromPresenter(String message) {
        progressDialog.dismiss();
        img_nodata.setVisibility(View.VISIBLE);
    }
}
