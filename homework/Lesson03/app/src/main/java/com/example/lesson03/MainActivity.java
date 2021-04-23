package com.example.lesson03;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int requestCodeWriteStorage = 1001;

    FloatingActionButton fbAdd;
    ScrollView scrollView;
    LinearLayout linearLayoutSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fbAdd = findViewById(R.id.fb_add);
        final ScrollView scrollView = findViewById(R.id.Sc_im);
        final LinearLayout linearLayoutSc = findViewById(R.id.Line1_Sc);

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutSc.addView(createImageView(MainActivity.this));
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        setupPermission();
    }

    public CustomView createImageView(Context context) {
        CustomView imageView = new CustomView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 16);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(randomImage());
        return imageView;
    }

    public int randomImage() {
        Random random = new Random();
        int rdImage = random.nextInt(5) + 1;
        switch (rdImage) {
            case 1:
                return R.drawable.food_1;
            case 2:
                return R.drawable.food_2;
            case 3:
                return R.drawable.food_3;
            case 4:
                return R.drawable.food_4;
            case 5:
                return R.drawable.food_5;
        }
        return 0;
    }

    private void setupPermission() {
        String[] permissions = new String[1];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, requestCodeWriteStorage);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == requestCodeWriteStorage) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Warning!")
                        .setMessage("Without permission you can not use this app. Do you want to grant permission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setupPermission();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        })
                        .show();
            }
        }
    }
}



