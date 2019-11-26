package com.vehiclent.pastJobDetails;

import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;

public interface IPastJobDetailsActivity {

    void onPastJobDetailsFromPresenter(int status);
    void onPastJobDetailsSucessFromPresenter(PastJobDetailsResponseModel pastJobDetailsResponseModel);
    void onPastJobDetailsFailedFromPresenter(String message);

}
