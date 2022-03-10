package com.example.gamecenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDB{

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    public final static String SCR_TABLE="Scores"; // name of table

    public final static String SCR_NAME="name"; // id value for employee
    public final static String SCR_PASSWORD="password"; // id value for employee
    public final static String SCR_2048="score_2048"; // id value for employee
    public final static String SCR_PEG="score_peg";  // name of employee

    /**
     *
     * @param context
     */
    public MyDB(Context context){
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public void addPegScore(String name, double score) {
        String[] cols = new String[] {SCR_NAME, SCR_PEG};
        Cursor cursor = database.query(true, SCR_TABLE,cols,"name = \'" + name + "\'"
                , null, null, null, null, null);
        double currentScore = Double.MAX_VALUE;
        if (cursor.moveToFirst()) {
            if (cursor.getString(1) != null) {
                currentScore = Double.valueOf(cursor.getString(1));
            }
        }
        if (score < currentScore) {
            String[] auxColumns = new String[1];
            auxColumns[0] = "score_peg";
            ContentValues auxScore = new ContentValues();
            auxScore.put("score_peg", String.valueOf(Math.round(score)));
            database.update(SCR_TABLE, auxScore, "name = \'" + name + "\'", null);
        }
    }

    public void add2048Score(String name, int score) {
        String[] cols = new String[] {SCR_NAME, SCR_2048};
        Cursor cursor = database.query(true, SCR_TABLE,cols,"name = \'" + name + "\'"
                , null, null, null, null, null);
        int currentScore = 0;
        if (cursor.moveToFirst()) {
            if (cursor.getString(1) != null) {
                currentScore = Integer.valueOf(cursor.getString(1));
            }
        }
        if (score > currentScore) {
            ContentValues auxScore = new ContentValues();
            auxScore.put("score_2048", String.valueOf(score));
            database.update(SCR_TABLE, auxScore, "name = \'" + name + "\'", null);
        }
    }



    public long createRecords(String name, String password){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password", password);
        return database.insert("Scores", null, values);
    }


    public ScoreAndName listPeg() {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> scores = new ArrayList<String>();
        String[] cols = new String[] {SCR_NAME, SCR_PEG};
        Cursor cursor = database.query(true, SCR_TABLE,cols,null
                , null, null, null, "length(score_peg), score_peg ASC" , null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1) != null) {
                    String name = cursor.getString(0);
                    String score = cursor.getString(1);
                    names.add(name);
                    scores.add(score);
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        ScoreAndName scoreAndName = new ScoreAndName();
        scoreAndName.setNames(names);
        scoreAndName.setScores(scores);
        return scoreAndName;
    }

    public Boolean isNameInUse(String name) {
        Boolean isNameInUse = false;
        String[] cols = new String[] {SCR_NAME};
        Cursor cursor = database.query(true, SCR_TABLE,cols,null
                , null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(name)) {
                    isNameInUse = true;
                }

            }
            while (cursor.moveToNext() && !isNameInUse);
        }
        cursor.close();
        return isNameInUse;
    }

    public Boolean isNameAndPassword(String name, String password) {
        Boolean doesPasswordMatch = false;
        String[] cols = new String[] {SCR_NAME, SCR_PASSWORD};
        Cursor cursor = database.query(true, SCR_TABLE,cols,"name = \'" + name + "\'"
                , null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
            if ((cursor.getString(0).equals(name)) && (cursor.getString(1).equals(password))) {
                doesPasswordMatch = true;
            }
            cursor.close();
        }


        return doesPasswordMatch;
    }

    public ScoreAndName list2048() {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> scores = new ArrayList<String>();
        String[] cols = new String[] {SCR_NAME, SCR_2048};
        Cursor cursor = database.query(true, SCR_TABLE,cols,null
                , null, null, null, "cast(score_2048 as unsigned) DESC" , null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1) != null) {
                    String name = cursor.getString(0);
                    String score = cursor.getString(1);
                    names.add(name);
                    scores.add(score);
                }

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        ScoreAndName scoreAndName = new ScoreAndName();
        scoreAndName.setNames(names);
        scoreAndName.setScores(scores);
        return scoreAndName;
    }

    public void changePassword(String name, String newPassword) {

        String[] cols = new String[] {SCR_NAME, SCR_PASSWORD};
        Cursor cursor = database.query(true, SCR_TABLE,cols,"name = \'" + name + "\'"
                , null, null, null, null, null);
        if (cursor.moveToFirst()) {
            ContentValues auxPassword = new ContentValues();
            auxPassword.put(SCR_PASSWORD, newPassword);
            database.update(SCR_TABLE, auxPassword, "name = \'" + name + "\'", null);
        }
    }

    public void deleteUser(String name) {
        if (isNameInUse(name)) {
            database.delete(SCR_TABLE, "name = \'" + name + "\'", null);
        }
    }
}