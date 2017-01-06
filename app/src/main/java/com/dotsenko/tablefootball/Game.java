package com.dotsenko.tablefootball;

import android.graphics.Color;


public class Game {
    private Player pl1;
    private Player pl2;

    private int current_turn;

    public Game(){
        pl1=new Player(1,"Игрок 1",Color.RED);
        pl2=new Player(2,"Игрок 2",Color.GREEN);
        this.current_turn=1;
    }

}
