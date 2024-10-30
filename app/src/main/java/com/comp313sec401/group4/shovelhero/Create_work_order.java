package com.comp313sec401.group4.shovelhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_work_order extends AppCompatActivity {

 private static final String TAG = "Create_work_order";



    private String workOrderId;
    private TextView addressTextView;
    private TextView sqFootageTextView;
    //private EditText squareFootageEditText; //-->This should be from AddressId
    private EditText customShovelerEditText;

    private TextView workOrderPriceTextView;


    //Date fields
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    private TextView requestDate;   //date request was placed
    private EditText requestedDate; //date customer has requested in the future
    private TextClock requestedTime;  //time customer has requested in the future


    //WO Items and pricing
    private List<String> itemsRequested;
    private List<CheckBox> itemCheckBoxList;
    private CheckBox drivewayCheckBox;
    private CheckBox walkwayCheckBox;
    private CheckBox sidewalkCheckBox;
    private Double wOPrice = 2.00;  //app overhead cost - maybe more with stripe fee

    private EditText addressNotesEditText;
    TextView dateTimeInLongTextView;

    //Buttons
    private Button btnOrderShovelling;
    private Button btnCancelOrder;
    //instantiated Objects
    //private WorkOrder currentWorkOrder;
    private String currentWOId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work_order);

        addressTextView = findViewById(R.id.tvAddress);
        sqFootageTextView = findViewById(R.id.tvSquareFootage);
        requestDate = findViewById(R.id.tvRequestDate);
        customShovelerEditText = findViewById(R.id.etCustomShoveller);
        addressNotesEditText = findViewById(R.id.etAddressNotes);
        workOrderPriceTextView = findViewById(R.id.tvWorkOrderPrice);

        requestedDate = findViewById(R.id.etCustomDate);
        requestedTime = findViewById(R.id.tpCustomTime);

        calendar = Calendar.getInstance();
        //simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm aaa z");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        //requestedDate.setText(dateTime);

        // Todo: No further implementation after adding to list.
        itemCheckBoxList = new ArrayList<>();
        itemCheckBoxList.add(findViewById(R.id.cbDriveway));
        itemCheckBoxList.add(findViewById(R.id.cbSidewalk));
        itemCheckBoxList.add(findViewById(R.id.cbWalkway));

        btnOrderShovelling = findViewById(R.id.btnOrderShovelling);
        btnCancelOrder = findViewById(R.id.btnCancel);



        btnOrderShovelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = requestedDate.getText().toString();
                String time = requestedTime.getFormat12Hour().toString();
                String userName = customShovelerEditText.getText().toString();

                if (!isValidDate(date)) {
                    Toast.makeText(Create_work_order.this, "Please enter valid date in yyyy-MM-dd format", Toast.LENGTH_SHORT).show();
                }
                if (!isValidTime(time)) {
                    Toast.makeText(Create_work_order.this, "Please enter valid time in h:mm format", Toast.LENGTH_SHORT).show();
                }
                if (!isValidUserName(userName)) {
                    Toast.makeText(Create_work_order.this, "Please enter valid username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        System.out.println("Work order id intent ok from profile: " + currentWOId);
    }

    // Validation function for Date
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Validation function for Time
    private boolean isValidTime(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
            sdf.setLenient(false);
            sdf.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Validation function for UserName
    private boolean isValidUserName(String userName) {
        return userName.length() >= 5 && userName.length() <= 20;
    }


}