package com.vehiclent.ReviewActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.congratulationsActivity.CongratulationActivity;
import com.vehiclent.responseModelClasses.ReviewsResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsActivity extends BaseClass implements View.OnClickListener, IReviewsActivity {

    @BindView(R.id.tv_reviews)
    TextView tv_reviews;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.img_back_reviews)
    ImageView img_back_reviews;

    @BindView(R.id.img_userprofile)
    CircleImageView img_userprofile;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.ratingbar)
    RatingBar ratingbar;

    @BindView(R.id.edit_carcare)
    EditText edit_carcare;

    String firstname = "", jobid = "", total_rating = "", partner_id = "", user_id = "", your_profile = "";

    SavePref savePref;
    ProgressDialog progressDialog;
    ReviewsActivity context;

    IPReviewsActivity ipReviewsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        ButterKnife.bind(this);
        context = ReviewsActivity.this;
        savePref = new SavePref(this);
        progressDialog = new ProgressDialog(context);
        ipReviewsActivity = new PReviewsActivity(this);

        Initialization();
        EventListner();
    }

    private void Initialization() {

        firstname = getIntent().getStringExtra("firstname");
        jobid = getIntent().getStringExtra("jobid");
        partner_id = getIntent().getStringExtra("partner_id");
        your_profile = getIntent().getStringExtra("your_profile");
        user_id = savePref.getid();

        tv_reviews.setTypeface(Utility.typeFaceForBoldText(this));
        tv_username.setTypeface(Utility.typeFaceForBoldText(this));
        tv_username.setText(firstname);

        if (your_profile.isEmpty() || your_profile.equals("")) {

            Glide.with(context).load(your_profile).placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image).into(img_userprofile);

        } else {
            Glide.with(context).load(your_profile).error(R.drawable.no_image).into(img_userprofile);
        }

    }

    private void EventListner() {

        img_back_reviews.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        ratingbar.setOnClickListener(this);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                total_rating = String.valueOf(rating);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back_reviews:

                finish();
                break;

            case R.id.btn_submit:

                if (Utility.isNetworkConnected(context)) {
                    validationonRatingSubmit();
                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void validationonRatingSubmit() {

        if (ratingbar.getRating() == 0) {
            Toast.makeText(context, "Please give your rating", Toast.LENGTH_SHORT).show();
        } else if (edit_carcare.getText().toString().trim().isEmpty() || edit_carcare.getText().toString().equals("")) {

            edit_carcare.setError("Please give your review");
        } else {
            progressDialog = Utility.showLoader(context);
            ipReviewsActivity.doReviews(jobid, partner_id, user_id, total_rating, edit_carcare.getText().toString().trim());
        }
    }

    @Override
    public void onReviewsFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onReviewsSuccessResponseFromPresenter(ReviewsResponseModel reviewsResponseModel) {
        progressDialog.dismiss();
        Intent intent = new Intent(ReviewsActivity.this, CongratulationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReviewsFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
