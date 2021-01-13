package com.example.golf_score_0_0_1;

public class ScoreArrange {
    private String mName;
    private int mScore;
    private String mThruHole;
    private String mShot;

    public ScoreArrange(String mName, int mScore, String mThruHole, String mShot) {
        this.mName = mName;
        this.mScore = mScore;
        this.mThruHole = mThruHole;
        this.mShot = mShot;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public String getmThruHole() {
        return mThruHole;
    }

    public void setmThruHole(String mThruHole) {
        this.mThruHole = mThruHole;
    }

    public String getmShot() {
        return mShot;
    }

    public void setmShot(String mShot) {
        this.mShot = mShot;
    }
}

