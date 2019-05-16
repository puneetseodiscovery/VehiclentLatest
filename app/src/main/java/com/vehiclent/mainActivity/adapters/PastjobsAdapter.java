package com.vehiclent.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vehiclent.R;
import com.vehiclent.pastJobDetails.PastJobDetailsActivity;
import com.vehiclent.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastjobsAdapter extends RecyclerView.Adapter<PastjobsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public PastjobsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_past_jobs, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PastJobDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_jobsname)
        TextView tv_jobsname;

        @BindView(R.id.tv_jobstime)
        TextView tv_jobstime;
        @BindView(R.id.tv_car_care)
        TextView tv_car_care;

        @BindView(R.id.btn_details)
        Button btn_details;


        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_jobsname.setTypeface(Utility.typeFaceForBoldText(context));
            tv_jobstime.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_car_care.setTypeface(Utility.typeFaceForRegulerText(context));
            btn_details.setTypeface(Utility.typeFaceForRegulerText(context));


        }
    }


}
