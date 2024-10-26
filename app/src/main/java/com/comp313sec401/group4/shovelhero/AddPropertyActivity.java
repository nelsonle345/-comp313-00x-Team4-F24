package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPropertyActivity extends AppCompatActivity {

    private EditText addressEditText, specialInstructionsEditText;
    private CheckBox cbDriveway, cbSidewalk, cbWalkway;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property_info);

        addressEditText = findViewById(R.id.addressEditText);
        specialInstructionsEditText = findViewById(R.id.etAddressNotes);
        cbDriveway = findViewById(R.id.cbDriveway);
        cbSidewalk = findViewById(R.id.cbSidewalk);
        cbWalkway = findViewById(R.id.cbWalkway);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressEditText.getText().toString().trim();
                String specialInstructions = specialInstructionsEditText.getText().toString().trim();
                boolean hasDriveway = cbDriveway.isChecked();
                boolean hasSidewalk = cbSidewalk.isChecked();
                boolean hasWalkway = cbWalkway.isChecked();

                if (address.isEmpty()) {
                    Toast.makeText(AddPropertyActivity.this, "Please enter the property address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddPropertyActivity.this,
                            "Property Added: \nAddress: " + address +
                                    "\nDriveway: " + (hasDriveway ? "Yes" : "No") +
                                    "\nSidewalk: " + (hasSidewalk ? "Yes" : "No") +
                                    "\nWalkway: " + (hasWalkway ? "Yes" : "No") +
                                    "\nSpecial Instructions: " + specialInstructions,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressEditText.setText("");
                specialInstructionsEditText.setText("");
                cbDriveway.setChecked(false);
                cbSidewalk.setChecked(false);
                cbWalkway.setChecked(false);
                Toast.makeText(AddPropertyActivity.this, "Input cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
