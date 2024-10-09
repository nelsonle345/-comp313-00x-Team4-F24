package com.comp313sec401.group4.shovelhero;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class User_registration extends AppCompatActivity {
    private String userId;
    private Spinner spinnerAccountType;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private TextView birthdateText;
    private String selectedBirthdate;
    private EditText emailEditText;
    private EditText phoneEditText;
    private ImageButton uploadIdImage;
    private TextView uploadIdCardTextView;
    private Button createAccountButton;
    private Uri selectedIdUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // Get User Input from activity_user_registration
        //select account type
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerAccountType = findViewById(R.id.spAccountType);

        //get text input fields
        usernameEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);
        confirmPasswordEditText = findViewById(R.id.etCoPassword);
        firstNameEditText = findViewById(R.id.etFirstname);
        lastNameEditText = findViewById(R.id.etLastname);
        emailEditText = findViewById(R.id.etEmail);
        phoneEditText = findViewById(R.id.etPhone);
        birthdateText = findViewById(R.id.btnBirthdate);
        uploadIdCardTextView = findViewById(R.id.tvUploadIdCard);
        uploadIdImage = findViewById(R.id.imgUploadIdCard);
        createAccountButton = findViewById(R.id.btnCreateAccount);

        // Set visibility of ID card button and add ID text to hidden
        uploadIdImage.setVisibility(View.GONE);
        uploadIdCardTextView.setVisibility(View.GONE);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()){
                    Toast.makeText(User_registration.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
                if(username.length() <= 3 || username.length() >= 10){
                    Toast.makeText(User_registration.this, "Please enter valid username", Toast.LENGTH_SHORT).show();
                }
                if(!(password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*"))){
                    Toast.makeText(User_registration.this, "Password must contain at least 8 characters and one letter and one digit", Toast.LENGTH_SHORT).show();
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(User_registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                    Toast.makeText(User_registration.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                if(!(android.util.Patterns.PHONE.matcher(phone).matches())){
                    Toast.makeText(User_registration.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}