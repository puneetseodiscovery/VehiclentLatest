package com.vehiclent.mainActivity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.mainActivity.ContainersForFragments.PostJobsContainer;
import com.vehiclent.mainActivity.bottomFragments.CategoriesFragment;
import com.vehiclent.mainActivity.bottomFragments.PastJobsFragment;
import com.vehiclent.mainActivity.bottomFragments.ProfileFragment;
import com.vehiclent.mainActivity.bottomFragments.SettingsFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

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

}
