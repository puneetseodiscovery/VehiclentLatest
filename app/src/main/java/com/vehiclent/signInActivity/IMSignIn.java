package com.vehiclent.signInActivity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMSignIn {
    void signInRestCalls(String email,String password,String device_token,String latitude,String longitude);
    void uploadImageRestCall(String id, MultipartBody.Part body, RequestBody imgReq);
}
