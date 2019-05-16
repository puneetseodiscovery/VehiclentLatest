package com.vehiclent.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;

import com.vehiclent.R;
import com.vehiclent.base.MyApp;
import com.vehiclent.signupActivity.SignUpActivity;

import java.io.IOException;

public class Utility {

     public static  ProgressDialog progressDialog;


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean validEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static Typeface typeFaceForBoldText(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/MYRIADPRO-SEMIBOLD.OTF");
    }

    public static Typeface typeFaceForRegulerText(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/MYRIADPRO-REGULAR.OTF");
    }

    // need to remove below method

    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (event.getRawX() >= (locationText.getLeft() - locationText.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
//                // your action here
//
//                return true;
//            }
        }
        return false;
    }

    public static Typeface typeFaceForSemiBoldText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/MYRIADPRO-SEMIBOLD.OTF");
    }

    public static ProgressDialog showLoader(Context context) {
        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading..");
        try {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progress);
        return progressDialog;
    }

    public static boolean isValidPassword(final String password) {
        String pattern= "^[a-zA-Z0-9]*$";
        return password.matches(pattern);

    }

}
