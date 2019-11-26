package com.vehiclent.mainActivity.bottomFragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.mainActivity.adapters.PastjobsAdapter;
import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IPPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.IPastJobsFragment.IPastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PPastJobsFragment;
import com.vehiclent.responseModelClasses.PastJobListResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastJobsFragment extends Fragment implements IPastJobsFragment {

    View view;
    TextView tv_pastjobs;
    RecyclerView pastjobs_recyclerview;
    PastjobsAdapter pastjobsAdapter;
    Context context;
    ProgressDialog progressDialog;
    SavePref savePref;
    IPPastJobsFragment ipPastJobsFragment;
    ImageView nodata_found;
    String user_id;


    public PastJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_past_jobs, container, false);

        context = this.getContext();
        progressDialog = new ProgressDialog(context);
        savePref = new SavePref(context);
        user_id = savePref.getid();
        ipPastJobsFragment = new PPastJobsFragment(this);

        pastjobs_recyclerview = (RecyclerView) view.findViewById(R.id.pastjobs_recyclerview);
        tv_pastjobs = (TextView) view.findViewById(R.id.tv_pastjobs);
        nodata_found = (ImageView) view.findViewById(R.id.nodata_found);
        tv_pastjobs.setTypeface(Utility.typeFaceForBoldText(getActivity()));


        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(context);
            ipPastJobsFragment.doPastJobsList(user_id);

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onPastJobFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onPastJobSuccessFromPresenter(PastJobListResponseModel pastJobListResponseModel) {
        progressDialog.dismiss();
        if (pastJobListResponseModel != null && pastJobListResponseModel.getData().size() > 0) {

            nodata_found.setVisibility(View.GONE);
            pastjobsAdapter = new PastjobsAdapter(context, pastJobListResponseModel.getData());
            pastjobs_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            pastjobs_recyclerview.setAdapter(pastjobsAdapter);

        } else {

        }
    }

    @Override
    public void onPastJobFailedFromPresenter(String messge) {
        progressDialog.dismiss();
    }

    @Override
    public void onPastJobEmptyFromPresenter(String messge) {
        progressDialog.dismiss();
        nodata_found.setVisibility(View.VISIBLE);
    }
}
