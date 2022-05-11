package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BaseGame;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.Recyclable;
import com.example.dragonflight.framework.RecycleBin;

import java.util.ArrayList;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();

    public static MainGame get() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame) singleton;
    }

    public enum Layer {
        bg1, bullet, enemy, player, bg2, ui, controller, COUNT
    }

    private Fighter fighter;

    Score score;

    public void init() {
        initLayers(Layer.COUNT.ordinal());  // ordinal() : 정수로 만들어주는 함수.

        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);  // y좌표 고정 위치 설정.
        fighter = new Fighter(fx, fy);
        add(Layer.player, fighter);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());

        add(Layer.ui, score);

        // add(Layer.bg1, new VertScrollBackground(R.mipmap.bg_city, Metrics.size(R.dimen.bg_speed_city)));
        // add(Layer.bg2, new VertScrollBackground(R.mipmap.clouds, Metrics.size(R.dimen.bg_speed_clouds)));

        add(Layer.bg1, new HorzScrollBackground(R.mipmap.bg_city, Metrics.size(R.dimen.bg_speed_city)));
        add(Layer.bg2, new HorzScrollBackground(R.mipmap.clouds, Metrics.size(R.dimen.bg_speed_clouds)));
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

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void add(Layer layer, GameObject gameObject) {
        add(layer.ordinal(), gameObject);
    }
}
