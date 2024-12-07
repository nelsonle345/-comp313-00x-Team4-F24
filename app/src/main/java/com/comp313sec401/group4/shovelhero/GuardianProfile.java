package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GuardianProfile extends AppCompatActivity {

    DatabaseReference userTable;
    private String userId;
    private TextView usernameTV;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView emailTV;
    private TextView phoneTV;
    private Spinner addressSpinner;
    private Spinner linkedYouthSpinner;
    private User currentUser;

    Button btnAddYouth;
    Button btnViewYouthProfile;
    Button btnViewRatings;
    Button btnViewJobs;
    Button btnManagePaymentInfo;
    Button btnManageProfileInfo;
    Button btnAddAddress;
    Button btnEditPassword;

    //Link youth
    private List<User> linkedYouthList;

    EditText addYouthET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String selectedRole = sharedPreferences.getString("selected_role", "No Role Selected");
        String userId = sharedPreferences.getString("user_id", null);


        userTable = FirebaseDatabase.getInstance().getReference("users");

        usernameTV = findViewById(R.id.tvUsername);
        firstNameTV = findViewById(R.id.tvFirstName);
        lastNameTV = findViewById(R.id.tvLastname);
        emailTV = findViewById(R.id.tvEmail);
        phoneTV = findViewById(R.id.tvPhone);

        addressSpinner = findViewById(R.id.spinnerAddress);

        addYouthET = findViewById(R.id.etAddYouth);
        btnAddYouth = findViewById(R.id.btnAddYouth);
        linkedYouthSpinner = findViewById(R.id.spinnerYouths);

        btnViewYouthProfile = findViewById(R.id.btnViewYouthProfile);
        btnViewRatings = findViewById(R.id.btnViewRatings);

        btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
        btnManageProfileInfo = findViewById(R.id.btnManageProfileInfo);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnEditPassword = findViewById(R.id.btnEditPassword);


        //GET USERID FROM LOGIN OR REGISTRATION
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("USER_ID");
            if (userId != null) {
                retrieveGuardianProfile(userId);
            }
        }
    }
    private void retrieveGuardianProfile(String userId) {
        userTable.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);

                    if (user != null) {
                        //display user profile info
                        usernameTV.setText("Username: " + user.getUserName());
                        firstNameTV.setText("First Name: " + user.getFirstName());
                        lastNameTV.setText(user.getLastName());
                        emailTV.setText("Email: " + user.getEmail());
                        phoneTV.setText("Phone Number: " + user.getPhoneNumber());



                        //ADD YOUTH BUTTON
                        btnAddYouth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                System.out.println("this is the guardian being sent to link the youth: " + user.getUserName());
                                linkYouthProfile(user);
                            }
                        });


                        //MANAGE PROFILE BUTTON
                        btnManageProfileInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(GuardianProfile.this, "Temp msg: Manage user profile under construction", Toast.LENGTH_SHORT).show();
                                Intent intentManageYouthProfile = new Intent(GuardianProfile.this, EditProfileInfo.class);
                                int guardianId = user.getUserId();
                                intentManageYouthProfile.putExtra("USER_ID", guardianId);
                                startActivity(intentManageYouthProfile);
                            }
                        });

                        //MANAGE PAYMENT BUTTON
                        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(GuardianProfile.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();
                                Intent intentManageYouthPayment = new Intent(GuardianProfile.this, Manage_Payment.class);
                                int guardianId = user.getUserId();
                                intentManageYouthPayment.putExtra("USER_ID", guardianId);
                                startActivity(intentManageYouthPayment);
                            }
                        });


                        //EDIT PASSWORD BUTTON
                        btnEditPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentEditPassword = new Intent(GuardianProfile.this, EditPassword.class);
                                int guardianId = user.getUserId();
                                intentEditPassword.putExtra("USER_ID", guardianId);
                                startActivity(intentEditPassword);
                            }
                        });
                    } else {
                        //handle no user data error
                    }
                } else {
                    //handle user id does not exist
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //handle error
            }
        });
    }
    private void linkYouthProfile(User guardian){
        String youthUsername = addYouthET.getText().toString();

        System.out.println("The youth username to link to the guardian, from edit text: " + youthUsername);

        //CHECK IF USERNAME EXISTS
        userTable.orderByChild("username").equalTo(youthUsername)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot userSnapShot : snapshot.getChildren()) {
                                User youthUser = userSnapShot.getValue(User.class);

                                System.out.println("The youthId linking to the guardian profile: " + youthUser.getUserId());

                                System.out.println("The youth account type: " + youthUser.getAccountType());

                                //CHECK THAT USERNAME = YOUTH ACCT
                                if (youthUser.getAccountType() == "Youth Shoveller") {

                                    //readYouthProfilesFromFirebase(youthUser);
                                    // addGuardianToYouthAccount(youthUser, guardian);
                                    // addYouthToGuardianAccount(youthUser, guardian);
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
//    private void addGuardianToYouthAccount(User youthUser, User guardianUser) {
//        System.out.println("Adding guardian: :" + guardianUser.getUserName() + " to" + youthUser.getUserName());
//
////        userTable.child(youthUser.getUserId()).child("linkedusers").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////
////                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    //manually deserialize the HashMap
////                    Map<String, Object> youthUserMap = (Map<String, Object>) snapshot.getValue();
////
////                    // retrieve values from the Hashmap
////                    int userId = (int) youthUserMap.get("userId");
////                    String accountType = (String) youthUserMap.get("accounttype");
////                    String username = (String) youthUserMap.get("username");
////                    String password = "Hidden";
////                    String firstName = (String) youthUserMap.get("firstname");
////                    String lastName = (String) youthUserMap.get("lastname");
////                    String birthdate = (String) youthUserMap.get("birthdate");
////                    String email = (String) youthUserMap.get("email");
////                    String phoneNo = (String) youthUserMap.get("phonenumber");
//                    // User userObject = new User("", username, password, firstName, accountType,firstName, lastName, birthdate, email, phoneNo, 5);
//
//                    // Add the User object to the linkedUsers HashMap in User model
////                    youthUser.addLinkedUser(userId, userObject);
////
////                    DatabaseReference linkedUserReference = userTable.child(youthUser.getUserId()).child("linkedusers").child(youthUser.getUserId());
////                    Map<String, Object> updateGuardianInfo = new HashMap<>();
////
////                    linkedUserReference.updateChildren(updateGuardianInfo)
////                            .addOnSuccessListener(aVoid -> Toast.makeText(GuardianProfile.this, "Guardian Validation and Profile Pic added to youth profile successfully", Toast.LENGTH_SHORT).show())
////                            //.addOnSuccessListener(addYouthToGuardianAccount(youthUser, guardianUser)
////                            //.addOnFailureListener(e -> updateGuardianInfo.clear())
////                            .addOnFailureListener(e -> Toast.makeText(GuardianProfile.this, "Unable to add validated : " + e.getMessage(), Toast.LENGTH_SHORT).show());
//                }
//
//                // Retrieve list of linkedUsers from Hashmap
//                // List<User> linkedUsers = new ArrayList<>(youthUser.getLinkedUsers().values());
//
//                // Update the Spinner with linked youth(s)
////                ArrayAdapter<User> linkedYouthAdapter = new ArrayAdapter<>(GuardianProfile.this, android.R.layout.simple_spinner_item, linkedUsers);
////                linkedYouthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                linkedYouthSpinner.setAdapter(linkedYouthAdapter);
//
//                // Enable or disable the youth spinner button based on the presence of linked User
//                // linkedYouthSpinner.setEnabled(!linkedUsers.isEmpty());
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
//    private void addYouthToGuardianAccount(User youthUser, User guardianUser) {
//        System.out.println("Adding guardian: :" + guardianUser.getUsername() + " to" + youthUser.getUsername());
//
//        userTable.child(guardianUser.getUserId()).child("linkedusers").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //manually deserialize the HashMap
//                    Map<String, Object> youthUserMap = (Map<String, Object>) snapshot.getValue();
//
//                    // retrieve values from the Hashmap
//                    String userId = (String) youthUserMap.get("userId");
//                    String accountType = (String) youthUserMap.get("accounttype");
//                    String username = (String) youthUserMap.get("username");
//                    String password = "Hidden";
//                    String firstName = (String) youthUserMap.get("firstname");
//                    String lastName = (String) youthUserMap.get("lastname");
//                    String birthdate = (String) youthUserMap.get("birthdate");
//                    String email = (String) youthUserMap.get("email");
//                    String phoneNo = (String) youthUserMap.get("phonenumber");
//
//                    // Create new User object
//                    User youthObject = new User(userId, accountType, username, password, firstName, lastName, birthdate, email, phoneNo);
//
//                    // Add the User object to the linkedUsers HashMap in User model
//                    guardianUser.addLinkedUser(userId, youthObject);
//
//                    DatabaseReference linkedUserReference = userTable.child(guardianUser.getUserId()).child("linkedusers").child(youthObject.getUserId());
//                    Map<String, Object> updateGuardianInfo = new HashMap<>();
//
//                    linkedUserReference.updateChildren(updateGuardianInfo)
//                            .addOnSuccessListener(aVoid -> Toast.makeText(GuardianProfile.this, "Guardian Validation and Profile Pic added to youth profile successfully", Toast.LENGTH_SHORT).show())
//                            //.addOnFailureListener(e -> updateGuardianInfo.clear())
//                            .addOnFailureListener(e -> Toast.makeText(GuardianProfile.this, "Unable to add validated : " + e.getMessage(), Toast.LENGTH_SHORT).show());
//                }
//
//                // Retrieve list of linkedUsers from Hashmap
//                List<User> linkedUsers = new ArrayList<>(guardianUser.getLinkedUsers().values());
//
//                // Update the Spinner with linked youth(s)
//                ArrayAdapter<User> linkedYouthAdapter = new ArrayAdapter<>(GuardianProfile.this, android.R.layout.simple_spinner_item, linkedUsers);
//                linkedYouthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                linkedYouthSpinner.setAdapter(linkedYouthAdapter);
//
//                // Enable or disable the youth spinner button based on the presence of linked User
//                linkedYouthSpinner.setEnabled(!linkedUsers.isEmpty());
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
    // }
}