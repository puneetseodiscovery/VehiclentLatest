package com.vehiclent.mainActivity.bottomFragments.Models;
import android.os.Handler;
import android.os.Message;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.IMSettingFragment;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.IPSettingsFragments;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PSettingsFragment;
import com.vehiclent.responseModelClasses.LogoutResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MSettingsFragment implements IMSettingFragment {


    IPSettingsFragments ipSettingsFragments;

    public MSettingsFragment(PSettingsFragment pSettingsFragment) {

        this.ipSettingsFragments = pSettingsFragment;

    }

    @Override
    public void logoutRestCall(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_Logout(id, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = ((LogoutResponseModel) msg.obj);
                    ipSettingsFragments.onLogoutSuccessResponseFromModel(logoutResponseModel);
                    break;

                case APIInterface.LOGOUT_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipSettingsFragments.onLogoutFailedMessage(mesFailed);
                    break;
            }
        }
    };
}
