package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

    public void open2048(View v) {
        Intent i = new Intent(this, Game2048.class);
        i.putExtra("USERNAME", userName);
        startActivity(i);
    }

    public void openPegSolitaire(View v) {
        Intent i = new Intent(this, PegSolitaire.class);
        i.putExtra("USERNAME", userName);
        startActivity(i);
    }

    public void openOptions(View v) {
        Intent i = new Intent(this, Options.class);
        i.putExtra("USERNAME", userName);
        startActivity(i);
    }

    public void openScores(View v) {
        Intent switchActivityIntent = new Intent(this, Scores.class);
        startActivity(switchActivityIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("user", userName);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userName = savedInstanceState.getString("user");
    }
}