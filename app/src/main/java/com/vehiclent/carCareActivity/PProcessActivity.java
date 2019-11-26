package com.vehiclent.carCareActivity;

import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;

public class PProcessActivity implements IPProcessActivity {

    IProcessActivity iProcessActivity;
    IMProcessActivity imProcessActivity;

    public PProcessActivity(IProcessActivity iProcessActivity) {
        this.iProcessActivity = iProcessActivity;
    }

    @Override
    public void doSubmitUserQuery(String get_userId, String service_id, String my_query, String placeCurrentLatitude, String placeCurrentLongitude) {
        imProcessActivity = new MProcessActivity(this);
        imProcessActivity.omSubmitUserQueryRestCall(get_userId, service_id, my_query, placeCurrentLatitude, placeCurrentLongitude);
    }

    @Override
    public void onSubmitUserQueryFromModel(int statusValue) {
        iProcessActivity.onSubmitUserQueryResponseFromPresenet(statusValue);
    }

    @Override
    public void onSubmitUserQuerySucessFromModel(SubmitQueryResponseModel submitQueryResponseModel) {
        iProcessActivity.onSubmitUserQuerySuccessResponseFromPresenter(submitQueryResponseModel);
    }

    @Override
    public void onSubmitUserQueryFailedFromModel(String message) {

        iProcessActivity.onSubmitUserQueryFailedResponseFromPresenter(message);
    }
}
