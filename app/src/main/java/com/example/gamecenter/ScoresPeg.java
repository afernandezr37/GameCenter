package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScoresPeg extends AppCompatActivity {
    private MyDB mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDatabaseHelper helper = new MyDatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_peg);
        TextView score = findViewById(R.id.scoreTitle);
        mDatabase = new MyDB(this);
        RecyclerView contactView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        ScoreAndName allScores = mDatabase.listPeg();
        if (allScores.getNames().size() > 0) {
            contactView.setVisibility(View.VISIBLE);
            LinkedList auxNames = new LinkedList();
            LinkedList auxScores = new LinkedList();
            auxNames.addAll(allScores.getNames());
            auxScores.addAll(allScores.getScores());
            WordListAdapter mAdapter = new WordListAdapter(this, auxNames, auxScores);
            contactView.setAdapter(mAdapter);
        }
        else {
            contactView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no scores for this game", Toast.LENGTH_LONG).show();
        }


    }


}