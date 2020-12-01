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

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.golf_score_0_0_1.R.drawable.design_button_add_player;
import static com.example.golf_score_0_0_1.R.drawable.design_button_remove_player;

public class CountingPage extends AppCompatActivity {

    private TextView scoreView1, scoreView2, scoreDisp;
    private TextView namePlayer1, namePlayer2, namePlayer3, namePlayer4;
    private Button buttonMinus1, buttonPlus1, buttonOK, buttonReset;
    private Button buttonMinus2, buttonPlus2;
    private Button buttonAddPlayer1, buttonAddPlayer2, buttonAddPlayer3, buttonAddPlayer4;
    private Button buttonRemovePlayer1, buttonRemovePlayer2, buttonRemovePlayer3, buttonRemovePlayer4;

    private int tempScore1 = 0, tempScore2 = 0;
    private int hole = 0;
    private int sumScore = 0;
    private ArrayList<Integer> arrayScore1 = new ArrayList<>();
    private ArrayList<Integer> arrayScore2 = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Integer> par = new ArrayList<>();
    private final int PAR3 = 3;
    private final int PAR4 = 4;
    private final int PAR5 = 5;
    private final int PAR6 = 6;
    private RadioButton btnPar3, btnPar4, btnPar5, btnPar;
    private String pars, tempPlayerName;
    private RadioGroup radioGroup;
    private Dialog playerDialog, confirmRemoveDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        variablesSetter();
        //buttonDesignSetting();
        scoreView1.setText(String.valueOf(tempScore1));
        scoreView2.setText(String.valueOf(tempScore2));

        buttonAddPlayer1.setOnClickListener(v -> {
            /**    Dialog POPup to get name and button OK / CANCEL        */
            clickAddPlayer();
        });


        buttonRemovePlayer1.setOnClickListener(v -> {
            clickRemovePlayer1();
        });

        buttonPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp1(++tempScore1);
            }
        });

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp1(--tempScore1);
            }
        });

        buttonPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp2(++tempScore2);
            }
        });

        buttonMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDisp2(--tempScore2);
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

                /**    Put PAR info to score card acc to HOLE info        */


                /**    데이터 구조의 전면적인 개편 필요. 루프 구문/어레이 데이터 구조를 통해
                 *     전면적인 개편일 필요. 디스플레이도 루프구문으로 계속 돌려야 함          */

                TextView parInfo01 = findViewById(R.id.hole_no1_par);
                TextView parInfo02 = findViewById(R.id.hole_no2_par);

                parInfo01.setText(pars);

                arrayScore1.add(tempScore1);
                arrayScore2.add(tempScore2);

                scoreDisp.append(hole + "  " + pars + "  " + tempScore1 + "  " + tempScore2 + "\n");
                tempScore1 = 0;
                tempScore2 = 0;
                scoreView1.setText(String.valueOf(tempScore1));
                scoreView2.setText(String.valueOf(tempScore2));

                if (hole == 18) {
                    int totalPar = 0;
                    for (int i = 0; i < arrayScore1.size(); i++) {
                        sumScore += arrayScore1.get(i);
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
            arrayScore1.clear();
            arrayScore2.clear();
            par.clear();
            scoreDisp.setText(null);
            hole = 0;
            sumScore = 0;
            tempScore1 = 0;
            tempScore2 = 0;
            scoreView1.setText(String.valueOf(tempScore1));
            scoreView2.setText(String.valueOf(tempScore2));
        });


    }

    public void clickRemovePlayer1() {
        confirmRemoveDialog.setContentView(R.layout.dialog_confirm_remove_player);

        Button YES = confirmRemoveDialog.findViewById(R.id.dlg_confirm_Yes);
        Button NO = confirmRemoveDialog.findViewById(R.id.dlg_confirm_No);

        YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPlayerName = null;
                //     players.remove(0);
                namePlayer1.setText(null);
                confirmRemoveDialog.dismiss();
            }
        });

        NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmRemoveDialog.dismiss();
            }
        });

        // buttonDesignSetting();

        confirmRemoveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmRemoveDialog.show();
    }

/*    public void buttonDesignSetting() {
        if (tempPlayerName.equals(null)) {
            buttonRemovePlayer1.setBackground(CountingPage.this.getResources().getDrawable(R.drawable.design_button_remove_player_pressed));
            //buttonRemovePlayer1.setClickable(false);
        } else {
            buttonRemovePlayer1.setBackground(CountingPage.this.getResources().getDrawable(R.drawable.button_remove_player));
        }
    }*/

    public void clickAddPlayer() {
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
                namePlayer1.setTextColor(Color.BLACK);
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
    }

    public void scoreDisp1(int score) {
        if (score <= 0) {
            scoreView1.setTextColor(Color.RED);
        } else {
            scoreView1.setTextColor(Color.BLUE);
        }
        scoreView1.setText(String.valueOf(score));
    }

    public void scoreDisp2(int score) {
        if (score <= 0) {
            scoreView2.setTextColor(Color.RED);
        } else {
            scoreView2.setTextColor(Color.BLUE);
        }
        scoreView2.setText(String.valueOf(score));
    }

    public void variablesSetter() {
        scoreView1 = findViewById(R.id.view_score1);
        scoreView2 = findViewById(R.id.view_score2);
        buttonMinus1 = findViewById(R.id.button_minus1);
        buttonPlus1 = findViewById(R.id.button_plus1);
        buttonMinus2 = findViewById(R.id.button_minus2);
        buttonPlus2 = findViewById(R.id.button_plus2);
        buttonOK = findViewById(R.id.button_ok);
        scoreDisp = findViewById(R.id.textView_score);
        arrayScore1.clear();
        arrayScore2.clear();
        buttonReset = findViewById(R.id.button_reset);
        players.clear();
        par.clear();
        buttonAddPlayer1 = findViewById(R.id.button_addName1);
        buttonAddPlayer2 = findViewById(R.id.button_addName2);
        buttonRemovePlayer1 = findViewById(R.id.button_removeName1);
        buttonRemovePlayer2 = findViewById(R.id.button_removeName2);
        btnPar3 = findViewById(R.id.button_par_3);
        btnPar4 = findViewById(R.id.button_par_4);
        btnPar5 = findViewById(R.id.button_par_5);
        radioGroup = findViewById(R.id.rdo_grp);
        playerDialog = new Dialog(CountingPage.this);
        confirmRemoveDialog = new Dialog(CountingPage.this);
        namePlayer1 = findViewById(R.id.textView_player1);
        namePlayer2 = findViewById(R.id.textView_player2);
        tempPlayerName = null;

    }
}