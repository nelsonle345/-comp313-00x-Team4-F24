package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp313sec401.group4.shovelhero.Adapters.ListAllWorkOrdersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListAllWorkOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_work_orders);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workorder");

        RecyclerView allOrdersView = findViewById(R.id.listAllWorkOrders);
        allOrdersView.setLayoutManager(new LinearLayoutManager(this));

        List<WorkOrder> workOrders = new ArrayList<>();
        ListAllWorkOrdersAdapter adapter = new ListAllWorkOrdersAdapter(this, workOrders);

        allOrdersView.setAdapter(adapter);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workOrders.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    WorkOrder workOrder = dataSnapshot.getValue(WorkOrder.class);
                    if(workOrder != null && workOrder.getStatus().equals("Open")) {
                        workOrders.add(workOrder);
                    }
                }

                adapter.notifyDataSetChanged();
                allOrdersView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Error fetching orders: " + error);
            }
        });
    }
}
