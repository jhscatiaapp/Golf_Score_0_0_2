package com.example.golf_score_0_0_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelperCC extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Golf_CC_test.db";
    public static final String TABLE_NAME1 = "Golf_CC";

    public static final String COL_CC = "CC";
    public static final String COL_HOLE1 = "HOLE1";
    public static final String COL_HOLE2 = "HOLE2";
    public static final String COL_HOLE3 = "HOLE3";
    public static final String COL_HOLE4 = "HOLE4";
    public static final String COL_HOLE5 = "HOLE5";
    public static final String COL_HOLE6 = "HOLE6";
    public static final String COL_HOLE7 = "HOLE7";
    public static final String COL_HOLE8 = "HOLE8";
    public static final String COL_HOLE9 = "HOLE9";
    public static final String COL_HOLE_OUT = "OUT";
    public static final String COL_HOLE10 = "HOLE10";
    public static final String COL_HOLE11 = "HOLE11";
    public static final String COL_HOLE12 = "HOLE12";
    public static final String COL_HOLE13 = "HOLE13";
    public static final String COL_HOLE14 = "HOLE14";
    public static final String COL_HOLE15 = "HOLE15";
    public static final String COL_HOLE16 = "HOLE16";
    public static final String COL_HOLE17 = "HOLE17";
    public static final String COL_HOLE18 = "HOLE18";
    public static final String COL_HOLE_IN = "IN_";
    public static final String COL_HOLE_TTL = "TTL";

    public static final int DB_VERSION = 1;

    public MyDBHelperCC(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " (CC STRING, HOLE1 STRING, HOLE2 STRING, " +
                "HOLE3 STRING, HOLE4 STRING, HOLE5 STRING, HOLE6 STRING, HOLE7 STRING, " +
                "HOLE8 STRING, HOLE9 STRING, OUT STRING, HOLE10 STRING, HOLE11 STRING, " +
                "HOLE12 STRING, HOLE13 STRING, HOLE14 STRING, HOLE15 STRING, HOLE16 STRING, " +
                "HOLE17 STRING, HOLE18 STRING, IN_ STRING, TTL STRING)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1 );
        onCreate(db);
    }

    public boolean saveToDB(String CC, String h1, String h2, String h3, String h4, String h5,
                            String h6, String h7, String h8, String h9, String out, String h10,
                            String h11, String h12, String h13,String h14,String h15, String h16,
                            String h17, String h18, String in_,String ttl) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_CC, CC);
        contentValues.put(COL_HOLE1, h1);
        contentValues.put(COL_HOLE2, h2);
        contentValues.put(COL_HOLE3, h3);
        contentValues.put(COL_HOLE4, h4);
        contentValues.put(COL_HOLE5, h5);
        contentValues.put(COL_HOLE6, h6);
        contentValues.put(COL_HOLE7, h7);
        contentValues.put(COL_HOLE8, h8);
        contentValues.put(COL_HOLE9, h9);
        contentValues.put(COL_HOLE_OUT, out);
        contentValues.put(COL_HOLE10, h10);
        contentValues.put(COL_HOLE11, h11);
        contentValues.put(COL_HOLE12, h12);
        contentValues.put(COL_HOLE13, h13);
        contentValues.put(COL_HOLE14, h14);
        contentValues.put(COL_HOLE15, h15);
        contentValues.put(COL_HOLE16, h16);
        contentValues.put(COL_HOLE17, h17);
        contentValues.put(COL_HOLE18, h18);
        contentValues.put(COL_HOLE_IN, in_);
        contentValues.put(COL_HOLE_TTL, ttl);

        long error = db.insert(TABLE_NAME1, null, contentValues);
        if (error == -1)
            return false;
        else
            return true;
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Integer deleteData(String cc) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "CC = ?", new String[] {cc});
    }

    public void deleteAllData() {
        String query = "DELETE FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
