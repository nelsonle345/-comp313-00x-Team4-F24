package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOpenWorkOrder extends AppCompatActivity {
    private RecyclerView workOrderRecyclerView;
    private WorkOrderAdapter workOrderAdapter;
    private ArrayList<WorkOrderModel> workOrderList = new ArrayList<>();
    private ArrayList<WorkOrderModel> originalWorkOrderList = new ArrayList<>();
    private Button filterButton, sortStatusButton, resetButton;
    private CheckBox cbHigh, cbMedium, cbLow;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_open_work_order);

        // Initialize RecyclerView and Buttons
        workOrderRecyclerView = findViewById(R.id.workOrderRecyclerView);
        filterButton = findViewById(R.id.filterButton);
        sortStatusButton = findViewById(R.id.sortStatusButton);
        resetButton = findViewById(R.id.resetButton);
        searchEditText = findViewById(R.id.searchEditText);

        // Initialize CheckBoxes
        cbHigh = findViewById(R.id.cbHigh);
        cbMedium = findViewById(R.id.cbMedium);
        cbLow = findViewById(R.id.cbLow);

        // Set up RecyclerView and Adapter
        workOrderAdapter = new WorkOrderAdapter(this, workOrderList);
        workOrderRecyclerView.setAdapter(workOrderAdapter);
        workOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadWorkOrders();

        // Set up button click listeners
        filterButton.setOnClickListener(view -> filterByUrgency());
        sortStatusButton.setOnClickListener(view -> sortByStatus());
        resetButton.setOnClickListener(view -> resetFilters());

        // Set up TextWatcher for the search bar
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBySearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadWorkOrders() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("workorder");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                originalWorkOrderList.clear();
                workOrderList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkOrderModel workOrder = dataSnapshot.getValue(WorkOrderModel.class);
                    if (workOrder != null) {
                        originalWorkOrderList.add(workOrder);
                        workOrderList.add(workOrder);
                    }
                }
                workOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListOpenWorkOrder.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterBySearch(String query) {
        workOrderList.clear();
        if (query.isEmpty()) {
            workOrderList.addAll(originalWorkOrderList);
        } else {
            for (WorkOrderModel item : originalWorkOrderList) {
                if (item.getAddress().toLowerCase().contains(query.toLowerCase()) ||
                        item.getInstructions().toLowerCase().contains(query.toLowerCase())) {
                    workOrderList.add(item);
                }
            }
        }
        workOrderAdapter.notifyDataSetChanged();
    }

    private void filterByUrgency() {
        ArrayList<String> selectedUrgencies = new ArrayList<>();
        if (cbHigh.isChecked()) selectedUrgencies.add("high");
        if (cbMedium.isChecked()) selectedUrgencies.add("medium");
        if (cbLow.isChecked()) selectedUrgencies.add("low");

        workOrderList.clear();
        for (WorkOrderModel item : originalWorkOrderList) {
            if (selectedUrgencies.contains(item.getUrgency().toLowerCase())) {
                workOrderList.add(item);
            }
        }
        workOrderAdapter.notifyDataSetChanged();
    }

    private void sortByStatus() {
        Collections.sort(workOrderList, (o1, o2) -> {
            int value1 = getUrgencyValue(o1.getUrgency());
            int value2 = getUrgencyValue(o2.getUrgency());
            System.out.println("Sorting: " + value1 + " vs " + value2);
            return Integer.compare(value2, value1);
        });

        workOrderAdapter.notifyDataSetChanged();
    }

    private int getUrgencyValue(String urgency) {
        switch (urgency.toLowerCase()) {
            case "high":
                return 3;
            case "medium":
                return 2;
            case "low":
                return 1;
            default:
                return 0;
        }
    }

    private void resetFilters() {
        workOrderList.clear();
        workOrderList.addAll(originalWorkOrderList);
        cbHigh.setChecked(false);
        cbMedium.setChecked(false);
        cbLow.setChecked(false);
        searchEditText.setText("");
        workOrderAdapter.notifyDataSetChanged();
    }
}
