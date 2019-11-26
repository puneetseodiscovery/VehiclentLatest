package com.vehiclent.mainActivity.bottomFragments.ISettingsFragments;

import com.vehiclent.responseModelClasses.LogoutResponseModel;

public interface ISettingsFragments {

    void onLogoutFromPresenter(int statusValue);
    void onLogoutSccressFromPresenter(LogoutResponseModel logoutResponseModel);
    void onLogoutFailedFromPresenter(String message);
}
