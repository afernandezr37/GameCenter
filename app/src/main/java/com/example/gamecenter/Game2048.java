package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class Game2048 extends AppCompatActivity {
    private MyDB mDatabase;
    Board2048 board2048;
    TextView [] [] textViews;
    TextView gameOver;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
                currentUser = String.valueOf(extras.getString("USERNAME"));
            }
        } else {
            currentUser=String.valueOf ((String) savedInstanceState.getSerializable("USERNAME"));
        }
        mDatabase = new MyDB(this);
        board2048 = new Board2048();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2048);
        gameOver = (TextView) findViewById(R.id.gameOverText);
        gameOver.setText("GAME OVER");
        populateTextViews();
        GridLayout layout = (GridLayout) findViewById(R.id.gridLayout2048);
        board2048.spawn();
        board2048.spawn();
        paintLayout(board2048);
        layout.setOnTouchListener(new OnSwipeTouchListener(Game2048.this) {
            public void onSwipeTop() {
                if (board2048.changeUp()) {
                    board2048.up();
                    board2048.spawn();
                    paintLayout(board2048);
                }
            }

            public void onSwipeRight() {
                if (board2048.changeRight()) {
                    board2048.right();
                    board2048.spawn();
                    paintLayout(board2048);
                }
            }

            public void onSwipeLeft() {
                if (board2048.changeLeft()) {
                    board2048.left();
                    board2048.spawn();
                    paintLayout(board2048);
                }
            }

            public void onSwipeBottom() {
                if (board2048.changeDown()) {
                    board2048.down();
                    board2048.spawn();
                    paintLayout(board2048);
                }
            }

        });
    }
    //Debuging method that forces a game over
    public void forceGameOver() {
        for (int i = 0;i<board2048.getBoard().length; i++) {
            for (int j = 0; j<board2048.getBoard()[i].length; j++) {
                if (((i + j) % 2 )== 0) {
                    board2048.getBoard()[i][j].setValue(2);
                }
                else {
                    board2048.getBoard()[i][j].setValue(4);
                }
            }
        }
    }

    public void populateTextViews() {
        textViews = new TextView [4] [4];
        textViews[0][0] = (TextView) findViewById(R.id.textView1);
        textViews[0][1] = (TextView) findViewById(R.id.textView2);
        textViews[0][2] = (TextView) findViewById(R.id.textView3);
        textViews[0][3] = (TextView) findViewById(R.id.textView4);
        textViews[1][0] = (TextView) findViewById(R.id.textView5);
        textViews[1][1] = (TextView) findViewById(R.id.textView6);
        textViews[1][2] = (TextView) findViewById(R.id.textView7);
        textViews[1][3] = (TextView) findViewById(R.id.textView8);
        textViews[2][0] = (TextView) findViewById(R.id.textView9);
        textViews[2][1] = (TextView) findViewById(R.id.textView10);
        textViews[2][2] = (TextView) findViewById(R.id.textView11);
        textViews[2][3] = (TextView) findViewById(R.id.textView12);
        textViews[3][0] = (TextView) findViewById(R.id.textView13);
        textViews[3][1] = (TextView) findViewById(R.id.textView14);
        textViews[3][2] = (TextView) findViewById(R.id.textView15);
        textViews[3][3] = (TextView) findViewById(R.id.textView16);
    }

    public void paintLayout(Board2048 board2048) {
        for (int i = 0; i< board2048.getBoard().length; i++) {
            for (int j = 0; j< board2048.getBoard()[i].length; j++) {
                textViews[i][j].setText(String.valueOf(board2048.getBoard()[i][j].getValue()));
                setColor(board2048.getBoard()[i][j], textViews[i][j]);
            }
        }
        gameOver = (TextView) findViewById(R.id.gameOverText);
        gameOver.setVisibility(View.INVISIBLE);
        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Score: "+ board2048.getScore());
        if (board2048.isGameOver()) {
            mDatabase.add2048Score(currentUser, board2048.getScore());
            gameOver.setVisibility(View.VISIBLE);
            gameOver.setText("GAME OVER");
        }

        if (board2048.getHighTile() == 2048) {
            mDatabase.add2048Score(currentUser, board2048.getScore());
            gameOver.setVisibility(View.VISIBLE);
            gameOver.setText("2048!!!");
        }

    }

    public void setColor(Tile2048 tile2048, TextView textView) {


        switch (tile2048.getValue()) {
            case 0:
                textView.setBackgroundColor(getResources().getColor(R.color.purple_500));
                textView.setText("");
                break;
            case 2:
                textView.setBackgroundColor(getResources().getColor(R.color.number2));
                break;
            case 4:
                textView.setBackgroundColor(getResources().getColor(R.color.number4));
                break;
            case 8:
                textView.setBackgroundColor(getResources().getColor(R.color.number8));
                break;
            case 16:
                textView.setBackgroundColor(getResources().getColor(R.color.number16));
                break;
            case 32:
                textView.setBackgroundColor(getResources().getColor(R.color.number32));
                break;
            case 64:
                textView.setBackgroundColor(getResources().getColor(R.color.number64));
                break;
            case 128:
                textView.setBackgroundColor(getResources().getColor(R.color.number128));
                break;
            case 256:
                textView.setBackgroundColor(getResources().getColor(R.color.number256));
                break;
            case 512:
                textView.setBackgroundColor(getResources().getColor(R.color.number512));
                break;
            case 1024:
                textView.setBackgroundColor(getResources().getColor(R.color.number1024));
                break;
            case 2048:
                textView.setBackgroundColor(getResources().getColor(R.color.number2048));
                break;
            default:
                textView.setBackgroundColor(getResources().getColor(R.color.teal_200));

        }
    }

    public void restart(View v) {
        for (int i = 0; i < board2048.getBoard().length; i++) {
            for (int j = 0; j < board2048.getBoard()[i].length; j++) {
                board2048.getBoard()[i][j].setValue(0);
            }
        }
        gameOver = (TextView) findViewById(R.id.gameOverText);
        board2048.spawn();
        board2048.spawn();
        board2048.setScore(0);
        paintLayout(board2048);
    }

    public void goBack(View v) {
        board2048.goBack();
        paintLayout(board2048);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("board2048", board2048);
        savedInstanceState.putString("username", String.valueOf(currentUser));
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        populateTextViews();
        currentUser = savedInstanceState.getString("username");
        board2048 = (Board2048) savedInstanceState.getSerializable("board2048");
        paintLayout(board2048);
        super.onRestoreInstanceState(savedInstanceState);
        paintLayout(board2048);
    }


}