package ru.ifmo.android_2016.calc;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;



public class CalculatorActivity extends Activity implements OnClickListener {

    private Button d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, clear, div, mul, sub, eqv, add;

    private TextView result;
    private double param1, param2;
    private String sign;
    private boolean error;
    private boolean reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        d0 = (Button) findViewById(R.id.d0);
        d1 = (Button) findViewById(R.id.d1);
        d2 = (Button) findViewById(R.id.d2);
        d3 = (Button) findViewById(R.id.d3);
        d4 = (Button) findViewById(R.id.d4);
        d5 = (Button) findViewById(R.id.d5);
        d6 = (Button) findViewById(R.id.d6);
        d7 = (Button) findViewById(R.id.d7);
        d8 = (Button) findViewById(R.id.d8);
        d9 = (Button) findViewById(R.id.d9);
        clear = (Button) findViewById(R.id.clear);
        div = (Button) findViewById(R.id.div);
        mul = (Button) findViewById(R.id.mul);
        sub = (Button) findViewById(R.id.sub);
        eqv = (Button) findViewById(R.id.eqv);
        add = (Button) findViewById(R.id.add);

        result = (TextView) findViewById(R.id.result);


        d0.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        d9.setOnClickListener(this);
        clear.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        eqv.setOnClickListener(this);

        if (savedInstanceState != null){

            param1 = savedInstanceState.getFloat("param1");
            param2 = savedInstanceState.getFloat("param2");
            sign = savedInstanceState.getString("sign");
            result.setText(savedInstanceState.getString("result"));
            reset = savedInstanceState.getBoolean("reset");

        } else {
            param1 = 0;
            param2 = 0;
            sign = "";
            result.setText("0");
            reset = false;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d0:
                Checking(0);
                break;
            case R.id.d1:
                Checking(1);
                break;
            case R.id.d2:
                Checking(2);
                break;
            case R.id.d3:
                Checking(3);
                break;
            case R.id.d4:
                Checking(4);
                break;
            case R.id.d5:
                Checking(5);
                break;
            case R.id.d6:
                Checking(6);
                break;
            case R.id.d7:
                Checking(7);
                break;
            case R.id.d8:
                Checking(8);
                break;
            case R.id.d9:
                Checking(9);
                break;
            case R.id.clear:
                sign = "";
                param2 = 0;
                param1 = 0;
                result.setText("0");
                break;
            case R.id.add:
                if (sign != "") {
                    Continuing();
                }
                sign = "+";
                break;
            case R.id.sub:
                if (sign != "") {
                    Continuing();
                }
                sign = "-";
                break;
            case R.id.div:
                if (sign != "") {
                    Continuing();
                }
                sign = "/";
                break;
            case R.id.mul:
                if (sign != "") {
                    Continuing();
                }
                sign = "*";
                break;
            case R.id.eqv:
                Continuing();
                break;

        }
    }

    public void Checking(float num) {
        if (sign.equals("") && reset == true) {
            param1 = 0;
        }
        reset = false;
        if (sign.equals("")) {
            param1 = param1 * 10 + num;
            setResult(param1);
        } else {
            param2 = param2 * 10 + num;
            setResult(param2);
        }
    }

    private void setResult(double num) {
        if (Math.abs(Math.round(num) - num) < 0.001) {
            result.setText(String.valueOf((int) Math.round(num)));
        } else {
            result.setText(String.valueOf(num));
        }
    }

    public void Continuing() {
        switch (sign) {
            case "-":
                param1 -= param2;
                break;
            case "+":
                param1 += param2;
                break;
            case "*":
                param1 *= param2;
                break;
            case "/":
                error = false;
                if (param2 != 0) {
                    param1 /= param2;
                } else {
                    error = true;
                }
                break;
        }
        if (error == false) {
            setResult(param1);
            reset = true;
        } else {
            result.setText("division by zero");
            param1 = 0;
        }
        param2 = 0;
        sign = "";
        error = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("param1", param1);
        outState.putDouble("param2", param2);
        outState.putString("sign",sign);
        outState.putString("result", result.getText().toString());
        outState.putBoolean("reset",reset);
    }
}
