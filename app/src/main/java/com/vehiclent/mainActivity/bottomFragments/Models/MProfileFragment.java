package com.vehiclent.mainActivity.bottomFragments.Models;

import android.os.Message;

import com.vehiclent.mainActivity.bottomFragments.IProfileFragments.IMProfileFragments;
import com.vehiclent.mainActivity.bottomFragments.IProfileFragments.IPProfileFragment;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PProfileFragments;
import com.vehiclent.responseModelClasses.UpdateProfileResponseModel;
import com.vehiclent.responseModelClasses.UserProfileResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MProfileFragment implements IMProfileFragments {

    IPProfileFragment ipProfileFragment;

    public MProfileFragment(PProfileFragments pProfileFragments) {
        this.ipProfileFragment = pProfileFragments;
    }

    @Override
    public void getProfileRestCall(String id) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getuserProfile(id, mHandler);

    }

    @Override
    public void updateProfileRestCall(String id, String first_name, String last_name, String gender, String email, String phone_number, String address, MultipartBody.Part body, RequestBody imgReq) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.updateuserProfile(id, first_name, last_name, gender, email,phone_number, address, body,imgReq, mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.PROFILE_SUCCESS:
                    UserProfileResponseModel getUserProfileResponseModel = ((UserProfileResponseModel) msg.obj);
                    ipProfileFragment.onGetProfileSucessResponseFromModel(getUserProfileResponseModel);
                    break;

                case APIInterface.PROFILE_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipProfileFragment.onGetProfileFailedMessage(mesFailed);
                    break;

                case APIInterface.UPDATE_PROFILE_SUCCESS:
                    UpdateProfileResponseModel updateProfileResponseModel = ((UpdateProfileResponseModel) msg.obj);
                    ipProfileFragment.onUpdateProfileSucessResponseModel(updateProfileResponseModel);
                    break;

                case APIInterface.UPDATE_PROFILE_FAILED:
                    String mesUpdateFailed = String.valueOf(msg.obj);
                    ipProfileFragment.onUpdateProfileFailedMessage(mesUpdateFailed);
                    break;

            }
        }
    };
}
