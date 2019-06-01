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

    public void setuser_email(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getuser_email() {
        String email = preferences.getString("email", "");
        return email;
    }

    public void setAuthorization_key(String authorization_key) {
        editor.putString("authorization_key", authorization_key);
        editor.commit();
    }

    public String getAuthorization_key() {
        String authorization_key = preferences.getString("authorization_key", "");
        return authorization_key;
    }

    public static void setDeviceToken(Context mContext, String key, String value) {
        SharedPreferences sharedpreferences = mContext.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
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
