package com.example.gamecenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public final static String SCR_TABLE="Scores"; // name of table

    public final static String SCR_NAME="name"; // id value for employee
    public final static String SCR_PASSWORD="password"; // id value for employee
    public final static String SCR_2048="score_2048"; // id value for employee
    public final static String SCR_PEG="score_peg";  // name of employee

    private static final String DATABASE_NAME = "DBName";

    private static final int DATABASE_VERSION = 6;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table Scores( name text primary key,password text not null, score_2048 text, score_peg);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        Log.w(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS Scores");
        onCreate(database);
    }
}