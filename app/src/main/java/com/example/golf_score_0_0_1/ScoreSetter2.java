package com.example.golf_score_0_0_1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreSetter2 extends AppCompatActivity {

    @SuppressLint("UseCompatLoadingForDrawables")
    public String setScore(String name, int score) {
        String retString;
        String strScore = String.valueOf(score);

        if (name == null || name == "" || name == " ") {
            retString = "-";
        } else {
            retString = strScore;
        }

        return retString;
    }

}
