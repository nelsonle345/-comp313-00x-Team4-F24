package com.comp313sec401.group4.shovelhero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.IOException;

public class GuardianProfile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnAddIDPicture;
    private ImageView imgProfilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guardian_profile);

        imgProfilePicture = findViewById(R.id.imgProfilePicture);

        if (imgProfilePicture != null) {
            imgProfilePicture.setOnClickListener(v -> openImageChooser());
        } else {
            Log.e("GuardianProfile", "ImageView imgProfilePicture is null.");
        }

        btnAddIDPicture = findViewById(R.id.btnAddIDPicture);
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgProfilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onUploadIDPictureClick(View view) {
        openImageChooser();
    }
}
