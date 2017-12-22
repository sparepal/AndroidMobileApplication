package com.example.vikhy.group19_hw04;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikhy on 9/27/2017.
 */

public class Question implements Serializable {
    int id;
    String questionText;
    String image;
    ArrayList<String> options;
    int answer;

    public Question(int id, String questionText, String image, ArrayList<String> options, int answer) {
        this.id = id;
        this.questionText = questionText;
        this.image = image;
        this.options=options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }
}
