package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * @Author: Benton Le
 * @Description: Activity to handle job progress editing and completion
 * @Date: 11/28/2024
 */
public class UpdateJobProgressActivity extends AppCompatActivity {

    private int jobProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job_progress);

        WorkOrder orderPassed = (WorkOrder) getIntent().getParcelableExtra("order");

        Log.d("Debugging", "Checking to see if order was found" + orderPassed);

        InitializePage(orderPassed);
        // Log.d("Debugging", "Checking seekbar");
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbarUpdateJob);

        seekBar.setProgress(orderPassed.getJobProgress());
        Log.d("Debugging", String.valueOf(orderPassed.getJobProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                jobProgress = seekBar.getProgress();

            }
        });

        Button btnUpdate = (Button) findViewById(R.id.jobProgressUpdateButton);
        btnUpdate.setOnClickListener(view -> {
            EditText jobDetails = (EditText) findViewById(R.id.jobProgressComments);

            DatabaseReference workOrderRef = FirebaseDatabase.getInstance().getReference("workorder");

            workOrderRef.orderByChild("workorderId").equalTo(orderPassed.getWorkOrderId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String workOrderKey = snapshot.getKey();
                            Log.d("UpdateJobProgress", "Found workorder key: " + workOrderKey);

                            DatabaseReference userReference = workOrderRef.child(workOrderKey);
                            userReference.child("jobprogress").setValue(jobProgress);
                            userReference.child("jobdetails").setValue(jobDetails.getText().toString());

                            Intent intent = new Intent(UpdateJobProgressActivity.this, AdultShovellerProfileActivity.class);
                            intent.putExtra("user_id", orderPassed.getUserId());
                            startActivity(intent);
                        }
                    } else {
                        Log.e("UpdateJobProgress", "WorkOrderId not found in Firebase.");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("UpdateJobProgress", "Database error: " + databaseError.getMessage());
                }
            });
        });

    }

    public void InitializePage(WorkOrder orderPassed) {
        TextView txtWorkOrder = (TextView) findViewById(R.id.jobProgressId);
        TextView txtDescription = (TextView) findViewById(R.id.jobProgressDescription);
        TextView txtLocation = (TextView) findViewById(R.id.jobProgressLocation);
        TextView txtUrgency = (TextView) findViewById(R.id.jobProgressUrgency);

        EditText editJobDetails = (EditText) findViewById(R.id.jobProgressComments);
        editJobDetails.setText(orderPassed.getJobDetails());

        txtWorkOrder.setText(String.valueOf(orderPassed.getWorkOrderId()));
        txtDescription.setText(orderPassed.getInstructions());
        txtLocation.setText(orderPassed.getUserAddress());
        txtUrgency.setText(orderPassed.getUrgency());
    }
}
