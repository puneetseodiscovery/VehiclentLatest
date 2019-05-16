package com.vehiclent.retrofit;

import com.vehiclent.responseModel.UserLoginResponseModel;
import com.vehiclent.signInActivity.BeanLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;

    @Headers({"Accept: application/json"})
    @POST("Cliknfixx/api/login")
    Call<UserLoginResponseModel> loginUser(@Body BeanLogin userModel);

}
