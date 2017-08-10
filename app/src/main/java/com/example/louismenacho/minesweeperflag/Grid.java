package com.example.louismenacho.minesweeperflag;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

public class Grid {

    Context context;
    RelativeLayout container;
    RelativeLayout grid;
    ScoreBoard scoreBoard;
    Random rand = new Random();

    private static final int COLS = 12;//2:3 ratio
    private static final int ROWS = 18;//2:3 ratio
    private static final int FLAGS = 35;
    private int squareSize;
    private Tile tile[][];
    private int[][] neighborCoord ={
            {-1,-1},{0,-1},{1,-1},//top
            {-1,0},{1,0},         //middle
            {-1,1},{0,1},{1,1} //bottom
    };

    public Grid(Context context,RelativeLayout container, ScoreBoard scoreBoard) {
        this.context = context;
        this.container = container;
        this.scoreBoard = scoreBoard;
        this.grid = (RelativeLayout)container.findViewById(R.id.grid);
        this.tile = new Tile[COLS][ROWS];
    }

    public void buildTiles(int screenWidth){

        squareSize = getSquareSize(screenWidth);

        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                int xCoord = getXCoord(c);
                int yCoord = getYCoord(r);
                tile[c][r] = new Tile(context,c,r);
                setOnTouch(c,r);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(squareSize, squareSize);
                params.leftMargin = xCoord;
                params.topMargin = yCoord;
                grid.addView(tile[c][r], params);
            }
        }
    }

    public void placeFlags(){
        for (int i = 0; i < FLAGS; i++) {
            int x = rand.nextInt(COLS);
            int y = rand.nextInt(ROWS);
            if(tile[x][y].hasFlag()){
                i--;
                continue;
            }
            tile[x][y].setHasFlag(true);
            incrementSurroundingTiles(x,y);
        }
    }

    private void incrementSurroundingTiles(int x, int y){
        for (int coord[]: neighborCoord) {
            int targetX = x+coord[0];
            int targetY = y+coord[1];
            if(targetX >= 0 && targetX < COLS && targetY >= 0 && targetY < ROWS){
                tile[targetX][targetY].incrementNeighborFlagCount();
            }
        }
    }

    private void setOnTouch(final int x, final int y){
        tile[x][y].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("state1", "xCoord: " + x + " yCoord: " + y);
                if(tile[x][y].hasFlag() && !tile[x][y].isOpened()){
                    int player;
                    player = (scoreBoard.getPlayerTurn()) ? tile[x][y].setBlueFlag() : tile[x][y].setRedFlag();
                    scoreBoard.updateScore(player);
                } else if(!tile[x][y].isOpened()) {
                    setOpen(tile[x][y]);
                    scoreBoard.setPlayerTurn(!scoreBoard.getPlayerTurn());
                }
            }
        });
    }

    private void setOpen(Tile tile){
        tile.setOpen();
        if(tile.getNeighborFlagCount() > 0){
            return;
        }
        int x = tile.getxCoord();
        int y = tile.getyCoord();
        for (int coord[]: neighborCoord) {
            int targetX = x+coord[0];
            int targetY = y+coord[1];
            if(targetX >= 0 && targetX < COLS && targetY >= 0 && targetY < ROWS){
                if(!this.tile[targetX][targetY].isOpened())
                    setOpen(this.tile[targetX][targetY]);
            }
        }
    }

    public void openAll(){
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                tile[i][j].performClick();
            }
        }
    }

    private int getSquareSize(final int width) {
        return width / 12;
    }

    private int getXCoord(final int x) {
        return squareSize * x;
    }

    private int getYCoord(final int y) {
        return squareSize * y;
    }

}
