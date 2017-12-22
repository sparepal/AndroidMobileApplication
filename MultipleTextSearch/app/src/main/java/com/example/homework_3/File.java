package com.example.homework_3;
/*
Created by
        Sai Vikhyat Parepalli
        Geeta Priyanka janapareddy*/

import java.io.Serializable;

/**
 * Created by smank on 9/23/2017.
 */

public class File implements Serializable {
    String key, linebefore, lineafter;

    @Override
    public String toString() {
        return "File{" +
                "key='" + key + '\'' +
                ", linebefore='" + linebefore + '\'' +
                ", lineafter='" + lineafter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (key != null ? !key.equals(file.key) : file.key != null) return false;
        if (linebefore != null ? !linebefore.equals(file.linebefore) : file.linebefore != null)
            return false;
        return lineafter != null ? lineafter.equals(file.lineafter) : file.lineafter == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (linebefore != null ? linebefore.hashCode() : 0);
        result = 31 * result + (lineafter != null ? lineafter.hashCode() : 0);
        return result;
    }

    public File(String key, String linebefore, String lineafter) {
        this.key = key;
        this.linebefore = linebefore;
        this.lineafter = lineafter;
    }

    public String getKey() {
        return key;
    }

    public String getLinebefore() {
        return linebefore;
    }

    public String getLineafter() {
        return lineafter;
    }
}
