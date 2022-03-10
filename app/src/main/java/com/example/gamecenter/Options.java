package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Options extends AppCompatActivity {
String userName;
MyDB mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = new MyDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
                userName = String.valueOf(extras.getString("USERNAME"));
            }
        } else {
            userName=String.valueOf ((String) savedInstanceState.getSerializable("USERNAME"));

        }
    }


    public void changePassword(View v) {
        Intent i = new Intent(this, ChangePassword.class);
        i.putExtra("USERNAME", userName);
        startActivity(i);
    }

    public void deleteUser(View v) {
        mDatabase.deleteUser(userName);
        Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("username", String.valueOf(userName));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        userName = savedInstanceState.getString("username");
        super.onRestoreInstanceState(savedInstanceState);
    }
}