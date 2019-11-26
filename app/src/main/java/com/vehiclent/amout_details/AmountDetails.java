package com.vehiclent.amout_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.startPayment.StartPaymentActivity;

public class AmountDetails extends BaseClass {
    EditText phone, amount;

    String jobid = "", service_price = "", user_name = "", phone_number = "",email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_details);
        // Lasted
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        jobid = getIntent().getStringExtra("jobid");
        service_price = getIntent().getStringExtra("service_price");
        user_name = getIntent().getStringExtra("user_name");
        phone_number = getIntent().getStringExtra("phone_number");
        email = getIntent().getStringExtra("email");

        Button btn = (Button) findViewById(R.id.start_transaction);
        phone = (EditText) findViewById(R.id.phone);
        amount = (EditText) findViewById(R.id.amountid);
        amount.setText(service_price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AmountDetails.this, StartPaymentActivity.class);
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("amount", amount.getText().toString());
                intent.putExtra("user_name", user_name);
                intent.putExtra("phone_number", phone_number);
                intent.putExtra("jobid", jobid);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }

}
