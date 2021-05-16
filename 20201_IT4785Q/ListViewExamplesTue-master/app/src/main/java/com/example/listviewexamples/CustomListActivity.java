package com.example.listviewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.listviewexamples.adapters.CustomAdapter;
import com.example.listviewexamples.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class CustomListActivity extends AppCompatActivity {

    List<ItemModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        items = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            items.add(new ItemModel("Title " + i, "Subtitle " + i, R.drawable.thumb2));

        CustomAdapter adapter = new CustomAdapter(items, this);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}