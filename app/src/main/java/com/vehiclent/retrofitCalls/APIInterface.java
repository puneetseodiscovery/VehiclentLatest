package com.vehiclent.retrofitCalls;

import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.ForgotPasswordResponse;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;
import com.vehiclent.responseModelClasses.GetPartnerProfileResponseModel;
import com.vehiclent.responseModelClasses.LogoutResponseModel;
import com.vehiclent.responseModelClasses.OnGoingResponseModel;
import com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import com.vehiclent.responseModelClasses.PastJobListResponseModel;
import com.vehiclent.responseModelClasses.PaymentSucessModel;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.ReviewsResponseModel;
import com.vehiclent.responseModelClasses.ServicesResponseModel;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;
import com.vehiclent.responseModelClasses.SubmitQueryResponseModel;
import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import com.vehiclent.responseModelClasses.UpdateProfileResponseModel;
import com.vehiclent.responseModelClasses.UploadImageResponseModel;
import com.vehiclent.responseModelClasses.UserProfileResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;

    public static final int SIGNUP_SUCCESS = 3;
    public static final int SIGNUP_FAILED = 4;

    public static final int ABOUTUS_SUCCESS = 3;
    public static final int ABOUTUS_FAILED = 4;

    public static final int TERMCONDITION_SUCCESS = 5;
    public static final int TERMCONDITION_FAILED = 6;

    public static final int LOGOUT_SUCCESS = 7;
    public static final int LOGOUT_FAILED = 8;

    public static final int CONTACTUS_SUCCESS = 9;
    public static final int CONTACTUS_FAILED = 10;

    public static final int UPDATE_PROFILE_SUCCESS = 11;
    public static final int UPDATE_PROFILE_FAILED = 12;

    public static final int PROFILE_SUCCESS = 13;
    public static final int PROFILE_FAILED = 14;

    public static final int FORGOT_SUCCESS = 13;
    public static final int FORGOT_FAILED = 15;

    public static final int SERVICE_LIST_SUCCESS = 16;
    public static final int SERVICE_LIST_FAILED = 17;

    public static final int UPLOAD_IMAGE_SUCESS = 18;
    public static final int UPLOAD_IMAGE_FAILED = 19;

    public static final int GET_CONTACTUS_SUCCESS = 20;
    public static final int GET_CONTACTUS_FAILED = 21;

    public static final int SOCIAL_LOGIN_SUCCESS = 22;
    public static final int SOCIAL_LOGIN_FAILED = 23;

    public static final int SUBMIT_QUERY_SUCCESS = 24;
    public static final int SUBMIT_QUERY_FAILED = 25;

    public static final int PAYMENT_SUCCESS = 26;
    public static final int PAYMENT_FAILED = 27;

    public static final int PASTJOB_SUCCESS = 28;
    public static final int PASTJOB_FAILED = 29;
    public static final int PASTJOB_EMPTY = 291;

    public static final int PASTJOBDETAILS_SUCCESS = 30;
    public static final int PASTJOBDETAILS_FAILED = 31;

    public static final int SUBMITTED_REVIEWS_SUCCESS = 32;
    public static final int SUBMITTED_REVIEWS_FAILED = 33;

    public static final int ONGOING_SUCCESS = 34;
    public static final int ONGOING_FAILED = 35;
    public static final int ONGOING_EMPTY = 351;

    public static final int ONGOING_DETAILS_SUCESS = 352;
    public static final int ONGOING_DETAILS_FAILED= 353;


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_registration.php")
    Call<RegisterResponseModel> user_Registration_Api(@Field("firstname") String firstname,
                                                      @Field("lastname") String lastname,
                                                      @Field("email") String email,
                                                      @Field("password") String password,
                                                      @Field("phonenumber") String phonenumber,
                                                      @Field("address") String address,
                                                      @Field("gender") String gender);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_login.php")
    Call<SignInResponseModel> user_Login_Api(@Field("email") String email,
                                             @Field("password") String password,
                                             @Field("device_token") String device_token,
                                             @Field("latitude") String latitude,
                                             @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_sociallogin.php")
    Call<SocialLoginResponseModel> socail_Login_Api(@Field("email") String email,
                                                    @Field("user_name") String user_name,
                                                    @Field("device_token") String device_token);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_aboutus.php")
    Call<AboutUsResponseModel> about_us(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_privatepolicy.php")
    Call<TermsConditionsResponseModel> term_conditions(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_logout.php")
    Call<LogoutResponseModel> logout_user(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_profile.php")
    Call<UserProfileResponseModel> getuserProfile(@Field("id") String id);

    @Multipart
    @POST("vehiclentsss/vehiclents/apis/userupload_image.php")
    Call<UploadImageResponseModel> uploadUserImage(@Part("id") RequestBody user_id,
                                                   @Part("image") RequestBody imgReq,
                                                   @Part MultipartBody.Part image);


    @Multipart
    @POST("vehiclentsss/vehiclents/apis/editusers_profile.php")
    Call<UpdateProfileResponseModel> updateuserProfile(@Part("id") RequestBody user_id,
                                                       @Part("first_name") RequestBody user_firstname,
                                                       @Part("last_name") RequestBody user_last_name,
                                                       @Part("gender") RequestBody user_gender,
                                                       @Part("email") RequestBody user_email,
                                                       @Part("phone_number") RequestBody user_phone_number,
                                                       @Part("address") RequestBody user_address,
                                                       @Part("image") RequestBody imgReq,
                                                       @Part MultipartBody.Part profile_pic);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_contactusmail.php")
    Call<ContactsUsResponseModel> contacts_Us(@Field("id") String id,
                                              @Field("message") String message);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_contactus.php")
    Call<GetContactsusResponseModel> getcontacts_Us(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_forgotpassword.php")
    Call<ForgotPasswordResponse> forgot_Password(@Field("email") String email);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/services_list.php")
    Call<ServicesResponseModel> serviceLsiting(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/payment.php")
    Call<PaymentSucessModel> paymnet_Success(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/past_jobs_list.php")
    Call<PastJobListResponseModel> pastJobListing(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/ongoing_jobs_list.php")
    Call<OnGoingResponseModel> onGoingJob(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/past_jobs_detail.php")
    Call<PastJobDetailsResponseModel> pastJobDetails(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/ongoing_jobs_detail.php")
    Call<GetPartnerProfileResponseModel> getpartnerProfile(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/partner_reviews_by_user.php")
    Call<ReviewsResponseModel> submit_Reviews(@Field("jobid") String jobid,
                                              @Field("partnerid") String partnerid,
                                              @Field("userid") String userid,
                                              @Field("rating") String rating
            , @Field("reviews") String reviews);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/submituser_query.php")
    Call<SubmitQueryResponseModel> submitUserQuery(@Field("id") String id,
                                                   @Field("service_id") String service_id,
                                                   @Field("user_query") String user_query,
                                                   @Field("latitude") String latitude,
                                                   @Field("longitude") String longitude);
}
