/*
Homework01
demopro
Sai Vikhyat Parepalli
Geetha Priyanka Janapareddy
*/

package com.example.vikhy.demopro;

import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    TextView dot;
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView zero;
    TextView mul;
    TextView div;
    TextView sub;
    TextView add;
    TextView AC;
    Button equals;
    Double a = Double.NaN;
    Double b;
    Double result;
    String flag = "";
    int df = 1;



    public void init() {
        zero = (TextView) findViewById(R.id.textView0);
        one = (TextView) findViewById(R.id.textView1);
        two = (TextView) findViewById(R.id.textView2);
        three = (TextView) findViewById(R.id.textView3);
        four = (TextView) findViewById(R.id.textView4);
        five = (TextView) findViewById(R.id.textView5);
        six = (TextView) findViewById(R.id.textView6);
        seven = (TextView) findViewById(R.id.textView7);
        eight = (TextView) findViewById(R.id.textView8);
        nine = (TextView) findViewById(R.id.textView9);
        AC = (TextView) findViewById(R.id.textViewAC);
        add = (TextView) findViewById(R.id.plus);
        display = (TextView) findViewById(R.id.textViewDisplay);
        sub = (TextView) findViewById(R.id.minus);
        mul = (TextView) findViewById(R.id.multiply);
        div = (TextView) findViewById(R.id.divide);
        equals = (Button) findViewById(R.id.equals);
        dot = (TextView) findViewById(R.id.textViewDot);
    }


    public void numberClicked(View view)
    {
        if(display.getText().toString().equals("0"))
            display.setText("");

        if (!Double.isNaN(a) && df == 1) {
            display.setText("");
            df = -1;
        }
        display.setText(display.getText().toString() + ((TextView)view).getText().toString());
    }
    

    public void operatorClicked(View view)
    {
        a = Double.parseDouble(display.getText().toString());

        flag = ((TextView)view).getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        init();


        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Double.isNaN(a) && df == 1) {
                    display.setText("");
                    df = -1;
                }

                if(!display.getText().toString().contains("."))
                    display.setText(display.getText().toString() + ".");
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = Double.parseDouble(display.getText().toString());
                calculate();
                result = a;
                df = 1;
                b = Double.NaN;
                if (result.toString().length() > 14)
                    Math.round(a);
                // Math.r
                display.setText(String.valueOf(result));
            }
        });
        AC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                display.setText("0");

                a = Double.NaN;
                b = 0.0;
                result = 0.0;
            }
        });

    }

    public void calculate() {

        switch (flag) {
            case "+":
                a += b;
                break;
            case "-":
                a -= b;
                break;
            case "*":
                if (Double.isNaN(b))
                    a *= 1;
                else
                    a *= b;
                break;
            case "/":
                if (Double.isNaN(b))
                    a /= 1;
                else

                    try {
                        a /= b;
                    } catch (Exception e) {
                        Log.e("demo", e.getMessage(), e);
                    }

        }


    }
}
