package com.example.homework05;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.progress;

public class HomeActivity extends AppCompatActivity implements GetSongsAsyncTask.IData,AdapterView.OnItemClickListener{
    Button search;
    EditText name;
    ArrayList<Song> songs;
    SongAdapter songAdapter;
    ListView listViewFav;
    ArrayList<Song> favShared;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        songs = new ArrayList<Song>();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        search = (Button) findViewById(R.id.Search);
        name = (EditText) findViewById(R.id.name);
        listViewFav = (ListView) findViewById(R.id.fav_list);
        progressBar=(ProgressBar)findViewById(R.id.progressBarHomeActivity);

        SharedPreferences sharedPreferences = getSharedPreferences("favList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MyObject", "");
        Song[] obj = gson.fromJson(json, Song[].class);
        favShared = new ArrayList<>(Arrays.asList(obj));
        if (obj != null) {
            songAdapter = new SongAdapter(this, R.layout.activity_result, favShared);
            listViewFav.setAdapter(songAdapter);
            songAdapter.setNotifyOnChange(true);
        }
        listViewFav.setOnItemClickListener(this);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String song_name = name.getText().toString();
                StringBuilder sb = new StringBuilder();
                sb.append("http://ws.audioscrobbler.com/2.0/?method=track.search&track=&api_key=8f0dcf40249910dea4433b9e6ddde034&limit=20&format=json");
                sb.insert(60, song_name);
                new GetSongsAsyncTask(HomeActivity.this).execute(sb.toString());


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setupData(final ArrayList<Song> result) {
        progressBar.setMax(100);

            progressBar.incrementProgressBy(100);

        songs = result;
        Intent intent = new Intent(HomeActivity.this, ResultsActivity.class);
        intent.putExtra("songs", songs);
        if (songs != null)
            startActivity(intent);
        else
            Log.d("demo", "No-results");
            finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                return true;
            case R.id.action_quit:
/*                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Quit")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();*/
                HomeActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(HomeActivity.this, TrackDetailsActivity.class);
        intent.putExtra("song", favShared.get(position));
        startActivity(intent);
        finish();
    }
}
