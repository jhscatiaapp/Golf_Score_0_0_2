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
    private Button buttonOK;

    private Button buttonPlus1, buttonPlus2, buttonPlus3, buttonPlus4;
    private Button buttonMinus1, buttonMinus2, buttonMinus3, buttonMinus4;
    private Button buttonAddPlayer1, buttonAddPlayer2, buttonAddPlayer3, buttonAddPlayer4;
    private Button buttonRemovePlayer1, buttonRemovePlayer2, buttonRemovePlayer3, buttonRemovePlayer4;
    private ButtonSetter myButtonSetter1, myButtonSetter2, myButtonSetter3, myButtonSetter4;

    private int tempScore = 0;
    private int hole = 0;
    private int sumScore = 0;
    private ArrayList<Integer> arrayScore = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Integer> par = new ArrayList<>();
    private ArrayList<String> arrCCNAme = new ArrayList<>();
    private ArrayList<String> arrH1 = new ArrayList<>();
    private ArrayList<String> arrH2 = new ArrayList<>();
    private ArrayList<String> arrH3 = new ArrayList<>();
    private ArrayList<String> arrH4 = new ArrayList<>();
    private ArrayList<String> arrH5 = new ArrayList<>();
    private ArrayList<String> arrH6 = new ArrayList<>();
    private ArrayList<String> arrH7 = new ArrayList<>();
    private ArrayList<String> arrH8 = new ArrayList<>();
    private ArrayList<String> arrH9 = new ArrayList<>();
    private ArrayList<String> arrHout = new ArrayList<>();
    private ArrayList<String> arrH10 = new ArrayList<>();
    private ArrayList<String> arrH11 = new ArrayList<>();
    private ArrayList<String> arrH12 = new ArrayList<>();
    private ArrayList<String> arrH13 = new ArrayList<>();
    private ArrayList<String> arrH14 = new ArrayList<>();
    private ArrayList<String> arrH15 = new ArrayList<>();
    private ArrayList<String> arrH16 = new ArrayList<>();
    private ArrayList<String> arrH17 = new ArrayList<>();
    private ArrayList<String> arrH18 = new ArrayList<>();
    private ArrayList<String> arrHin = new ArrayList<>();
    private ArrayList<String> arrHttl = new ArrayList<>();

    private final int PAR3 = 3;
    private final int PAR4 = 4;
    private final int PAR5 = 5;
    private final int PAR6 = 6;
    private RadioButton btnPar3, btnPar4, btnPar5, btnPar;
    private String pars, tempPlayerName = null;
    private RadioGroup radioGroup;
    private Dialog playerDialog, confirmRemoveDialog;

    //private MyDBHelperCC myDBHelperCC = new MyDBHelperCC(this);
    //private SQLiteDatabase myDBCC = myDBHelperCC.getWritableDatabase() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        variablesSetter();
        //putCCDBToArray();
        //buttonSetter(tempPlayerName);
        myButtonSetter1 = new ButtonSetter(tempPlayerName, buttonAddPlayer1, buttonRemovePlayer1, buttonPlus1, buttonMinus1);
        myButtonSetter2 = new ButtonSetter(tempPlayerName, buttonAddPlayer2, buttonRemovePlayer2, buttonPlus2, buttonMinus2);
        myButtonSetter3 = new ButtonSetter(tempPlayerName, buttonAddPlayer3, buttonRemovePlayer3, buttonPlus3, buttonMinus3);
        myButtonSetter4 = new ButtonSetter(tempPlayerName, buttonAddPlayer4, buttonRemovePlayer4, buttonPlus4, buttonMinus4);



        scoreView1.setText(String.valueOf(tempScore));

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
                //scoreDisp(++tempScore);
            }
        });

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
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
                namePlayer1.setText(tempPlayerName);
                //buttonSetter(tempPlayerName);
                myButtonSetter1.getmName(tempPlayerName);
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
                //namePlayer1.setTextColor(Color.BLACK);
                //buttonSetter(tempPlayerName);
                myButtonSetter1.getmName(tempPlayerName);
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

        buttonPlus1 = findViewById(R.id.button_plus1);
        buttonPlus2 = findViewById(R.id.button_plus2);
        buttonPlus3 = findViewById(R.id.button_plus3);
        buttonPlus4 = findViewById(R.id.button_plus4);
        buttonMinus1 = findViewById(R.id.button_minus1);
        buttonMinus2 = findViewById(R.id.button_minus2);
        buttonMinus3 = findViewById(R.id.button_minus3);
        buttonMinus4 = findViewById(R.id.button_minus4);

        buttonOK = findViewById(R.id.button_ok);
        arrayScore.clear();
        players.clear();
        par.clear();
        buttonAddPlayer1 = findViewById(R.id.button_addName1);
        buttonAddPlayer2 = findViewById(R.id.button_addName2);
        buttonAddPlayer3 = findViewById(R.id.button_addName3);
        buttonAddPlayer4 = findViewById(R.id.button_addName4);
        buttonRemovePlayer1 = findViewById(R.id.button_removeName1);
        buttonRemovePlayer2 = findViewById(R.id.button_removeName2);
        buttonRemovePlayer3 = findViewById(R.id.button_removeName3);
        buttonRemovePlayer4 = findViewById(R.id.button_removeName4);
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

    /*public void buttonSetter(String name) {
        if (name == null || name == "" || name == " ") {
            buttonRemovePlayer1.setEnabled(false);
            buttonPlus.setEnabled(false);
            buttonMinus.setEnabled(false);
            buttonAddPlayer1.setBackground(this.getResources().getDrawable(R.drawable.player_add_btn));
        } else {
            buttonRemovePlayer1.setEnabled(true);
            buttonPlus.setEnabled(true);
            buttonMinus.setEnabled(true);
            buttonAddPlayer1.setBackground(this.getResources().getDrawable(R.drawable.player_change_btn));
        }
    }*/

    /*private void putCCDBToArray() {
        arrCCNAme.clear();
        arrH1.clear();
        arrH2.clear();
        arrH3.clear();
        arrH4.clear();
        arrH5.clear();
        arrH6.clear();
        arrH7.clear();
        arrH8.clear();
        arrH9.clear();
        arrHout.clear();
        arrH10.clear();
        arrH11.clear();
        arrH12.clear();
        arrH13.clear();
        arrH14.clear();
        arrH15.clear();
        arrH16.clear();
        arrH17.clear();
        arrH18.clear();
        arrHin.clear();
        arrHttl.clear();

        Cursor cursor = myDBHelperCC.readAllData();

        while (cursor.moveToNext()) {
            arrCCNAme.add(cursor.getString(0));
            arrH1.add(cursor.getString(1));
            arrH2.add(cursor.getString(2));
            arrH3.add(cursor.getString(3));
            arrH4.add(cursor.getString(4));
            arrH5.add(cursor.getString(5));
            arrH6.add(cursor.getString(6));
            arrH7.add(cursor.getString(7));
            arrH8.add(cursor.getString(8));
            arrH9.add(cursor.getString(9));
            arrHout.add(cursor.getString(10));
            arrH10.add(cursor.getString(11));
            arrH11.add(cursor.getString(12));
            arrH12.add(cursor.getString(13));
            arrH13.add(cursor.getString(14));
            arrH14.add(cursor.getString(15));
            arrH15.add(cursor.getString(16));
            arrH16.add(cursor.getString(17));
            arrH17.add(cursor.getString(18));
            arrH18.add(cursor.getString(19));
            arrHin.add(cursor.getString(20));
            arrHttl.add(cursor.getString(21));
        }
    }*/

    /*private Cursor getAllItemsCC() {
        return myDBCC.query(TABLE_NAME1, null, null, null,
                null, null, null);
    }*/
}