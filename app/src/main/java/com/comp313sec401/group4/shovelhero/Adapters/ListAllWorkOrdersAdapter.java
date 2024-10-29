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
import com.comp313sec401.group4.shovelhero.R;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.comp313sec401.group4.shovelhero.ListAllWorkOrders;
import com.comp313sec401.group4.shovelhero.ViewRequestedWorkOrderActivity;

import java.util.List;


/**
 * @Author: Benton Le
 * @Description: This adapter works by adapting recyclerview items to a specific format.
 * @Date: 10/28/2024
 */
public class ListAllWorkOrdersAdapter extends RecyclerView.Adapter<ListAllWorkOrdersAdapter.MyViewHolder>{

    private final List<WorkOrder> workOrderList;
    private final Context context;

    public ListAllWorkOrdersAdapter(Context context, List<WorkOrder> workOrders) {
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.work_order_item_adapter, parent, false);
        return new ListAllWorkOrdersAdapter.MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ListAllWorkOrdersAdapter.MyViewHolder holder, int position) {
        WorkOrder order = workOrderList.get(position);

        holder.requestDate.setText(String.format("Requested Date: %s", order.getRequestDate()));
        holder.sqrft.setText(String.format("Square Foot of Property: %d", order.getSquareFootage()));
        holder.status.setText(String.format("Status: %s", order.getStatus()));

        Log.d("Debugging", "Work Order: " + order.getWorkOrderId());

        holder.btnView.setOnClickListener(view -> {

            Intent intent = new Intent(context, AcceptOpenWorkActivity.class);
            Log.d("Debugging", "This is order: " + order.getWorkOrderId() + ", " + order.getSquareFootage());
            intent.putExtra("order", order);
            context.startActivity(intent);

        });

        holder.btnRequestGuardian.setOnClickListener(view -> {

            Intent intent = new Intent(context, AcceptOpenWorkActivity.class);
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
