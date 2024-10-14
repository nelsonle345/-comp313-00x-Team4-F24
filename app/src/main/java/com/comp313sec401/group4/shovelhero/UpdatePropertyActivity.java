package com.comp313sec401.group4.shovelhero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePropertyActivity extends AppCompatActivity {

    private EditText addressEditText, specialInstructionsEditText;
    private CheckBox cbDriveway, cbSidewalk, cbWalkway;
    private Button updateButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_property_info);

        addressEditText = findViewById(R.id.addressEditText);
        specialInstructionsEditText = findViewById(R.id.etAddressNotes);
        cbDriveway = findViewById(R.id.cbDriveway);
        cbSidewalk = findViewById(R.id.cbSidewalk);
        cbWalkway = findViewById(R.id.cbWalkway);
        updateButton = findViewById(R.id.updateButton);
        cancelButton = findViewById(R.id.cancelButton);

        String currentAddress = "123 Main Street";
        String currentInstructions = "Clear snow from driveway and walkway";
        boolean hasDriveway = true;
        boolean hasSidewalk = true;
        boolean hasWalkway = false;

        addressEditText.setText(currentAddress);
        specialInstructionsEditText.setText(currentInstructions);
        cbDriveway.setChecked(hasDriveway);
        cbSidewalk.setChecked(hasSidewalk);
        cbWalkway.setChecked(hasWalkway);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedAddress = addressEditText.getText().toString().trim();
                String updatedInstructions = specialInstructionsEditText.getText().toString().trim();
                boolean hasDriveway = cbDriveway.isChecked();
                boolean hasSidewalk = cbSidewalk.isChecked();
                boolean hasWalkway = cbWalkway.isChecked();

                if (updatedAddress.isEmpty()) {
                    Toast.makeText(UpdatePropertyActivity.this, "Please enter the property address", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(UpdatePropertyActivity.this,
                        "Property Updated: \nAddress: " + updatedAddress +
                                "\nDriveway: " + (hasDriveway ? "Yes" : "No") +
                                "\nSidewalk: " + (hasSidewalk ? "Yes" : "No") +
                                "\nWalkway: " + (hasWalkway ? "Yes" : "No") +
                                "\nSpecial Instructions: " + updatedInstructions,
                        Toast.LENGTH_LONG).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdatePropertyActivity.this, "Update canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
