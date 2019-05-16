package com.vehiclent.paymentActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.amout_details.AmountDetails;
import com.vehiclent.cardActivity.CardActivity;
import com.vehiclent.mainActivity.adapters.CategoriesAdapter;
import com.vehiclent.paymentActivity.paymentAdapter.PaymentAdapter;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    PaymentActivity context;
    //PaymentAdapter paymentAdapter;

    @BindView(R.id.relative_debitcard)
    RelativeLayout relative_debitcard;


    @BindView(R.id.relative_payforu)
    RelativeLayout relative_payforu;


    @BindView(R.id.img_paymentback)
    ImageView img_paymentback;

    @BindView(R.id.tv_payment)
    TextView tv_payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        context = PaymentActivity.this;
        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {

        tv_payment.setTypeface(Utility.typeFaceForBoldText(this));

       /* paymentAdapter = new PaymentAdapter(context);
        payment_recyclerview.setLayoutManager(new LinearLayoutManager(context));
        payment_recyclerview.setAdapter(paymentAdapter);
*/

    }

    private void EventListner() {

        img_paymentback.setOnClickListener(this);
        relative_debitcard.setOnClickListener(this);
        relative_payforu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_paymentback:
                finish();
                break;

            case R.id.relative_debitcard:
                Intent intent = new Intent(context, CardActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_payforu:
                Intent intent2 = new Intent(context, AmountDetails.class);
                startActivity(intent2);
                break;
        }
    }
}
