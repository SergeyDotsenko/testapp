package com.dotsenko.tablefootball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Vector;


public class Ball {
    private int currentPositionX;
    private int currentPositionY;
    private Vector<CurrentBallPosition> ballPositions;
    private int centerX = 360;
    private int centerY = 555;

    public Ball() {
        //32 на 48(17 на 25)
        this.currentPositionX = 0;
        this.currentPositionY = 0;
        this.ballPositions = new Vector<CurrentBallPosition>();
        ballPositions.add(new CurrentBallPosition(17, 25, 1));
    }

    public void drawBallPositions(Canvas canvas, Paint paint) {
        int widthLine = 5;
        for (int i = 0; i < ballPositions.size(); i++) {
            CurrentBallPosition cbp = ballPositions.get(i);
            CurrentBallPosition next_cbp=null;
            if(i!=0) {
                if (i < ballPositions.size() - 1) {
                    next_cbp = ballPositions.get(i + 1);
                }
            }
            if (cbp.player == 1) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.GREEN);
            }
            paint.setStrokeWidth(widthLine);
            if(next_cbp != null) {
                canvas.drawLine(getX(cbp.x), getY(cbp.y), getX(next_cbp.x), getY(next_cbp.y), paint);
            }
            else{
                canvas.drawCircle((float)getX(cbp.x), (float)getY(cbp.y),6,paint);
            }
        }
    }

    private int getX(int x) {
        int value = 0;
        if (x == 17) {
            value = centerX;
        }
        else{
            value=x;
        }
        return value;
    }

    private int getY(int y) {
        int value = 0;
        if (y == 25) {
            value = centerY;
        }
        else{
            value=y;
        }
        return value;
    }

    public void setNewBallPosition(int x,int y,int player){
        ballPositions.add(new CurrentBallPosition(x, y, 1));
    }
}
