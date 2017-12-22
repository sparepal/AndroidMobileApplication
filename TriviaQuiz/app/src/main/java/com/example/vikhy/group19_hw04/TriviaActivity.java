package com.example.vikhy.group19_hw04;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TriviaActivity extends AppCompatActivity {

    ArrayList<Question> questions;
    ImageView imageView;
    TextView textViewQno;
    TextView textViewTimeLeft;
    TextView textViewQuestion;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    int qNo, count = 0;
    boolean isRunning = false;
    int solution[] = new int[20];
    int sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        final CountDown timer = new CountDown(120000, 1000);
        timer.start();
        Button buttonQuit = (Button) findViewById(R.id.buttonQuit);
        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        textViewQno = (TextView) findViewById(R.id.textViewQno);
        imageView = (ImageView) findViewById(R.id.imageViewTrivia);
        textViewTimeLeft = (TextView) findViewById(R.id.textViewTimeLeft);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        progressBar = (ProgressBar) findViewById(R.id.progressBarTrivia);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        textViewQno.setText("Q1");
        textViewTimeLeft.setText("Time left:" + "X" + "seconds ");
        qNo = 0;
        questions = new ArrayList<>();
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(TriviaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        if (getIntent().getExtras() != null)

            questions = (ArrayList) getIntent().getExtras().getSerializable("questions");
        else
            Log.d("demo", "Null-Result");

        setDisplay();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioButtonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonID);
                int position = group.indexOfChild(radioButton);
                solution[qNo] = position + 1;
                Log.d("ans", String.valueOf(solution[qNo]));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qNo++;
                if (qNo <= 15)
                    setDisplay();
                else if (qNo > 15) {
                    for (int i = 0; i < 16; i++) {
                        if (solution[i] == questions.get(i).getAnswer()) {
                            count++;
                        }
                    }
                    Intent intent = new Intent(TriviaActivity.this, TriviaStats.class);
                    sent =1;
                    intent.putExtra("count", count);
                    intent.putExtra("questions", questions);
                    startActivity(intent);
                }

            }
        });
    }


    public void setDisplay() {

        progressBar.setVisibility(View.VISIBLE);
        textViewQno.setText("Q" + String.valueOf(qNo + 1));
        if (!questions.get(qNo).getImage().equals(" ")) {

            Picasso.with(this)
                    .load(questions.get(qNo).getImage())
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError() {
                            Toast.makeText(TriviaActivity.this, "Couldn't download image", Toast.LENGTH_SHORT).show();


                        }
                    });

        } else
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.trivia));


        textViewQuestion.setText(questions.get(qNo).getQuestionText());
        ArrayList<String> choices = questions.get(qNo).getOptions();
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.removeAllViews();

        for (int i = 0; i < choices.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(choices.get(i));
            radioGroup.addView(radioButton);
        }
    }


    //countdown class
    public class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //      boolean isRunning = true;

            long ms = millisUntilFinished;
            String text = String.format("%02d\' %02d\"",
                    TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                    TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
            textViewTimeLeft.setText("Time left : " + text);
        }

        @Override
        public void onFinish() {
            textViewTimeLeft.setText("Time left : " + "00'00\"");
            if(sent==0) {

                for (int i = 0; i < 16; i++)

                    if (solution[i] == questions.get(i).getAnswer())

                        count++;


                Intent intent = new Intent(TriviaActivity.this, TriviaStats.class);
                intent.putExtra("count", count);
                intent.putExtra("questions", questions);
                startActivity(intent);
            }

        }
    }
}









