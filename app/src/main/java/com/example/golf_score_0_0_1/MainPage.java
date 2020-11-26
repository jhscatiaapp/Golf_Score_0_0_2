package com.example.golf_score_0_0_1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;
    private Button buttonNewPlay, buttonRecords, buttonMyName, buttonFriends;
    private Button dlgButtonOk, dlgButtonCancel;
    private String myName;
    private TextView dlgTextViewRegisteredName, dlgTextViewRegisterQuestion;
    private EditText dlgEditTextInputName;
    private Dialog nameDialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        setTitle("Main Page");

        variablesSetter();

        buttonNewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNewPlay();
            }
        });


        buttonMyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerMyName();
            }
        });




        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    public void clickNewPlay() {
        Intent intent = new Intent(MainPage.this, CountingPage.class);
        startActivity(intent);
    }

    public String registerMyName() {
        nameDialog.setContentView(R.layout.myname_dialog);

        dlgTextViewRegisteredName = nameDialog.findViewById(R.id.textView_registeredName);
        dlgTextViewRegisterQuestion = nameDialog.findViewById(R.id.textView_registerQuestion);
        dlgEditTextInputName = nameDialog.findViewById(R.id.editText_inputName);
        dlgButtonOk = nameDialog.findViewById(R.id.dlg_button_ok);
        dlgButtonCancel = nameDialog.findViewById(R.id.dlg_button_cancel);

        if (myName.equals("")) {

        } else {dlgTextViewRegisteredName.setText(myName);}


        dlgButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp;
                temp = dlgEditTextInputName.getText().toString();

                if (temp.isEmpty()) {
                    Toast.makeText(MainPage.this, "Please Input Name", Toast.LENGTH_SHORT).show();
                } else {
                    myName = temp;
                    dlgTextViewRegisteredName.setText(myName);
                    dlgTextViewRegisteredName.setTextColor(Color.BLACK);
                    dlgTextViewRegisterQuestion.setText("Do You Want To Change Name?");
                    buttonMyName.setText("Change My Name");

                    nameDialog.dismiss();
                }
            }
        });

        dlgButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameDialog.dismiss();
            }
        });

/*        if (myName.isEmpty()) {
            dlgTextViewRegisteredName.setText("Not Registered");
        } else {
            buttonMyName.setText("Change My Name");
            dlgTextViewRegisteredName.setText(myName);
            dlgTextViewRegisterQuestion.setText("Do You Want To Change Name?");
            dlgButtonOk.setText("Change");
        }*/



        //dlgButtonOk.setText("Change");

        nameDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        nameDialog.show();

        return myName;
    }

    public void variablesSetter() {

        buttonNewPlay = findViewById(R.id.button_start_new_play);
        buttonRecords = findViewById(R.id.button_records);
        buttonMyName = findViewById(R.id.button_myName);
        buttonFriends = findViewById(R.id.button_friends);
        nameDialog = new Dialog(MainPage.this);
        myName = "";

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

}
