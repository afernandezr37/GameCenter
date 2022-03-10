package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class Scores2048 extends AppCompatActivity {

    private MyDB mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores2048);
        TextView score = findViewById(R.id.scoreTitle);
        mDatabase = new MyDB(this);
        RecyclerView contactView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        ScoreAndName allScores = mDatabase.list2048();
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