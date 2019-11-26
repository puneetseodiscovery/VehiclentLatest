package com.vehiclent.retrofitCalls;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    /*user_register_Api method user to new user registrations for create new account*/

    public void user_register_Api(String firstname, String lastname, String email, String password, String phonenumber, String address, String gender, final Handler mHandler) {
        final Message message = new Message();
        Call<RegisterResponseModel> call = apiInterface.user_Registration_Api(firstname, lastname, email, password, phonenumber, address, gender);
        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SIGNUP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.SIGNUP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                message.what = apiInterface.SIGNUP_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }


    /*user_Login_Api method use to Login in the app */

    public void user_Login_Api(String email, String password, String device_token, String latitude, String longitude, final Handler mHandler) {
        final Message message = new Message();
        Call<SignInResponseModel> call = apiInterface.user_Login_Api(email, password, device_token, latitude, longitude);
        call.enqueue(new Callback<SignInResponseModel>() {
            @Override
            public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignInResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }

    /*socail_Login_Api method use to Login in the app USING facebook and google */

    public void socail_Login_Api(String email, String user_name, String device_token, final Handler mHandler) {
        final Message message = new Message();
        Call<SocialLoginResponseModel> call = apiInterface.socail_Login_Api(email, user_name, device_token);
        call.enqueue(new Callback<SocialLoginResponseModel>() {
            @Override
            public void onResponse(Call<SocialLoginResponseModel> call, Response<SocialLoginResponseModel> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SOCIAL_LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialLoginResponseModel> call, Throwable t) {
                message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }

    /*forgotpassword */

    public void forgot_password_Api(String email, final Handler mHandler) {
        final Message message = new Message();
        Call<ForgotPasswordResponse> call = apiInterface.forgot_Password(email);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FORGOT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.FORGOT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                message.what = apiInterface.FORGOT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }


    /*about us api*/
    public void about_us_Api(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<AboutUsResponseModel> call = apiInterface.about_us(id);
        call.enqueue(new Callback<AboutUsResponseModel>() {
            @Override
            public void onResponse(Call<AboutUsResponseModel> call, Response<AboutUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.ABOUTUS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ABOUTUS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AboutUsResponseModel> call, Throwable t) {
                message.what = apiInterface.ABOUTUS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*term condition api*/
    public void term_conditions(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<TermsConditionsResponseModel> call = apiInterface.term_conditions(id);
        call.enqueue(new Callback<TermsConditionsResponseModel>() {
            @Override
            public void onResponse(Call<TermsConditionsResponseModel> call, Response<TermsConditionsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.TERMCONDITION_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.TERMCONDITION_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<TermsConditionsResponseModel> call, Throwable t) {
                message.what = apiInterface.TERMCONDITION_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    /*userProfile api*/
    public void getuserProfile(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<UserProfileResponseModel> call = apiInterface.getuserProfile(id);
        call.enqueue(new Callback<UserProfileResponseModel>() {
            @Override
            public void onResponse(Call<UserProfileResponseModel> call, Response<UserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*Update_userProfile api*/
    public void updateuserProfile(String id, String first_name, String last_name, String gender, String email, String phone_number, String address,  MultipartBody.Part profile_pic, RequestBody imgReq, final Handler mHandler) {

       /* File newfile1=null;
        MultipartBody.Part logoBody = null;
        if (profile_pic != null && !profile_pic.equals("")) {
            newfile1 = new File(profile_pic);
        }
        if (newfile1 != null && !newfile1.equals("")) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), newfile1);
            logoBody = MultipartBody.Part.createFormData(Constants.IMAGE, newfile1.getName(), requestBody);
        }
*/
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody user_firstname = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody user_last_name = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody user_gender = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody user_email = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody user_phone_number = RequestBody.create(MediaType.parse("text/plain"), phone_number);
        RequestBody user_address = RequestBody.create(MediaType.parse("text/plain"), address);
        final Message message = new Message();

       Call<UpdateProfileResponseModel> call= apiInterface.updateuserProfile(user_id,user_firstname,user_last_name,user_gender,user_email,user_phone_number,user_address,imgReq,profile_pic);
        call.enqueue(new Callback<UpdateProfileResponseModel>() {
            @Override
            public void onResponse(Call<UpdateProfileResponseModel> call, Response<UpdateProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.UPDATE_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.UPDATE_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.UPDATE_PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*Update_userProfile api*/
    public void updateImage(String id, MultipartBody.Part profile_pic, RequestBody imgReq, final Handler mHandler) {

        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);
        final Message message = new Message();

        Call<UploadImageResponseModel> call=apiInterface.uploadUserImage(user_id,imgReq,profile_pic);
        call.enqueue(new Callback<UploadImageResponseModel>() {
            @Override
            public void onResponse(Call<UploadImageResponseModel> call, Response<UploadImageResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.UPLOAD_IMAGE_SUCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.UPLOAD_IMAGE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadImageResponseModel> call, Throwable t) {
                message.what = apiInterface.UPLOAD_IMAGE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*user logout api*/
    public void user_Logout(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.logout_user(id);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call, Response<LogoutResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGOUT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*user contacts_us api*/
    public void contact_us(String id, String message, final Handler mHandler) {
        final Message messages = new Message();
        Call<ContactsUsResponseModel> call = apiInterface.contacts_Us(id, message);
        call.enqueue(new Callback<ContactsUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactsUsResponseModel> call, Response<ContactsUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.CONTACTUS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.CONTACTUS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactsUsResponseModel> call, Throwable t) {
                messages.what = apiInterface.CONTACTUS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*user contacts_us api*/
    public void get_Contact_us(String id, final Handler mHandler) {
        final Message messages = new Message();
        Call<GetContactsusResponseModel> call = apiInterface.getcontacts_Us(id);
        call.enqueue(new Callback<GetContactsusResponseModel>() {
            @Override
            public void onResponse(Call<GetContactsusResponseModel> call, Response<GetContactsusResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.GET_CONTACTUS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.GET_CONTACTUS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetContactsusResponseModel> call, Throwable t) {
                messages.what = apiInterface.GET_CONTACTUS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*shows serives listing to user */
    public void serviceListing(String id, final Handler mHandler) {
        final Message messages = new Message();
        Call<ServicesResponseModel> call = apiInterface.serviceLsiting(id);
        call.enqueue(new Callback<ServicesResponseModel>() {
            @Override
            public void onResponse(Call<ServicesResponseModel> call, Response<ServicesResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.SERVICE_LIST_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.SERVICE_LIST_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<ServicesResponseModel> call, Throwable t) {
                messages.what = apiInterface.SERVICE_LIST_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*payment sucessfull */
    public void paymnet_Sucessful(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<PaymentSucessModel> call = apiInterface.paymnet_Success(jobid);
        call.enqueue(new Callback<PaymentSucessModel>() {
            @Override
            public void onResponse(Call<PaymentSucessModel> call, Response<PaymentSucessModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.PAYMENT_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.PAYMENT_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentSucessModel> call, Throwable t) {
                messages.what = apiInterface.PAYMENT_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }



    /*payment sucessfull */
    public void onGoingDetails(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<GetPartnerProfileResponseModel> call = apiInterface.getpartnerProfile(jobid);
        call.enqueue(new Callback<GetPartnerProfileResponseModel>() {
            @Override
            public void onResponse(Call<GetPartnerProfileResponseModel> call, Response<GetPartnerProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.ONGOING_DETAILS_SUCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.ONGOING_DETAILS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPartnerProfileResponseModel> call, Throwable t) {
                messages.what = apiInterface.ONGOING_DETAILS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*past job listing */
    public void pastjobListing_Api(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<PastJobListResponseModel> call = apiInterface.pastJobListing(jobid);
        call.enqueue(new Callback<PastJobListResponseModel>() {
            @Override
            public void onResponse(Call<PastJobListResponseModel> call, Response<PastJobListResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.PASTJOB_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else if (response.body().getStatus() == 400){
                        messages.what = apiInterface.PASTJOB_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }else {
                        messages.what = apiInterface.PASTJOB_EMPTY;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<PastJobListResponseModel> call, Throwable t) {
                messages.what = apiInterface.PASTJOB_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }


    /*past job detiald */
    public void pastjobDetails_Api(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<PastJobDetailsResponseModel> call = apiInterface.pastJobDetails(jobid);
        call.enqueue(new Callback<PastJobDetailsResponseModel>() {
            @Override
            public void onResponse(Call<PastJobDetailsResponseModel> call, Response<PastJobDetailsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.PASTJOBDETAILS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.PASTJOBDETAILS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<PastJobDetailsResponseModel> call, Throwable t) {
                messages.what = apiInterface.PASTJOBDETAILS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*ongoing */
    public void ongoing_api(String userid, final Handler mHandler) {
        final Message messages = new Message();
        Call<OnGoingResponseModel> call = apiInterface.onGoingJob(userid);
        call.enqueue(new Callback<OnGoingResponseModel>() {
            @Override
            public void onResponse(Call<OnGoingResponseModel> call, Response<OnGoingResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.ONGOING_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.ONGOING_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<OnGoingResponseModel> call, Throwable t) {
                messages.what = apiInterface.ONGOING_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*Submitted reviews */
    public void submitted_Reviews_Api(String jobid,String partnerid,String userid,String rating,String reviews, final Handler mHandler) {
        final Message messages = new Message();
        Call<ReviewsResponseModel> call = apiInterface.submit_Reviews(jobid,partnerid,userid,rating,reviews);
        call.enqueue(new Callback<ReviewsResponseModel>() {
            @Override
            public void onResponse(Call<ReviewsResponseModel> call, Response<ReviewsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.SUBMITTED_REVIEWS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.SUBMITTED_REVIEWS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewsResponseModel> call, Throwable t) {
                messages.what = apiInterface.SUBMITTED_REVIEWS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*shows serives listing to user */
    public void submitUserQuery(String id, String service_id, String user_query, String latitude, String longitude, final Handler mHandler) {
        /*Log.e("submitUserQuery",service_id);
        Log.e("submitUserQuery",latitude);
        Log.e("submitUserQuery",longitude);*/

        final Message messages = new Message();
        Call<SubmitQueryResponseModel> call = apiInterface.submitUserQuery(id, service_id, user_query, latitude, longitude);
        call.enqueue(new Callback<SubmitQueryResponseModel>() {
            @Override
            public void onResponse(Call<SubmitQueryResponseModel> call, Response<SubmitQueryResponseModel> response) {
                Log.e("response status", "" + response.body().getStatus());
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.SUBMIT_QUERY_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.SUBMIT_QUERY_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitQueryResponseModel> call, Throwable t) {
                messages.what = apiInterface.SUBMIT_QUERY_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }



}
