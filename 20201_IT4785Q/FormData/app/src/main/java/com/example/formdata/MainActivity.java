package com.example.formdata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button btSelect;
    Button btRegister;
    EditText editTextLastName;
    EditText editTextFirstName;
    TextView textViewBirthday;
    EditText editTextAddress;
    EditText editTextEmail;
    CheckBox checkBox;
    TextView check;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewBirthday = findViewById(R.id.tv_birthday);
        editTextAddress = findViewById(R.id.et_address);
        editTextEmail = findViewById(R.id.et_email);
        editTextFirstName = findViewById(R.id.et_firstname);
        editTextLastName = findViewById(R.id.et_lastname);
        checkBox = findViewById(R.id.checkbox);
        check = findViewById(R.id.tv_check);
        btSelect = findViewById(R.id.bt_select);
        btRegister = findViewById(R.id.bt_register);

        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textViewBirthday.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setText("Information require: ");
                if (Objects.equals(editTextFirstName.getText().toString(), "")) {
                    check.append("First name");
                }
                if (Objects.equals(editTextLastName.getText().toString(), "")) {
                    check.append("Last name");
                }
                if (Objects.equals(editTextAddress.getText().toString(), "")) {
                    check.append("Address");
                }
                if (Objects.equals(editTextEmail.getText().toString(), "")) {
                    check.append("Email");
                }
                if (Objects.equals(textViewBirthday.getText().toString(), "")) {
                    check.append("Birthday");
                }
                check.setText(check.getText().toString().substring(0, check.getText().toString().length() - 2));
                check.append(".");
                
                if (check.getText().toString().length() == 14) check.setText("");

                if (!checkBox.isChecked()) {
                    if (check.getText().toString().length() > 0) check.append("\n");
                    check.append("Please agree with Terms of Use.");
                }
            }
        });
    }
}

