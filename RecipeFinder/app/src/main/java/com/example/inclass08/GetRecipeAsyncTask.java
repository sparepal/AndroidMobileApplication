package com.example.inclass08;

/**
 * Created by Sai vikhyat Parepalli
 * Geeta priyanka janpareddy
 * on 9/25/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by smank on 9/25/2017.
 */

public class GetRecipeAsyncTask extends AsyncTask<String, Void, ArrayList<Ingredient>> {
    ProgressDialog mProgressDialog;

    IData activity;

    GetRecipeAsyncTask(IData activity) {
        this.activity = activity;
    }



    @Override
    protected ArrayList<Ingredient> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();


                String line = reader.readLine();

                while (line != null){
                    builder.append(line);
                    line = reader.readLine();
                }

                return RecipeUtil.parseIngredients(builder.toString());
            }
                else {

                    Log.d("demo", "No results");

                }
            } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static interface IData {

        public Context getContext();

        public void setupData(ArrayList<Ingredient> result);


    }

    @Override
    protected void onPostExecute(ArrayList<Ingredient> ingredients) {
        super.onPostExecute(ingredients);

        this.activity.setupData(ingredients);


    }
}