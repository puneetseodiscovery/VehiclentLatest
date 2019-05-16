package com.vehiclent.mainActivity.bottomFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    View view;

    @BindView(R.id.tv_userprofile)
    TextView tv_userprofile;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.mobile)
    TextView mobile;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.edit_user_name)
    EditText edit_user_name;

    @BindView(R.id.edit_user_email)
    EditText edit_user_email;

    @BindView(R.id.edit_user_mobile)
    EditText edit_user_mobile;

    @BindView(R.id.edit_user_address)
    EditText edit_user_address;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.img_done)
    ImageView img_done;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        Initialization();
        return view;
    }


    private void Initialization() {

        tv_userprofile.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_username.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        edit_user_name.setEnabled(false);
        edit_user_email.setEnabled(false);
        edit_user_mobile.setEnabled(false);
        edit_user_address.setEnabled(false);



        EventListner();

    }

    private void EventListner() {

        img_edit.setOnClickListener(this);
        img_done.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_edit:

                img_edit.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);


                edit_user_name.setEnabled(true);
                edit_user_email.setEnabled(true);
                edit_user_mobile.setEnabled(true);
                edit_user_address.setEnabled(true);


                break;
            case R.id.img_done:

                img_done.setVisibility(View.GONE);
                img_edit.setVisibility(View.VISIBLE);

                edit_user_name.setEnabled(false);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(false);
                edit_user_address.setEnabled(false);

                break;
        }

    }
}
