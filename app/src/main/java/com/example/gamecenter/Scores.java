package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class Scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

    }


    public void open2048Scores(View v) {
        Intent switchActivityIntent = new Intent(this, Scores2048.class);
        startActivity(switchActivityIntent);
    }

    public void openPegSolitaireScores(View v) {
        Intent switchActivityIntent = new Intent(this, ScoresPeg.class);
        startActivity(switchActivityIntent);
    }

}