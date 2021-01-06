package com.example.golf_score_0_0_1;

import android.annotation.SuppressLint;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonSetter2 extends AppCompatActivity {

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setButton(String name, Button btn) {

        if (name == null || name == "" || name == " ") {
            btn.setEnabled(false);
        } else btn.setEnabled(true);
    }

}
