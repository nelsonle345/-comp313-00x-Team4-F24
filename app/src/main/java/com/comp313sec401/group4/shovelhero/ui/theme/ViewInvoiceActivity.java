package com.comp313sec401.group4.shovelhero.ui.theme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.Invoice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewInvoiceActivity extends AppCompatActivity {

    private TextView tvJobId, tvJobDescription, tvPaymentAmount, tvPaymentStatus;
    private Button btnConfirmPayment;
    private DatabaseReference invoiceTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoice);

        // Initialize Firebase reference
        invoiceTable = FirebaseDatabase.getInstance().getReference("invoices");

        // Initialize UI components
        tvJobId = findViewById(R.id.tvJobId);
        tvJobDescription = findViewById(R.id.tvJobDescription);
        tvPaymentAmount = findViewById(R.id.tvPaymentAmount);
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        // Retrieve the Invoice ID from the Intent
        String invoiceId = getIntent().getStringExtra("INVOICE_ID");

        if (invoiceId != null && !invoiceId.isEmpty()) {
            loadInvoiceDetails(invoiceId);
        } else {
            Toast.makeText(this, "Invalid Invoice ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Confirm Payment Button Listener
        btnConfirmPayment.setOnClickListener(v -> confirmPayment(invoiceId));
    }

    private void loadInvoiceDetails(String invoiceId) {
        // Fetch the invoice details from Firebase
        invoiceTable.child(invoiceId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Invoice invoice = dataSnapshot.getValue(Invoice.class);
                if (invoice != null) {
                    tvJobId.setText("Job ID: " + invoice.getJobId());
                    tvJobDescription.setText("Job Description: " + invoice.getJobDescription());
                    tvPaymentAmount.setText("Payment Amount: $" + invoice.getPaymentAmount());
                    tvPaymentStatus.setText("Payment Status: " + invoice.getPaymentStatus());
                } else {
                    Toast.makeText(ViewInvoiceActivity.this, "Invoice not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewInvoiceActivity.this, "Failed to load invoice details.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmPayment(String invoiceId) {
        // Update payment status in Firebase
        invoiceTable.child(invoiceId).child("paymentStatus").setValue("Confirmed")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ViewInvoiceActivity.this, "Payment Confirmed", Toast.LENGTH_SHORT).show();
                        tvPaymentStatus.setText("Payment Status: Confirmed");
                    } else {
                        Toast.makeText(ViewInvoiceActivity.this, "Failed to confirm payment.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

