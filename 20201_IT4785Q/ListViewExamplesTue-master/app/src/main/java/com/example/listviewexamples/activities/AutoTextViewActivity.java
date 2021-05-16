package com.example.listviewexamples.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.listviewexamples.R;

public class AutoTextViewActivity extends AppCompatActivity {
    String[] items = { "words", "starting", "with", "set", "Setback",
            "Setline", "Setoffs", "Setouts", "Setters", "Setting",
            "Settled", "Settler", "Wordless", "Wordiness", "Adios" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_text_view);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                items);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.layout_item,
                R.id.text_view,
                items);

        AutoCompleteTextView editSearch = findViewById(R.id.edit_search);
        editSearch.setAdapter(adapter);
    }
}