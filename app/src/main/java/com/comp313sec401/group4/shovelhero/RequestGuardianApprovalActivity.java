package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;

public class RequestGuardianApprovalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_approval_request);

        WorkOrder order = (WorkOrder) getIntent().getParcelableExtra("order");
    }
}
