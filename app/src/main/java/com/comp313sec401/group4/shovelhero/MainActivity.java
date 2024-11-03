package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Debugging", "Connecting to firebase");
        shovelHeroDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        Log.d("Debugging", "Firebase connected");

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this::loginUser);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, User_registration.class);
            startActivity(intent);
        });
    }
    public void loginUser(View view) {
        String username = ((EditText) findViewById(R.id.etUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();

        //Check if username exists
        shovelHeroDatabaseReference.orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                                User user = userSnapshot.getValue(User.class);
                                if (user != null) {
                                    if (password.equals(user.getPassword())) {
                                        Log.d("Debugging", "User Account + id: " + user.getAccountType() + ", " + user.getUserId());
                                        switch (user.getAccountType()) {
                                            case "Guardian": {
                                                Intent intent = new Intent(MainActivity.this, GuardianProfile.class);
                                                intent.putExtra("user_id", user.getUserId());
                                                startActivity(intent);
                                                break;
                                            }
                                            case "Youth Shoveler": {
                                                Intent intent = new Intent(MainActivity.this, YouthShovellerProfile.class);
                                                intent.putExtra("user_id", user.getUserId());
                                                startActivity(intent);
                                                break;
                                            }
                                            case "Customer": {
                                                Intent intent = new Intent(MainActivity.this, CustomerProfile.class);
                                                intent.putExtra("user_id", user.getUserId());
                                                startActivity(intent);
                                                break;
                                            }
                                            case "Adult Shoveler": {
                                                Intent intent = new Intent(MainActivity.this, AdultShovellerProfile.class);
                                                intent.putExtra("user_id", user.getUserId());
                                                startActivity(intent);
                                                break;
                                            }
                                        }
                                        Log.d("Debugging", "This is the logged-in user: " + user.getEmail());
                                    } else {
                                        Log.d("Debugging", "Password mismatch.");
                                    }
                                } else {
                                    Log.d("Debugging", "User Object is null");
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User not found. Please try again or create a new account.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}