package com.example.webcoding.simpleeventnotebook;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    DialogFragment fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Button date=(Button)findViewById(R.id.event_date);
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        fr = new DatePickerFragment();
        fr.show(getFragmentManager(), "fr");
    }
}
