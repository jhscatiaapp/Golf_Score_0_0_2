package com.example.golf_score_0_0_1;

import java.util.ArrayList;

public class ScoreSum {

    int sumOUT, sumIN, sumTTL;
    int OUT = 9;
    int IN = 19;
    int TTL = 20;
    ArrayList<Integer> intArray = new ArrayList<>();
    ArrayList<String> retArray = new ArrayList<>();



    public String[] sumScore(ArrayList<String> score) {
        String[] retList = new String[score.size()];
        sumOUT = sumIN = sumTTL = 0;
        intArray.clear();
        retArray.clear();

        retArray = score;

        for (int i = 0; i < score.size(); i++) {
            if (score.get(i).equals("-")) {
                intArray.add(0);
            } else {
                intArray.add(Integer.parseInt(score.get(i)));
            }
        }
        for (int j = 0; j < 9; j++) {
            sumOUT = sumOUT + intArray.get(j);
        }
        for (int k = 10; k < 19; k++) {
            sumIN = sumIN + intArray.get(k);
        }

        sumTTL = sumOUT + sumIN;

        intArray.set(9, sumOUT);
        intArray.set(19, sumIN);
        intArray.set(20, sumTTL);

        for (int i = 0; i < intArray.size(); i++) {
            retList[i] = String.valueOf(intArray.get(i));
        }

        /*retArray.set(9, String.valueOf(intArray.get(9)));
        retArray.set(19, String.valueOf(intArray.get(19)));
        retArray.set(20, String.valueOf(intArray.get(20)));*/

        return retList;
    }

    /*intArrParInfo = scoreSum.sumScore(parInfo);
            parInfo.set(9, String.valueOf(intArrParInfo.get(9)));
            parInfo.set(19, String.valueOf(intArrParInfo.get(19)));
            parInfo.set(20, String.valueOf(intArrParInfo.get(20)));*/

}
