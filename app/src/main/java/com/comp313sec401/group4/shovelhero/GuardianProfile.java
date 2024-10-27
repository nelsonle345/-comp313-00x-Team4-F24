package com.comp313sec401.group4.shovelhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GuardianProfile extends AppCompatActivity {

    Button btnManagePaymentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_profile);

        btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);

        //MANAGE PAYMENT BUTTON
        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GuardianProfile.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();
                Intent intentManageYouthPayment = new Intent(GuardianProfile.this, Manage_Payment.class);
                //String guardianId = user.getUserId();
                //intentManageYouthPayment.putExtra("USER_ID", guardianId);
                startActivity(intentManageYouthPayment);
            }
        });
    }
}