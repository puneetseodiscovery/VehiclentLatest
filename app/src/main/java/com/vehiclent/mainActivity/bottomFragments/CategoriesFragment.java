package com.vehiclent.mainActivity.bottomFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.mainActivity.adapters.CategoriesAdapter;
import com.vehiclent.utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    TextView tv_categories;
    RecyclerView categories_recyclerview;
    CategoriesAdapter categoriesAdapter;
    View view;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categories, container, false);

        categories_recyclerview = (RecyclerView) view.findViewById(R.id.categories_recyclerview);
        tv_categories = (TextView) view.findViewById(R.id.tv_categories);

        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));

        categoriesAdapter = new CategoriesAdapter(getActivity());
        categories_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        categories_recyclerview.setAdapter(categoriesAdapter);

        return view;
    }

}
