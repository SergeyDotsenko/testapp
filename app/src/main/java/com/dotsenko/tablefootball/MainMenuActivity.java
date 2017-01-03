package com.dotsenko.tablefootball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        start_game=(Button)findViewById(R.id.start_game);
        start_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_game){
            Intent intent = new Intent(MainMenuActivity.this,GameActivity.class);
            startActivity(intent);
        }
    }
}
