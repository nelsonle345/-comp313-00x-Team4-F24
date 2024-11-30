package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewRequestedWorkOrderActivity extends AppCompatActivity {

    private DatabaseReference userTable;
    private TextView requestWorkId, requestInstructions, requestLocation, requestUrgency, requestStatus, clientName, clientContact;
    private Button contactClientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requested_work_order);

        // Initialize Firebase reference
        userTable = FirebaseDatabase.getInstance().getReference("users");

        // Initialize UI elements
        requestWorkId = findViewById(R.id.requestedWorkOrderId);
        requestInstructions = findViewById(R.id.requestedWorkOrderDescription);
        requestLocation = findViewById(R.id.requestedWorkOrderLocation);
        requestUrgency = findViewById(R.id.requestedWorkOrderPriority);
        requestStatus = findViewById(R.id.requestedWorkOrderStatus);
        clientName = findViewById(R.id.clientName);
        clientContact = findViewById(R.id.clientContact);
        contactClientButton = findViewById(R.id.contactClientButton);

        // Retrieve WorkOrder object passed from the previous activity
        WorkOrder order = getIntent().getParcelableExtra("order");
        if (order != null) {
            Log.d("Debugging", "Order: " + order.getWorkOrderId());

            // Populate UI with WorkOrder details
            requestWorkId.setText("Work Order ID: " + order.getWorkOrderId());
            requestInstructions.setText("Instructions: " + order.getInstructions());
            requestLocation.setText("Location: " + order.getUserAddress());
            requestUrgency.setText("Urgency: " + order.getUrgency());
            requestStatus.setText("Status: " + order.getStatus());

            // Fetch client information
            fetchClientInfo(order.getRequestUser());
        } else {
            Toast.makeText(this, "No work order details available!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set up Contact Client button functionality
        contactClientButton.setOnClickListener(v -> {
            String contactInfo = clientContact.getText().toString();
            if (!contactInfo.isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + contactInfo));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Work Order " + order.getWorkOrderId());
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi " + clientName.getText().toString() + ",\n\n");

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                } else {
                    Toast.makeText(ViewRequestedWorkOrderActivity.this, "No email app installed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ViewRequestedWorkOrderActivity.this, "No contact information available for the client", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchClientInfo(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            Toast.makeText(this, "Client information is not available.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query the "users" table to fetch client details
        userTable.child(clientId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User client = dataSnapshot.getValue(User.class);
                if (client != null) {
                    clientName.setText(client.getFirstName() + " " + client.getLastName());
                    clientContact.setText(client.getPhoneNumber());
                } else {
                    Toast.makeText(ViewRequestedWorkOrderActivity.this, "Failed to load client information.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewRequestedWorkOrderActivity.this, "Error fetching client info.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
//        acceptButton = findViewById(R.id.acceptButton);
//        declineButton = findViewById(R.id.declineButton);
//        commentsEditText = findViewById(R.id.etComments);
//        dateOfRequestTextView = findViewById(R.id.dateOfRequest);
//        nameOfRequesterTextView = findViewById(R.id.nameOfRequester);
//
//        String dateOfRequest = "15/10/2024";
//        String nameOfRequester = "Alice Johnson";
//
//        dateOfRequestTextView.setText("Date Of Request: " + dateOfRequest);
//        nameOfRequesterTextView.setText("Name of Requester: " + nameOfRequester);
//
//        acceptButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String comments = commentsEditText.getText().toString().trim();
//                Toast.makeText(ViewRequestedWorkOrderActivity.this, "Work Order Accepted", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        declineButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ViewRequestedWorkOrderActivity.this, "Work Order Declined", Toast.LENGTH_SHORT).show();
//            }
//        });
