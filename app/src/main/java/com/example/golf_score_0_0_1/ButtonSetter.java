package com.example.golf_score_0_0_1;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonSetter extends AppCompatActivity {

    private String mName;


    public ButtonSetter(String name, Button addBtn, Button removeBtn, Button plusBtn, Button minusBtn) {
        name = mName;
        if (name == null || name == "" || name == " ") {
            removeBtn.setEnabled(false);
            plusBtn.setEnabled(false);
            minusBtn.setEnabled(false);
            addBtn.setBackground(this.getResources().getDrawable(R.drawable.player_add_btn));
        } else {
            removeBtn.setEnabled(true);
            plusBtn.setEnabled(true);
            minusBtn.setEnabled(true);
            addBtn.setBackground(this.getResources().getDrawable(R.drawable.player_change_btn));
        }
    }

    public String getmName(String name) {
        mName = name;
        return mName;
    }
}
