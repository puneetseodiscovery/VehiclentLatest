package com.vehiclent.mainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.vehiclent.R;
import com.vehiclent.mainActivity.ContainersForFragments.CategoryContainer;
import com.vehiclent.mainActivity.ContainersForFragments.Container_for_tabs;
import com.vehiclent.mainActivity.ContainersForFragments.PostJobsContainer;
import com.vehiclent.mainActivity.ContainersForFragments.ProfileContainer;
import com.vehiclent.mainActivity.ContainersForFragments.SettingsContainer;

public class MainActivity extends AppCompatActivity {

    public final String TAB_0_TAG = "tab_0";
    public final String TAB_1_TAG = "tab_1";
    public final String TAB_2_TAG = "tab_2";
    public final String TAB_3_TAG = "tab_3";

    public static FragmentTabHost mFragmentTabHost;


    private LayoutInflater inflater = null;
    private TabWidget tabs;
    MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingtabhost();
    }

    private void settingtabhost() {
        context = MainActivity.this;
        tabs = (TabWidget) findViewById(android.R.id.tabs);

        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.tabhost_s);
        mFragmentTabHost.setup(context, getSupportFragmentManager(), R.id.realtabcontent);// getchildFragment for calling First Tab fragment from home fragment
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view0 = (View) prepareTabView_withText(R.drawable.home);
        TabHost.TabSpec tabSpec0 = mFragmentTabHost.newTabSpec(TAB_0_TAG).setIndicator(view0);
        mFragmentTabHost.addTab(tabSpec0, CategoryContainer.class, null);

        View viewHome = prepareTabView_withText(R.drawable.history);
        TabHost.TabSpec tabSpec1 = mFragmentTabHost.newTabSpec(TAB_1_TAG).setIndicator(viewHome);
        mFragmentTabHost.addTab(tabSpec1, PostJobsContainer.class, null);

        View viewMessage = prepareTabView_withText(R.drawable.set);
        TabHost.TabSpec tabSpec2 = mFragmentTabHost.newTabSpec(TAB_2_TAG).setIndicator(viewMessage);
        mFragmentTabHost.addTab(tabSpec2, SettingsContainer.class, null);

        View viewaddpost = prepareTabView_withText(R.drawable.user);
        TabHost.TabSpec tabSpec3 = mFragmentTabHost.newTabSpec(TAB_3_TAG).setIndicator(viewaddpost);
        mFragmentTabHost.addTab(tabSpec3, ProfileContainer.class, null);

        mFragmentTabHost.setCurrentTab(0);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);

        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

    }

    /***Customization For tabs***/
    @SuppressLint("InflateParams")
    private View prepareTabView_withText(int drawable) {
        View view = inflater.inflate(R.layout.layout_tab, null);
        ImageView tab_image = (ImageView) view.findViewById(R.id.tab_image);
        tab_image.setBackgroundResource(drawable);
        return view;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        try {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
            boolean isPopFragment = false;
            String currentTabTag = mFragmentTabHost.getCurrentTabTag();
            if (currentTabTag.equals(TAB_0_TAG)) {
                isPopFragment = ((Container_for_tabs) getSupportFragmentManager().findFragmentByTag(TAB_0_TAG)).popFragment();
            }
            if (currentTabTag.equals(TAB_1_TAG)) {
                isPopFragment = ((Container_for_tabs) getSupportFragmentManager().findFragmentByTag(TAB_1_TAG)).popFragment();
            } else if (currentTabTag.equals(TAB_2_TAG)) {
                isPopFragment = ((Container_for_tabs) getSupportFragmentManager().findFragmentByTag(TAB_2_TAG)).popFragment();
            } else if (currentTabTag.equals(TAB_3_TAG)) {
                isPopFragment = ((Container_for_tabs) getSupportFragmentManager().findFragmentByTag(TAB_3_TAG)).popFragment();
            } else {

            }
            if (!isPopFragment) {
                finish();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            super.onBackPressed();
        }
    }

}
