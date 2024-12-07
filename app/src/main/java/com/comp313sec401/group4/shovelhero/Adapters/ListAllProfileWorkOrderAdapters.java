package com.comp313sec401.group4.shovelhero.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp313sec401.group4.shovelhero.AcceptOpenWorkActivity;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.comp313sec401.group4.shovelhero.R;
import com.comp313sec401.group4.shovelhero.UpdateJobProgressActivity;

import java.util.List;

public class ListAllProfileWorkOrderAdapters extends RecyclerView.Adapter<ListAllProfileWorkOrderAdapters.MyViewHolder>{

    private final List<WorkOrder> workOrderList;
    private final Context context;

    public ListAllProfileWorkOrderAdapters(Context context, List<WorkOrder> workOrders) {
        this.context = context;
        this.workOrderList = workOrders;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView requestDate, sqrft, status;
        public Button btnView, btnRequestGuardian;


        public MyViewHolder(View view) {
            super(view);

            requestDate = view.findViewById(R.id.tvRequestDate);
            sqrft = view.findViewById(R.id.tvSquareFootage);
            status = view.findViewById(R.id.tvStatus);

            btnView = view.findViewById(R.id.btnView);
            btnRequestGuardian = view.findViewById(R.id.btnRequestGuardian);
        }
    }


    @NonNull
    @Override
    public ListAllProfileWorkOrderAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_profile_workorder_adapter, parent, false);
        return new ListAllProfileWorkOrderAdapters.MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ListAllProfileWorkOrderAdapters.MyViewHolder holder, int position) {
        WorkOrder order = workOrderList.get(position);

        holder.requestDate.setText(String.format("Requested Date: %s", order.getRequestDate()));
        holder.sqrft.setText(String.format("Square Foot of Property: %d", order.getSquareFootage()));
        holder.status.setText(String.format("Status: %s", order.getStatus()));

        Log.d("Debugging", "Work Order: " + order.getWorkOrderId());

        holder.btnView.setOnClickListener(view -> {

            Intent intent = new Intent(context, UpdateJobProgressActivity.class);
            // Log.d("Debugging", "This is order: " + order.getWorkOrderId() + ", " + order.getSquareFootage());
            intent.putExtra("order", order);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<WorkOrder> newWorkOrders) {
        workOrderList.clear();
        workOrderList.addAll(newWorkOrders);
        notifyDataSetChanged();
    }
}
