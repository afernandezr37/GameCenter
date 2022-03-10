package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView logo3 = (TextView) findViewById(R.id.TextViewBottomVersion);

        ImageView image1 = (ImageView) findViewById(R.id.ImageView2_Left);
        ImageView image4 = (ImageView) findViewById(R.id.ImageView3_Right);
        Animation custom1 = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        MainActivity thisHolder = this;
        View myView = findViewById(R.id.mainLayout);
        custom1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent switchActivityIntent = new Intent(thisHolder, LoginScreen.class);
                startActivity(switchActivityIntent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo3.startAnimation(fade1);
        image1.startAnimation(custom1);
        image4.startAnimation(custom1);


    }
}