package com.example.list_based;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> money;
    List<Integer> rate;
    ArrayAdapter<String> arrayAdapter;
    EditText convertfrom;
    TextView convertto;
    Spinner spinnerfrom, spinnerto;
    int ratefrom, rateto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertfrom = findViewById(R.id.et_input);
        convertto = findViewById(R.id.tv_output);

        spinnerfrom = findViewById(R.id.sp_input);
        spinnerfrom.setAdapter(arrayAdapter);
        spinnerto = findViewById(R.id.sp_output);
        spinnerto.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, R.id.tv_output, money);

        money = new ArrayList<>();
        money.add("VND");
        money.add("USD");
        money.add("EUR");

        rate = new ArrayList<>();
        rate.add(1);
        rate.add(23176);
        rate.add(27163);

        convertfrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertto.setText(Integer.toString(convert()));
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
        spinnerfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ratefrom = rate.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rateto = rate.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public int convert() {
        if (convertto.getText().toString().equals("")) return 0;
        else return Integer.parseInt(convertto.getText().toString()) * ratefrom / rateto;
    }
}
