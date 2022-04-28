package com.example.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}