package com.dotsenko.tablefootball;

/**
 * Created by Webcoding on 11.01.2017.
 */

public class Field {
    private int x;
    private int y;
    private int scale;
    private int field_width=720;
    private int field_height=1108;

    public void Field(){
        this.x=0;
        this.y=0;
        this.scale=0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        if(x<=0 && x> -1*field_width) {
            this.x = x;
        }
    }

    public void setY(int y){
        if(y<=0 && y> -1*field_height+120) {
            this.y = y;
        }
    }
}
