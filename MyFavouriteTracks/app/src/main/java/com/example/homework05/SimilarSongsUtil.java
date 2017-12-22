
package com.example.homework05;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SimilarSongsUtil {

    static public class SimilarSongsJSONParser {
        static ArrayList<Song> parseSongs(String in) throws JSONException {

            ArrayList<Song> trackslist = new ArrayList<Song>();
            JSONObject root = new JSONObject(in);
            JSONArray tracksJSONArray = root.getJSONObject("similartracks").getJSONArray("track");
            ArrayList<String> images = new ArrayList<>();

            String small_image=null, large_image=null;
            for (int i = 0; i < tracksJSONArray.length(); i++) {
                JSONObject trackJSONObject = tracksJSONArray.getJSONObject(i);
                JSONArray imagesArray = trackJSONObject.getJSONArray("image");

                for(int j=0;j<imagesArray.length();j++) {
                    JSONObject single = imagesArray.getJSONObject(j);
                    String demo=single.getString("size");
                    if (demo.equals( "small" ))
                        small_image = single.getString("#text");

                    else if (demo.equals( "large" ))
                        large_image = single.getString("#text");
                }
                Song song = new Song(trackJSONObject.getString("name"), trackJSONObject.getJSONObject("artist").getString("name"), trackJSONObject.getString("url"), small_image, large_image );
                Log.d("track",trackJSONObject.getJSONObject("artist").getString("name"));
                trackslist.add(song);
            }
            return trackslist;
        }

    }
}

