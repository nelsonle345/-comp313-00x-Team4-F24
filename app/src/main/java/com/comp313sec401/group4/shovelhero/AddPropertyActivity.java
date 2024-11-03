package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.comp313sec401.group4.shovelhero.Models.User;
import com.comp313sec401.group4.shovelhero.Models.Property;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddPropertyActivity extends AppCompatActivity {

    private EditText addressEditText, specialInstructionsEditText;
    private CheckBox cbDriveway, cbSidewalk, cbWalkway;
    private Button saveButton, cancelButton;

    private User currentUser;
    private String currentUserId;

    private DatabaseReference userTable;
    private List<String> itemsRequestedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property_info);

        //inialize itemsRequestedList
        itemsRequestedList = new ArrayList<>();

        addressEditText = findViewById(R.id.addressEditText);
        specialInstructionsEditText = findViewById(R.id.etAddressNotes);
        cbDriveway = findViewById(R.id.cbDriveway);
        cbSidewalk = findViewById(R.id.cbSidewalk);
        cbWalkway = findViewById(R.id.cbWalkway);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        //get userID
        currentUserId = getIntent().getStringExtra("USER_ID");
        if (currentUserId == null) {
            Toast.makeText(this, "Temp msg: CustomerID is null", Toast.LENGTH_SHORT).show();
        }

        //Instantiate userTable
        userTable = FirebaseDatabase.getInstance().getReference("users").child(currentUserId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressEditText.getText().toString().trim();
                String specialInstructions = specialInstructionsEditText.getText().toString().trim();

                if (address.isEmpty()) {
                    Toast.makeText(AddPropertyActivity.this, "Please enter the property address", Toast.LENGTH_SHORT).show();
                } else {
                    createProperty();
                    saveAndReturnToProfile(currentUserId);
                    Toast.makeText(AddPropertyActivity.this, "Address created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressEditText.setText("");
                specialInstructionsEditText.setText("");
                cbDriveway.setChecked(false);
                cbSidewalk.setChecked(false);
                cbWalkway.setChecked(false);
                Toast.makeText(AddPropertyActivity.this, "Input cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void createProperty(){
        String addressId = userTable.child("property").push().getKey();

        String address = addressEditText.getText().toString();
        String specialInstructions = specialInstructionsEditText.getText().toString();

        //ITEMS REQUESTED LIST
        if (cbDriveway.isChecked()) {
            itemsRequestedList.add("Driveway");
        } else {
            itemsRequestedList.add("NO Driveway Please");
        }
        if (cbSidewalk.isChecked()) {
            itemsRequestedList.add("Sidewalk");
        } else {
            itemsRequestedList.add("NO Sidewalk Please");
        }
        if (cbWalkway.isChecked()) {
            itemsRequestedList.add("Walkway");
        } else {
            itemsRequestedList.add("NO Walkway Please");
        }
        //CREATE ADDRESS OBJECT (WITHIN USER) THEN RESET FIELDS FOR NEW ENTRY
        if (!address.isEmpty()) {
            Property newAddress = new Property(addressId, address, specialInstructions);

            System.out.println("New address created: " + newAddress.getAddress());

            //save new address and reset input form
            if (addressId != null) {
                userTable.child("property").child(addressId).setValue(newAddress);
                System.out.println("New address added to Firebase under user: " + newAddress.getAddress());

                addressEditText.setText("");
                specialInstructionsEditText.setText("");
                cbDriveway.setChecked(false);
                cbSidewalk.setChecked(false);
                cbWalkway.setChecked(false);
            }
        }
        else {
            System.out.println("Missing address info, please retry");
        }
    }
    private void saveAndReturnToProfile(String currentUserId){
        DatabaseReference userTable = FirebaseDatabase.getInstance().getReference("users").child(currentUserId);
        userTable.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUser = snapshot.getValue(User.class);
                String accountType = currentUser.getAccountType();

                //**todo** ADD ADULT SHOVELLER AND GUARDIAN
                if(accountType != null) {
                    switch (accountType) {
                        case "Youth Shoveller":
                            Intent intentYouth = new Intent(AddPropertyActivity.this, YouthShovellerProfile.class);
                            int youthID = currentUser.getUserId();
                            intentYouth.putExtra("USER_ID", youthID);
                            startActivity(intentYouth);
                            break;
                        case "Customer":
                            Intent intentCustomer = new Intent(AddPropertyActivity.this, CustomerProfile.class);
                            int customerId = currentUser.getUserId();
                            intentCustomer.putExtra("USER_ID", customerId);
                            startActivity(intentCustomer);
                            break;
                        case "Guardian":
                            Intent intentGuardian = new Intent(AddPropertyActivity.this, GuardianProfile.class);
                            int guardianId = currentUser.getUserId();
                            intentGuardian.putExtra("USER_ID", guardianId);
                            startActivity(intentGuardian);
                            break;
                        default:
                            Intent intent = new Intent(AddPropertyActivity.this, User_registration.class);
                            startActivity(intent);
                            break;
                    }
                } else{
                    System.out.println("Account Type is Null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("There is a database error");
            }
        });
    }
}
