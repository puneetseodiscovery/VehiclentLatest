package com.vehiclent.mainActivity.ContainersForFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.vehiclent.R;

/**
 * Created by android on 16/3/17.
 */

public class Container_for_tabs extends Fragment
{
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.frame_layout_for_container, fragment, "search");
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

}