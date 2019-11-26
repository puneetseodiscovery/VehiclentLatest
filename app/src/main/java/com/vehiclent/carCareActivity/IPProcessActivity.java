package com.vehiclent.carCareActivity;

import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;

public interface IPProcessActivity {

    void doSubmitUserQuery(String id, String service_id, String user_query, String latitude,String longitude);

    void onSubmitUserQueryFromModel(int statusValue);

    void onSubmitUserQuerySucessFromModel(SubmitQueryResponseModel submitQueryResponseModel);

    void onSubmitUserQueryFailedFromModel(String message);

}
