package com.example.dragonflight.game;

import android.graphics.RectF;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Enemy extends Sprite implements BoxCollidable {
    protected final int level;

    protected float dy;

    public static float size;
    protected RectF boundingBox = new RectF();

    protected static int[] bitmapIds =  {
            R.mipmap.f_01_01, R.mipmap.f_02_01, R.mipmap.f_03_01, R.mipmap.f_04_01, R.mipmap.f_05_01,
            R.mipmap.f_06_01, R.mipmap.f_07_01, R.mipmap.f_08_01, R.mipmap.f_09_01, R.mipmap.f_10_01
    };

    public Enemy(int level, float x, float speed) {
        super(x, -size, size, size, bitmapIds[level - 1]);
        this.level = level;

        dy = speed;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        setDstRectWithRadius();

        boundingBox.set(dstRect);
        boundingBox.inset(size / 10, size / 10);

        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }
}
