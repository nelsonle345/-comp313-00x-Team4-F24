package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Buttons for selecting user roles
        Button btnAdultShoveler = findViewById(R.id.btnAdultShoveler);
        Button btnGuardian = findViewById(R.id.btnGuardian);
        Button btnYouthShoveler = findViewById(R.id.btnYouthShoveler);

        // Navigate to login page with the selected role
        btnAdultShoveler.setOnClickListener(v -> {
            navigateToLogin("Adult Shoveler");
        });

        btnGuardian.setOnClickListener(v -> {
            navigateToLogin("Guardian");
        });

        btnYouthShoveler.setOnClickListener(v -> {
            navigateToLogin("Youth Shoveler");
        });
    }

    // Method to navigate to the login page with the selected role
    private void navigateToLogin(String role) {
        Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
        intent.putExtra("account_type", role); // Pass the selected role
        startActivity(intent);
        finish(); // Close LandingPageActivity
    }
}
