package com.comp313sec401.group4.shovelhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SelectWorkOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // Todo: when a youth shoveler accepts a work order and request should be sent to guardian.

        EditText sendto = findViewById(R.id.editTextSendTo);
        EditText subject = findViewById(R.id.editTextSubject);
        EditText body = findViewById(R.id.editTextBody);

        Button sendEmail = findViewById(R.id.btnSendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailSend = sendto.getText().toString();
                String emailSubject = subject.getText().toString();
                String emailBody = body.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailSend});
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailBody);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client: "));
            }
        });
    }
}
