package com.vehiclent.cardActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.paymnetSuccessful.PaymentSuccessfulActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.tv_card_name)
    TextView tv_card_name;

    @BindView(R.id.tv_visacard)
    TextView tv_visacard;

    @BindView(R.id.tv_mastercard)
    TextView tv_mastercard;

    @BindView(R.id.tv_cardnumber)
    TextView tv_cardnumber;

    @BindView(R.id.tv_cardexpiredate)
    TextView tv_cardexpiredate;

    @BindView(R.id.tv_cardcvv)
    TextView tv_cardcvv;

    @BindView(R.id.tv_cardholdername)
    TextView tv_cardholdername;

    @BindView(R.id.tv_iagree)
    TextView tv_iagree;

    @BindView(R.id.edit_cardNumber)
    EditText edit_cardNumber;

    @BindView(R.id.edit_cardexpiredate)
    EditText edit_cardexpiredate;

    @BindView(R.id.edit_cardcvv)
    EditText edit_cardcvv;

    @BindView(R.id.edit_cardname)
    EditText edit_cardname;

    @BindView(R.id.img_back_card)
    ImageView img_back_card;

    @BindView(R.id.btn_ok)
    Button btn_ok;

    @BindView(R.id.btn_verify)
    Button btn_verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        tv_card_name.setTypeface(Utility.typeFaceForBoldText(this));
        tv_visacard.setTypeface(Utility.typeFaceForBoldText(this));
        tv_mastercard.setTypeface(Utility.typeFaceForBoldText(this));
        tv_cardnumber.setTypeface(Utility.typeFaceForBoldText(this));
        tv_cardexpiredate.setTypeface(Utility.typeFaceForBoldText(this));
        tv_cardcvv.setTypeface(Utility.typeFaceForBoldText(this));
        tv_cardholdername.setTypeface(Utility.typeFaceForBoldText(this));
        tv_iagree.setTypeface(Utility.typeFaceForBoldText(this));
        edit_cardNumber.setTypeface(Utility.typeFaceForBoldText(this));
        edit_cardexpiredate.setTypeface(Utility.typeFaceForBoldText(this));
        edit_cardcvv.setTypeface(Utility.typeFaceForBoldText(this));
        edit_cardname.setTypeface(Utility.typeFaceForBoldText(this));


    }

    private void EventListner() {

        img_back_card.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_verify.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent=null;
        switch (v.getId()) {
            case R.id.img_back_card:
                finish();
                break;
            case R.id.btn_ok:

                break;
            case R.id.btn_verify:
                intent =new Intent(CardActivity.this, PaymentSuccessfulActivity.class);
                startActivity(intent);
                break;
        }
    }
}
