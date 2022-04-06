package com.example.samplegame;

import android.graphics.Canvas;
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
        for(int i = 0;i < BALL_COUNT; i++) {  // 공 여러개 생성
            int dx = random.nextInt(10) + 5;
            int dy = random.nextInt(10) + 5;
            Ball ball = new Ball(dx, dy);
            gameObjects.add(ball);  // gameObjects에 넣음.
        }

        fighter = new Fighter();  // fighter 생성
        gameObjects.add(fighter);  // gameObjects에 넣음.
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                fighter.setTargetPosition(x, y);
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
}
