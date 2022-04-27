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
import com.example.dragonflight.framework.Recyclable;
import com.example.dragonflight.framework.RecycleBin;

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

    protected ArrayList<ArrayList<GameObject>> layers;
    public enum Layer {
        bullet, enemy, player, controller, COUNT
    }

    private Fighter fighter;

    public float frameTime;

    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());  // ordinal() : 정수로 만들어주는 함수.

        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);  // y좌표 고정 위치 설정.
        fighter = new Fighter(fx, fy);
        add(Layer.player, fighter);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());

        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    private void initLayers(int count) {  // layer 초기화.
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
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
        for(ArrayList<GameObject> gameObjects : layers) {  // layer를 돌면서 그림.
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);

                if (gobj instanceof BoxCollidable) {  // 바운딩 박스 그리기.
                    RectF box = ((BoxCollidable) gobj).getBoundingRect();
                    canvas.drawRect(box, collisionPaint);
                }
            }
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);

        for(ArrayList<GameObject> gameObjects : layers) {  // layer를 돌면서 update.
            for (GameObject gobj : gameObjects) {
                gobj.update();
            }
        }
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layer.ordinal());  // 해당 레이어 찾음.
                gameObjects.add(gameObject);  // 해당 레이어에 gameObject 추가.
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for(ArrayList<GameObject> gameObjects : layers) {  // layer을 돌면서
                    boolean removed = gameObjects.remove(gameObject);  // gameObject 삭제.
                    if(!removed) continue;

                    if (gameObject instanceof Recyclable) {
                        RecycleBin.add((Recyclable) gameObject);
                    }
                    break;
                }
            }
        });
    }

    public int objectCount() {
        int count = 0;
        for(ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }

        return count;
    }
}
