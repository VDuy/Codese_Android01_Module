package com.example.intentexamples;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class PickAndViewActivity extends AppCompatActivity {

    TextView textSelectedContact;
    TextView textSelectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_view);

        textSelectedContact = findViewById(R.id.text_selected_contact);
        textSelectedImage = findViewById(R.id.text_selected_image);

        findViewById(R.id.btn_pick_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 123);
            }
        });

        findViewById(R.id.btn_view_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(textSelectedContact.getText().toString()));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_pick_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 456);
            }
        });

        findViewById(R.id.btn_view_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(textSelectedImage.getText().toString()));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                String contactUri = data.getDataString();
                textSelectedContact.setText(contactUri);
            }
        } else if (requestCode == 456) {
            if (resultCode == Activity.RESULT_OK) {
                String imageUri = data.getDataString();
                textSelectedImage.setText(imageUri);
            }
        }
    }
}