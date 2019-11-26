package com.vehiclent.pastJobDetails;

import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;

public interface IPPastJobDetails {

    void  doPastJobDetails(String jobid);
    void  onPastJobDetailsResponseFromModel(int statusValue);
    void  onPastJobDetailsSucessResponseFromModel(PastJobDetailsResponseModel  pastJobDetailsResponseModel);
    void  onPastJobDetailsFailedResponseFromModel(String message);

}
