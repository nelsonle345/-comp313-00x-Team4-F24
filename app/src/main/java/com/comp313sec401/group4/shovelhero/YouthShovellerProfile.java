package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.comp313sec401.group4.shovelhero.Adapters.ListApprovedWorkOrdersAdapter;
import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class YouthShovellerProfile extends AppCompatActivity {


    private ListApprovedWorkOrdersAdapter adapter;
    private List<com.comp313sec401.group4.shovelhero.models.WorkOrder> approvedWorkOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_shoveller_profile);

        DatabaseReference userTable = FirebaseDatabase.getInstance().getReference("users");

        Button btnViewRatings = findViewById(R.id.btnViewRatings);

        RecyclerView rvPendingWorkOrders = findViewById(R.id.rvPendingWorkOrders);
        rvPendingWorkOrders.setLayoutManager(new LinearLayoutManager(this));
        approvedWorkOrderList = new ArrayList<>();
        adapter = new ListApprovedWorkOrdersAdapter(this, approvedWorkOrderList);
        rvPendingWorkOrders.setAdapter(adapter);

        // Initialize Firebase reference to guardian_approval_request node
        userTable = FirebaseDatabase.getInstance().getReference("guardian_approval_request");

        fetchApprovedWorkOrdersFromFirebase();


        // Get user intent passed from login page
        int userId = 0;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userId = extras.getInt("user_id");
                Log.d("Debugging", "this is passed Id: " + userId);
                if (userId != 0) {
                    retrieveYouthProfile(userId, userTable);
                }
            }
        } else {
            userId = 0;
        }

        // TODO: add ManageProfile Functionality
        Button btnManageProfileInfo = findViewById(R.id.btnManageProfileInfo);
//        btnManageProfileInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(YouthShovellerProfile.this, "Temp msg: Manage Youth activity under construction", Toast.LENGTH_SHORT).show();
//                Intent intentManageYouthProfile = new Intent(YouthShovellerProfile.this, EditProfileInfo.class);
//                String youthId = user.getUserId();
//                intentManageYouthProfile.putExtra("USER_ID", youthId);
//                startActivity(intentManageYouthProfile);
//            }
//        });

        Button btnViewJobs = findViewById(R.id.btnViewJobs);
        int finalUserId = userId;
        btnViewJobs.setOnClickListener(view -> {
            Intent intentViewYouthJobs = new Intent(YouthShovellerProfile.this, ListAllWorkOrders.class);
            intentViewYouthJobs.putExtra("user_id", finalUserId);
            startActivity(intentViewYouthJobs);
        });

        // TODO: Need to implement managementPayment Info Button
        Button btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
//        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(YouthShovellerProfile.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();
//
//                Intent intentManageYouthPayment = new Intent(YouthShovellerProfile.this, Manage_Payment.class);
//                String youthId = user.getUserId();
//                intentManageYouthPayment.putExtra("USER_ID", youthId);
//                startActivity(intentManageYouthPayment);
//            }
//        });

        // TODO: Need to implement Edit Password functionality
        Button btnEditPassword = findViewById(R.id.btnEditPassword);
//        btnEditPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentEditPassword = new Intent(YouthShovellerProfile.this, EditPassword.class);
//                String youthId = user.getUserId();
//                intentEditPassword.putExtra("USER_ID", youthId);
//                startActivity(intentEditPassword);
//            }
//        });
    }


    // to fetch approved workorder from firebase
    private void fetchApprovedWorkOrdersFromFirebase() {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("workOrders");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    approvedWorkOrderList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        WorkOrder order = dataSnapshot.getValue(WorkOrder.class);
                        if (order != null && "approved".equalsIgnoreCase(order.getStatus())) {
                            approvedWorkOrderList.add(order);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("FirebaseError", "Failed to read approved work orders", error.toException());
                    Toast.makeText(YouthShovellerProfile.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
                }
            });
        }


    private void retrieveYouthProfile(int userId, DatabaseReference ref){
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
}