package com.vehiclent.mainActivity.bottomFragments.IProfileFragments;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMProfileFragments {

    void getProfileRestCall(String id);
    void updateProfileRestCall(String id, String first_name, String last_name, String gender, String email, String phone_number, String address, MultipartBody.Part body, RequestBody imgReq);
}
