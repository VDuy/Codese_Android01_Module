package com.example.codese_android01_todo.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codese_android01_todo.R;
import com.example.codese_android01_todo.database.DatabaseUtils;
import com.example.codese_android01_todo.model.TodoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNoteActivity<AddAction> extends AppCompatActivity {


    private static final String TAG = "Length";
    @BindView(R.id.sp_tag)
    Spinner spTag;

    @BindView(R.id.et_set_title)
    EditText etSetTitle;
   @BindView(R.id.et_set_date)
   EditText etSetDate;
    @BindView(R.id.et_set_time)
    EditText etSetTime;

    @BindView(R.id.et_set_content)
    EditText etSetContent;
    @BindView(R.id.bt_save)
    Button btSave;

    TodoModel todoModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        ButterKnife.bind(this);
        setSpinner();
    }

    public void setDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
                etSetDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public void setTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                etSetTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    public void setSpinner() {
        ArrayList<String> arrayListTodo = new ArrayList<String>();
        arrayListTodo.add("Family");
        arrayListTodo.add("Work");
        arrayListTodo.add("Home");
        arrayListTodo.add("Default");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayListTodo);
        spTag.setAdapter(arrayAdapter);
        spTag.setSelection(0);
    }

    @OnClick({R.id.bt_save, R.id.et_set_time, R.id.et_set_date})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                int length = getIntent().getIntExtra("length", 0);
                Log.d(TAG, "onViewClick: " + length);
                DatabaseUtils.getInstance(CreateNoteActivity.this)
                        .AddModel(etSetTitle.getText().toString(),
                                spTag.getSelectedItem().toString(),
                                etSetContent.getText().toString(),
                                etSetDate.getText().toString(), length);
                Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.et_set_date:
                setDate();
                break;
            case R.id.et_set_time:
                setTime();
                break;
        }
    }
}
