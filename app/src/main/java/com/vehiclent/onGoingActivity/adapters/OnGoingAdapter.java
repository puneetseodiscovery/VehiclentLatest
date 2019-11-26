package com.vehiclent.onGoingActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.vehiclent.R;
import com.vehiclent.partnerProfileActivity.PartnerProfileActivity;
import com.vehiclent.responseModelClasses.OnGoingResponseModel;
import com.vehiclent.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OnGoingAdapter extends RecyclerView.Adapter<OnGoingAdapter.MyViewHolder> {

    Context context;
    List<OnGoingResponseModel.Datum> ongoinglist;

    public OnGoingAdapter(Context context, List<OnGoingResponseModel.Datum> ongoinglist) {

        this.context = context;
        this.ongoinglist = ongoinglist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_ongoing, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int position) {

        Glide.with(context).load(ongoinglist.get(position).getYourProfile())
                .error(R.drawable.no_image)
                .into(myViewHolder.img_vehical);

        myViewHolder.tv_jobsname.setText(ongoinglist.get(position).getFirstName() + " " + ongoinglist.get(position).getLastName());
        myViewHolder.tv_jobstime.setText(ongoinglist.get(position).getCreateddate());
        myViewHolder.tv_car_care.setText(ongoinglist.get(position).getServiceName());
       // myViewHolder.tv_car_model.setText("Car Model : " + upcomingItemList.get(position).getServiceName());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PartnerProfileActivity.class);
                intent.putExtra("jobid", ongoinglist.get(0).getId());
                intent.putExtra("partner_id", ongoinglist.get(0).getPartnerid());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return ongoinglist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_jobsname)
        TextView tv_jobsname;

        @BindView(R.id.tv_jobstime)
        TextView tv_jobstime;

        @BindView(R.id.tv_car_care)
        TextView tv_car_care;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_car_model)
        TextView tv_car_model;

        @BindView(R.id.img_vehical)
        CircleImageView img_vehical;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tv_jobsname.setTypeface(Utility.typeFaceForBoldText(context));
            tv_jobstime.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_car_care.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_car_model.setTypeface(Utility.typeFaceForRegulerText(context));
            status.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_status.setTypeface(Utility.typeFaceForRegulerText(context));
        }
    }
}
