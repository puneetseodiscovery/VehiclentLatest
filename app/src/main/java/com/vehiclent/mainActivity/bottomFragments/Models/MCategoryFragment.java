package com.vehiclent.mainActivity.bottomFragments.Models;
import android.os.Handler;
import android.os.Message;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.IMCategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.IPCategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PCategoryFragment;
import com.vehiclent.responseModelClasses.ServicesResponseModel;
import com.vehiclent.retrofitCalls.APIInterface;
import com.vehiclent.retrofitCalls.RetrofitCalls;

public class MCategoryFragment implements IMCategoryFragment {

    IPCategoryFragment ipCategoryFragment;

    public MCategoryFragment(PCategoryFragment pCategoryFragment) {
        this.ipCategoryFragment = pCategoryFragment;
    }

    @Override
    public void getServicesListRestCall(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.serviceListing(id, mHandler);

    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.SERVICE_LIST_SUCCESS:
                    ServicesResponseModel servicesResponseModel = ((ServicesResponseModel) msg.obj);
                    ipCategoryFragment.onServicesListSuccessResponseFromModel(servicesResponseModel);
                    break;

                case APIInterface.SERVICE_LIST_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipCategoryFragment.onServicesListFaildResponseFromModel(mesFailed);
                    break;
            }
        }
    };
}
