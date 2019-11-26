package com.vehiclent.carCareActivity;

import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;

public interface IProcessActivity {

    void onSubmitUserQueryResponseFromPresenet(int statusValue);
    void onSubmitUserQuerySuccessResponseFromPresenter(SubmitQueryResponseModel submitQueryResponseModel);
    void onSubmitUserQueryFailedResponseFromPresenter(String message);

}
