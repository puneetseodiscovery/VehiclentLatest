package com.vehiclent.mainActivity.ContainersForFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vehiclent.R;
import com.vehiclent.mainActivity.bottomFragments.CategoriesFragment;
import com.vehiclent.mainActivity.bottomFragments.ProfileFragment;


/**
 * Created by android on 16/3/17.
 */


@SuppressLint("ValidFragment")
public class ProfileContainer extends Container_for_tabs {
    private boolean mIsViewInited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.container_for_tab, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!mIsViewInited) {
            mIsViewInited = true;
            onIntialView();
        }
    }

    private void onIntialView() {

        replaceFragment(new ProfileFragment(), false);
    }
}

