package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.GameObject;

import java.util.ArrayList;

public class MainGame {
    private static MainGame singleton;
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }
    private static final int BALL_COUNT = 10;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Fighter fighter;
    public float frameTime;

    public void init() {
        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);  // y좌표 고정 위치 설정.
        fighter = new Fighter(fx, fy);
        gameObjects.add(fighter);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                fighter.setTargetPosition(x, y);
                if (action == MotionEvent.ACTION_DOWN) {
                    fighter.fire();
                }
                return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : gameObjects) {
            gobj.draw(canvas);
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (GameObject gobj : gameObjects) {
            gobj.update();
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
