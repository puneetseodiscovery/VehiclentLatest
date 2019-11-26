package com.vehiclent.aboutusActivity;

import android.os.Message;
import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;


public class ModelAboutUs implements IModelAboutUs {

    IPAboutUs ipAboutUs;

    public ModelAboutUs(PAboutUs pAboutUs) {

        this.ipAboutUs = pAboutUs;
    }

    @Override
    public void aboutUsRestCall(String id) {

        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.about_us_Api(id,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.ABOUTUS_SUCCESS:
                    AboutUsResponseModel aboutUsResponseModel = ((AboutUsResponseModel) msg.obj);
                    ipAboutUs.onAboutUsSucess(aboutUsResponseModel);
                    break;

                case APIInterface.ABOUTUS_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipAboutUs.onAboutUsFailed(mesFailed);
                    break;
            }
        }
    };
}
