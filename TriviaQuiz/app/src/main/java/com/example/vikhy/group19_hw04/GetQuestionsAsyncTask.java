package com.example.vikhy.group19_hw04;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vikhy on 9/27/2017.
 */


public class GetQuestionsAsyncTask extends AsyncTask<String, Void, ArrayList<Question>> {

    IData activity;

    GetQuestionsAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Question> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                if (QuestionUtil.QuestionsJSONParser.parseQuestions(sb.toString()) != null)

                    return QuestionUtil.QuestionsJSONParser.parseQuestions(sb.toString());

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
        this.activity.setupData(questions);
    }

    public static interface IData {

        public Context getContext();

        public void setupData(ArrayList<Question> result);


    }
}
