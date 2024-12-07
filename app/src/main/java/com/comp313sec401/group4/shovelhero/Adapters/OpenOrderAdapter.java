package com.comp313sec401.group4.shovelhero.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.comp313sec401.group4.shovelhero.Models.WorkOrder;

import com.comp313sec401.group4.shovelhero.R;

import java.util.ArrayList;
public class OpenOrderAdapter extends RecyclerView.Adapter<OpenOrderAdapter.MyViewHolder> {
    Context context;
    ArrayList<WorkOrder> list;

    public OpenOrderAdapter(Context context, ArrayList<WorkOrder> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.openorder_adapter, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenOrderAdapter.MyViewHolder holder, int position) {
        WorkOrder wo = list.get(position);
        holder.userId.setText(String.valueOf(wo.getUserId()));
        holder.userAddress.setText(wo.getUserAddress());
        holder.workorderid.setText(String.valueOf(wo.getWorkOrderId()));
        holder.status.setText(wo.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userId, userAddress, workorderid, status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            userAddress = itemView.findViewById(R.id.userAddress);
            workorderid = itemView.findViewById(R.id.workorderid);
            status = itemView.findViewById(R.id.status);
        }
    }
}