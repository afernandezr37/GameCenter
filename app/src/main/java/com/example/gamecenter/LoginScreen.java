package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private MyDB mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = new MyDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void login(View v) {
        EditText userText = findViewById(R.id.userText);
        EditText passwordText = findViewById(R.id.passwordText);
        if (mDatabase.isNameInUse(userText.getText().toString())) {
            if (mDatabase.isNameAndPassword(userText.getText().toString(), passwordText.getText().toString())) {
                Intent i = new Intent(this, Menu.class);
                String userName = userText.getText().toString();
                i.putExtra("USERNAME", userName);
                startActivity(i);
            }
            else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View v) {
        EditText userText = findViewById(R.id.userText);
        EditText passwordText = findViewById(R.id.passwordText);
        if (!userText.getText().toString().equals("")) {
            if (!mDatabase.isNameInUse((userText.getText().toString()))) {
                if (!passwordText.equals("")) {
                    mDatabase.createRecords(userText.getText().toString(), passwordText.getText().toString());
                    Toast.makeText(this, "User " + userText.getText().toString() + " created!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Username already in use", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please input a username", Toast.LENGTH_SHORT).show();
        }

    }
}