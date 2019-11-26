package com.vehiclent.partnerProfileActivity;

import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;

public interface IPartnerProfileActivity {

    void onPartnerProfileResponseFromPresenter(GetPartnerProfileResponseModel getPartnerProfileResponseModel);
    void onPartnerProfileResponseFromPresenter(String message);
}
