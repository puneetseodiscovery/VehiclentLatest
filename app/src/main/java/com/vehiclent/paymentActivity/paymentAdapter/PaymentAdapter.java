package com.vehiclent.paymentActivity.paymentAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.carCareActivity.CarCareActivity;
import com.vehiclent.cardActivity.CardActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public PaymentAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_payment, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return 5;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_card_name)
        TextView tv_card_name;

        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_card_name.setTypeface(Utility.typeFaceForRegulerText(context));


        }
    }


}
