package com.example.cookierun.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookierun.framework.BaseGame;
import com.example.cookierun.framework.GameView;
import com.example.dragonflight.framework.BaseGame;
import com.example.dragonflight.framework.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGame.get();
        setContentView(new GameView(this, null));
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        BaseGame.clear();
        super.onDestroy();
    }
}