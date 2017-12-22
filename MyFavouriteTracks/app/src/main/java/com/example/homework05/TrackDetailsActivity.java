package com.example.homework05;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrackDetailsActivity extends AppCompatActivity implements GetSimilarSongsAsyncTask.IData,AdapterView.OnItemClickListener{
    TextView artist, name, url;
    ImageView large_image;
    ArrayList<Song> songs;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarTrackDetails);
        setSupportActionBar(myToolbar);
        Song song = (Song) getIntent().getExtras().get("song");
        name=(TextView)findViewById(R.id.name);
        artist=(TextView)findViewById(R.id.artist);
        url=(TextView)findViewById(R.id.url);
        large_image=(ImageView)findViewById(R.id.large_image);
        final String track_url = song.getUrl().toString();
        progressBar=(ProgressBar)findViewById(R.id.progressBarSimilarSongs);



        name.setText(song.getName().toString());
        artist.setText(song.getArtist().toString());
        url.setText(track_url);
        Picasso.with(this)
                .load(song.getLarge_image()).into(large_image);

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(track_url));
                startActivity(intent);
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("http://ws.audioscrobbler.com/2.0/?method=track.getsimilar&artist=&track=&api_key=8f0dcf40249910dea4433b9e6ddde034&limit=10&format=json");
        sb.insert(72,song.getName());
        sb.insert(65,song.getArtist());
        new GetSimilarSongsAsyncTask(TrackDetailsActivity.this).execute(sb.toString());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent=new Intent(TrackDetailsActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_quit:
              /*  AlertDialog.Builder builder = new AlertDialog.Builder(TrackDetailsActivity.this);
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
                TrackDetailsActivity.this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void setupData(ArrayList<Song> result) {

        songs = result;
        ListView listView = (ListView) findViewById(R.id.ListViewDetailsActivity);
        if (songs != null){
            SongAdapter adapter = new SongAdapter(this, R.layout.activity_result, songs);
            progressBar.setVisibility(View.GONE);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
    }
    else
            Log.d("demo","No results");
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(TrackDetailsActivity.this,TrackDetailsActivity.class);
        intent.putExtra("song",songs.get(position));
        startActivity(intent);
        TrackDetailsActivity.this.finish();
    }
}
