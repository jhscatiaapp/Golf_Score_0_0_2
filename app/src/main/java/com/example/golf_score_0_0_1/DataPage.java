/*
package com.example.golf_score_0_0_1;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataPage extends AppCompatActivity {

    private Button deleteAllButton;
    private SQLiteDatabase myDB;
    private myDBHelper mydbHelper;
    private myAdapter myadapter;
    private RecyclerView recyclerView;

    private ArrayList<String> part_name, part_size, size_unit;
    private ArrayList<String> material, pressure, pressure_unit;
    private ArrayList<String> cavity, rate, tonnage;

    public static final String TABLE_NAME = "injection_table";
    public static final String COL_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        variableSetter();
        clickDeleteAllButton();
        swipeDelete();

        */
/** --------------- Click and show result activity ----------------------*//*

        final Intent intent = new Intent(ShowFolderActivity.this, ReportActivity.class);
        myadapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                storeDBinArrayList();
                intent.putExtra("part_name", part_name.get(position));
                intent.putExtra("part_size", part_size.get(position));
                intent.putExtra("size_unit", size_unit.get(position));
                intent.putExtra("material", material.get(position));
                intent.putExtra("pressure", pressure.get(position));
                intent.putExtra("pressure_unit", pressure_unit.get(position));
                intent.putExtra("cavity", cavity.get(position));
                intent.putExtra("rate", rate.get(position));
                intent.putExtra("tonnage", tonnage.get(position));
                startActivity(intent);
            }
        });
    }

    private void swipeDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private Cursor getAllItems() {
        return myDB.query(TABLE_NAME, null, null, null,
                null, null, null);
    }

    private void removeItem(long id) {
        myDB.delete(TABLE_NAME, COL_ID + "=" + id, null);
        myadapter.swapCursor(getAllItems());
    }

    private void storeDBinArrayList() {
        part_name.clear();
        part_size.clear();
        size_unit.clear();
        material.clear();
        pressure.clear();
        pressure_unit.clear();
        cavity.clear();
        rate.clear();
        tonnage.clear();

        Cursor cursor = mydbHelper.readAllData();

        while (cursor.moveToNext()) {
            // columnIndex '0' is autoincrement ID
            part_name.add(cursor.getString(1));
            part_size.add(cursor.getString(2));
            size_unit.add(cursor.getString(3));
            material.add(cursor.getString(4));
            pressure.add(cursor.getString(5));
            pressure_unit.add(cursor.getString(6));
            cavity.add(cursor.getString(7));
            rate.add(cursor.getString(8));
            tonnage.add(cursor.getString(9));
        }
    }

    private void clickDeleteAllButton() {
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeDBinArrayList();
                boolean isEmpty = part_size.isEmpty();
                if (isEmpty) {
                    Toast.makeText(ShowFolderActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                } else {
                    popUpDialog();

                }
            }
        });
    }

    public void popUpDialog() {
        final Dialog dialog = new Dialog(ShowFolderActivity.this);
        dialog.setContentView(R.layout.question_delete_all_dialog);
        Button yes = dialog.findViewById(R.id.question_Yes);
        Button no = dialog.findViewById(R.id.question_No);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.delete(TABLE_NAME, COL_ID + ">= 0", null);
                myadapter.swapCursor(getAllItems());
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void variableSetter() {
        deleteAllButton = findViewById(R.id.button_Delete_folder);

        mydbHelper = new myDBHelper(this);
        myDB = mydbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.recyclerView_showFolder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter = new myAdapter(this, getAllItems());
        recyclerView.setAdapter(myadapter);

        part_name = new ArrayList<>();
        part_size = new ArrayList<>();
        size_unit = new ArrayList<>();
        material = new ArrayList<>();
        pressure = new ArrayList<>();
        pressure_unit = new ArrayList<>();
        cavity = new ArrayList<>();
        rate = new ArrayList<>();
        tonnage = new ArrayList<>();
    }
}
*/
