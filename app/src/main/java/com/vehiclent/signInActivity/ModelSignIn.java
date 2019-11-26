package com.vehiclent.signInActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.SignInResponseModel;
import com.vehiclent.responseModelClasses.UploadImageResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ModelSignIn implements IMSignIn {

    IPSignIn ipSignIn;

    public ModelSignIn(PSignIn pSignIn) {
        this.ipSignIn = pSignIn;
    }

    @Override
    public void signInRestCalls(String email, String password, String device_token,String latitude,String longitude) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_Login_Api(email, password, device_token,latitude,longitude, mHandler);
    }

    @Override
    public void uploadImageRestCall(String id, MultipartBody.Part body, RequestBody imgReq) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.updateImage(id, body,imgReq, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:

                    SignInResponseModel signInResponseModel = (SignInResponseModel) msg.obj;
                    ipSignIn.onSignInSucess(signInResponseModel);

                    break;
                case APIInterface.LOGIN_FAILED:
                    ipSignIn.onSignInFailed(String.valueOf(msg.obj));
                    break;

                case APIInterface.UPLOAD_IMAGE_SUCESS:

                    UploadImageResponseModel uploadImageResponseModel = (UploadImageResponseModel) msg.obj;
                    ipSignIn.onUploadImageSuccess(uploadImageResponseModel);

                    break;
                case APIInterface.UPLOAD_IMAGE_FAILED:
                    ipSignIn.onUploadImageFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
