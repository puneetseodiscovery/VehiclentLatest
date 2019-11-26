package com.vehiclent.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vehiclent.R;
import com.vehiclent.carCareActivity.CarCareActivity;
import com.vehiclent.responseModelClasses.ServicesResponseModel;
import com.vehiclent.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<ServicesResponseModel.Datum> servicesResponseModelArrayList;

    public CategoriesAdapter(Context context, List<ServicesResponseModel.Datum> servicesResponseModelArrayList) {
        this.context = context;
        this.servicesResponseModelArrayList = servicesResponseModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_car_categories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

      /*  Glide.with(context).load(servicesResponseModelArrayList.get(position).getServiceImage())
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.placeholder)
                .into(holder.img_vehical);
*/

        Glide.with(context).load(servicesResponseModelArrayList.get(position).getServiceImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.img_vehical);

        holder.tv_vehicel_name.setText(servicesResponseModelArrayList.get(position).getServiceName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarCareActivity.class);
                intent.putExtra("service_Name",servicesResponseModelArrayList.get(position).getServiceName());
                intent.putExtra("service_id",servicesResponseModelArrayList.get(position).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesResponseModelArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_vehicel_name)
        TextView tv_vehicel_name;

        @BindView(R.id.img_vehical)
        ImageView img_vehical;


        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_vehicel_name.setTypeface(Utility.typeFaceForRegulerText(context));
        }
    }
}
