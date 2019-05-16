package com.vehiclent.mainActivity.bottomFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    View view;
    @BindView((R.id.tv_categories))
    TextView tv_categories;

    @BindView(R.id.tv_aboutus)
    TextView tv_aboutus;

    @BindView(R.id.tv_notivation)
    TextView tv_notivation;

    @BindView(R.id.tv_contactus)
    TextView tv_contactus;

    @BindView(R.id.tv_howwork)
    TextView tv_howwork;

    @BindView(R.id.tv_payment)
    TextView tv_payment;

    @BindView(R.id.tv_termcondintion)
    TextView tv_termcondintion;

    @BindView(R.id.tv_logout)
    TextView tv_logout;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        ButterKnife.bind(this, view);

        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_aboutus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_notivation.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_contactus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_howwork.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_payment.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_termcondintion.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_logout.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        return view;
    }

}
