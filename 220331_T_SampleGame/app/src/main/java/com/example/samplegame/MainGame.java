package com.example.samplegame;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    private static MainGame singleton;
    public static MainGame getInstance() {
        if(singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    private static final int BALL_COUNT = 10;

    private Fighter fighter;  // fighter.

    private ArrayList<GameObject> gameObjects = new ArrayList<>();  // GameObject

    public float frameTime;  // 시간차

    public void init() {
        Random random = new Random();

        float min = Metrics.size(R.dimen.ball_speed_min);
        float max = Metrics.size(R.dimen.ball_speed_max);
        float diff = max - min;

        for(int i = 0;i < BALL_COUNT; i++) {  // 공 여러개 생성
            float dx = random.nextFloat() * diff + min;
            float dy = random.nextFloat() * diff + min;

            Ball ball = new Ball(dx, dy);
            gameObjects.add(ball);  // gameObjects에 넣음.
        }

        float fx = Metrics.width / 2;
        float fy = Metrics.height / 2;
        fighter = new Fighter(fx, fy);  // fighter 생성
        gameObjects.add(fighter);  // gameObjects에 넣음.
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
        for(GameObject gobj : gameObjects) {  // 모든 gameObjects에 대해 그림.
            gobj.draw(canvas);
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);  // frameTime 구함.

        for(GameObject gobj : gameObjects) {  // 모든 gameObjects 업데이트.
            gobj.update();
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
