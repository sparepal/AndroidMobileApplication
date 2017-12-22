package com.example.homework_3;
/*
Created by
        Sai Vikhyat Parepalli
        Geeta Priyanka janapareddy*/

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> words;
    ProgressBar progressBar;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        words = new ArrayList<String>();

        InputStream is = null;
        try {
            is = getAssets().open("textfile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int countMax = 0;
        try {
            while (br.readLine() != null) {
                countMax++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(countMax);


        final EditText word = (EditText) findViewById(R.id.editTextWord);
        adapter = new CustomAdapter(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        findViewById(R.id.floatingActionButtonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String w = word.getText().toString();
                if (w.trim().length() > 0 && w != null) {
                    if (adapter.getCount() < 20)
                        adapter.add(w);
                    else
                        Toast.makeText(MainActivity.this, "Max-entries", Toast.LENGTH_SHORT).show();
                    word.setText("");

                } else
                    Toast.makeText(MainActivity.this, "Input a keyword", Toast.LENGTH_SHORT).show();


            }
        });

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                word.setFocusable(false);

                words.addAll(adapter.allWords());


                CheckBox checkBox = (CheckBox) (findViewById(R.id.checkBox));
                if (checkBox.isChecked())

                    new doWork().execute(1);
                else
                    new doWork().execute(0);
            }
        });


    }

    class doWork extends AsyncTask<Integer, Integer, ArrayList<File>> {

        ArrayList<File> result = new ArrayList<>();
        InputStream is;
        BufferedReader br;
        String line, line2;
        int count;
        ArrayList<String> A = new ArrayList<>(words);
        int counter = 0;


        @Override
        protected void onPostExecute(ArrayList<File> files) {
            if (files != null) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                Intent intent = new Intent(MainActivity.this, DisplayLines.class);
                intent.putExtra("key", files);
                intent.putExtra("input", A);
                startActivity(intent);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected ArrayList<File> doInBackground(Integer... params) {

            int match_case = params[0];

            try {
                // result = new ArrayList<>();
                is = getAssets().open("textfile.txt");
                br = new BufferedReader(new InputStreamReader(is));
                boolean line2Used = true;


                while (true) {


                    if (line2Used) {


                        line = br.readLine();
                        if (line == null)
                            break;

                        counter++;


                        line = line.replaceAll("\\W", " ");

                        while (line.length() <= 30) {
                            String lineTemp = br.readLine();
                            counter++;
                            if (lineTemp == null)
                                break;

                            line = line + lineTemp;
                            line = line.replaceAll("\\W", " ");

                        }
                    }
                    line2 = "";
                    while (line2.length() <= 30) {
                        String lineTemp = br.readLine();
                        counter++;
                        if (lineTemp == null)
                            break;
                        line2 = line2 + lineTemp;
                        line2 = line2.replaceAll("\\W", " ");

                    }
                    if (line2.length() < 30)
                        break;


                    for (int i = 0; i < A.size(); i++) {
                        //   progressBar.incrementProgressBy(((i+1)/(A.size()+1))*100);
                        if (match_case == 1) {
                            if (line.contains(A.get(i))) {

                                count++;

                                int idx = line.indexOf(A.get(i));
                                if ((idx - 10 > 0) && ((idx + 20) < (line.length()))) {
                                    String sub = line.substring(idx - 10, idx + 20);

                                    /////////////
                                    result.add(new File(A.get(i), line.substring(idx - 10, idx), line.substring(idx + A.get(i).length(), idx + 20)));
                                    line2Used = false;

                                } else if (idx - 10 < 0) {
                                    count++;

                                    int t = 10 - idx;
                                    String sub = "";

                                    sub = line.substring(0, idx + 20 + t);

                                    result.add(new File(A.get(i), line.substring(0, t), line.substring(A.get(i).length() + t, idx + 20)));


                                    line2Used = false;

                                } else if (idx + 20 > (line.length())) {
                                    count++;
                                    int t = 20 + idx - line.length();
                                    String sub = "";
                                    sub = line.substring(idx - 10, line.length());
                                    if (line2 != null) {
                                        if (line2.length() > t) {
                                            sub = sub + line2.substring(0, t);
                                            result.add(new File(A.get(i), line.substring(0, 10), line.substring(idx + A.get(i).length(), line.length()) + line2.substring(0, t)));

                                        } else {
                                            sub = sub + line2.substring(0, line2.length() - 1);
                                            result.add(new File(A.get(i), line.substring(0, 10), line.substring(idx + A.get(i).length(), line.length()) + line2.substring(0, line2.length())));


                                        }
                                    } else {

                                        result.add(new File(A.get(i), line.substring(0, 10), line.substring(idx + A.get(i).length(), line.length())));

                                    }
                                    line2Used = true;


                                }


                            }
                        } else {
                            String smallline = line.toLowerCase();
                            String smallsearch = A.get(i).toLowerCase();

                            if (smallline.contains(smallsearch)) {
                                String tempLine, tempLine2, wordTemp;

                                tempLine = line;
                                tempLine2 = line2;
                                wordTemp = A.get(i);

                                line = line.toLowerCase();
                                line2 = line2.toLowerCase();
                                //smallsearch = smallsearch.toLowerCase();

                                int idx = line.indexOf(smallsearch);
                                if ((idx - 10 > 0) && ((idx + 20) < (line.length()))) {
                                    count++;
                                    String sub = tempLine.substring(idx - 10, idx + 20);

                                    result.add(new File(A.get(i), line.substring(idx - 10, idx), line.substring(idx + A.get(i).length(), idx + 20)));
                                    line2Used = false;
                                } else if (idx - 10 < 0) {
                                    count++;

                                    int t = 10 - idx;
                                    String sub = "";

                                    sub = tempLine.substring(0, idx + 20 + t);

                                    result.add(new File(A.get(i), line.substring(0, t), line.substring(A.get(i).length() + t, idx + 20)));
                                    line2Used = false;
                                } else if (idx + 20 > (line.length())) {
                                    count++;
                                    int t = 20 + idx - line.length();
                                    String sub = "";
                                    sub = tempLine.substring(idx - 10, line.length());
                                    if (line2 != null) {
                                        if (line2.length() > t) {
                                            sub = sub + tempLine2.substring(0, t);
                                        } else {
                                            sub = sub + tempLine2.substring(0, tempLine2.length() - 1);

                                        }
                                    }

                                    result.add(new File(A.get(i), line.substring(0, 10), line.substring(idx + A.get(i).length(), line.length()) + line2.substring(0, t)));
                                    line2Used = true;

                                }


                            }

                        }

                        // }


                    }
                    publishProgress(counter);


                    if (!line2Used) {
                        line = line2;

                    }

                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return result;
        }


    }

    public void method12(int pos) {
        adapter.remove(pos);
        adapter.notifyDataSetChanged();

    }

}



