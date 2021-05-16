package com.example.intentexamples;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText editParam1, editParam2;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editParam1 = findViewById(R.id.edit_param1);
        editParam2 = findViewById(R.id.edit_param2);
        textResult = findViewById(R.id.text_result);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double p1 = Double.parseDouble(editParam1.getText().toString());
                double p2 = Double.parseDouble(editParam2.getText().toString());

                Intent intent = new Intent(SecondActivity.this, CalculatorActivity.class);
                intent.putExtra("param1", p1);
                intent.putExtra("param2", p2);
                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                double result = data.getDoubleExtra("result", 0.0);
                textResult.setText("Result: " + result);
            }
        }
    }
}