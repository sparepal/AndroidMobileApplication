package com.example.vikhy.group19_hw04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetQuestionsAsyncTask.IData {
    ProgressBar progressBar;
    Button buttonStart;
    TextView textView;
    ImageView imageView;
    Button buttonExit;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        textView = (TextView) findViewById(R.id.textViewLoad);
        imageView = (ImageView) findViewById(R.id.imageView);
        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonStart.setEnabled(false);

        new GetQuestionsAsyncTask(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
                intent.putExtra("questions", questions);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setupData(final ArrayList<Question> result) {
        questions = result;
        buttonStart.setEnabled(true);
        textView.setText("Trivia Ready");
        progressBar.setVisibility(View.INVISIBLE);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.trivia));


        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }
}
