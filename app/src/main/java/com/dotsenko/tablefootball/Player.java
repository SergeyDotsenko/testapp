package com.dotsenko.tablefootball;

/**
 * Created by Webcoding on 06.01.2017.
 */

public class Player {
    private int id;
    private String name;
    private int goals;
    private int color;

    public Player(int id,String name,int color){
        this.color=color;
        this.id=id;
        this.name=name;
        this.goals=0;
    }
}
