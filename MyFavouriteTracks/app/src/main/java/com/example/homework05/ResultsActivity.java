package com.example.homework05;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ArrayList<Song> songs;
    AlertDialog.Builder builder;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        songs=new ArrayList<Song>();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarResultsActivity);
        setSupportActionBar(myToolbar);
        ListView listView=(ListView)findViewById(R.id.listViewResultsActivity);
        if(getIntent().getExtras()!=null)
        {
            songs=(ArrayList<Song>)getIntent().getExtras().getSerializable("songs");
            SongAdapter adapter=new SongAdapter(this,R.layout.activity_result,songs);
            listView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
        }
        else
        Log.d("demo","No results");
        listView.setOnItemClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent=new Intent(ResultsActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_quit:
                finish();
          /*      builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setTitle("Quit")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", this)
                        .setNegativeButton("No", this);
                alert = builder.create();
                alert.show();*/
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(ResultsActivity.this,TrackDetailsActivity.class);
        intent.putExtra("song",songs.get(position));
        startActivity(intent);
        ResultsActivity.this.finish();
    }

/*    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == -1)



        else
            alert.cancel();


    }*/
}
