package com.vehiclent.partnerProfileActivity;

import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;

public class PPartnerProfile implements IPPartnerProfileActivity {


    IPartnerProfileActivity iPartnerProfileActivity;
    IMPartnerProfileActivity imPartnerProfileActivity;

    public PPartnerProfile(PartnerProfileActivity partnerProfileActivity) {

        this.iPartnerProfileActivity=partnerProfileActivity;
    }

    @Override
    public void doGetPartnerProfile(String jobid) {
        imPartnerProfileActivity=new MPartnerProfile(this);
        imPartnerProfileActivity.getPartnerProfileRestCall(jobid);
    }

    @Override
    public void onGetPartnerProfileSucess(GetPartnerProfileResponseModel getPartnerProfileResponseModel) {
        iPartnerProfileActivity.onPartnerProfileResponseFromPresenter(getPartnerProfileResponseModel);
    }

    @Override
    public void onGetPartnerProfileFailed(String message) {
        iPartnerProfileActivity.onPartnerProfileResponseFromPresenter(message);
    }
}
