package com.example.homework05;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by vikhy on 10/10/2017.
 */

public class SongAdapter extends ArrayAdapter<Song>{
    ArrayList<Song> songs;
    ArrayList<Song> favSongs = new ArrayList<>();
    Context mContext;
    int mResource;
    SharedPreferences sharedPreferences;

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, List<Song> objects) {
        super(context, resource, objects);
        songs = (ArrayList<Song>) objects;
        this.mContext = context;
        mResource = resource;
        sharedPreferences = mContext.getSharedPreferences("favList", MODE_PRIVATE);
            String json1 = sharedPreferences.getString("MyObject", "");
            Gson gson = new Gson();
            Song[] obj = gson.fromJson(json1, Song[].class);
            ArrayList<Song> favShared = new ArrayList<>(Arrays.asList(obj));
            favSongs.addAll(favShared);

    }
    @Override
    public int getCount() {
        return songs.size();
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        TextView artistName = (TextView) convertView.findViewById(R.id.textViewArtistName);
        artistName.setText(songs.get(position).getArtist());
        TextView trackName = (TextView) convertView.findViewById(R.id.textViewTrackName);
        trackName.setText(songs.get(position).getName());
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewSmallImage);
        Picasso.with(getContext()).load(songs.get(position).getSmall_image()).into(imageView);
        final ImageView  imageViewStar = (ImageView) convertView.findViewById(R.id.imageViewFavourite);

        for (int i=0;i<favSongs.size();i++){
            if(songs.get(position).equals(favSongs.get(i)))
                songs.get(position).favorited=true;
        }
        if (songs.get(position).isFavorited())
            imageViewStar.setImageResource(android.R.drawable.btn_star_big_on);
        else
            imageViewStar.setImageResource(android.R.drawable.btn_star_big_off);


        final View finalConvertView = convertView;
        imageViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (!songs.get(position).isFavorited()) {
                        songs.get(position).setFavorited(true);
                        ((ImageView) v).setImageResource(android.R.drawable.btn_star_big_on);
                        favSongs.add(getItem(position));
                        Toast.makeText(mContext, "You have favorited  " + getItem(position).getName(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(favSongs);
                        editor.putString("MyObject", json);
                        editor.commit();

                    } else if (songs.get(position).isFavorited()) {

                        ((ImageView) v).setImageResource(android.R.drawable.btn_star_big_off);
                        favSongs.remove(songs.get(position));
                        Toast.makeText(mContext, "Track removed from favorites", Toast.LENGTH_SHORT).show();
                        songs.get(position).setFavorited(false);
                        if (mContext instanceof HomeActivity)
                        {
                            songs.remove(getItem(position));
                            notifyDataSetChanged();
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(favSongs);
                        editor.putString("MyObject", json);
                        editor.commit();
                        setNotifyOnChange(true);
                    }
                }catch (Exception e){
                    Log.d("demo","position doesn't exist anymore");
            }


            }
        });
        return convertView;
    }


}
