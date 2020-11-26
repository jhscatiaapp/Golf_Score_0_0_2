package com.example.golf_score_0_0_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.golf_score_0_0_1.R.drawable.design_button_add_player;
import static com.example.golf_score_0_0_1.R.drawable.design_button_remove_player;

public class CountingPage extends AppCompatActivity {

    private TextView scoreView, scoreDisp;
    private Button buttonMinus, buttonPlus, buttonOK, buttonReset;
    private Button buttonAddPlayer;
    private int tempScore = 0;
    private int hole = 0;
    private int sumScore = 0;
    private ArrayList<Integer>  arrayScore = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        variablesSetter();
        scoreView.setText(String.valueOf(tempScore));



        buttonAddPlayer.setOnClickListener(v -> {

            /**    Dialog POPup to get name and button OK / CANCEL        */

        });




        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp(++tempScore);
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp(--tempScore);
            }
        });

        buttonOK.setOnClickListener(v -> {
            hole++;
            if (hole <= 18) {
                arrayScore.add(tempScore);
                scoreDisp.append(hole + "  " + tempScore + "\n");
                tempScore = 0;
                scoreView.setText(String.valueOf(tempScore));
                if (hole == 18) {
                    for (int i = 0; i < arrayScore.size(); i++) {
                        sumScore += arrayScore.get(i);
                    }
                    scoreDisp.append("Total Score is " + sumScore + "\n");
                }
            } else {
                Toast.makeText(this, "Finish 18 holes", Toast.LENGTH_SHORT).show();
            }
        });

        buttonReset.setOnClickListener(v -> {
            arrayScore.clear();
            scoreDisp.setText(null);
            hole = 0;
            sumScore = 0;
            tempScore = 0;
            scoreView.setText(String.valueOf(tempScore));
        });


    }

    public void scoreDisp(int score) {
        if (score <= 0) {
            scoreView.setTextColor(Color.BLUE);
        }
        else {
            scoreView.setTextColor(Color.RED);
        }
        scoreView.setText(String.valueOf(score));
    }

    public void variablesSetter() {
        scoreView = findViewById(R.id.view_score1);
        buttonMinus = findViewById(R.id.button_minus1);
        buttonPlus = findViewById(R.id.button_plus1);
        buttonOK = findViewById(R.id.button_ok);
        scoreDisp = findViewById(R.id.textView_score);
        arrayScore.clear();
        buttonReset = findViewById(R.id.button_reset);
        players.clear();
        buttonAddPlayer = findViewById(R.id.button_addName);
        buttonAddPlayer.setBackground(this.getResources().getDrawable(R.drawable.button_add_player));
    }
}