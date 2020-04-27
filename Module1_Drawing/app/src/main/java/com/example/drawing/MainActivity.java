package com.example.drawing;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    int requestCodeWriteStorage = 1001;

    private GridView gridView;
    private ImageAdapter imageAdapter;

    TextView tvUp, tvDown;
    ImageUtils imageUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fbAdd = findViewById(R.id.fb_add);

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });

        setupPermission();

        gridView = findViewById(R.id.gv_images);
        imageAdapter = new ImageAdapter();
        gridView.setAdapter(imageAdapter);

        tvUp = findViewById(R.id.tv_up);
        tvDown = findViewById(R.id.tv_down);




        imageUtils = new ImageUtils();
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("tag", "onItemLongClick: ed");
                DialogDelete(MainActivity.this, position);
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        imageAdapter.notifyDataSetChanged();
        if (ImageUtils.getListImage().isEmpty()) {
            tvUp.setText("No Image");
            tvDown.setText("Tab + to create the new one");
        }
        if (ImageUtils.getListImage().isEmpty() == false) {
            tvUp.setText(null);
            tvDown.setText(null);
             gridView.setAdapter(imageAdapter);
             imageAdapter.notifyDataSetChanged();
        }
    }


    private void setupPermission() {
        String[] permissions = new String[1];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        //new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, requestCodeWriteStorage);
            }
        }
    }


    private void DialogDelete(Context context, final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setTitle("Do you want to delete ths image?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageUtils.getListImage().get(position).delete();

                        if (ImageUtils.getListImage().isEmpty()) {
                            tvUp.setText("No Image");
                            tvDown.setText("Tab + to creat the new one");
                        }
                        imageAdapter.notifyDataSetChanged();
                    }
                }).show();
    }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
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
