package com.vehiclent.ReviewActivity;

import com.vehiclent.responseModelClasses.ReviewsResponseModel;

public class PReviewsActivity implements IPReviewsActivity {

    IReviewsActivity iReviewsActivity;
    IMReviewsActivity imReviewsActivity;


    public PReviewsActivity(ReviewsActivity reviewsActivity) {
        this.iReviewsActivity=reviewsActivity;
    }

    @Override
    public void doReviews(String jobid, String partnerid, String userid, String rating, String reviews) {
        imReviewsActivity=new MReviewsActivity(this);
        imReviewsActivity.reviewRestCall(jobid,partnerid,userid,rating,reviews);
    }

    @Override
    public void onReviewsResponseFromModel(int statusValue) {
        iReviewsActivity.onReviewsFromPresenter(statusValue);
    }

    @Override
    public void onReviewsSucessFromModel(ReviewsResponseModel reviewsResponseModel) {
        iReviewsActivity.onReviewsSuccessResponseFromPresenter(reviewsResponseModel);
    }

    @Override
    public void onReviewsFailedFromModel(String message) {
        iReviewsActivity.onReviewsFailedResponseFromPresenter(message);
    }

}
