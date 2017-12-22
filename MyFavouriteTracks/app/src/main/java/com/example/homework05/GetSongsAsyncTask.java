package com.example.homework05;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class GetSongsAsyncTask extends AsyncTask<String, Void, ArrayList<Song>> {

    IData activity;

    GetSongsAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Song> doInBackground(String... params) {
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
                if (SongsUtil.SongsJSONParser.parseSongs(sb.toString()) != null)

                    return SongsUtil.SongsJSONParser.parseSongs(sb.toString());

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    protected void onPostExecute(ArrayList<Song> songs) {
        super.onPostExecute(songs);
        this.activity.setupData(songs);


    }

    public static interface IData {

        public Context getContext();

        public void setupData(ArrayList<Song> result);


    }
}
