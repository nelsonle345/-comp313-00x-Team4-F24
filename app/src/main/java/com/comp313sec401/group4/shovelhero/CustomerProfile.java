package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfile extends AppCompatActivity {

    DatabaseReference userTable;

    private TextView usernameTV;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private DatePicker birthdateDatePicker;
    private TextView emailTV;
    private TextView phoneTV;
    private Spinner addressSpinner;
    private User user;
    private String userId;
    Button btnAddAddress;
    Button btnOrderShoveling;
    Button btnEditProfile;
    Button btnManagePaymentInfo;
    Button btnEditPassword;
    Button btnViewMyRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        //Instantiate userTable to current listing
        userTable = FirebaseDatabase.getInstance().getReference("users");

        usernameTV = findViewById(R.id.tvUsername);
        firstNameTV = findViewById(R.id.tvFirstName);
        lastNameTV = findViewById(R.id.tvLastname);
        emailTV = findViewById(R.id.tvEmail);
        phoneTV = findViewById(R.id.tvPhone);
        addressSpinner = findViewById(R.id.spinnerAddress);

        btnOrderShoveling = findViewById(R.id.btnOrderShoveling);
        btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnEditProfile = findViewById(R.id.btnEditUserInfo);
        btnEditPassword = findViewById(R.id.btnEditPassword);
        btnViewMyRatings = findViewById(R.id.btnViewMyRatings);

        //GET USERID FROM LOGIN OR REGISTRATION
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("USER_ID");
            if (userId != null) {
                System.out.println("customer ID recieved: " + userId);
                retrieveCustomerProfileData(userId);
            }
        }
    }
    private void retrieveCustomerProfileData(String userId) {
        System.out.println("customer ID recieved to retrieve cx profile: " + userId);

        userTable.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(User.class);

                    if (user != null) {
                        //display user profile data
                        usernameTV.setText("Username: " + user.getUserName());
                        firstNameTV.setText("Name: " + user.getFirstName());
                        lastNameTV.setText(user.getLastName());
                        emailTV.setText("Email: " + user.getEmail());
                        phoneTV.setText("Phone Number: " + user.getPhoneNumber());

                        System.out.println("User data loaded: " + user.getUserName());
                        System.out.println("Sending userid to read addresses: " + user);


                        //*******
                        //CUSTOMER BUTTONS
                        //*******

                        //REQUEST SHOVELLING BUTTON
                        btnOrderShoveling.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(CustomerProfile.this, "Temp msg: Manage Youth activity under construction", Toast.LENGTH_SHORT).show();
                                Intent intentCreateWorkOrder = new Intent(CustomerProfile.this, Create_work_order.class);
                                int customerId = user.getUserId();
                                intentCreateWorkOrder.putExtra("USER_ID", customerId);
                                startActivity(intentCreateWorkOrder);
                            }
                        });

                        //EDIT PROFILE BUTTON
                        btnEditProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(CustomerProfile.this, "Temp msg: Manage Youth activity under construction", Toast.LENGTH_SHORT).show();
                                Intent intentManageCustomerProfile = new Intent(CustomerProfile.this, EditProfileInfo.class);
                                int customerId = user.getUserId();
                                intentManageCustomerProfile.putExtra("USER_ID", customerId);
                                startActivity(intentManageCustomerProfile);
                            }
                        });

                        //MANAGE PAYMENT BUTTON
                        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                 Intent intentManagePayment = new Intent(CustomerProfile.this, Manage_Payment.class);
                                 int customerId = user.getUserId();
                                 intentManagePayment.putExtra("USER_ID", customerId);
                                 startActivity(intentManagePayment);

                            }
                        });

                        //EDIT PASSWORD BUTTON
                        btnEditPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentEditPassword = new Intent(CustomerProfile.this, EditPassword.class);
                                int customerId = user.getUserId();
                                intentEditPassword.putExtra("USER_ID", customerId);
                                startActivity(intentEditPassword);
                            }
                        });

                        //ADD ADDRESS BUTTON
                        btnAddAddress.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentAddAddress = new Intent(CustomerProfile.this, AddPropertyActivity.class);
                                int customerId = user.getUserId();
                                intentAddAddress.putExtra("USER_ID", customerId);
                                startActivity(intentAddAddress);
                            }
                        });

                    } else {
                        //handle no user data
                    }
                } else {
                    //handle userid does not exist
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerProfile.this, "Could not create user. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

}