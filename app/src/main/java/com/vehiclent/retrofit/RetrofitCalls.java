package com.vehiclent.retrofit;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModel.UserLoginResponseModel;
import com.vehiclent.signInActivity.BeanLogin;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    private APIInterface apiInterface;

    public RetrofitCalls() {

        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void loginUser(BeanLogin UserModel, final Handler mHandler) {
        final Message message = new Message();
        Call<UserLoginResponseModel> call = apiInterface.loginUser(UserModel);
        call.enqueue(new Callback<UserLoginResponseModel>() {
            @Override
            public void onResponse(Call<UserLoginResponseModel> call, Response<UserLoginResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    }

                } else {
                    message.what = apiInterface.LOGIN_FAILED;
                    message.obj = response.body().getMessage();
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponseModel> call, Throwable t) {

            }
        });
    }
}
