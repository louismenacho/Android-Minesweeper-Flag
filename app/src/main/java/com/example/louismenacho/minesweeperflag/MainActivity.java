package com.example.louismenacho.minesweeperflag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Grid minesweeperGrid;
    private ScoreBoard scoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;

        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        scoreBoard = new ScoreBoard(this,container);
        minesweeperGrid = new Grid(this,container,scoreBoard);
        minesweeperGrid.buildTiles(screenWidth);
        minesweeperGrid.placeFlags();
    }

}
