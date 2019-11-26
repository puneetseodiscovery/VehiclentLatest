package com.vehiclent.ReviewActivity;

import com.vehiclent.responseModelClasses.ReviewsResponseModel;

public interface IReviewsActivity {

    void onReviewsFromPresenter(int statusValue);

    void onReviewsSuccessResponseFromPresenter(ReviewsResponseModel reviewsResponseModel);

    void onReviewsFailedResponseFromPresenter(String message);
}
