package com.vehiclent.trackActivity;


import android.Manifest;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.vehiclent.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapsTrackerActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsTrackerActivity.class.getSimpleName();
    private GoogleMap mMap;
    ImageView img_back;
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    String partner_id = "", my_currentLatitude = "", my_currentLongitude = "";
    MapsTrackerActivity context;
    double lat;
    double lng;
    Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tracker);

        context = MapsTrackerActivity.this;

        partner_id = getIntent().getStringExtra("partner_id");

        my_currentLatitude = getIntent().getStringExtra("my_currentLatitude");
        my_currentLongitude = getIntent().getStringExtra("my_currentLongitude");


        img_back = findViewById(R.id.img_back);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // drawPolylines();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMaxZoomPreference(16);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        subscribeToUpdates();
    }

    private void subscribeToUpdates() {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(getString(R.string.firebase_path));
        ref.child(partner_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setMarker(dataSnapshot);
                //  drawPolylines();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setMarker(DataSnapshot dataSnapshot) {

        Log.d("dataSnapshot++", "" + dataSnapshot);

        String key = dataSnapshot.getKey();
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();

        lat = Double.parseDouble(value.get("latitude").toString());
        lng = Double.parseDouble(value.get("longitude").toString());
        LatLng location = new LatLng(lat, lng);

        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)));
            drawPolyLine(location);
        } else {
            mMarkers.get(key).setPosition(location);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers.values()) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 15));

    }

    private void drawPolyLine(LatLng location) {

        double my_Latitude = Double.parseDouble(my_currentLatitude);
        double my_Longitude = Double.parseDouble(my_currentLongitude);

        Log.e("++locationsdata","data"+my_Latitude);
        Log.e("++locationsdata","data"+my_Longitude);

        LatLng userLatLong = new LatLng(my_Latitude, my_Longitude);
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(userLatLong);
        markerOptions.title("You");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
*/
        mMap.addMarker(new MarkerOptions().position(userLatLong).title("You"));


    /*LatLng madrid = new LatLng(techLat,techLong);
    mMap.addMarker(new MarkerOptions().position(madrid).title(techLat + "," + techLong));*/

        List<LatLng> path = new ArrayList();

        String userLocation = my_Latitude + "," + my_Longitude;
        //   String userLoc = 30.7164 + "," +  76.7441;
        String partnerLoccation = lat + "," + lng;
  //      GeoApiContext context = new GeoApiContext().setApiKey(getResources().getString(R.string.map_api2));
        GeoApiContext context = new GeoApiContext().setApiKey(getResources().getString(R.string.google_maps_key));
        DirectionsApiRequest req = DirectionsApi.getDirections(context, userLocation, partnerLoccation);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    Log.e("legs length", "" + route.legs.length);
                    //for(int i=0; i<route.legs.length; i++) {
                    DirectionsLeg leg = route.legs[0];
                    Log.e("distance", "" + leg.distance);
                    Log.e("time", "" + leg.duration);
                    String distance, time;
                    distance = String.valueOf(leg.distance);
                    time = String.valueOf(leg.duration) + " to reach";

                    // setBottomSheetBehaviour(distance,time);

                    /*tvDistance.setText(distance);
                    tvTime.setText(time);*/
                    if (leg.steps != null) {
                        for (int j = 0; j < leg.steps.length; j++) {
                            DirectionsStep step = leg.steps[j];
                            if (step.steps != null && step.steps.length > 0) {
                                for (int k = 0; k < step.steps.length; k++) {
                                    DirectionsStep step1 = step.steps[k];
                                    EncodedPolyline points1 = step1.polyline;
                                    if (points1 != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                        for (com.google.maps.model.LatLng coord1 : coords1) {
                                            path.add(new LatLng(coord1.lat, coord1.lng));
                                        }
                                    }
                                }
                            } else {
                                EncodedPolyline points = step.polyline;
                                if (points != null) {
                                    //Decode polyline and add points to list of route coordinates
                                    List<com.google.maps.model.LatLng> coords = points.decodePath();
                                    for (com.google.maps.model.LatLng coord : coords) {
                                        path.add(new LatLng(coord.lat, coord.lng));
                                    }
                                }
                            }
                        }
                    }
                    //}
                /*Info distanceInfo = route.getLegList().get(0).getDistance();
                Info durationInfo = route.getLegList().get(0).getDuration();*/
                    //route.legs
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
        mMap.setTrafficEnabled(true);

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

    }
}
