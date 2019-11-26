package com.vehiclent.mainActivity.bottomFragments.Presenters;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.IMSettingFragment;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.IPSettingsFragments;
import com.vehiclent.mainActivity.bottomFragments.ISettingsFragments.ISettingsFragments;
import com.vehiclent.mainActivity.bottomFragments.Models.MSettingsFragment;
import com.vehiclent.mainActivity.bottomFragments.SettingsFragment;
import com.vehiclent.responseModelClasses.LogoutResponseModel;

public class PSettingsFragment implements IPSettingsFragments {

    ISettingsFragments ipSettingsFragments;
    IMSettingFragment imSettingFragment;


    public PSettingsFragment(SettingsFragment settingsFragment) {
        this.ipSettingsFragments = settingsFragment;

    }

    @Override
    public void doLogout(String id) {
        imSettingFragment = new MSettingsFragment(this);
        imSettingFragment.logoutRestCall(id);
    }

    @Override
    public void onLogoutResposeFromModel(int statusValue) {

        ipSettingsFragments.onLogoutFromPresenter(statusValue);

    }

    @Override
    public void onLogoutSuccessResponseFromModel(LogoutResponseModel logoutResponseModel) {

        ipSettingsFragments.onLogoutSccressFromPresenter(logoutResponseModel);

    }

    @Override
    public void onLogoutFailedMessage(String message) {

        ipSettingsFragments.onLogoutFailedFromPresenter(message);

    }
}
