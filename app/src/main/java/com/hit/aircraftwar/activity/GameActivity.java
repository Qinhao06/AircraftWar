package com.hit.aircraftwar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.hit.aircraftwar.view.GameSurfaceView;

public class GameActivity extends AppCompatActivity {
    public static int screenWidth;
    public static int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenHW();
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        setContentView(gameSurfaceView);
    }
    public void getScreenHW(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }
}