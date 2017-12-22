package com.example.vikhy.group19_hw04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vikhy on 9/27/2017.
 */


public class QuestionUtil {

    static public class QuestionsJSONParser {
        static ArrayList<Question> parseQuestions(String in) throws JSONException {

            ArrayList<Question> questionsList = new ArrayList<Question>();
            JSONObject root = new JSONObject(in);
            JSONArray questionsJSONArray = root.getJSONArray("questions");
            for (int i = 0; i < questionsJSONArray.length(); i++) {
                JSONObject questionJSONObject = questionsJSONArray.getJSONObject(i);
                ArrayList<String> options = new ArrayList<>();
                JSONArray jsonArray = questionJSONObject.getJSONObject("choices").getJSONArray("choice");
                for (int j = 0; j < jsonArray.length(); j++)
                    options.add(jsonArray.get(j).toString());
                String a = questionJSONObject.getString("id");
                String b = questionJSONObject.getString("text");
                String image = " ";

                if (questionJSONObject.has("image"))
                    image = questionJSONObject.getString("image");
                String d = questionJSONObject.getJSONObject("choices").getString("answer");

                Question question = new Question(Integer.parseInt(questionJSONObject.getString("id")), questionJSONObject.getString("text"), image, options, Integer.parseInt(d));
                questionsList.add(question);
            }
            return questionsList;
        }

    }
}

