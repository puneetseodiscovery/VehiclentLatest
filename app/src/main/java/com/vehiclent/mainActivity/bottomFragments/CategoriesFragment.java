package com.vehiclent.mainActivity.bottomFragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vehiclent.R;
import com.vehiclent.mainActivity.adapters.CategoriesAdapter;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.ICategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.ICategoryFragment.IPCategoryFragment;
import com.vehiclent.mainActivity.bottomFragments.Presenters.PCategoryFragment;
import com.vehiclent.responseModelClasses.ServicesResponseModel;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;
public class CategoriesFragment extends Fragment implements ICategoryFragment {

    TextView tv_categories;
    ImageView no_record_found;
    RecyclerView categories_recyclerview;
    CategoriesAdapter categoriesAdapter;
    View view;
    Context context;
    SavePref savePref;
    IPCategoryFragment ipCategoryFragment;
    ProgressDialog progressDialog;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categories, container, false);
        context = this.getContext();
        savePref = new SavePref(context);
        progressDialog = new ProgressDialog(context);
        ipCategoryFragment = new PCategoryFragment(this);
        categories_recyclerview = (RecyclerView) view.findViewById(R.id.categories_recyclerview);
        tv_categories = (TextView) view.findViewById(R.id.tv_categories);
        no_record_found = (ImageView) view.findViewById(R.id.no_record_found);
        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));


        if (Utility.isNetworkConnected(context)){
            progressDialog=Utility.showLoader(context);
            ipCategoryFragment.doGetServicesList(savePref.getid());

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onServicesFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onServicesSuccessFromPresenter(ServicesResponseModel servicesResponseModel) {

        progressDialog.dismiss();
        if (servicesResponseModel != null && servicesResponseModel.getData().size() > 0) {

            no_record_found.setVisibility(View.GONE);
            categoriesAdapter = new CategoriesAdapter(context, servicesResponseModel.getData());
            categories_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            categories_recyclerview.setAdapter(categoriesAdapter);

        } else {
            no_record_found.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onServicesFailedFromPresenter(String messge) {
        progressDialog.dismiss();
      //  Toast.makeText(context, "" + messge, Toast.LENGTH_SHORT).show();
    }
}
