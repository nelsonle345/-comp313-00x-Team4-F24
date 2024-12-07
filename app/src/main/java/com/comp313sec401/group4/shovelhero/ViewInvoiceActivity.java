package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.google.firebase.database.DatabaseReference;

public class ViewInvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoice);
    }
}
