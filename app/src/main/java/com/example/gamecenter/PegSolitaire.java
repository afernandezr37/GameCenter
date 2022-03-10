package com.example.gamecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PegSolitaire extends AppCompatActivity {
    MyDB mDatabase;
    ImageView [][] pegMatrix = new ImageView[7][7];
    BoardPegSolitaire boardPegSolitaire = new BoardPegSolitaire();
    TextView pegNumber;
    TextView gameOverText;
    TextView timerText;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    boolean timerStarted = false;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = new MyDB(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                currentUser= null;
            } else {
                currentUser= extras.getString("USERNAME");
            }
        } else {
            currentUser= (String) savedInstanceState.getSerializable("USERNAME");
        }
        boardPegSolitaire.assignDefaultValues();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peg_solitaire);
        populateTextViews();
        pegNumber.setText(String.valueOf(boardPegSolitaire.pegNumber() + " pegs left"));
        paintBoard(boardPegSolitaire);
        timerText = (TextView) findViewById(R.id.timerText);
        timer = new Timer();
        startTimer();
        timerStarted = true;
    }

    public void populateTextViews() {
        gameOverText = (TextView) findViewById(R.id.gameOverText);
        pegNumber = (TextView) findViewById(R.id.score);
        pegMatrix[0][0] = (ImageView) findViewById(R.id.peg00);
        pegMatrix[0][1] = (ImageView) findViewById(R.id.peg01);
        pegMatrix[0][2] = (ImageView) findViewById(R.id.peg02);
        pegMatrix[0][3] = (ImageView) findViewById(R.id.peg03);
        pegMatrix[0][4] = (ImageView) findViewById(R.id.peg04);
        pegMatrix[0][5] = (ImageView) findViewById(R.id.peg05);
        pegMatrix[0][6] = (ImageView) findViewById(R.id.peg06);
        pegMatrix[1][0] = (ImageView) findViewById(R.id.peg10);
        pegMatrix[1][1] = (ImageView) findViewById(R.id.peg11);
        pegMatrix[1][2] = (ImageView) findViewById(R.id.peg12);
        pegMatrix[1][3] = (ImageView) findViewById(R.id.peg13);
        pegMatrix[1][4] = (ImageView) findViewById(R.id.peg14);
        pegMatrix[1][5] = (ImageView) findViewById(R.id.peg15);
        pegMatrix[1][6] = (ImageView) findViewById(R.id.peg16);
        pegMatrix[2][0] = (ImageView) findViewById(R.id.peg20);
        pegMatrix[2][1] = (ImageView) findViewById(R.id.peg21);
        pegMatrix[2][2] = (ImageView) findViewById(R.id.peg22);
        pegMatrix[2][3] = (ImageView) findViewById(R.id.peg23);
        pegMatrix[2][4] = (ImageView) findViewById(R.id.peg24);
        pegMatrix[2][5] = (ImageView) findViewById(R.id.peg25);
        pegMatrix[2][6] = (ImageView) findViewById(R.id.peg26);
        pegMatrix[3][0] = (ImageView) findViewById(R.id.peg30);
        pegMatrix[3][1] = (ImageView) findViewById(R.id.peg31);
        pegMatrix[3][2] = (ImageView) findViewById(R.id.peg32);
        pegMatrix[3][3] = (ImageView) findViewById(R.id.peg33);
        pegMatrix[3][4] = (ImageView) findViewById(R.id.peg34);
        pegMatrix[3][5] = (ImageView) findViewById(R.id.peg35);
        pegMatrix[3][6] = (ImageView) findViewById(R.id.peg36);
        pegMatrix[4][0] = (ImageView) findViewById(R.id.peg40);
        pegMatrix[4][1] = (ImageView) findViewById(R.id.peg41);
        pegMatrix[4][2] = (ImageView) findViewById(R.id.peg42);
        pegMatrix[4][3] = (ImageView) findViewById(R.id.peg43);
        pegMatrix[4][4] = (ImageView) findViewById(R.id.peg44);
        pegMatrix[4][5] = (ImageView) findViewById(R.id.peg45);
        pegMatrix[4][6] = (ImageView) findViewById(R.id.peg46);
        pegMatrix[5][0] = (ImageView) findViewById(R.id.peg50);
        pegMatrix[5][1] = (ImageView) findViewById(R.id.peg51);
        pegMatrix[5][2] = (ImageView) findViewById(R.id.peg52);
        pegMatrix[5][3] = (ImageView) findViewById(R.id.peg53);
        pegMatrix[5][4] = (ImageView) findViewById(R.id.peg54);
        pegMatrix[5][5] = (ImageView) findViewById(R.id.peg55);
        pegMatrix[5][6] = (ImageView) findViewById(R.id.peg56);
        pegMatrix[6][0] = (ImageView) findViewById(R.id.peg60);
        pegMatrix[6][1] = (ImageView) findViewById(R.id.peg61);
        pegMatrix[6][2] = (ImageView) findViewById(R.id.peg62);
        pegMatrix[6][3] = (ImageView) findViewById(R.id.peg63);
        pegMatrix[6][4] = (ImageView) findViewById(R.id.peg64);
        pegMatrix[6][5] = (ImageView) findViewById(R.id.peg65);
        pegMatrix[6][6] = (ImageView) findViewById(R.id.peg66);
    }

    public void paintBoard(BoardPegSolitaire boardPegSolitaire) {
        if (!boardPegSolitaire.isGameOver()) {
            gameOverText.setVisibility(View.INVISIBLE);
        }
        if (boardPegSolitaire.isWin()) {
            mDatabase.addPegScore(currentUser, time);
            gameOverText.setText("YOU WIN!!!");
            gameOverText.setVisibility(View.VISIBLE);
            timerTask.cancel();
            timerStarted = false;
        }
        else if (boardPegSolitaire.isGameOver()) {
            gameOverText.setVisibility(View.VISIBLE);
            gameOverText.setText("GAME OVER");
            timerTask.cancel();
            timerStarted = false;
        }
        pegNumber.setText(String.valueOf(boardPegSolitaire.pegNumber() + " pegs left"));
        int pegValue;
        for (int i = 0; i< boardPegSolitaire.getBoard().length; i++) {
            for (int j = 0; j< boardPegSolitaire.getBoard()[i].length; j++) {
                pegValue = boardPegSolitaire.getBoard()[i][j].getValue();
                switch (pegValue){
                case 0:
                    pegMatrix[i][j].setImageDrawable(getDrawable(R.drawable.none));
                    break;

                case 1:
                    pegMatrix[i][j].setImageDrawable(getDrawable(R.drawable.empty));
                    break;

                case 2:
                    pegMatrix[i][j].setBackground(getDrawable((R.drawable.empty)));
                    pegMatrix[i][j].setImageDrawable(getDrawable(R.drawable.peg));
                    break;

                case 3:
                    pegMatrix[i][j].setBackground(getDrawable((R.drawable.empty)));
                    pegMatrix[i][j].setImageDrawable(getDrawable(R.drawable.selected));
                break;

            }
            }
        }
    }
    public void selectPeg(View v) {
        int i = Character.getNumericValue(v.getTag().toString().charAt(0));
        int j = Character.getNumericValue(v.getTag().toString().charAt(1));
        boardPegSolitaire.selectPeg(i, j);
        paintBoard(boardPegSolitaire);
    }

    public void restart(View v) {
        boardPegSolitaire = new BoardPegSolitaire();
        boardPegSolitaire.assignDefaultValues();
        paintBoard(boardPegSolitaire);
        if(timerTask != null)
        {
            timerTask.cancel();
            time = 0.0;
            timerText.setText(formatTime(0,0,0));
            startTimer();
            timerStarted = true;
        }
    }


    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }


    public void goBack(View v) {
        boardPegSolitaire.goBack();
        paintBoard((boardPegSolitaire));
        if (!timerStarted) {
            startTimer();
            timerStarted = true;
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putDouble("time", time);
        savedInstanceState.putString("username", currentUser);
        savedInstanceState.putSerializable("boardPegSolitaire", boardPegSolitaire);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        populateTextViews();
        currentUser = savedInstanceState.getString("username");
        boardPegSolitaire = (BoardPegSolitaire) savedInstanceState.getSerializable("boardPegSolitaire");
        time = savedInstanceState.getDouble("time");
        paintBoard(boardPegSolitaire);
        super.onSaveInstanceState(savedInstanceState);
    }
}