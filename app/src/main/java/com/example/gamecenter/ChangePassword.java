package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
String userName;
MyDB mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = new MyDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
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

    public void changePassword (View v) {
        EditText passText = findViewById(R.id.editText);
        mDatabase.changePassword(userName, passText.getText().toString());
        Toast.makeText(this, "Password changed!", Toast.LENGTH_SHORT).show();
    }
}