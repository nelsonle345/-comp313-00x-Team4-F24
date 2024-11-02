package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.GuardianApprovalRequests;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestGuardianApprovalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_approval_request);

        WorkOrder order = (WorkOrder) getIntent().getParcelableExtra("order");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("guardian_approval_requests");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    GuardianApprovalRequests guardianApproval = dataSnapshot.getValue(GuardianApprovalRequests.class);
                    if(guardianApproval != null) {
                        TextView txtGuardianApprovalStatus = findViewById(R.id.txtGuardianApprovalStatus);
                        txtGuardianApprovalStatus.setText(guardianApproval.getStatus());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Error fetching orders: " + error);
            }
        });
    }
}
