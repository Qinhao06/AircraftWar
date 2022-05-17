package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("ha");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button easyButton  = (Button) findViewById(R.id.easyButton);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLunch();
            }
        });
    }


    public void gameLunch(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

}