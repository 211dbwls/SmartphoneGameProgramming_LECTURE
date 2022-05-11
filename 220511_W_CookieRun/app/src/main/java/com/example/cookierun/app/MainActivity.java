package com.example.cookierun.app;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookierun.R;
import com.example.cookierun.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
    }

    public void onBtnFirst(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
    }

    public void onBtnSecond(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 1);
        startActivity(intent);
    }}