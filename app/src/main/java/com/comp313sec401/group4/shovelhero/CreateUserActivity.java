package com.comp313sec401.group4.shovelhero;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.comp313sec401.group4.shovelhero.Models.User;


public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Back button on click functionality
        FloatingActionButton btnBack = findViewById(R.id.createUserBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Spinner accountTypeList = findViewById(R.id.txtCreateAccountType);
        String[] accountTypes = new String[]{"Young Shoveler", "Adult Shoveler", "Guardian"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accountTypes);
        accountTypeList.setAdapter(adapter);

        Button btnCreateUser = findViewById(R.id.btnAddUser);
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ((EditText) findViewById(R.id.txtCreateUsername)).getText().toString();
                // Todo: Add password hashing
                String password = ((EditText) findViewById(R.id.txtCreatePassword)).getText().toString();
                String fname = ((EditText) findViewById(R.id.txtCreateFirstname)).getText().toString();
                String lname = ((EditText) findViewById(R.id.txtCreateLastname)).getText().toString();
                String email = ((EditText) findViewById(R.id.txtCreateEmail)).getText().toString();
                String phone = ((EditText) findViewById(R.id.txtCreatePhone)).getText().toString();
                String accountType = ((Spinner) findViewById(R.id.txtCreateAccountType)).getSelectedItem().toString();
                // Suppressed because my API is too old :(
                @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate currDate = LocalDate.now();

                // Todo: Add a error message, use method:
                // https://stackoverflow.com/questions/30953449/design-android-edittext-to-show-error-message-as-described-by-google

                // Todo: Add validation checks


                // Log.d(String.valueOf(Log.DEBUG), "This is user: " + currDate.toString());

                User newUser = new User(fname, lname, email, username, password, phone, currDate.toString(), accountType);
                insertData(newUser);
                // Log.d(String.valueOf(Log.DEBUG), "This is user: " + newUser.getUsername() + ", date: " + newUser.getAddedDate());
            }
        });
    }

    private void insertData(User user) {
        Map<String, Object> map = new HashMap<>();

        map.put("firstname", user.getFname());
        map.put("lastname", user.getLname());
        map.put("email", user.getEmail());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("phonenumber", user.getPhonenumber());
        map.put("date-added", user.getAddedDate());
        map.put("accounttype", user.getAccountType());

        FirebaseDatabase.getInstance().getReference().child("users").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CreateUserActivity.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateUserActivity.this, "Error Creating User", Toast.LENGTH_SHORT).show();
                    }
                })
        ;

    }
}
