package com.comp313sec401.group4.shovelhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomerProfile extends AppCompatActivity {

    Button btnAddAddress;
    Button btnOrderShoveling;
    Button btnEditProfile;
    Button btnManagePaymentInfo;
    Button btnEditPassword;
    Button btnViewMyRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        btnOrderShoveling = findViewById(R.id.btnOrderShoveling);
        btnManagePaymentInfo = findViewById(R.id.btnManagePaymentInfo);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnEditProfile = findViewById(R.id.btnEditUserInfo);
        btnEditPassword = findViewById(R.id.btnEditPassword);
        btnViewMyRatings = findViewById(R.id.btnViewMyRatings);

        //MANAGE PAYMENT BUTTON
        btnManagePaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerProfile.this, "Temp msg: Manage Payment activity under construction", Toast.LENGTH_SHORT).show();

                Intent intentManageCustomerPayment = new Intent(CustomerProfile.this, Manage_Payment.class);
                //String customerId = user.getUserId();
                //intentManageYouthPayment.putExtra("USER_ID", customerId);
                startActivity(intentManageCustomerPayment);
            }
        });
    }
}