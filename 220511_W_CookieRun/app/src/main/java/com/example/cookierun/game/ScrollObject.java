package com.example.cookierun.game;

import android.graphics.RectF;

import com.example.cookierun.framework.BoxCollidable;
import com.example.cookierun.framework.Recyclable;
import com.example.cookierun.framework.Sprite;

public class ScrollObject extends Sprite implements Recyclable, BoxCollidable {
    protected ScrollObject() {
    }

    @Override
    public void finish() {
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }
}