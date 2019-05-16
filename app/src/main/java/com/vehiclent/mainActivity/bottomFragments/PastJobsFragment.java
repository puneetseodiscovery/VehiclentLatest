package com.vehiclent.mainActivity.bottomFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.mainActivity.adapters.CategoriesAdapter;
import com.vehiclent.mainActivity.adapters.PastjobsAdapter;
import com.vehiclent.utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastJobsFragment extends Fragment {

    View view;
    TextView tv_pastjobs;
    RecyclerView pastjobs_recyclerview;
    PastjobsAdapter pastjobsAdapter;

    public PastJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_past_jobs, container, false);

        pastjobs_recyclerview = (RecyclerView) view.findViewById(R.id.pastjobs_recyclerview);
        tv_pastjobs = (TextView) view.findViewById(R.id.tv_pastjobs);
        tv_pastjobs.setTypeface(Utility.typeFaceForBoldText(getActivity()));

        pastjobsAdapter = new PastjobsAdapter(getActivity());
        pastjobs_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        pastjobs_recyclerview.setAdapter(pastjobsAdapter);
        return view;
    }

}
