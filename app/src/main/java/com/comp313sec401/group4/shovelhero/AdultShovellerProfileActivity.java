package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp313sec401.group4.shovelhero.Adapters.ListAllProfileWorkOrderAdapters;
import com.comp313sec401.group4.shovelhero.Adapters.ListApprovedWorkOrdersAdapter;
import com.comp313sec401.group4.shovelhero.Models.User;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdultShovellerProfileActivity extends AppCompatActivity {

    private ListAllProfileWorkOrderAdapters adapter;
    private final List<WorkOrder> workOrderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_shoveller_profile);

        DatabaseReference userTable = FirebaseDatabase.getInstance().getReference("users");

        RecyclerView rvPendingWorkOrders = findViewById(R.id.rvPendingWorkOrders);
        rvPendingWorkOrders.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAllProfileWorkOrderAdapters(this, workOrderList);

        // Get user intent passed from login page
        int userId;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userId = extras.getInt("user_id");
                // Log.d("Debugging", "this is passed Id: " + userId);
                if (userId != 0) {
                    retrieveAdultProfile(userId, userTable);
                    fetchApprovedWorkOrdersFromFirebase(userId);

                    rvPendingWorkOrders.setAdapter(adapter);
                    Log.d("Debugging", "Set Adapter: ");
                }
            } else {
                userId = 0;
            }
        } else {
            userId = 0;
        }

        Button btnViewJobs = findViewById(R.id.btnViewJobs);
        btnViewJobs.setOnClickListener(view -> {
            Intent intentViewYouthJobs = new Intent(AdultShovellerProfileActivity.this, ListOpenWorkOrder.class);
            intentViewYouthJobs.putExtra("USER_ID", userId);
            startActivity(intentViewYouthJobs);
        });

        Button btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
        btnManagePaymentInfo.setOnClickListener(view -> {
            Toast.makeText(AdultShovellerProfileActivity.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();
            Intent intentManageYouthPayment = new Intent(AdultShovellerProfileActivity.this, Manage_Payment.class);
            intentManageYouthPayment.putExtra("USER_ID", userId);
            startActivity(intentManageYouthPayment);
        });

        Button btnManageProfileInfo = findViewById(R.id.btnManageProfileInfo);
        btnManageProfileInfo.setOnClickListener(view -> {
            Toast.makeText(AdultShovellerProfileActivity.this, "Temp msg: Manage Adult activity under construction", Toast.LENGTH_SHORT).show();
            Intent intentManageYouthProfile = new Intent(AdultShovellerProfileActivity.this, EditProfileInfo.class);
            intentManageYouthProfile.putExtra("USER_ID", userId);
            startActivity(intentManageYouthProfile);
        });

        Button btnEditPassword = findViewById(R.id.btnEditPassword);
        btnEditPassword.setOnClickListener(view -> {
            Intent intentEditPassword = new Intent(AdultShovellerProfileActivity.this, EditPassword.class);
            intentEditPassword.putExtra("USER_ID", userId);
            startActivity(intentEditPassword);
        });
    }

    // to fetch approved workorder from firebase
    private void fetchApprovedWorkOrdersFromFirebase(int user_id) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("workorder");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workOrderList.clear();
                Log.d("Debugging", "Looking for work Orders - " + snapshot);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkOrder order = dataSnapshot.getValue(WorkOrder.class);
                    // Log.d("Debugging", "Found Work Orders" + user_id + ", " + order.getUserId());
                    if (order != null && order.getUserId() == user_id) {
                        workOrderList.add(order);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Failed to read approved work orders", error.toException());
                Toast.makeText(AdultShovellerProfileActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void retrieveAdultProfile(int userId, DatabaseReference ref){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Debugging", "Snapshot checking: " + snapshot);
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d("Debugging", "User found: " + dataSnapshot);
                    Integer dbUserId = dataSnapshot.child("userId").getValue(Integer.class);
                    Log.d("Debugging", "User found: " + dbUserId + ", " + userId);

                    if(dbUserId != null && dbUserId.equals(userId)) {

                        User user =  dataSnapshot.getValue(User.class);
                        if (user != null) {

                            TextView usernameTV = findViewById(R.id.tvUsername);
                            TextView firstNameTV = findViewById(R.id.tvFirstName);
                            TextView lastNameTV = findViewById(R.id.tvLastname);
                            TextView emailTV = findViewById(R.id.tvEmail);
                            TextView phoneTV = findViewById(R.id.tvPhone);
                            TextView birthdayTV = findViewById(R.id.tvBirthdate);
                            TextView addressTV = findViewById(R.id.tvAddress);



                            // display user profile info in TV profile box
                            usernameTV.setText(String.format("Username: %s", user.getUserName()));
                            firstNameTV.setText(String.format("First Name: %s", user.getFirstName()));
                            lastNameTV.setText(String.format("Last Name: %s", user.getLastName()));
                            emailTV.setText(String.format("Email: %s", user.getEmail()));
                            phoneTV.setText(String.format("Phone Number: %s", user.getPhoneNumber()));
                            birthdayTV.setText(String.format("Birthday: %s", user.getBirthDate()));
                            addressTV.setText(String.format("Address: %s", user.getAddress()));
                        } else {
                            Log.d("Error", "User is empty");
                        }
                    }
                    else {
                        Log.d("Error", "User does not exist");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //handle error
            }
        });
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, OpenOrderActivity_ar.class);
        startActivity(intent);
    }
}