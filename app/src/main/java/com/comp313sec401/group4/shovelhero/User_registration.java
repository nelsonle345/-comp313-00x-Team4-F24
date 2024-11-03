package com.comp313sec401.group4.shovelhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: Benton Le
 * @Description: User Registration that will create new user VIA Google FireBase
 * @Date: 11/2/2024
 */
public class User_registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        String uploadIdImage = findViewById(R.id.imgUploadIdCard).toString();

        Button btnDate = findViewById(R.id.btnBirthDate);
        btnDate.setOnClickListener(view -> {
            generateCalendar();
        });

        Button createAccountButton = findViewById(R.id.btnCreateAccount);
        createAccountButton.setOnClickListener(view -> {
            registerUser();
        });
    }

    /**
     * @Author: Benton Le
     * @Description: Method to generate calendar dynamically in-app
     * @Date: 11/2/2024
     */
    public void generateCalendar() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(User_registration.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                TextView selectedDateTV = findViewById(R.id.txtBirthdateTV);
                selectedDateTV.setText(dayOfMonth+ "-" + (monthOfYear + 1) + "-" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    /**
     * @Author: Benton Le
     * @param birthday
     * @return age
     */
    private int calculateAge(String birthday) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);
        LocalDate currDate = LocalDate.now();

        return Period.between(birthDate, currDate).getYears();
    }

    private void registerUser() {
        String username = ((EditText) findViewById(R.id.etUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.etCoPassword)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.etFirstname)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.etLastname)).getText().toString();
        String address = ((EditText) findViewById(R.id.etAddress)).getText().toString();
        String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
        String phone = ((EditText) findViewById(R.id.etPhone)).getText().toString();
        String birthdate = ((TextView) findViewById(R.id.txtBirthdateTV)).getText().toString();
        String accountType = ((Spinner) findViewById(R.id.spAccountType)).getSelectedItem().toString();

        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) || !(android.util.Patterns.PHONE.matcher(phone).matches())) {
            Toast.makeText(User_registration.this, "Please enter valid email or phone number", Toast.LENGTH_SHORT).show();
        }
        else if(!(password.equals(confirmPassword))) {
            Toast.makeText(User_registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else {
            User newUser = new User(address, new Random().nextInt(1000000), username, password, accountType, firstName, lastName, birthdate, email, phone, calculateAge(birthdate));

            Map<String, Object> map = new HashMap<>();

            map.put("firstname", newUser.getFirstName());
            map.put("lastname", newUser.getLastName());
            map.put("email", newUser.getEmail());
            map.put("username", newUser.getUserName());
            map.put("password", newUser.getPassword());
            map.put("phonenumber", newUser.getPhoneNumber());
            map.put("accounttype", newUser.getAccountType());
            map.put("address", newUser.getAddress());
            map.put("userId", newUser.getUserId());
            map.put("birthdate", newUser.getBirthDate());
            map.put("age", newUser.getAge());


            FirebaseDatabase.getInstance().getReference().child("users").push()
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(User_registration.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(User_registration.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(User_registration.this, "Error Creating User", Toast.LENGTH_SHORT).show();
                        }
                    })
            ;
        }
    }
    private void insertData(User user) {




    }
}