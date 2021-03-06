package com.example.golf_score_0_0_1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonSetter extends AppCompatActivity {

    private String mName;
    private Button mAddBtn, mRemoveButton, mPlusButton, mMinusButton;
    private int mCount;

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setButton() {
        if (mCount < 19) {
            if (mName == null || mName == "" || mName == " ") {
                mRemoveButton.setEnabled(false);
                mPlusButton.setEnabled(false);
                mMinusButton.setEnabled(false);
                //mAddBtn.setEnabled(true);
            } else {
                mRemoveButton.setEnabled(true);
                mPlusButton.setEnabled(true);
                mMinusButton.setEnabled(true);
                //mAddBtn.setBackground(getResources().getDrawable(R.drawable.player_change_btn));
            }
        } else {
            mAddBtn.setEnabled(false);
            mRemoveButton.setEnabled(false);
            mPlusButton.setEnabled(false);
            mMinusButton.setEnabled(false);
        }
    }

    public void initSetButton(String name, Button addBtn, Button removeBtn, Button plusBtn, Button minusBtn, int count) {
        mName = name;
        mAddBtn = addBtn;
        mRemoveButton = removeBtn;
        mPlusButton = plusBtn;
        mMinusButton = minusBtn;
        mCount = count;
    }

    public void getName(String name) {
        mName = name;
    }
    public void getAddButton(Button button) {
        mAddBtn = button;
    }
    public void getRemoveButton(Button button) {
        mRemoveButton = button;
    }
    public void getPlusButton(Button button) {
        mPlusButton = button;
    }
    public void getMinusButton(Button button) {
        mMinusButton = button;
    }
}
