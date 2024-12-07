package com.comp313sec401.group4.shovelhero;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp313sec401.group4.shovelhero.Adapters.OpenOrderAdapter;
import com.comp313sec401.group4.shovelhero.Adapters.TempAdapter;
import com.comp313sec401.group4.shovelhero.Models.User;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class OpenOrderActivity_ar extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        ArrayList<WorkOrder> list;
        DatabaseReference databaseReference;
        OpenOrderAdapter adapter;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_openworkorder_ar);

        recyclerView = findViewById(R.id.recyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("workorder");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OpenOrderAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    WorkOrder wo = dataSnapshot.getValue(WorkOrder.class);
                    if (wo != null && "Open".equalsIgnoreCase(wo.getStatus())) {
                        list.add(wo);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
