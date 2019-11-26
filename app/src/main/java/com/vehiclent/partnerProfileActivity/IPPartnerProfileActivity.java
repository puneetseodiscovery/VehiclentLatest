package com.vehiclent.partnerProfileActivity;

import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;
public interface IPPartnerProfileActivity {

    void doGetPartnerProfile(String jobid);
    void onGetPartnerProfileSucess(GetPartnerProfileResponseModel getPartnerProfileResponseModel);
    void onGetPartnerProfileFailed(String message);

}
