package com.vehiclent.termServicesActivity;

import android.os.Message;

import com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;


public class ModelTermCondition implements  IMTermsConditions{
    IPTermsCondition ipTermsCondition;

    public ModelTermCondition(PTermsCondition pTermsCondition) {

        this.ipTermsCondition = pTermsCondition;
    }

    @Override
    public void termconditionRestCall(String id) {

        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.term_conditions(id,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.TERMCONDITION_SUCCESS:
                    TermsConditionsResponseModel termsConditionsResponseModel = ((TermsConditionsResponseModel) msg.obj);
                    ipTermsCondition.onTermsConditionSucess(termsConditionsResponseModel);
                    break;

                case APIInterface.TERMCONDITION_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipTermsCondition.onTermsConditionFailed(mesFailed);
                    break;
            }
        }
    };
}
