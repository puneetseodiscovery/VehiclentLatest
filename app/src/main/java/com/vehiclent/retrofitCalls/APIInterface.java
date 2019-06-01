package com.vehiclent.retrofitCalls;

import com.vehiclent.responseModelClasses.AboutUsResponseModel;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int SIGNUP_SUCCESS = 3;
    public static final int SIGNUP_FAILED = 4;
    public static final int ABOUTUS_SUCCESS = 3;
    public static final int ABOUTUS_FAILED = 4;
    public static final int TERMCONDITION_SUCCESS = 5;
    public static final int TERMCONDITION_FAILED = 6;


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_registration.php")
    Call<RegisterResponseModel> user_Registration_Api(@Field("firstname") String firstname,
                                                      @Field("lastname") String lastname,
                                                      @Field("email") String email,
                                                      @Field("password") String password,
                                                      @Field("phonenumber") String phonenumber,
                                                      @Field("address") String address);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_registration.php")
    Call<SignInResponseModel> user_Login_Api(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/about_us.php")
    Call<AboutUsResponseModel> about_us(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/private_policy.php")
    Call<TermsConditionsResponseModel> term_conditions(@Field("id") String id);



}
