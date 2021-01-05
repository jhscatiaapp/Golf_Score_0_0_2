package com.example.golf_score_0_0_1;

public class Ordinal {
    public String ordinalChange(int num) {
        String[] ordinalNum = new String[] {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th",
        "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "END"};
        return ordinalNum[num - 1];
    }
}
