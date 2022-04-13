package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.CollisionHelper;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.GameObject;

import java.util.ArrayList;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();

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

    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        gameObjects.clear();

        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);  // y좌표 고정 위치 설정.
        fighter = new Fighter(fx, fy);
        gameObjects.add(fighter);

        gameObjects.add(new EnemyGenerator());

        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
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
        for (GameObject gobj : gameObjects) {
            gobj.draw(canvas);

            if(gobj instanceof BoxCollidable) {  // 바운딩 박스 그리기.
                RectF box = ((BoxCollidable) gobj).getBoundingRect();
                canvas.drawRect(box, collisionPaint);
            }
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (GameObject gobj : gameObjects) {
            gobj.update();
        }

        checkCollision();
    }

    private void checkCollision() {
        for(GameObject o1 : gameObjects) {
            if(!(o1 instanceof Enemy)) {  // Enemy가 아닌 경우 무시.
                continue;
            }
            Enemy enemy = (Enemy) o1;
            boolean removed = false;

            for(GameObject o2 : gameObjects) {
                if(!(o2 instanceof Bullet)) {  // Bullet이 아닌 경우 무시.
                    continue;
                }
                Bullet bullet = (Bullet) o2;

                if(CollisionHelper.collides(enemy, bullet)) {  // enemy와 bullet이 충돌했을 경우
                    Log.d(TAG, "Collision");
                    remove(bullet);
                    remove(enemy);
                    removed = true;
                    break;
                }
            }
            if(removed) {
                continue;
            }
        }
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.remove(gameObject);
            }
        });
    }

    public int objectCount() {
        return gameObjects.size();
    }
}
