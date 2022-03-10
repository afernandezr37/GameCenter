package com.example.gamecenter;

import java.util.ArrayList;

public class ScoreAndName {

    private ArrayList<String> names;
    private ArrayList<String> scores;

    public ScoreAndName(ArrayList<String> names, ArrayList<String> scores) {
        this.names = names;
        this.scores = scores;
    }

    public ScoreAndName() {
        this.names = new ArrayList<String>();
        this.scores = new ArrayList<String>();
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }
}
