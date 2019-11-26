package com.vehiclent.carCareActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarCareActivity extends BaseClass implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @BindView(R.id.tv_carcare)
    TextView tv_carcare;

    @BindView(R.id.edit_carcare)
    EditText edit_carcare;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.img_back_carcare)
    ImageView img_back_carcare;

    @BindView(R.id.layout_service)
    RelativeLayout layout_service;

    @BindView(R.id.relative_layout)
    RelativeLayout relative_layout;

    CarCareActivity context;
    SavePref savePref;
    String service_Name = "", service_id = "", get_UserId = "";

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    String placeCurrentLatitude = "";
    String placeCurrentLongitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_care);

        ButterKnife.bind(this);
        context = CarCareActivity.this;
        savePref = new SavePref(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Initialization();
        EventListner();

    }

    private void Initialization() {

        mGoogleApiClient.connect();
        if (!isGpsOn())
            showGPSDisabledAlertToUser();

        get_UserId = savePref.getid();
        service_id = getIntent().getStringExtra("service_id");
        service_Name = getIntent().getStringExtra("service_Name");
        tv_carcare.setTypeface(Utility.typeFaceForBoldText(this));
        tv_carcare.setText(service_Name);

    }

    private void EventListner() {

        btn_submit.setOnClickListener(this);
        img_back_carcare.setOnClickListener(this);

    }


    private boolean isGpsOn() {

        boolean isGpsOn = false;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isGpsOn = false;

        } else {

            isGpsOn = true;

        }

        return isGpsOn;
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(getResources().getString(R.string.gps_disable))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.turn_on),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                                dialog.cancel();
                            }
                        });
        /*alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:

                validationOnField();

                break;
            case R.id.img_back_carcare:
                finish();
                break;
        }
    }

    private void validationOnField() {

        if (Utility.isNetworkConnected(context)) {
            if (edit_carcare.getText().toString().trim().isEmpty()) {

                Snackbar snackbar = Snackbar
                        .make(relative_layout, "Please write your problem first.", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(relative_layout, "", Snackbar.LENGTH_SHORT);
                                snackbar1.dismiss();
                            }
                        });

                snackbar.show();


            } else {

                Intent intent = new Intent(CarCareActivity.this, ProcessActivity.class);
                intent.putExtra("get_UserId",get_UserId);
                intent.putExtra("service_id",service_id);
                intent.putExtra("my_query",edit_carcare.getText().toString().trim());
                intent.putExtra("placeCurrentLatitude",placeCurrentLatitude);
                intent.putExtra("placeCurrentLongitude",placeCurrentLongitude);
                startActivity(intent);
            }

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            //If everything went fine lets get latitude and longitude


            if (String.valueOf(location.getLatitude()).isEmpty()) {

            } else {

                placeCurrentLatitude = String.valueOf(location.getLatitude());
                placeCurrentLongitude = String.valueOf(location.getLongitude());

                if (placeCurrentLatitude.isEmpty()) {

                } else {


                }

            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    protected void onResume() {
        super.onResume();


        mGoogleApiClient.connect();
        if (!isGpsOn())
            showGPSDisabledAlertToUser();
    }
}
