package com.vehiclent.contactusActivity;


import com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import com.vehiclent.responseModelClasses.GetContactsusResponseModel;

public interface IPContactsUs {

    void doContactsUs(String email, String message);

    void onContactsUsResponseFromModel(int statusValue);

    void onContactsUsSucess(ContactsUsResponseModel contactsUsResponseModel);

    void onContactsUsFailed(String message);


    void doGetContactsUs(String id);

    void onGetContactsUsResponseFromModel(int statusValue);

    void onGetContactsUsSucess(GetContactsusResponseModel getContactsusResponseModel);

    void onGetContactsUsFailed(String message);


}
