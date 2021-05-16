package com.example.listviewexamples.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.listviewexamples.adapters.ImageAdapter;
import com.example.listviewexamples.R;

import java.util.ArrayList;
import java.util.List;

public class GridImageActivity extends AppCompatActivity {

    List<Integer> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image);

        items = new ArrayList<>();
        items.add(R.drawable.thumb1);
        items.add(R.drawable.thumb2);
        items.add(R.drawable.thumb3);
        items.add(R.drawable.thumb4);
        items.add(R.drawable.thumb5);
        items.add(R.drawable.thumb6);
        items.add(R.drawable.thumb7);
        items.add(R.drawable.thumb8);
        items.add(R.drawable.thumb9);
        items.add(R.drawable.thumb10);
        items.add(R.drawable.thumb11);
        items.add(R.drawable.thumb12);

        ImageAdapter adapter = new ImageAdapter(this, items);
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
    }
}