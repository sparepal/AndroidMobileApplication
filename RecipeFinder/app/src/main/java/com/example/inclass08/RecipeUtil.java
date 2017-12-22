package com.example.inclass08;

/**
 * Created by Sai vikhyat Parepalli
 * Geeta priyanka janpareddy
 * on 9/25/2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by smank on 9/25/2017.
 */

public class RecipeUtil {

        static ArrayList<Ingredient> parseIngredients(String in) throws JSONException {

            ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
            JSONObject root = new JSONObject(in);
            JSONArray personsJSONArray = root.getJSONArray("results");
            for (int i = 0; i < personsJSONArray.length(); i++) {
                JSONObject ingredientJSONObject = personsJSONArray.getJSONObject(i);

                Ingredient ingredient = new Ingredient();
                ingredient.setTitle(ingredientJSONObject.getString("title"));
                ingredient.setImage(ingredientJSONObject.getString("thumbnail"));
                ingredient.setIngredients(ingredientJSONObject.getString("ingredients"));
                ingredient.setUrl(ingredientJSONObject.getString("href"));
                ingredientsList.add(ingredient);
            }
            return ingredientsList;
        }
  /*      static public class IngredientsPullParser {

        Ingredient ingredient;

*//*        static ArrayList<Ingredient> parseIngredients(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Ingredient ingredient =null;
            ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();

            int event = parser.getEventType();
            while (event !=XmlPullParser.END_DOCUMENT)
            {
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("recipe"))
                        {
                            ingredient = new Ingredient();
                            //ingredient.setId(parser.getAttributeValue(null,"id"));
                        }
                        else if(parser.getName().equals("title"))
                        {
                            ingredient.setTitle(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("href"))
                        {
                            ingredient.setUrl(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("ingredients"))
                        {
                            ingredient.setIngredients(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("thumbnail"))
                            ingredient.setImage(parser.nextText().trim());
                        else{
                            int var =0;
                        }
                            //System.out.println(parser.nextText().trim());
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("recipe")){
                            ingredientsList.add(ingredient);
                            ingredient = null;}
                    default: break;
                }

                event=parser.next();
            }
            return ingredientsList;
        }


    }*/
}


