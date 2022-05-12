package com.example.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;

import com.example.cookierun.R;
import com.example.cookierun.framework.BoxCollidable;
import com.example.cookierun.framework.SheetSprite;

public class Player extends SheetSprite implements BoxCollidable {

    private static final float FRAMES_PER_SECOND = 8f;  // 1초에 8장

    public Player(float x, float y, float w, float h) {
        super(R.mipmap.cookie, FRAMES_PER_SECOND);

        this.x = x;
        this.y = y;
        setDstRect(w, h);
        srcRects = new Rect[] {
                new Rect(72 + 0 * 272, 404, 72+140 + 0 * 272, 404+140),
                new Rect(72 + 1 * 272, 404, 72+140 + 1 * 272, 404+140),
                new Rect(72 + 2 * 272, 404, 72+140 + 2 * 272, 404+140),
                new Rect(72 + 3 * 272, 404, 72+140 + 3 * 272, 404+140),
        };
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }
}