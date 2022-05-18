package com.example.cookierun.framework.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.cookierun.BuildConfig;
import com.example.cookierun.framework.interfaces.BoxCollidable;
import com.example.cookierun.framework.interfaces.GameObject;
import com.example.cookierun.framework.view.GameView;
import com.example.cookierun.framework.interfaces.Recyclable;
import com.example.cookierun.framework.interfaces.Touchable;

import java.util.ArrayList;

public class Scene {
    protected static Scene singleton;
    protected float frameTime, elapsedTime;

    public static Scene getInstance() {
//        if (singleton == null) {
//            singleton = new BaseGame();
//        }
        return singleton;
    }
    public static void clear() {
        singleton = null;
    }
    protected ArrayList<ArrayList<GameObject>> layers;
    protected Paint collisionPaint;

    public void init() {
        if (BuildConfig.showsCollisionBox) {
            collisionPaint = new Paint();
            collisionPaint.setStyle(Paint.Style.STROKE);
            collisionPaint.setColor(Color.RED);
        }
        elapsedTime = 0;
    }

    protected void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        elapsedTime += frameTime;
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.update(frameTime);
            }
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);
            }
        }

        if (BuildConfig.showsCollisionBox) {
            drawBoxCollidables(canvas);
        }
    }

    public void drawBoxCollidables(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                if (gobj instanceof BoxCollidable) {
                    RectF box = ((BoxCollidable) gobj).getBoundingRect();
                    canvas.drawRect(box, collisionPaint);
                }
            }
        }
    }
    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layerIndex);
                gameObjects.add(gameObject);
            }
        });
    }
    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);
                    if (!removed) continue;
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
        for (ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }
        return count;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int touchLayer = getTouchLayerIndex();
        if (touchLayer < 0) {
            return false;
        }

        ArrayList<GameObject> gameObjects = layers.get(touchLayer);
        for (GameObject gobj : gameObjects) {
            if (!(gobj instanceof Touchable)) {
                continue;
            }
            boolean processed = ((Touchable) gobj).onTouchEvent(event);
            if (processed) return true;
        }
        return false;
    }

    protected int getTouchLayerIndex() {
        return -1;
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }
    public void finish() {
        GameView.view.getActivity().finish();
    }
}