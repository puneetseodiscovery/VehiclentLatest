package com.vehiclent.contactusActivity;

import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;

public class PContactsUs implements IPContactsUs {


    IContactsUs iContactsUs;
    IMContactsUs imContactsUs;


    public PContactsUs(IContactsUs iContactsUs) {

        this.iContactsUs = iContactsUs;
    }

    @Override
    public void doContactsUs(String email, String message) {
        imContactsUs =new MContactsUs(this);
        imContactsUs.contactUsRestCalls(email, message);

    }

    @Override
    public void onContactsUsResponseFromModel(int statusValue) {

        iContactsUs.onContactsUsResponseFromPresenter(statusValue);

    }

    @Override
    public void onContactsUsSucess(ContactsUsResponseModel contactsUsResponseModel) {

       iContactsUs.onContactsUsSuccessResponseFromPresenter(contactsUsResponseModel);

    }

    @Override
    public void onContactsUsFailed(String message) {

        iContactsUs.onContactsUsFailedResponseFromPresenter(message);

    }

    @Override
    public void doGetContactsUs(String id) {
        imContactsUs =new MContactsUs(this);
        imContactsUs.getContactUsRestCall(id);

    }

    @Override
    public void onGetContactsUsResponseFromModel(int statusValue) {
        iContactsUs.onGetContactUsResponseFromPresenter(statusValue);
    }

    @Override
    public void onGetContactsUsSucess(GetContactsusResponseModel getContactsusResponseModel) {
        iContactsUs.onGetContactUsSuccessResponseFromPresenter(getContactsusResponseModel);

    }

    @Override
    public void onGetContactsUsFailed(String message) {
        iContactsUs.onGetContactUsFailedResponseFromPresenter(message);

    }

}
