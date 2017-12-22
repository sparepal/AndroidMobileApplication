package com.example.vikhy.group19_hw04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaStats extends Activity {
    int total_count;
    ArrayList<Question> questions;
    double percentage = 0;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_stats);

        if (getIntent().getExtras() != null) {
            questions = (ArrayList) (getIntent().getExtras().getSerializable("questions"));
            total_count = (int) getIntent().getExtras().getInt("count");
            percentage = ((double) total_count / 16) * 100;
        }
        percent = (TextView) findViewById(R.id.textViewPercent);
        percent.setText(Double.toString(percentage) + "%");

        if (percentage == 100)
            ((TextView) findViewById(R.id.textViewComment)).setText("Congrats, You got them all correct");

        ((ProgressBar) findViewById(R.id.progressBar2)).setProgress((int) percentage);


        findViewById(R.id.buttonQuitStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaStats.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaStats.this, TriviaActivity.class);
                intent.putExtra("questions", questions);
                startActivity(intent);
            }
        });

    }

}
