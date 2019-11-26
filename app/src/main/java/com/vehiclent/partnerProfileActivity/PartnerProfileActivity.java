package com.vehiclent.partnerProfileActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.carCareActivity.CarCareActivity;
import com.vehiclent.chatActivity.ChatActivity;
import com.vehiclent.paymentActivity.PaymentActivity;
import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;
import com.vehiclent.trackActivity.MapsTrackerActivity;
import com.vehiclent.utils.Constants;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerProfileActivity extends BaseClass implements IPartnerProfileActivity,
        View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    PartnerProfileActivity context;

    @BindView(R.id.tv_partner_profile)
    TextView tv_partner_profile;

    @BindView(R.id.tv_partnername)
    TextView tv_partnername;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_call)
    ImageView img_call;

    @BindView(R.id.img_partner)
    CircleImageView img_partner;

    @BindView(R.id.img_message)
    ImageView img_message;

    @BindView(R.id.tv_payment)
    Button tv_payment;

    @BindView(R.id.tv_partneraddress)
    TextView tv_partneraddress;

    @BindView(R.id.tv_track)
    Button tv_track;

    @BindView(R.id.ratingbar)
    RatingBar ratingbar;

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_otp)
    TextView tv_otp;

    @BindView(R.id.tv_duraton)
    TextView tv_duraton;

    SavePref savePref;

    String partner_id = "", user_id = "", Service_Id = "", status = "", your_profile = "", email = "", last_name = "";
    String service_price = "", gender = "", address = "", rating = "", phone_number = "", first_name = "", otp = "", jobid = "";
    String my_currentLatitude = "", my_currentLongitude = "", user_name = "";
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    IPPartnerProfileActivity ipPartnerProfileActivity;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_profile);
        context = PartnerProfileActivity.this;
        savePref = new SavePref(this);
        progressDialog = new ProgressDialog(this);
        ipPartnerProfileActivity = new PPartnerProfile(this);

        ButterKnife.bind(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Intent intent = getIntent();

        partner_id = intent.getStringExtra("Partner");
        Service_Id = intent.getStringExtra("Service_Id");
        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        status = intent.getStringExtra("status");
        your_profile = intent.getStringExtra("your_profile");
        email = intent.getStringExtra("email");
        service_price = intent.getStringExtra("service_price");
        gender = intent.getStringExtra("gender");
        address = intent.getStringExtra("address");
        rating = intent.getStringExtra("rating");
        phone_number = intent.getStringExtra("phone_number");
        phone_number = intent.getStringExtra("phone_number");
        otp = intent.getStringExtra("otp");
        jobid = intent.getStringExtra("jobid");

        Log.e("data", "" + partner_id);
        Log.e("data", "" + Service_Id);
        Log.e("data", "" + first_name);
        Log.e("jobid", "" + jobid);

      /*  Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            String user_id= extras.getString("user_id");
            String service_id = extras.getString("service_id");

            Log.e("data","++++"+user_id);
            Log.e("data","++++"+service_id);
        }*/

        /*Partner,Service_Id*/
       /* if (getIntent().getExtras() != null) {
            //for (String key : getIntent().getExtras().keySet()) {
                Log.e("data","" + getIntent().getExtras().getString("Partner"));
                Log.e("data","" + getIntent().getStringExtra("Partner"));
                Log.e("data","" + getIntent().getExtras().get("Partner"));
                Log.e("data","" + getIntent().getData());
            //}
        }*/
        if (Utility.isNetworkConnected(context)) {
            progressDialog = Utility.showLoader(context);
            ipPartnerProfileActivity.doGetPartnerProfile(jobid);
        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();

        }

        Initialization();
        EventListner();


    }


    private void Initialization() {

        context = this;

        user_name = first_name + last_name;
        user_id = savePref.getid();
        tv_partner_profile.setTypeface(Utility.typeFaceForBoldText(this));
        tv_partnername.setTypeface(Utility.typeFaceForBoldText(this));
        tv_partnername.setText(first_name + " " + last_name);
        tv_partneraddress.setText(address);
        tv_totalprice.setText("Rate:-" + " " + "\u20B9" + service_price + " /Hours");
        tv_duraton.setText("Minimum Duration :- 2 Hours");
        tv_otp.setText("OTP :" + " - " + otp);
        tv_otp.setBackgroundColor(getResources().getColor(R.color.colorBlack));

       /* if (your_profile.isEmpty() || your_profile.equals("")) {
            Glide.with(context).load(your_profile).placeholder(R.drawable.no_image).error(R.drawable.no_image);
        } else {
            Glide.with(context).load(your_profile).error(R.drawable.no_image).into(img_partner);
        }*/


        if (ratingbar == null || ratingbar.equals("")) {
            ratingbar.setRating(Float.parseFloat("2.0"));
        } else {
            // ratingbar.setRating(Float.parseFloat(rating));
            ratingbar.setRating(Float.parseFloat("2.0"));
        }

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

    private void EventListner() {

        img_back.setOnClickListener(this);
        tv_payment.setOnClickListener(this);
        tv_track.setOnClickListener(this);
        img_call.setOnClickListener(this);
        img_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.img_back:

                finish();

                break;
            case R.id.img_message:
                firebaseChatWith = partner_id;
                Constants.user_name = first_name + " " + last_name;

                intent = new Intent(PartnerProfileActivity.this, ChatActivity.class);
                intent.putExtra("firebaseChatWith", firebaseChatWith);
                intent.putExtra("user_id", user_id);
                intent.putExtra("user_name", first_name + " " + last_name);

                startActivity(intent);
                break;
            case R.id.img_call:
                callPhoneNumber(phone_number);
                break;
            case R.id.tv_payment:
                intent = new Intent(PartnerProfileActivity.this, PaymentActivity.class);
                intent.putExtra("jobid", jobid);
                intent.putExtra("partner_id", partner_id);
                intent.putExtra("service_price", service_price);
                intent.putExtra("user_name", first_name + " " + last_name);
                intent.putExtra("phone_number", phone_number);
                intent.putExtra("email", email);
                intent.putExtra("your_profile", your_profile);
                startActivity(intent);
                break;
            case R.id.tv_track:
                intent = new Intent(PartnerProfileActivity.this, MapsTrackerActivity.class);
                intent.putExtra("partner_id", partner_id);
                intent.putExtra("my_currentLatitude", my_currentLatitude);
                intent.putExtra("my_currentLongitude", my_currentLongitude);
                startActivity(intent);

                break;
        }
    }

    public void callPhoneNumber(String phone_number) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(PartnerProfileActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_number));
                startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_number));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber(phone_number);
            } else {
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(PartnerProfileActivity.this, CarCareActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

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

                my_currentLatitude = String.valueOf(location.getLatitude());
                my_currentLongitude = String.valueOf(location.getLongitude());


                if (my_currentLatitude.isEmpty()) {

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

    // getpartner profile

    @Override
    public void onPartnerProfileResponseFromPresenter(GetPartnerProfileResponseModel getPartnerProfileResponseModel) {
        progressDialog.dismiss();

        if (getPartnerProfileResponseModel.getData().get(0).getYourProfile().isEmpty()) {
            Glide.with(context).load(your_profile).placeholder(R.drawable.no_image).error(R.drawable.no_image);

        } else {
            Glide.with(context).load(your_profile).error(R.drawable.no_image).into(img_partner);

        }

        tv_partnername.setText(getPartnerProfileResponseModel.getData().get(0).getFirstName() + " " + getPartnerProfileResponseModel.getData().get(0).getLastName());
        tv_partneraddress.setText(getPartnerProfileResponseModel.getData().get(0).getAddress());

        /*if (ratingbar == null || ratingbar.equals("")) {
            ratingbar.setRating(0.0f);
        } else {
            ratingbar.setRating(Float.parseFloat(getPartnerProfileResponseModel.getData().get(0).getRating()));

        }*/
        tv_totalprice.setText("Rate:-" + " " + "\u20B9" + getPartnerProfileResponseModel.getData().get(0).getPrice() + " /Hours");
        tv_duraton.setText("Minimum Duration :- 2 Hours");
        tv_otp.setText("OTP :" + getPartnerProfileResponseModel.getData().get(0).getOtp());

        phone_number=getPartnerProfileResponseModel.getData().get(0).getPhoneNumber();
        partner_id=getPartnerProfileResponseModel.getData().get(0).getPartner();
        first_name=getPartnerProfileResponseModel.getData().get(0).getFirstName();
        last_name=getPartnerProfileResponseModel.getData().get(0).getLastName();
        service_price=getPartnerProfileResponseModel.getData().get(0).getPrice();
        email=getPartnerProfileResponseModel.getData().get(0).getEmail();
        your_profile=getPartnerProfileResponseModel.getData().get(0).getYourProfile();
    }

    @Override
    public void onPartnerProfileResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
