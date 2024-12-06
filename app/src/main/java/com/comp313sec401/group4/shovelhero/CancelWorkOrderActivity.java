package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CancelWorkOrderActivity extends AppCompatActivity {
    private TextView workOrderIdTextView, cancellationDisabledTextView;
    private EditText cancellationReasonEditText;
    private Button cancelButton, backButton;
    private DatabaseReference workOrderDatabase;
    private String workOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_work_order);

        workOrderIdTextView = findViewById(R.id.tvWorkOrderId);
        cancellationReasonEditText = findViewById(R.id.etCancellationReason);
        cancelButton = findViewById(R.id.btnCancelWorkOrder);
        backButton = findViewById(R.id.btnBack);
        cancellationDisabledTextView = findViewById(R.id.tvCancellationDisabled);

        workOrderDatabase = FirebaseDatabase.getInstance().getReference("workorder");

        // Get data from intent
       /* Intent intent = getIntent();
        workOrderId = intent.getStringExtra("workOrderId");
        if (workOrderId == null || workOrderId.isEmpty()) {
            Toast.makeText(this, "Invalid Work Order ID", Toast.LENGTH_SHORT).show();
            return;
        }*/
        
        workOrderId = "wo5";
        /*workOrderId = "wo5";
        workOrderId = "wo6";
        workOrderId = "wo7";*/
        workOrderIdTextView.setText("Work Order ID: " + workOrderId);
        checkArrivalStatus();

        cancelButton.setOnClickListener(view -> cancelWorkOrder());
        backButton.setOnClickListener(view -> finish());
    }

    private void checkArrivalStatus() {
        cancelButton.setEnabled(false);

        DatabaseReference isArrivedRef = workOrderDatabase.child(workOrderId).child("isArrived");
        isArrivedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isArrived = snapshot.getValue(Boolean.class);
                if (isArrived == null) {
                    isArrived = false;
                }

                if (isArrived) {
                    cancellationDisabledTextView.setVisibility(View.GONE);
                    cancelButton.setEnabled(true);
                } else {
                    cancellationDisabledTextView.setVisibility(View.VISIBLE);
                    cancelButton.setEnabled(false);
                    cancellationDisabledTextView.setText("Cancellation is not allowed before arriving on site.");
                }
            }

        @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CancelWorkOrderActivity.this, "Error checking arrival status.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cancelWorkOrder() {
        if (workOrderId == null) {
            Toast.makeText(this, "Work order ID is missing.", Toast.LENGTH_SHORT).show();
            return;
        }
        String cancellationReason = cancellationReasonEditText.getText().toString().trim();
        workOrderDatabase.child(workOrderId).child("status").setValue("Cancelled");
        workOrderDatabase.child(workOrderId).child("cancellationReason")
                .setValue(cancellationReason.isEmpty() ? "No reason provided" : cancellationReason);
        Toast.makeText(this, "Work order cancelled successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CancelWorkOrderActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }
}
