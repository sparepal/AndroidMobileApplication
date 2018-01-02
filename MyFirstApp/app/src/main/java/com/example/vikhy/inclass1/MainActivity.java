package com.example.vikhy.inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView shape;
    double area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m);
        shape = (TextView) findViewById(R.id.textView3);


        ImageView img1 = (ImageView) findViewById(R.id.imageView);
        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                findViewById(R.id.editText2).setVisibility(View.VISIBLE);
                findViewById(R.id.textView5).setVisibility(View.VISIBLE);
                shape.setText("Triangle");
            }
        });
        ImageView img2 = (ImageView) findViewById(R.id.imageView2);
        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.editText2).setVisibility(View.GONE);
                findViewById(R.id.textView5).setVisibility(View.GONE);
                shape.setText("Square");
            }
        });
        ImageView img3 = (ImageView) findViewById(R.id.imageView3);
        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shape.setText("Circle");
                findViewById(R.id.editText2).setVisibility(View.GONE);
                findViewById(R.id.textView5).setVisibility(View.GONE);
            }
        });


        Button c = (Button) findViewById(R.id.button);

        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView t = (TextView) findViewById(R.id.textView2);

                if (shape.getText() == "Triangle") {
                    EditText e1 = (EditText) findViewById(R.id.editText);
                    EditText e2 = (EditText) findViewById(R.id.editText2);
                    TextView textView=(TextView)findViewById(R.id.textView5);
                    textView.setText("Base");
                    TextView textView2=(TextView)findViewById(R.id.textView4);
                    textView2.setText("Height");
                    area = 0.5 * Double.parseDouble(e1.getText().toString()) * Double.parseDouble(e2.getText().toString());
                    t.setText(String.valueOf(area));
                } else if (shape.getText() == "Square") {
                    EditText e1 = (EditText) findViewById(R.id.editText);
                    EditText e2 = (EditText) findViewById(R.id.editText2);
                    e2.setVisibility(View.GONE);
                    TextView textView=(TextView)findViewById(R.id.textView5);
                    textView.setText("Side");

                    area = Double.parseDouble(e1.getText().toString()) * Double.parseDouble(e1.getText().toString());
                    Log.d("demo", "fsddsv");
                    t.setText(String.valueOf(area));
                } else if (shape.getText() == "Circle") {
                    EditText e1 = (EditText) findViewById(R.id.editText);
                    EditText e2 = (EditText) findViewById(R.id.editText2);
                    e2.setVisibility(View.GONE);
                    TextView textView=(TextView)findViewById(R.id.textView5);
                    textView.setText("Radius");

                    area = (22 / 7) * Double.parseDouble(e1.getText().toString()) * Double.parseDouble(e1.getText().toString());
                    t.setText(String.valueOf(area));
                }
            }

        });


        Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                           //Clear function
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setText("");
            }
        });
    }


}





