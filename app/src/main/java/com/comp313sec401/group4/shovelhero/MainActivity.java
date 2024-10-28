package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.comp313sec401.group4.shovelhero.Models.User;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference shovelHeroDatabaseReference;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);

        shovelHeroDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        System.out.println("Firebase connected");
    }
    public void loginUser(View view) {
        final String username = usernameEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        //Check if username exists
        shovelHeroDatabaseReference.orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot userSnapShot : snapshot.getChildren()) {
                                User user = userSnapShot.getValue(User.class);

                                if (user != null && user.getPassword().equals(password)){
                                    System.out.println("Username and password ok");

                                    String accountType = user.getAccountType();

                                    System.out.println("Account Type confirmed: " + accountType);

                                    //valid username and password
                                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                    System.out.println("login success");

                                    switch (accountType) {
                                        case "Youth Shoveller":
                                            Intent intentLoginYouth = new Intent(MainActivity.this, YouthShovellerProfile.class);
                                            String youthID = user.getUserId();
                                            intentLoginYouth.putExtra("USER_ID", youthID);
                                            startActivity(intentLoginYouth);
                                            finish();
                                            break;
                                        case "Customer":
                                            Intent intentLoginCustomer = new Intent(MainActivity.this, CustomerProfile.class);
                                            String customerId = user.getUserId();
                                            intentLoginCustomer.putExtra("USER_ID", customerId);
                                            startActivity(intentLoginCustomer);
                                            finish();
                                            break;
                                        case "Guardian":
                                            Intent intentLoginGuardian = new Intent(MainActivity.this, GuardianProfile.class);
                                            String guardianId = user.getUserId();
                                            intentLoginGuardian.putExtra("USER_ID", guardianId);
                                            startActivity(intentLoginGuardian);
                                            finish();
                                            break;
                                        default:
                                            Intent intent = new Intent(MainActivity.this, User_registration.class);
                                            startActivity(intent);
                                            break;
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "invalid password or account type", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User not found. Please try again or create a new account.", Toast.LENGTH_SHORT).show();
                            ;                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //handle error
                    }
                });
    }
    public void createNewUser(View view){
        Intent intent = new Intent(MainActivity.this, User_registration.class);
        startActivity(intent);
    }
}