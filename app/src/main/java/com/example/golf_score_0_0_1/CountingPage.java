package com.example.golf_score_0_0_1;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.golf_score_0_0_1.R.drawable.design_button_add_player;
import static com.example.golf_score_0_0_1.R.drawable.design_button_remove_player;

public class CountingPage extends AppCompatActivity {

    private TextView scoreView, scoreDisp;
    private TextView namePlayer1, namePlayer2, namePlayer3, namePlayer4;
    private Button buttonMinus, buttonPlus, buttonOK, buttonReset;
    private Button buttonAddPlayer1, buttonAddPlayer2, buttonAddPlayer3, buttonAddPlayer4;

    private int tempScore = 0;
    private int hole = 0;
    private int sumScore = 0;
    private ArrayList<Integer> arrayScore = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Integer> par = new ArrayList<>();
    private final int PAR3 = 3;
    private final int PAR4 = 4;
    private final int PAR5 = 5;
    private final int PAR6 = 6;
    private RadioButton btnPar3, btnPar4, btnPar5, btnPar;
    private String pars, tempPlayerName;
    private RadioGroup radioGroup;
    private Dialog playerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        variablesSetter();
        scoreView.setText(String.valueOf(tempScore));

        buttonAddPlayer1.setOnClickListener(v -> {
            /**    Dialog POPup to get name and button OK / CANCEL        */
            playerDialog.setContentView(R.layout.dialog_add_player);

            Button OK = playerDialog.findViewById(R.id.dlg_addPlayer_Yes);
            Button CANCEL = playerDialog.findViewById(R.id.dlg_addPlayer_No);
            EditText getPlayerName = playerDialog.findViewById(R.id.editText_inputPlayerName);

            OK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempPlayerName = getPlayerName.getText().toString();
                    players.add(tempPlayerName);
                    namePlayer1.setText(tempPlayerName);
                    playerDialog.dismiss();

                }
            });

            CANCEL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerDialog.dismiss();
                }
            });

            playerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            playerDialog.show();
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
                int radioID = radioGroup.getCheckedRadioButtonId();
                switch (radioID) {
                    case R.id.button_par_3:
                        par.add(PAR3);
                        pars = String.valueOf(PAR3);
                        break;
                    case R.id.button_par_4:
                        par.add(PAR4);
                        pars = String.valueOf(PAR4);
                        break;
                    case R.id.button_par_5:
                        par.add(PAR5);
                        pars = String.valueOf(PAR5);
                        break;
                }

                arrayScore.add(tempScore);
                scoreDisp.append(hole + "  " + pars + "  " + tempScore + "\n");
                tempScore = 0;
                scoreView.setText(String.valueOf(tempScore));

                if (hole == 18) {
                    int totalPar = 0;
                    for (int i = 0; i < arrayScore.size(); i++) {
                        sumScore += arrayScore.get(i);
                        totalPar += par.get(i);
                    }
                    int totalScore = totalPar + sumScore;
                    scoreDisp.append("Total Score is " + sumScore + "\n");
                    scoreDisp.append("Total score/par : " + totalScore + "/" + totalPar);
                }
            } else {
                Toast.makeText(this, "Finish 18 holes", Toast.LENGTH_SHORT).show();
            }
        });

        buttonReset.setOnClickListener(v -> {
            arrayScore.clear();
            par.clear();
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
        } else {
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
        par.clear();
        buttonAddPlayer1 = findViewById(R.id.button_addName1);
        buttonAddPlayer1.setBackground(this.getResources().getDrawable(R.drawable.button_add_player));
        buttonAddPlayer2 = findViewById(R.id.button_addName2);
        buttonAddPlayer2.setBackground(this.getResources().getDrawable(R.drawable.button_add_player));
        btnPar3 = findViewById(R.id.button_par_3);
        btnPar4 = findViewById(R.id.button_par_4);
        btnPar5 = findViewById(R.id.button_par_5);
        radioGroup = findViewById(R.id.rdo_grp);
        playerDialog = new Dialog(CountingPage.this);
        namePlayer1 = findViewById(R.id.textView_player1);
        namePlayer2 = findViewById(R.id.textView_player2);

    }
}