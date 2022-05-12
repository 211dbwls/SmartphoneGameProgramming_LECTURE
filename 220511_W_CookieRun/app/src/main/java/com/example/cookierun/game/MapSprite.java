package com.example.cookierun.game;

import android.graphics.RectF;

import com.example.cookierun.framework.BaseGame;
import com.example.cookierun.framework.BoxCollidable;
import com.example.cookierun.framework.Recyclable;
import com.example.cookierun.framework.Sprite;

public class MapSprite extends Sprite implements Recyclable, BoxCollidable {
    private static final String TAG = MapSprite.class.getSimpleName();

    protected MapSprite() {
        // Log.d(TAG, "New:" + this);
    }

    @Override
    public void update(float frameTime) {
        float speed = MapLoader.get().speed;
        float dx = speed * frameTime;
        dstRect.offset(dx, 0);
        if (dstRect.right < 0) {
            // Log.d(TAG, "Removing:" + this);
            BaseGame.getInstance().remove(this);
        }
    }

    @Override
    public void finish() {
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }
}