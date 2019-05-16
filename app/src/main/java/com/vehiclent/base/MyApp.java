package com.vehiclent.base;

import android.app.Application;
import com.vehiclent.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {

    private static MyApp AppContext;

    public static synchronized MyApp getInstance() {
        return AppContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MYRIADPRO-REGULAR.OTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
