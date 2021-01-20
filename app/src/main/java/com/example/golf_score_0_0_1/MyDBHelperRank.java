package com.example.golf_score_0_0_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelperRank extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "rank_test01.db";
    public static final String TABLE_NAME = "Rank_Table";
    public static final String COL_RANK = "RANK";
    public static final String COL_PLAYER = "PLAYER";
    public static final String COL_SCORE = "SCORE";
    public static final String COL_THRU = "THRU";
    public static final String COL_SHOT = "SHOT";

    public static final int DB_VERSION = 1;

    public MyDBHelperRank(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (RANK String, PLAYER String, SCORE String, " +
                "THRU String, SHOT String" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public boolean saveToDB(String RANK, String PLAYER, String SCORE, String THRU, String SHOT) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_RANK, RANK);
        contentValues.put(COL_PLAYER, PLAYER);
        contentValues.put(COL_SCORE, SCORE);
        contentValues.put(COL_THRU, THRU);
        contentValues.put(COL_SHOT, SHOT);

        long error = db.insert(TABLE_NAME, null, contentValues);
        if (error == -1)
            return false;
        else
            return true;
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Integer deleteData(String PLAYER) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "PLAYER = ?", new String[] {PLAYER});
    }

    public void deleteAllData() {
        String query = "DELETE FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        //db.close();
        /**          after cancel close db it is working. i don't know y        */
    }
}
