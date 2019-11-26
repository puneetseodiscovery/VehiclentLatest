package com.vehiclent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SavePref {

    public static final String TAG = "SavePref";
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static SavePref _instance;
    public static final String PREF_TOKEN = "VehiClent_User";

    public SavePref(Context c) {
        context = c;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public static SavePref getInstance(Context context) {
        if (_instance == null) {
            _instance = new SavePref(context);
        }
        return _instance;
    }

    public void setid(String id) {
        editor.putString("id", id);
        editor.commit();
    }

    public String getid() {
        String id = preferences.getString("id", "");
        return id;
    }

    public void setStatus(String status) {
        editor.putString("status", status);
        editor.commit();
    }

    public String getStatus() {
        String status = preferences.getString("status", "");
        return status;
    }


    public void setuser_name(String user_name) {
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String getuser_name() {
        String user_name = preferences.getString("user_name", "");
        return user_name;
    }

    public void setfirst_name(String first_name) {
        editor.putString("first_name", first_name);
        editor.commit();
    }

    public String getfirst_name() {
        String first_name = preferences.getString("first_name", "");
        return first_name;
    }

    public void setlast_name(String last_name) {
        editor.putString("last_name", last_name);
        editor.commit();
    }

    public String getlast_name() {
        String last_name = preferences.getString("last_name", "");
        return last_name;
    }


    public void setuser_email(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getuser_email() {
        String email = preferences.getString("email", "");
        return email;
    }

    public void setuser_address(String address) {
        editor.putString("address", address);
        editor.commit();
    }

    public String getuser_address() {
        String address = preferences.getString("address", "");
        return address;
    }

    public void setuser_phone_number(String phone_number) {
        editor.putString("phone_number", phone_number);
        editor.commit();
    }

    public String getuser_phone_number() {
        String phone_number = preferences.getString("phone_number", "");
        return phone_number;
    }

    public void setuser_longitude(String longitude) {
        editor.putString("longitude", longitude);
        editor.commit();
    }

    public String getuser_longitude() {
        String longitude = preferences.getString("longitude", "");
        return longitude;
    }

    public void setuser_latitude(String latitude) {
        editor.putString("latitude", latitude);
        editor.commit();
    }

    public String getuser_latitude() {
        String latitude = preferences.getString("latitude", "");
        return latitude;
    }

    public void setuser_profile_pic(String profile_pic) {
        editor.putString("profile_pic", profile_pic);
        editor.commit();
    }

    public String getuser_profile_pic() {
        String profile_pic = preferences.getString("profile_pic", "");
        return profile_pic;
    }


    public static void setDeviceToken(Context mContext, String key, String value) {
        SharedPreferences sharedpreferences = mContext.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getDeviceToken(Context mContext, String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        String stringvalue = preferences.getString(key, "");
        return stringvalue;
    }

    public void clearPreferences() {

        preferences.edit().clear().commit();
    }


}
