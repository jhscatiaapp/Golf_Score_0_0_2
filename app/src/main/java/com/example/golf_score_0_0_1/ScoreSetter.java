package com.example.golf_score_0_0_1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreSetter extends AppCompatActivity {

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setScore(String name, TextView view) {

        if (name == null || name == "" || name == " ") {
            view.setTextColor(0xFFA5A5A5);
            view.setText("0");
        } else {
            view.setTextColor(Color.BLACK);
            view.setText("0");
        }
    }

}
