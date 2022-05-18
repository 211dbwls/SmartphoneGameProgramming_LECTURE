package com.example.cookierun.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookierun.framework.game.Scene;
import com.example.cookierun.framework.view.GameView;
import com.example.cookierun.game.MainScene;
import com.example.cookierun.game.PausedScene;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Scene.push(PausedScene.get());

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
        Scene.clear();
        super.onDestroy();
    }
}