package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;

public class AcceptOpenWorkActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_open_work);

        WorkOrder order = (WorkOrder) getIntent().getParcelableExtra("order");
        if(order != null) {
            Log.d("Debugging", "Order: " + order.getWorkOrderId());

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

        Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: This should create a new guardian_approval_request with youthId, guardianId (of youth), propertyId, workorderId.
                // This should be sent to guardian_approval_request table in Firebase.
                String comments = commentsEditText.getText().toString().trim();
                Toast.makeText(AcceptOpenWorkActivity.this, "Work Order Accepted", Toast.LENGTH_SHORT).show();
            }
        });

        Button declineButton = findViewById(R.id.declineButton);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcceptOpenWorkActivity.this, "Work Order Declined", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
