package com.example.homework05;

import java.io.Serializable;

/**
 * Created by smank on 10/10/2017.
 */

public class Song implements Serializable {
    String name, artist, url, small_image, large_image;
    boolean favorited;

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (name != null ? !name.equals(song.name) : song.name != null) return false;
        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        if (url != null ? !url.equals(song.url) : song.url != null) return false;
        if (small_image != null ? !small_image.equals(song.small_image) : song.small_image != null)
            return false;
        return large_image != null ? large_image.equals(song.large_image) : song.large_image == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (small_image != null ? small_image.hashCode() : 0);
        result = 31 * result + (large_image != null ? large_image.hashCode() : 0);
        return result;
    }

    public Song(String name, String artist, String url, String small_image, String large_image) {
        this.name = name;
        this.artist = artist;
        this.url = url;

        this.small_image = small_image;
        this.large_image = large_image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", url='" + url + '\'' +
                ", small_image='" + small_image + '\'' +
                ", large_image='" + large_image + '\'' +
                '}';
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public String getSmall_image() {
        return small_image;
    }

    public String getLarge_image() {
        return large_image;
    }
}
