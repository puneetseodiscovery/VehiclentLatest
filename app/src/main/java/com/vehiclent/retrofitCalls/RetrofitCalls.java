package com.vehiclent.retrofitCalls;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    /*user_register_Api method user to new user registrations for create new account*/

    public void user_register_Api(String firstname, String lastname, String email, String password, String phonenumber, String address, final Handler mHandler) {
        final Message message = new Message();
        Call<RegisterResponseModel> call = apiInterface.user_Registration_Api(firstname, lastname, email, password, phonenumber, address);
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
                        message.obj = response.body();
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

    public void user_Login_Api(String email, String password,final Handler mHandler) {
        final Message message = new Message();
        Call<SignInResponseModel> call = apiInterface.user_Login_Api(email, password);
        call.enqueue(new Callback<SignInResponseModel>() {
            @Override
            public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                if (response.body() != null) {

                   /* if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.LOGIN_FAILED;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    }*/
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

}
