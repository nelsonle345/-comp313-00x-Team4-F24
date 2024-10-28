package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AcceptOpenWorkActivity extends AppCompatActivity {

    private TextView workOrderIdTextView, workOrderDescriptionTextView, workOrderLocationTextView, workOrderPriorityTextView;
    private EditText commentsEditText;
    private Button acceptButton, declineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_open_work);

        workOrderIdTextView = findViewById(R.id.workOrderId);
        workOrderDescriptionTextView = findViewById(R.id.workOrderDescription);
        workOrderLocationTextView = findViewById(R.id.workOrderLocation);
        workOrderPriorityTextView = findViewById(R.id.workOrderPriority);
        commentsEditText = findViewById(R.id.etComments);
        acceptButton = findViewById(R.id.acceptButton);
        declineButton = findViewById(R.id.declineButton);

        String workOrderId = "12345";
        String description = "Snow removal for driveway and walkway.";
        String location = "123 Main Street, Toronto";
        String priority = "Urgent";

        workOrderIdTextView.setText("Work Order ID: " + workOrderId);
        workOrderDescriptionTextView.setText("Description: " + description);
        workOrderLocationTextView.setText("Location: " + location);
        workOrderPriorityTextView.setText("Priority: " + priority);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = commentsEditText.getText().toString().trim();
                Toast.makeText(AcceptOpenWorkActivity.this, "Work Order Accepted", Toast.LENGTH_SHORT).show();
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcceptOpenWorkActivity.this, "Work Order Declined", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
