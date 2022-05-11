package com.example.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dragonflight.framework.BaseGame;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.game.MainGame;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //MainGame.clear();
        BaseGame.clear();

        super.onDestroy();
    }
}