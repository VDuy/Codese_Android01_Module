package com.example.lesson6.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lesson6.R;
import com.example.lesson6.adapters.ItemAdapter;
import com.example.lesson6.databases.DatabaseUtils;

public class MainActivity extends AppCompatActivity {
    ItemAdapter adapter;
    ListView listView;
    public static BookDetailsActivity bookListActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookListActivity = new BookDetailsActivity();
        listView = findViewById(R.id.lv_book_list);
        adapter = new ItemAdapter(MainActivity.this, R.layout.item_books_list, DatabaseUtils.getInstance(this).getListTopic());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);


            }
        });


    }
}
