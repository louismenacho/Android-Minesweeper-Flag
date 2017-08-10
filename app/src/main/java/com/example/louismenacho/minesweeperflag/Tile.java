package com.example.louismenacho.minesweeperflag;

import android.content.Context;
import android.widget.ImageView;

public class Tile extends ImageView {

    private int xCoord;
    private int yCoord;
    private boolean isOpened = false;
    private boolean hasFlag = false;
    private int neighborFlagCount = 0;

    public Tile(Context context, int x, int y) {
        super(context);
        xCoord = x;
        yCoord = y;
        setImageResource(R.drawable.bluetile);
    }

    public void setOpen(){
        isOpened = true;
        switch (neighborFlagCount) {
            case 0: setImageResource(R.drawable.yellowtile);
                break;
            case 1: setImageResource(R.drawable.one);
                break;
            case 2: setImageResource(R.drawable.two);
                break;
            case 3: setImageResource(R.drawable.three);
                break;
            case 4: setImageResource(R.drawable.four);
                break;
            default: setImageResource(R.drawable.mine);
        }
    }

    public int setBlueFlag(){
        isOpened = true;
        setImageResource(R.drawable.blueflag);
        return 0;
    }

    public int setRedFlag(){
        isOpened = true;
        setImageResource(R.drawable.redflag);
        return 1;
    }

    public void setHasFlag(boolean bool){
        hasFlag = bool;
    }

    public int getxCoord(){
        return xCoord;
    }
    public int getyCoord(){
        return yCoord;
    }

    public int getNeighborFlagCount(){
        return neighborFlagCount;
    }

    public void incrementNeighborFlagCount(){
        neighborFlagCount++;
    }

    public boolean hasFlag(){
        return hasFlag;
    }

    public boolean isOpened(){
        return isOpened;
    }

//    public void setClickAction(){
//        setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Log.d("state1","xCoord: "+xCoord+" yCoord: "+yCoord+" flagCount: "+neighborFlagCount);
//                if(hasFlag){
//                    setFlag(1);
//                } else {
//                    setOpen();
//                }
//            }
//        });
//    }


}
