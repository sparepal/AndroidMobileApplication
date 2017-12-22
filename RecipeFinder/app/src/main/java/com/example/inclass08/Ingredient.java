package com.example.inclass08;

/**
 * Created by Sai vikhyat Parepalli
 * Geeta priyanka janpareddy
 * on 9/25/2017.
 */


import java.io.Serializable;

/**
 * Created by smank on 9/25/2017.
 */

public class Ingredient implements Serializable {
    String title;
    String ingredients;
    String url;
    String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {

        return image;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}