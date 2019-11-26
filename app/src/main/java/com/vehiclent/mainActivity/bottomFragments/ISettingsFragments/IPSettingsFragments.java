package com.vehiclent.mainActivity.bottomFragments.ISettingsFragments;

import com.vehiclent.responseModelClasses.LogoutResponseModel;

public interface IPSettingsFragments {
    void doLogout(String id);
    void onLogoutResposeFromModel(int statusValue);
    void onLogoutSuccessResponseFromModel(LogoutResponseModel logoutResponseModel);
    void onLogoutFailedMessage(String message);
}
