package com.vehiclent.ReviewActivity;

import android.os.Handler;
import android.os.Message;
import com.vehiclent.responseModelClasses.ReviewsResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MReviewsActivity implements IMReviewsActivity {

    IPReviewsActivity imReviewsActivity;

    public MReviewsActivity(PReviewsActivity pReviewsActivity) {
        this.imReviewsActivity = pReviewsActivity;
    }

    @Override
    public void reviewRestCall(String jobid, String partnerid, String userid, String rating, String reviews) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitted_Reviews_Api(jobid, partnerid, userid, rating, reviews, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.SUBMITTED_REVIEWS_SUCCESS:
                    ReviewsResponseModel reviewsResponseModel = (ReviewsResponseModel) msg.obj;
                    imReviewsActivity.onReviewsSucessFromModel(reviewsResponseModel);
                    break;

                case APIInterface.SUBMITTED_REVIEWS_FAILED:
                    imReviewsActivity.onReviewsFailedFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
