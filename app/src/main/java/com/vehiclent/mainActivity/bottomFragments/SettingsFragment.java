package com.vehiclent.mainActivity.bottomFragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.vehiclent.R;
import com.vehiclent.aboutusActivity.AboutUsActivity;
import com.vehiclent.contactusActivity.ContactsUsActivity;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.IPSettingsFragments;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.ISettingsFragments;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PSettingsFragment;
import com.vehiclent.onGoingActivity.OnGoingActivity;
import com.vehiclent.responseModelClasses.LogoutResponseModel;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.termServicesActivity.TermConditionsActivity;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements ISettingsFragments, View.OnClickListener {

    View view;
    @BindView((R.id.tv_categories))
    TextView tv_categories;

    @BindView(R.id.tv_aboutus)
    TextView tv_aboutus;

    @BindView(R.id.relative_on_going)
    RelativeLayout relative_on_going;

    @BindView(R.id.tv_contactus)
    TextView tv_contactus;

    @BindView(R.id.tv_howwork)
    TextView tv_howwork;

    @BindView(R.id.tv_payment)
    TextView tv_payment;

    @BindView(R.id.tv_on_going)
    TextView tv_on_going;

    @BindView(R.id.tv_termcondintion)
    TextView tv_termcondintion;

    @BindView(R.id.tv_logout)
    TextView tv_logout;

    @BindView(R.id.relative_logout)
    RelativeLayout relative_logout;

    @BindView(R.id.relative_aboutus)
    RelativeLayout relative_aboutus;

    @BindView(R.id.relative_contactus)
    RelativeLayout relative_contactus;

    @BindView(R.id.relative_termcondition)
    RelativeLayout relative_termcondition;


    Context context;
    IPSettingsFragments ipSettingsFragments;
    ProgressDialog progressDialog;
    SavePref savePref;
    String get_UserID;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = this.getContext();
        ipSettingsFragments = new PSettingsFragment(this);
        ButterKnife.bind(this, view);
        Initialization();

        return view;
    }

    private void Initialization() {

        savePref = new SavePref(context);
        get_UserID = savePref.getid();
        progressDialog = new ProgressDialog(context);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_aboutus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_on_going.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_contactus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_howwork.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_payment.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_termcondintion.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_logout.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        EventListeners();

    }

    private void EventListeners() {

        relative_logout.setOnClickListener(this);
        relative_aboutus.setOnClickListener(this);
        relative_contactus.setOnClickListener(this);
        relative_termcondition.setOnClickListener(this);
        relative_on_going.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()) {
            case R.id.relative_logout:

                AlertLogoutDialog();

                break;
            case R.id.relative_aboutus:
                intent = new Intent(context, AboutUsActivity.class);
                startActivity(intent);

                break;
            case R.id.relative_contactus:
                intent = new Intent(context, ContactsUsActivity.class);
                startActivity(intent);

                break;
            case R.id.relative_termcondition:

                intent = new Intent(context, TermConditionsActivity.class);
                startActivity(intent);

                break;
            case R.id.relative_on_going:

                intent = new Intent(context, OnGoingActivity.class);
                startActivity(intent);

                break;

        }
    }

    private void AlertLogoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(savePref.getuser_name());
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //   savePref.clearPreferences();

                        if (Utility.isNetworkConnected(context)) {
                            progressDialog = Utility.showLoader(getActivity());
                            ipSettingsFragments.doLogout(get_UserID);

                        } else {
                            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLogoutFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onLogoutSccressFromPresenter(LogoutResponseModel logoutResponseModel) {

        progressDialog.dismiss();
        savePref.clearPreferences();
        facebookLogout();
       // googleLogout();
        signOut();
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(context, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    private void googleLogout() {

        /*if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);

        } else {

        }*/


        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect().setResultCallback(new ResultCallback<Status>() {

                @Override
                public void onResult(Status status) {

                    mGoogleApiClient.disconnect();
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                }
            });

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void signOut() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }

    private void facebookLogout() {

        LoginManager.getInstance().logOut();
    }

    @Override
    public void onLogoutFailedFromPresenter(String message) {
        progressDialog.dismiss();

    }

}
