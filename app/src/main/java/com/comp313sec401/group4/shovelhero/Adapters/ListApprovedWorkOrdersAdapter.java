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

//import com.comp313sec401.group4.shovelhero.ApprovedWorkOrderActivity;
import com.comp313sec401.group4.shovelhero.ApproveWorkOrderActivity;
import com.comp313sec401.group4.shovelhero.R;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aruna Ravi Kumar
 * @Description: This adapter works by adapting recyclerview items to display approved work orders.
 * @Date: 10/29/2024
 */
public class ListApprovedWorkOrdersAdapter extends RecyclerView.Adapter<ListApprovedWorkOrdersAdapter.MyViewHolder> {

    private final List<WorkOrder> approvedWorkOrderList;
    private final Context context;

    public ListApprovedWorkOrdersAdapter(Context context, List<WorkOrder> apworkOrders) {
        this.context = context;
        // Filter for only approved work orders
        this.approvedWorkOrderList = new ArrayList<>();
        for (WorkOrder order : apworkOrders) {
            if ("approved".equalsIgnoreCase(order.getStatus())) {
                this.approvedWorkOrderList.add(order);
            }
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView requestDate, sqrft, status;
        public Button btnView;

        public MyViewHolder(View view) {
            super(view);

            requestDate = view.findViewById(R.id.tvRequestDate);
            sqrft = view.findViewById(R.id.tvSquareFootage);
            status = view.findViewById(R.id.tvStatus);
            btnView = view.findViewById(R.id.btnView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.work_order_item_adapter, parent, false);
        return new ListApprovedWorkOrdersAdapter.MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ListApprovedWorkOrdersAdapter.MyViewHolder holder, int position) {
        WorkOrder order = approvedWorkOrderList.get(position);

        holder.requestDate.setText(String.format("Requested Date: %s", order.getRequestDate()));
        holder.sqrft.setText(String.format("Square Foot of Property: %d", order.getSquareFootage()));
        holder.status.setText(String.format("Status: %s", order.getStatus()));

        Log.d("Debugging", "Approved Work Order: " + order.getWorkOrderId());

        holder.btnView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ApproveWorkOrderActivity.class);
            Log.d("Debugging", "Viewing approved order: " + order.getWorkOrderId());
            intent.putExtra("order", order);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return approvedWorkOrderList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<WorkOrder> newWorkOrders) {
        approvedWorkOrderList.clear();
        for (WorkOrder order : newWorkOrders) {
            if ("approved".equalsIgnoreCase(order.getStatus())) {
                approvedWorkOrderList.add(order);
            }
        }
        notifyDataSetChanged();
    }
}
