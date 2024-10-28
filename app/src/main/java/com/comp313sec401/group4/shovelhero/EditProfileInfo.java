package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.comp313sec401.group4.shovelhero.Models.User;

import java.util.HashMap;
import java.util.Map;

public class EditProfileInfo extends AppCompatActivity {

    private EditText editFirstname, editLastname, editBirthdate, editUsername, editEmail, editPhoneNumber;
    private Button updateProfile;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_info);

        // Get Views
        editFirstname = findViewById(R.id.etEditFirstname);
        editLastname = findViewById(R.id.etEditLastname);
        editBirthdate = findViewById(R.id.etEditBirthdate);
        editUsername = findViewById(R.id.etEditUsername);
        editEmail = findViewById(R.id.etEditEmail);
        editPhoneNumber = findViewById(R.id.etEditPhoneNumber);
        updateProfile = findViewById(R.id.btnUpdateProfile);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editFirstname.getText().toString();
                String lastName = editLastname.getText().toString();
                String usetName = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String phone = editPhoneNumber.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || usetName.isEmpty() || email.isEmpty() || phone.isEmpty()){
                    Toast.makeText(EditProfileInfo.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
                if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                    Toast.makeText(EditProfileInfo.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                if(!(android.util.Patterns.PHONE.matcher(phone).matches())){
                    Toast.makeText(EditProfileInfo.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Retrieve User ID
        userId = getIntent().getStringExtra("USER_ID");
        if(userId == null || userId.isEmpty()){
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            return;
        }
        //Load the User Data
        loadUserProfile();

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirebaseUserProfile();
            }
        });
    }
    private void loadUserProfile(){
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    if(user != null) {
                        editFirstname.setText(user.getFirstName());
                        editLastname.setText(user.getLastName());
                        editUsername.setText(user.getUserName());
                        editBirthdate.setText(user.getBirthDate());
                        editEmail.setText(user.getEmail());
                        editPhoneNumber.setText(user.getPhoneNumber());

                    } else {
                        Toast.makeText(EditProfileInfo.this, "User data cannot be found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditProfileInfo.this, "User doesn't exist in the database", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfileInfo.this, "Could not load user data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateFirebaseUserProfile() {
        String newFirstname = editFirstname.getText().toString();
        String newLastname = editLastname.getText().toString();
        String newUsername = editUsername.getText().toString();
        String newBirthdate = editBirthdate.getText().toString();
        String newEmail = editEmail.getText().toString();
        String newPhoneNumber = editPhoneNumber.getText().toString();

        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
        Map<String, Object> updateProfileData = new HashMap<>();
        updateProfileData.put("firstName", newFirstname);
        updateProfileData.put("lastName", newLastname);
        updateProfileData.put("username", newUsername);
        updateProfileData.put("birthdate", newBirthdate);
        updateProfileData.put("email", newEmail);
        updateProfileData.put("phoneNo", newPhoneNumber);

        userReference.updateChildren(updateProfileData)
                .addOnSuccessListener(aVoid -> Toast.makeText(EditProfileInfo.this, "Profile updated successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(EditProfileInfo.this, "Profile update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}