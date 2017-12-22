package com.example.homework_3;

/*
Created by
        Sai Vikhyat Parepalli
        Geeta Priyanka janapareddy*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class DisplayLines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listline);


        ArrayList<File> lines;
        ArrayList<String> input;


        ListView listView = (ListView) findViewById(R.id.listView);


        if (getIntent().getExtras() != null) {
            lines = (ArrayList<File>) getIntent().getExtras().get("key");
            input = (ArrayList<String>) getIntent().getExtras().get("input");
            listView.setAdapter(new CustomAdapter2(this, lines, input));
        }

    }

}
