package com.example.codese_android01_todo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codese_android01_todo.R;
import com.example.codese_android01_todo.adapter.ToDoAdapter;
import com.example.codese_android01_todo.database.DatabaseUtils;
import com.example.codese_android01_todo.model.TodoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ToDoAdapter adapter;
    private ArrayList<TodoModel> todoArrayList;
    FloatingActionButton fbAdd;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseUtils.getInstance(this).getListModel();

        listView = findViewById(R.id.lv_list);
        todoArrayList = (ArrayList<TodoModel>) DatabaseUtils.getInstance(this).getListModel();
        adapter = new ToDoAdapter(MainActivity.this, R.layout.item, todoArrayList);
        listView.setAdapter(adapter);

        fbAdd = findViewById(R.id.fb_add);
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
             startActivity(intent);
            }
        });

    }

    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
