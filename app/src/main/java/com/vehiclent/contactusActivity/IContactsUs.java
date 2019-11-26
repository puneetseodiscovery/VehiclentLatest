package com.vehiclent.contactusActivity;

import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;

public interface IContactsUs {

    void onContactsUsResponseFromPresenter(int statusValue);
    void onContactsUsSuccessResponseFromPresenter(ContactsUsResponseModel contactsUsResponseModel);
    void onContactsUsFailedResponseFromPresenter(String message);


    void onGetContactUsResponseFromPresenter(int statusValue);
    void onGetContactUsSuccessResponseFromPresenter(GetContactsusResponseModel getContactsusResponseModel);
    void onGetContactUsFailedResponseFromPresenter(String message);
}
