package com.example.louismenacho.minesweeperflag;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScoreBoard {

    Context context;
    RelativeLayout container;

    private boolean blueTurn = true;
    private int[] score = new int[2];
    private TextView[] player = new TextView[2];
    private TextView[] playerScore = new TextView[2];

    public ScoreBoard(Context context, RelativeLayout container){
        this.context = context;
        this.container = container;

        player[0] = (TextView) container.findViewById(R.id.player1);
        player[1] = (TextView) container.findViewById(R.id.player2);

        playerScore[0] = (TextView) container.findViewById(R.id.player1_score);
        playerScore[1] = (TextView) container.findViewById(R.id.player2_score);
    }

    public void updateScore(int player){
        score[player]++;
        playerScore[player].setText(score[player] + "");
    }

    public boolean getPlayerTurn(){
        return blueTurn;
    }

    public void setPlayerTurn(boolean bool){
        blueTurn = bool;
        if(blueTurn){
            player[0].setTypeface(null, Typeface.BOLD);
            player[1].setTypeface(null, Typeface.NORMAL);
        } else{
            player[0].setTypeface(null, Typeface.NORMAL);
            player[1].setTypeface(null, Typeface.BOLD);
        }
    }

}
