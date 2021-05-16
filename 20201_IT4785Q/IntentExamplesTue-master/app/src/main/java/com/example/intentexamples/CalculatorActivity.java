package com.example.intentexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        double p1 = intent.getDoubleExtra("param1", 0.0);
        double p2 = intent.getDoubleExtra("param2", 0.0);

        double s = p1 + p2;
        intent.putExtra("result", s);
        setResult(Activity.RESULT_OK, intent);

        TextView textLog = findViewById(R.id.text_log);
        textLog.setText("Param 1: " + p1 + "\nParam 2: " + p2 + "\n\nResult: " + s);

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}