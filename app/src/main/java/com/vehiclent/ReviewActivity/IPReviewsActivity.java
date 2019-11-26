package com.vehiclent.ReviewActivity;


import com.vehiclent.responseModelClasses.ReviewsResponseModel;

public interface IPReviewsActivity {

    void doReviews(String jobid,String partnerid,String userid,String rating,String reviews);
    void onReviewsResponseFromModel(int statusValue);
    void onReviewsSucessFromModel(ReviewsResponseModel reviewsResponseModel);
    void onReviewsFailedFromModel(String message);
}
