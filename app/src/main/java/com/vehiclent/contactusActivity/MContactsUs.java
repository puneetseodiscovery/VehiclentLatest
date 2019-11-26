package com.vehiclent.contactusActivity;

import android.os.Handler;
import android.os.Message;

import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MContactsUs implements IMContactsUs {

    IPContactsUs ipContactsUs;

    public MContactsUs(PContactsUs pContactsUs) {

        this.ipContactsUs = pContactsUs;

    }

    @Override
    public void contactUsRestCalls(String id, String message) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.contact_us(id, message, mHandler);
    }

    @Override
    public void getContactUsRestCall(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.get_Contact_us(id, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.CONTACTUS_SUCCESS:

                    ContactsUsResponseModel contactsUsResponseModel = (ContactsUsResponseModel) msg.obj;
                    ipContactsUs.onContactsUsSucess(contactsUsResponseModel);

                    break;
                case APIInterface.CONTACTUS_FAILED:
                    ipContactsUs.onContactsUsFailed(String.valueOf(msg.obj));
                    break;
                case APIInterface.GET_CONTACTUS_SUCCESS:

                    GetContactsusResponseModel getContactsusResponseModel = (GetContactsusResponseModel) msg.obj;
                    ipContactsUs.onGetContactsUsSucess(getContactsusResponseModel);

                    break;
                case APIInterface.GET_CONTACTUS_FAILED:
                    ipContactsUs.onGetContactsUsFailed(String.valueOf(msg.obj));
                    break;

            }
        }
    };

}
