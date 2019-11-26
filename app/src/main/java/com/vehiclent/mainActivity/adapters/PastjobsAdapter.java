package com.vehiclent.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vehiclent.R;
import com.vehiclent.pastJobDetails.PastJobDetailsActivity;
import com.vehiclent.responseModelClasses.PastJobListResponseModel;
import com.vehiclent.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastjobsAdapter extends RecyclerView.Adapter<PastjobsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    List<PastJobListResponseModel.Datum> pastjobresponseModelList;

    public PastjobsAdapter(Context context, List<PastJobListResponseModel.Datum> pastjobresponseModelList) {
        this.context = context;
        this.pastjobresponseModelList = pastjobresponseModelList;
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

        if (pastjobresponseModelList.get(position).getYourProfile().isEmpty() || pastjobresponseModelList.get(position).getYourProfile().equals("")) {
            Glide.with(context).load(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.img_vehical);
        } else {

            Glide.with(context).load(pastjobresponseModelList.get(position).getYourProfile())
                    .into(holder.img_vehical);
        }
        holder.tv_jobsname.setText(pastjobresponseModelList.get(position).getFirstName() + " " + pastjobresponseModelList.get(position).getLastName());
        holder.tv_jobstime.setText(pastjobresponseModelList.get(position).getCreateddate());
        holder.tv_car_care.setText(pastjobresponseModelList.get(position).getServiceName());

        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PastJobDetailsActivity.class);
                intent.putExtra("job_id", pastjobresponseModelList.get(position).getId());
                intent.putExtra("job_image", pastjobresponseModelList.get(position).getYourProfile());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return pastjobresponseModelList.size();
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

        @BindView(R.id.img_vehical)
        ImageView img_vehical;


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
