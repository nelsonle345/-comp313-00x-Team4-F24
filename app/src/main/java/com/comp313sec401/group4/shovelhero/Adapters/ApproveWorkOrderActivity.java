package com.comp313sec401.group4.shovelhero.Adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.comp313sec401.group4.shovelhero.databinding.ActivityViewApprovedWorkOrderBinding;
import com.comp313sec401.group4.shovelhero.R;

public class ApproveWorkOrderActivity extends AppCompatActivity {


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approved_work_order); // Update layout file for approved orders

        WorkOrder order = (WorkOrder) getIntent().getParcelableExtra("order");
        if(order != null) {
            Log.d("Debugging", "Approved Order: " + order.getWorkOrderId());

            TextView txtWorkId = findViewById(R.id.workOrderId);
            TextView txtInstructions = findViewById(R.id.workOrderDescription);
            TextView txtLocation = findViewById(R.id.workOrderLocation);
            TextView txtUrgency = findViewById(R.id.workOrderPriority);

            txtWorkId.setText(String.format("Work Order Id: %d", order.getWorkOrderId()));
            txtInstructions.setText(String.format("Description: %s", order.getInstructions()));
            txtLocation.setText(String.format("Location: %s", order.getUserAddress()));
            txtUrgency.setText(String.format("Priority: %s", order.getUrgency()));
        }

        EditText commentsEditText = findViewById(R.id.etComments);

        Button approveButton = findViewById(R.id.approveButton);
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = commentsEditText.getText().toString().trim();
                Toast.makeText(ApproveWorkOrderActivity.this, "Work Order Approved", Toast.LENGTH_SHORT).show();
            }
        });


    }
}