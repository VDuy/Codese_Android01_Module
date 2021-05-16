package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView number, result;
    private int op1, op2;
    private int op;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.et_result);
        findViewById(R.id.et_text);

        findViewById(R.id.bt_numSub);
        findViewById(R.id.bt_numMul);
        findViewById(R.id.bt_numDiv);
        findViewById(R.id.bt_numPlus);
        findViewById(R.id.bt_num);
        findViewById(R.id.bt_numCE);
        findViewById(R.id.bt_numC);
        findViewById(R.id.bt_numBS);
        findViewById(R.id.bt_numDot);

        findViewById(R.id.bt_num0);
        findViewById(R.id.bt_num1);
        findViewById(R.id.bt_num2);
        findViewById(R.id.bt_num3);
        findViewById(R.id.bt_num4);
        findViewById(R.id.bt_num5);
        findViewById(R.id.bt_num6);
        findViewById(R.id.bt_num7);
        findViewById(R.id.bt_num8);
        findViewById(R.id.bt_num9);

    }

    @Override
    public void onClick(View v) {

    }

    private void addDigit(int digit) {

    }

    private void selectOperator(int operator) {
        op = operator;
        state = 2;

    }

    private void calculateResult() {

    }

    private void removeDigit() {

    }

}