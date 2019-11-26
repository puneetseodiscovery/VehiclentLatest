package com.vehiclent.mainActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;

import com.vehiclent.mainActivity.bottomFragments.CategoriesFragment;
import com.vehiclent.mainActivity.bottomFragments.PastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.ProfileFragment;
import com.vehiclent.mainActivity.bottomFragments.SettingsFragment;

import io.fabric.sdk.android.Fabric;


public class HomeActivity extends BaseClass {

    private TextView mTextMessage;
    boolean doubleBackToExitPressedOnce = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new CategoriesFragment());
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(new PastJobsFragment());
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(new SettingsFragment());
                    return true;
                case R.id.navigation_profile:
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        setContentView(R.layout.activity_home);
        init();

    }

    private void init() {

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new CategoriesFragment());
    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }
}
