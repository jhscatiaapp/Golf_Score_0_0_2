package com.example.golf_score_0_0_1;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class CountingPage extends AppCompatActivity {

    public static final String TABLE_NAME1 = "Golf_CC";

    private TextView scoreView1, scoreView2, scoreView3, scoreView4;
    private TextView namePlayer1, namePlayer2, namePlayer3, namePlayer4;
    private Button buttonMinus, buttonPlus, buttonOK;
    private Button buttonAddPlayer1, buttonAddPlayer2, buttonAddPlayer3, buttonAddPlayer4;
    private Button buttonRemovePlayer1, buttonRemovePlayer2, buttonRemovePlayer3, buttonRemovePlayer4;

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
    private Dialog playerDialog, confirmRemoveDialog;

    private MyDBHelperCC myDBHelperCC = new MyDBHelperCC(this);
    private SQLiteDatabase myDBCC = myDBHelperCC.getWritableDatabase() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        variablesSetter();
        buttonSetter();







        scoreView1.setText(String.valueOf(tempScore));

        buttonAddPlayer1.setOnClickListener(v -> {
            /**    Dialog POPup to get name and button OK / CANCEL        */
            clickAddPlayer();
        });


        buttonRemovePlayer1.setOnClickListener(v -> {
            clickRemovePlayer1();
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scoreDisp(++tempScore);
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scoreDisp(--tempScore);
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
                    case R.id.button_par_6:
                        par.add(PAR6);
                        pars = String.valueOf(PAR6);
                        break;
                }

                arrayScore.add(tempScore);
                //scoreDisp.append(hole + "  " + pars + "  " + tempScore + "\n");
                tempScore = 0;
                //scoreView.setText(String.valueOf(tempScore));

                if (hole == 18) {
                    int totalPar = 0;
                    for (int i = 0; i < arrayScore.size(); i++) {
                        sumScore += arrayScore.get(i);
                        totalPar += par.get(i);
                    }
                    int totalScore = totalPar + sumScore;
                    //scoreDisp.append("Total Score is " + sumScore + "\n");
                    //scoreDisp.append("Total score/par : " + totalScore + "/" + totalPar);
                }
            } else {
                Toast.makeText(this, "Finish 18 holes", Toast.LENGTH_SHORT).show();
            }
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


        confirmRemoveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmRemoveDialog.show();
    }



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



    public void variablesSetter() {
        scoreView1 = findViewById(R.id.view_score1);
        scoreView2 = findViewById(R.id.view_score2);
        scoreView3 = findViewById(R.id.view_score3);
        scoreView4 = findViewById(R.id.view_score4);

        buttonMinus = findViewById(R.id.button_minus1);
        buttonPlus = findViewById(R.id.button_plus1);
        buttonOK = findViewById(R.id.button_ok);
        arrayScore.clear();
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

    public void buttonSetter() {
        if (namePlayer1.equals("")) {
            buttonRemovePlayer1.setEnabled(false);
            buttonPlus.setEnabled(false);
            buttonMinus.setEnabled(false);
        } else {
            buttonRemovePlayer1.setEnabled(true);
            buttonPlus.setEnabled(true);
            buttonMinus.setEnabled(true);
            buttonAddPlayer1.setBackground(this.getResources().getDrawable(R.drawable.player_change_btn));
        }
    }

    private Cursor getAllItemsCC() {
        return myDBCC.query(TABLE_NAME1, null, null, null,
                null, null, null);
    }
}