package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.comp313sec401.group4.shovelhero.Adapters.ListAllWorkOrdersAdapter;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.comp313sec401.group4.shovelhero.databinding.ActivityViewApprovedWorkOrderBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewApprovedWorkOrderActivity extends AppCompatActivity {

  //  private AppBarConfiguration appBarConfiguration;
  //  private ActivityViewApprovedWorkOrderBinding binding;
  List<WorkOrder> apprdworkOrders = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_all_work_orders);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workorder");

        RecyclerView allOrdersView = findViewById(R.id.listAllWorkOrders);
        allOrdersView.setLayoutManager(new LinearLayoutManager(this));

        ListAllWorkOrdersAdapter adapter = new ListAllWorkOrdersAdapter(this, apprdworkOrders);

        allOrdersView.setAdapter(adapter);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                apprdworkOrders.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkOrder workOrder = dataSnapshot.getValue(WorkOrder.class);
                    // Check for "Approved" status instead of "Open"
                    if (workOrder != null && workOrder.getStatus().equals("Approved")) {
                        apprdworkOrders.add(workOrder);
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

        Button btnRefresh = findViewById(R.id.refreshButton);
        btnRefresh.setOnClickListener(view -> {
            loadData(ref);
        });
//        binding = ActivityViewApprovedWorkOrderBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_view_approved_work_order);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    private void loadData(DatabaseReference ref) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                apprdworkOrders.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkOrder order = dataSnapshot.getValue(WorkOrder.class);
                    // Check for "Approved" status instead of "Open"
                    if (order != null && order.getStatus().equals("Approved")) {
                        apprdworkOrders.add(order);
                    }
                }
                // Notify adapter of data changes after refreshing
          //      adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Error refreshing orders: " + error);
            }
        });
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_view_approved_work_order);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}