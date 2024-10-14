package com.comp313sec401.group4.shovelhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class YouthShovellerProfile extends AppCompatActivity {

    Button btnViewJobs;
    Button btnManagePaymentInfo;
    Button btnManageProfileInfo;
    Button btnAddAddress;
    Button btnEditPassword;
    Button btnViewRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_shoveller_profile);

        btnViewJobs = findViewById(R.id.btnViewJobs);
        btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
        btnManageProfileInfo = findViewById(R.id.btnManageProfileInfo);
        btnEditPassword = findViewById(R.id.btnEditPassword);
        btnViewRatings = findViewById(R.id.btnViewRatings);

        //MANAGE PAYMENT BUTTON
        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(YouthShovellerProfile.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();

                Intent intentManageYouthPayment = new Intent(YouthShovellerProfile.this, Manage_Payment.class);
                //String youthId = user.getUserId();
                //intentManageYouthPayment.putExtra("USER_ID", youthId);
                startActivity(intentManageYouthPayment);
            }
        });
    }
}