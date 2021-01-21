package com.example.golf_score_0_0_1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CountingPage extends AppCompatActivity {

    public static final String TABLE_NAME1 = "Golf_CC";
    public static final String TABLE_NAME2 = "Golf_Total";
    public static final String TABLE_NAME3 = "Golf_Score";
    public static final String TABLE_NAME4 = "Rank_Table";

    private DecimalFormat df = new DecimalFormat();
    private Ordinal ordinal = new Ordinal();

    private TextView scoreView1, scoreView2, scoreView3, scoreView4;
    private TextView namePlayer1, namePlayer2, namePlayer3, namePlayer4;
    private TextView scoreName1, scoreName2, scoreName3, scoreName4;
    private TextView mainHoleNo;
    private TextView tempTextViewHole, tempTextViewPar;

    private Button buttonOK;
    private Button buttonPlus1, buttonPlus2, buttonPlus3, buttonPlus4;
    private Button buttonMinus1, buttonMinus2, buttonMinus3, buttonMinus4;
    private Button buttonAddPlayer1, buttonAddPlayer2, buttonAddPlayer3, buttonAddPlayer4;
    private Button buttonRemovePlayer1, buttonRemovePlayer2, buttonRemovePlayer3, buttonRemovePlayer4;
    private ButtonSetter myButtonSetter1 = new ButtonSetter();
    private ButtonSetter myButtonSetter2 = new ButtonSetter();
    private ButtonSetter myButtonSetter3 = new ButtonSetter();
    private ButtonSetter myButtonSetter4 = new ButtonSetter();

    private ButtonSetter2 my2ButtonSetter2 = new ButtonSetter2();
    private ButtonSetter2 my2ButtonSetter3 = new ButtonSetter2();
    private ButtonSetter2 my2ButtonSetter4 = new ButtonSetter2();

    private ScoreSetter scoreSetter = new ScoreSetter();
    private ScoreSetter2 scoreSetter2 = new ScoreSetter2();

    private int tempScore1 = 0;
    private int tempScore2 = 0;
    private int tempScore3 = 0;
    private int tempScore4 = 0;
    private int startHoleNo = 1;
    private int indexNo = 0;
    private int countNum = 1;
    private final int PAR3 = 3;
    private final int PAR4 = 4;
    private final int PAR5 = 5;
    private final int PAR6 = 6;

    private ArrayList<String> holeInfo = new ArrayList<>();
    private ArrayList<String> parInfo = new ArrayList<>();
    private ArrayList<String> p1Score = new ArrayList<>();
    private ArrayList<String> p2Score = new ArrayList<>();
    private ArrayList<String> p3Score = new ArrayList<>();
    private ArrayList<String> p4Score = new ArrayList<>();
    private ArrayList<String> rankScore = new ArrayList<>();
    private ArrayList<String> scoreArrange = new ArrayList<>();
    private ArrayList<String> shot = new ArrayList<>();
    private ArrayList<String> arrayThru = new ArrayList<>();
    private ArrayList<String> player = new ArrayList<>();

    private ArrayList<Integer> arrayScore = new ArrayList<>();

    private ArrayList<ScoreArrange> myList = new ArrayList<>();

    private RadioButton btnPar3, btnPar4, btnPar5, btnPar6;
    private String pars, tempPlayerName = null;
    private String tempHoleInfo;
    private RadioGroup radioGroupPar;
    private RadioGroup radioGroupHole;
    private Dialog playerDialog, confirmRemoveDialog, holeSelectionDialog;
    private String[] playerName = new String[4];

    private MyDBHelperCC myDBHelperCC = new MyDBHelperCC(this);
    private MyDBHelperTotal myDBHelperTotal = new MyDBHelperTotal(this);
    private MyDBHelperScore myDBHelperScore = new MyDBHelperScore(this);
    private MyDBHelperRank myDBHelperRank = new MyDBHelperRank(this);
    private SQLiteDatabase myDBCC;
    private SQLiteDatabase myDBTotal;
    private SQLiteDatabase myDBScore;
    private SQLiteDatabase myDBRank;

    private myAdapter adapter;
    private myAdapterRank adapterRank;
    private ScoreSum scoreSumPar = new ScoreSum();
    private ScoreSum scoreSumP1 = new ScoreSum();
    private ScoreSum scoreSumP2 = new ScoreSum();
    private ScoreSum scoreSumP3 = new ScoreSum();
    private ScoreSum scoreSumP4 = new ScoreSum();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewRank;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_counting);


        /**     Initialize variables     */
        variablesSetter();
        //putCCDBToArray();

        /**     Initialize Button     */
        buttonOK.setEnabled(false);
        myButtonSetter1.initSetButton(tempPlayerName, buttonAddPlayer1, buttonRemovePlayer1, buttonPlus1, buttonMinus1, countNum);
        myButtonSetter2.initSetButton(tempPlayerName, buttonAddPlayer2, buttonRemovePlayer2, buttonPlus2, buttonMinus2, countNum);
        myButtonSetter3.initSetButton(tempPlayerName, buttonAddPlayer3, buttonRemovePlayer3, buttonPlus3, buttonMinus3, countNum);
        myButtonSetter4.initSetButton(tempPlayerName, buttonAddPlayer4, buttonRemovePlayer4, buttonPlus4, buttonMinus4, countNum);
        myButtonSetter1.setButton();
        myButtonSetter2.setButton();
        myButtonSetter3.setButton();
        myButtonSetter4.setButton();

        my2ButtonSetter2.setButton(playerName[0], buttonAddPlayer2);
        my2ButtonSetter3.setButton(playerName[1], buttonAddPlayer3);
        my2ButtonSetter4.setButton(playerName[2], buttonAddPlayer4);


        /**     Add and Remove player action     */
        buttonAddPlayer1.setOnClickListener(v -> {
            clickAddPlayer(buttonAddPlayer1.getId());
        });
        buttonAddPlayer2.setOnClickListener(v -> {
            clickAddPlayer(buttonAddPlayer2.getId());
        });
        buttonAddPlayer3.setOnClickListener(v -> {
            clickAddPlayer(buttonAddPlayer3.getId());
        });
        buttonAddPlayer4.setOnClickListener(v -> {
            clickAddPlayer(buttonAddPlayer4.getId());
        });
        buttonRemovePlayer1.setOnClickListener(v -> {
            clickRemovePlayer1(buttonRemovePlayer1.getId());
        });
        buttonRemovePlayer2.setOnClickListener(v -> {
            clickRemovePlayer1(buttonRemovePlayer2.getId());
        });
        buttonRemovePlayer3.setOnClickListener(v -> {
            clickRemovePlayer1(buttonRemovePlayer3.getId());
        });
        buttonRemovePlayer4.setOnClickListener(v -> {
            clickRemovePlayer1(buttonRemovePlayer4.getId());
        });

        /**     Plus and Minus button action     */
        buttonPlus1.setOnClickListener(v -> {
            scoreView1.setText(dFormat(++tempScore1));
            scoreViewColor(tempScore1, scoreView1);
        });
        buttonMinus1.setOnClickListener(v -> {
            scoreView1.setText(dFormat(--tempScore1));
            scoreViewColor(tempScore1, scoreView1);
        });
        buttonPlus2.setOnClickListener(v -> {
            scoreView2.setText(dFormat(++tempScore2));
            scoreViewColor(tempScore2, scoreView2);
        });
        buttonMinus2.setOnClickListener(v -> {
            scoreView2.setText(dFormat(--tempScore2));
            scoreViewColor(tempScore2, scoreView2);
        });
        buttonPlus3.setOnClickListener(v -> {
            scoreView3.setText(dFormat(++tempScore3));
            scoreViewColor(tempScore3, scoreView3);
        });
        buttonMinus3.setOnClickListener(v -> {
            scoreView3.setText(dFormat(--tempScore3));
            scoreViewColor(tempScore3, scoreView3);
        });
        buttonPlus4.setOnClickListener(v -> {
            scoreView4.setText(dFormat(++tempScore4));
            scoreViewColor(tempScore4, scoreView4);
        });
        buttonMinus4.setOnClickListener(v -> {
            scoreView4.setText(dFormat(--tempScore4));
            scoreViewColor(tempScore4, scoreView4);
        });

        /**     Need to add if statement whether NEW or EXISTING hole     */
        /**     Here is for NEW game situation     */
        initNewCC();
        mainScoreDisplay(holeInfo, parInfo, p1Score, p2Score, p3Score, p4Score);

        /**     Hole Number Click Listener     */
        mainHoleNo.setOnClickListener(v -> {
            clickHole();
        });

        /**     OK button action - NEW GAME     */
        buttonOK.setOnClickListener(v -> {
            String[] arrParInfo = new String[holeInfo.size()];
            String[] arrP1Score = new String[holeInfo.size()];
            String[] arrP2Score = new String[holeInfo.size()];
            String[] arrP3Score = new String[holeInfo.size()];
            String[] arrP4Score = new String[holeInfo.size()];

            int radioID = radioGroupPar.getCheckedRadioButtonId();

            if (startHoleNo <= 9) {
                indexNo = startHoleNo - 1;
            } else if (9 < startHoleNo || startHoleNo <= 18) {
                indexNo = startHoleNo;
            }

            /**     Feeding PAR inform     */
            switch (radioID) {
                case R.id.button_par_3:
                    parInfo.set(indexNo, String.valueOf(PAR3));
                    break;
                case R.id.button_par_4:
                    parInfo.set(indexNo, String.valueOf(PAR4));
                    break;
                case R.id.button_par_5:
                    parInfo.set(indexNo, String.valueOf(PAR5));
                    break;
                case R.id.button_par_6:
                    parInfo.set(indexNo, String.valueOf(PAR6));
                    break;
            }

            /**     Put score and calculate OUT/IN/TTL     */
            p1Score.set(indexNo, scoreSetter2.setScore(playerName[0], tempScore1));
            p2Score.set(indexNo, scoreSetter2.setScore(playerName[1], tempScore2));
            p3Score.set(indexNo, scoreSetter2.setScore(playerName[2], tempScore3));
            p4Score.set(indexNo, scoreSetter2.setScore(playerName[3], tempScore4));


            /**     Main Hole DP and OK button disabled     */
            countNum++;
            if (countNum <= 18) {
                startHoleNo++;
                if (startHoleNo == 19) {
                    startHoleNo = 1;
                }
                mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
            } else if (countNum == 19) {
                buttonOK.setEnabled(false);
                mainHoleNo.setText(ordinal.ordinalChange(countNum));
                Toast.makeText(this, "Finished 18 holes", Toast.LENGTH_SHORT).show();
                disableButtonAll(countNum);
            }

            /**     Score calculate and feed back to variables     */
            for(int i = 0; i < holeInfo.size(); i++) {
                arrParInfo[i] = parInfo.get(i);
                arrP1Score[i] = p1Score.get(i);
                arrP2Score[i] = p2Score.get(i);
                arrP3Score[i] = p3Score.get(i);
                arrP4Score[i] = p4Score.get(i);
            }
            parInfo = scoreSumPar.sumScore(arrParInfo);
            p1Score = scoreSumP1.sumScore(arrP1Score);
            p2Score = scoreSumP2.sumScore(arrP2Score);
            p3Score = scoreSumP3.sumScore(arrP3Score);
            p4Score = scoreSumP4.sumScore(arrP4Score);

            /**     Score board display     */
            mainScoreDisplay(holeInfo, parInfo, p1Score, p2Score, p3Score, p4Score);

            /**     Rearranging score     */
            summaryScoreArrange();
            ArrayList<String> score = new ArrayList<>();
            ArrayList<String> thru = new ArrayList<>();
            ArrayList<String> shot = new ArrayList<>();
            ArrayList<String> arrangedPlayer = new ArrayList<>();
            score.clear();
            thru.clear();
            shot.clear();
            arrangedPlayer.clear();
            for (int i = 0; i < 4; i++) {
                arrangedPlayer.add(myList.get(i).getmName());
                score.add(myList.get(i).getmScore());
                thru.add(myList.get(i).getmThruHole());
                shot.add(myList.get(i).getmShot());
            }
            rankScoreDisplay(rankScore, arrangedPlayer, score, thru, shot);

            /**     After OK button will set score as '0'     */
            tempScore1 = 0;
            tempScore2 = 0;
            tempScore3 = 0;
            tempScore4 = 0;

            scoreSetter.setScore(playerName[0], scoreView1);
            scoreSetter.setScore(playerName[1], scoreView2);
            scoreSetter.setScore(playerName[2], scoreView3);
            scoreSetter.setScore(playerName[3], scoreView4);

            if (countNum == 19) {  greyTextAll();  }
        });


    }




    /**
     * ----------------------------------------METHODS------------------------------------------
     */


    public void summaryScoreArrange() {
        ArrayList<String> summScore = new ArrayList<>();
        myList.clear();
        rankScore.clear();
        summScore.clear();
        arrayThru.clear();
        shot.clear();
        player.clear();

        summScore.add(p1Score.get(20));
        summScore.add(p2Score.get(20));
        summScore.add(p3Score.get(20));
        summScore.add(p4Score.get(20));

        for (int i = 0; i < 4; i++) {
            arrayThru.add(String.valueOf(countNum-1));
            int temp1 = Integer.parseInt(parInfo.get(20));
            int temp2 = Integer.parseInt(summScore.get(i));
            shot.add(String.valueOf(temp1 + temp2));
            if (playerName[i] == null || playerName[i] == "" || playerName[i] == " ") {
                player.add("-");
                summScore.set(i, "-");
                arrayThru.set(i, "-");
                shot.set(i, "-");
            } else player.add(playerName[i]);
            myList.add(new ScoreArrange(player.get(i), summScore.get(i), arrayThru.get(i), shot.get(i)));
        }

        myList = sortASC(myList);

        rankScore.add(0, "1");

        for (int i = 0; i < 3; i++) {
            String nextRank = " ";
            for (int j = i + 1; j < 4; j++) {
                if (myList.get(j).getmScore() == "-") {
                    nextRank = "-";
                } else {
                    if (myList.get(i).getmScore() == myList.get(j).getmScore()) {
                        nextRank = " ";
                    } else {
                        nextRank = String.valueOf((i + 2));
                    }
                }
                rankScore.add(i + 1, nextRank);
                break;
            }
        }
    }

    public static ArrayList<ScoreArrange> sortASC(ArrayList<ScoreArrange> list) {
        Collections.sort(list, new Comparator<ScoreArrange>() {
            @Override
            public int compare(ScoreArrange o1, ScoreArrange o2) {
                int temp1 = 0;
                int temp2 = 0;

                if (o1.getmScore() == "-") temp1 = 1000;
                else if (o2.getmScore() == "-") temp2 = 1000;
                else {
                    temp1 = Integer.parseInt(o1.getmScore());
                    temp2 = Integer.parseInt(o2.getmScore());
                }

                if (temp1 > temp2) {
                    return 1;
                } else if (o1.getmScore() == o2.getmScore()) {
                    return o1.getmName().compareTo(o2.getmName());
                } else {
                    return -1;
                }
            }
        });
        return list;
    }

    public void greyTextAll() {
        scoreView1.setTextColor(0xFFA5A5A5);
        scoreView2.setTextColor(0xFFA5A5A5);
        scoreView3.setTextColor(0xFFA5A5A5);
        scoreView4.setTextColor(0xFFA5A5A5);
        namePlayer1.setTextColor(0xFFA5A5A5);
        namePlayer2.setTextColor(0xFFA5A5A5);
        namePlayer3.setTextColor(0xFFA5A5A5);
        namePlayer4.setTextColor(0xFFA5A5A5);
    }

    public void disableButtonAll(int num) {
        myButtonSetter1.initSetButton(tempPlayerName, buttonAddPlayer1, buttonRemovePlayer1, buttonPlus1, buttonMinus1, num);
        myButtonSetter2.initSetButton(tempPlayerName, buttonAddPlayer2, buttonRemovePlayer2, buttonPlus2, buttonMinus2, num);
        myButtonSetter3.initSetButton(tempPlayerName, buttonAddPlayer3, buttonRemovePlayer3, buttonPlus3, buttonMinus3, num);
        myButtonSetter4.initSetButton(tempPlayerName, buttonAddPlayer4, buttonRemovePlayer4, buttonPlus4, buttonMinus4, num);
        myButtonSetter1.setButton();
        myButtonSetter2.setButton();
        myButtonSetter3.setButton();
        myButtonSetter4.setButton();
    }

    public void initNewCC() {
        holeInfo.clear();
        parInfo.clear();
        p1Score.clear();
        p2Score.clear();
        p3Score.clear();
        p4Score.clear();
        for (int i = 0; i < 18; i++) {
            holeInfo.add(String.valueOf(i + 1));
        }
        holeInfo.add(9, "OUT");
        holeInfo.add(19, "IN");
        holeInfo.add(20, "TTL");
        for (int j = 0; j < 21; j++) {
            parInfo.add("-");
            p1Score.add("-");
            p2Score.add("-");
            p3Score.add("-");
            p4Score.add("-");
        }

    }

    public void initExistingCC() {
        initNewCC();

        /**   Add Replace algorithm hole & par info to array      */

    }

    public void mainScoreDisplay(ArrayList<String> holeArr, ArrayList<String> parArr,
                                 ArrayList<String> p1ScoreArr, ArrayList<String> p2ScoreArr,
                                 ArrayList<String> p3ScoreArr, ArrayList<String> p4ScoreArr) {

        putScoreArraytoDB(holeArr, parArr, p1ScoreArr, p2ScoreArr, p3ScoreArr, p4ScoreArr);

        adapter = new myAdapter(this, getAllItemsScore());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public void rankScoreDisplay(ArrayList<String> rankArr, ArrayList<String> playerArr,
                                 ArrayList<String> scoreArr, ArrayList<String> thruArr,
                                 ArrayList<String> shotArr) {

        putScoreArraytoRankDB(rankArr, playerArr, scoreArr, thruArr, shotArr);

        adapterRank = new myAdapterRank(this, getAllItemsRank());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRank.setLayoutManager(linearLayoutManager);
        adapterRank.notifyDataSetChanged();
        recyclerViewRank.setAdapter(adapterRank);
    }

    public void putScoreArraytoDB(ArrayList<String> holeArr, ArrayList<String> parArr,
                                  ArrayList<String> p1ScoreArr, ArrayList<String> p2ScoreArr,
                                  ArrayList<String> p3ScoreArr, ArrayList<String> p4ScoreArr) {

        myDBHelperScore.deleteAllData();
        for (int i = 0; i < holeArr.size(); i++) {
            myDBHelperScore.saveToDB(holeArr.get(i), parArr.get(i), p1ScoreArr.get(i),
                    p2ScoreArr.get(i), p3ScoreArr.get(i), p4ScoreArr.get(i));
        }

    }

    public void putScoreArraytoRankDB(ArrayList<String> rankArr, ArrayList<String> playerArr,
                                  ArrayList<String> scoreArr, ArrayList<String> thruArr,
                                  ArrayList<String> shotArr) {

        myDBHelperRank.deleteAllData();

        for (int i = 0; i < playerArr.size(); i++) {
            myDBHelperRank.saveToDB(rankArr.get(i), playerArr.get(i), scoreArr.get(i),
                    thruArr.get(i), shotArr.get(i));
        }

    }

    public void clickAddPlayer(int ID) {
        playerDialog.setContentView(R.layout.dialog_add_player);

        Button OK = playerDialog.findViewById(R.id.dlg_addPlayer_Yes);
        Button CANCEL = playerDialog.findViewById(R.id.dlg_addPlayer_No);
        EditText getPlayerName = playerDialog.findViewById(R.id.editText_inputPlayerName);

        OK.setOnClickListener(v -> {
            tempPlayerName = getPlayerName.getText().toString();
            switch (ID) {
                case R.id.button_addName1:
                    playerName[0] = tempPlayerName;
                    namePlayer1.setText(tempPlayerName);
                    scoreName1.setText(tempPlayerName);
                    scoreView1.setTextColor(Color.BLACK);
                    /**   Button change   */
                    myButtonSetter1.getName(tempPlayerName);
                    myButtonSetter1.setButton();
                    break;
                case R.id.button_addName2:
                    playerName[1] = tempPlayerName;
                    namePlayer2.setText(tempPlayerName);
                    scoreName2.setText(tempPlayerName);
                    scoreView2.setTextColor(Color.BLACK);
                    /**   Button change   */
                    myButtonSetter2.getName(tempPlayerName);
                    myButtonSetter2.setButton();
                    break;
                case R.id.button_addName3:
                    playerName[2] = tempPlayerName;
                    namePlayer3.setText(tempPlayerName);
                    scoreName3.setText(tempPlayerName);
                    scoreView3.setTextColor(Color.BLACK);
                    /**   Button change   */
                    myButtonSetter3.getName(tempPlayerName);
                    myButtonSetter3.setButton();
                    break;
                case R.id.button_addName4:
                    playerName[3] = tempPlayerName;
                    namePlayer4.setText(tempPlayerName);
                    scoreName4.setText(tempPlayerName);
                    scoreView4.setTextColor(Color.BLACK);
                    /**   Button change   */
                    myButtonSetter4.getName(tempPlayerName);
                    myButtonSetter4.setButton();
                    break;
            }

            my2ButtonSetter2.setButton(playerName[0], buttonAddPlayer2);
            my2ButtonSetter3.setButton(playerName[1], buttonAddPlayer3);
            my2ButtonSetter4.setButton(playerName[2], buttonAddPlayer4);

            if (playerName[0].isEmpty()) {
                buttonOK.setEnabled(false);
            } else buttonOK.setEnabled(true);

            playerDialog.dismiss();
        });

        CANCEL.setOnClickListener(v -> {
            playerDialog.dismiss();
        });

        playerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        playerDialog.show();
    }

    public void clickRemovePlayer1(int ID) {
        confirmRemoveDialog.setContentView(R.layout.dialog_confirm_remove_player);

        Button YES = confirmRemoveDialog.findViewById(R.id.dlg_confirm_Yes);
        Button NO = confirmRemoveDialog.findViewById(R.id.dlg_confirm_No);

        YES.setOnClickListener(v -> {
            tempPlayerName = "";
            switch (ID) {
                case R.id.button_removeName1:
                    playerName[0] = tempPlayerName;
                    namePlayer1.setText(tempPlayerName);
                    scoreName1.setText(tempPlayerName);
                    scoreView1.setTextColor(0xFFA5A5A5);
                    /**   Button change   */
                    myButtonSetter1.getName(tempPlayerName);
                    myButtonSetter1.setButton();
                    break;
                case R.id.button_removeName2:
                    playerName[1] = tempPlayerName;
                    namePlayer2.setText(tempPlayerName);
                    scoreName2.setText(tempPlayerName);
                    scoreView2.setTextColor(0xFFA5A5A5);
                    /**   Button change   */
                    myButtonSetter2.getName(tempPlayerName);
                    myButtonSetter2.setButton();
                    break;
                case R.id.button_removeName3:
                    playerName[2] = tempPlayerName;
                    namePlayer3.setText(tempPlayerName);
                    scoreName3.setText(tempPlayerName);
                    scoreView3.setTextColor(0xFFA5A5A5);
                    /**   Button change   */
                    myButtonSetter3.getName(tempPlayerName);
                    myButtonSetter3.setButton();
                    break;
                case R.id.button_removeName4:
                    playerName[3] = tempPlayerName;
                    namePlayer4.setText(tempPlayerName);
                    scoreName4.setText(tempPlayerName);
                    scoreView4.setTextColor(0xFFA5A5A5);
                    /**   Button change   */
                    myButtonSetter4.getName(tempPlayerName);
                    myButtonSetter4.setButton();
                    break;
            }

            my2ButtonSetter2.setButton(playerName[0], buttonAddPlayer2);
            my2ButtonSetter3.setButton(playerName[1], buttonAddPlayer3);
            my2ButtonSetter4.setButton(playerName[2], buttonAddPlayer4);
            confirmRemoveDialog.dismiss();
        });

        NO.setOnClickListener(v -> {
            confirmRemoveDialog.dismiss();
        });

        confirmRemoveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmRemoveDialog.show();
    }

    public void clickHole() {
        holeSelectionDialog.setContentView(R.layout.dialog_starthole_selection);

        Button OK = holeSelectionDialog.findViewById(R.id.dlg_Hole_ok);
        Button CANCEL = holeSelectionDialog.findViewById(R.id.dlg_Hole_cancel);

        radioGroupHole = holeSelectionDialog.findViewById(R.id.rdo_grp_hole);

        OK.setOnClickListener(v -> {
            /**  take radio button info and set hole number  */
            int radioID = radioGroupHole.getCheckedRadioButtonId();
            switch (radioID) {
                case R.id.hole_selection_no1:
                    startHoleNo = 1;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no2:
                    startHoleNo = 2;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no3:
                    startHoleNo = 3;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no4:
                    startHoleNo = 4;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no5:
                    startHoleNo = 5;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no6:
                    startHoleNo = 6;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no7:
                    startHoleNo = 7;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no8:
                    startHoleNo = 8;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no9:
                    startHoleNo = 9;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no10:
                    startHoleNo = 10;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no11:
                    startHoleNo = 11;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no12:
                    startHoleNo = 12;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no13:
                    startHoleNo = 13;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no14:
                    startHoleNo = 14;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no15:
                    startHoleNo = 15;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no16:
                    startHoleNo = 16;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no17:
                    startHoleNo = 17;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
                case R.id.hole_selection_no18:
                    startHoleNo = 18;
                    mainHoleNo.setText(ordinal.ordinalChange(startHoleNo));
                    break;
            }
            holeSelectionDialog.dismiss();
        });

        CANCEL.setOnClickListener(v -> {
            holeSelectionDialog.dismiss();
        });

        holeSelectionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        holeSelectionDialog.show();
    }

    public String dFormat(int num) {
        if (num > 0) {
            df = new DecimalFormat("+#");
        } else df = new DecimalFormat("#");
        return df.format(num);
    }

    public void scoreViewColor(int score, TextView view) {
        if (score == 0) {  view.setTextColor(Color.BLACK);  }
        else if (score > 0) {  view.setTextColor(Color.BLUE);  }
        else { view.setTextColor(Color.RED);  }
    }

    public String parFormat(int num) {
        df = new DecimalFormat("Par #");
        return df.format(num);
    }

    private Cursor getAllItemsCC() {
        return myDBCC.query(TABLE_NAME1, null, null, null,
                null, null, null);
    }

    private Cursor getAllItemsTotal() {
        return myDBTotal.query(TABLE_NAME2, null, null, null,
                null, null, null);
    }

    private Cursor getAllItemsScore() {
        return myDBScore.query(TABLE_NAME3, null, null, null,
                null, null, null);
    }

    private Cursor getAllItemsRank() {
        return myDBRank.query(TABLE_NAME4, null, null, null,
                null, null, null);
    }



    public void variablesSetter() {
        scoreView1 = findViewById(R.id.view_score1);
        scoreView2 = findViewById(R.id.view_score2);
        scoreView3 = findViewById(R.id.view_score3);
        scoreView4 = findViewById(R.id.view_score4);
/*        tempTextViewHole = findViewById(R.id.temp_textView_Hole);
        tempTextViewPar = findViewById(R.id.temp_textView_Par);*/

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
        btnPar6 = findViewById(R.id.button_par_6);
        radioGroupPar = findViewById(R.id.rdo_grp);
        mainHoleNo = findViewById(R.id.textView_holeNo);

        playerDialog = new Dialog(CountingPage.this);
        confirmRemoveDialog = new Dialog(CountingPage.this);
        holeSelectionDialog = new Dialog(CountingPage.this);

        namePlayer1 = findViewById(R.id.textView_player1);
        namePlayer2 = findViewById(R.id.textView_player2);
        namePlayer3 = findViewById(R.id.textView_player3);
        namePlayer4 = findViewById(R.id.textView_player4);
        scoreName1 = findViewById(R.id.player1Name1_score_board);
        scoreName2 = findViewById(R.id.player1Name2_score_board);
        scoreName3 = findViewById(R.id.player1Name3_score_board);
        scoreName4 = findViewById(R.id.player1Name4_score_board);
        tempPlayerName = null;
        myDBCC = myDBHelperCC.getWritableDatabase();
        myDBTotal = myDBHelperTotal.getWritableDatabase();
        myDBScore = myDBHelperScore.getWritableDatabase();
        myDBRank = myDBHelperRank.getWritableDatabase();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewRank = findViewById(R.id.recyclerView_summary);


    }


}