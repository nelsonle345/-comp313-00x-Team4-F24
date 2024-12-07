package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCustomerProfileActivity extends AppCompatActivity {
    DatabaseReference userTable;

    private TextView usernameTV;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private DatePicker birthdateDatePicker;
    private TextView emailTV;
    private TextView phoneTV;
    private TextView addressTV;
    private User user;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_profile);

        //Instantiate userTable to current listing
        userTable = FirebaseDatabase.getInstance().getReference("users");

        usernameTV = findViewById(R.id.tvUsername);
        firstNameTV = findViewById(R.id.tvFirstName);
        lastNameTV = findViewById(R.id.tvLastname);
        emailTV = findViewById(R.id.tvEmail);
        phoneTV = findViewById(R.id.tvPhone);
        addressTV = findViewById(R.id.tvAddress);

        //GET USERID FROM LOGIN OR REGISTRATION
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userId = extras.getInt("user_id");
                Log.d("Debugging", "customer ID received: " + userId);
                retrieveCustomerProfileData(String.valueOf(userId));
            }
        }
    }
    private void retrieveCustomerProfileData(String userId) {
        System.out.println("customer ID recieved to retrieve cx profile: " + userId);

        userTable.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Integer dbUserId = dataSnapshot.child("userId").getValue(Integer.class);

                    if(dbUserId != null && dbUserId.equals(Integer.valueOf(userId))) {
                        user = dataSnapshot.getValue(User.class);

                        if (user != null) {
                            //display user profile data
                            usernameTV.setText("Username: " + user.getUserName());
                            firstNameTV.setText("First Name: " + user.getFirstName());
                            lastNameTV.setText("Last Name: " +user.getLastName());
                            emailTV.setText("Email: " + user.getEmail());
                            phoneTV.setText("Phone Number: " + user.getPhoneNumber());
                            addressTV.setText("Address: " + user.getAddress());

                            System.out.println("User data loaded: " + user.getUserName());
                            System.out.println("Sending userid to read addresses: " + user);

                        } else {
                            //handle no user data
                        }
                    } else {
                        //handle userid does not exist
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewCustomerProfileActivity.this, "Could not create user. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}