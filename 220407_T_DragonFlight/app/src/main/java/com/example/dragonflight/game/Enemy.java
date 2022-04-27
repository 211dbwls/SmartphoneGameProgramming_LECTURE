package com.example.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Log;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.AnimSprite;
import com.example.dragonflight.framework.BitmapPool;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Recyclable;
import com.example.dragonflight.framework.RecycleBin;
import com.example.dragonflight.framework.Sprite;

import java.util.ArrayList;

public class Enemy extends AnimSprite implements BoxCollidable, Recyclable {
    private static final String TAG = Enemy.class.getSimpleName();

    public static final float FRAMES_PER_SECOND = 10.0f;

    protected float dy;

    public static float size;
    protected RectF boundingBox = new RectF();

    protected static int[] bitmapIds =  {
            R.mipmap.enemy_01, R.mipmap.enemy_02, R.mipmap.enemy_03, R.mipmap.enemy_04, R.mipmap.enemy_05,
            R.mipmap.enemy_06, R.mipmap.enemy_07, R.mipmap.enemy_08, R.mipmap.enemy_09, R.mipmap.enemy_10,
            R.mipmap.enemy_11, R.mipmap.enemy_12, R.mipmap.enemy_13, R.mipmap.enemy_14, R.mipmap.enemy_15,
            R.mipmap.enemy_16, R.mipmap.enemy_17, R.mipmap.enemy_18, R.mipmap.enemy_19, R.mipmap.enemy_20
    };

    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = bitmapIds.length;
    protected int level;

    public static Enemy get(int level, float x, float speed) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);

        if(enemy != null) {
            enemy.set(level, x, speed);
            return enemy;
        }
        return new Enemy(level, x, speed);
    }

    private void set(int level, float x, float speed) {
        bitmap = BitmapPool.get(bitmapIds[level - 1]);
        this.x = x;
        this.y = -size;
        this.dy = speed;
        this.level = level;
    }

    private Enemy(int level, float x, float speed) {
        super(x, -size, size, size, bitmapIds[level - 1], FRAMES_PER_SECOND, 0);
        this.level = level;

        dy = speed;

        Log.d(TAG, "Created: " + this);
    }

    @Override
    public void update() {
        // super.update();

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

    @Override
    public void finish() {

    }

    public int getScore() {
        return level * 10;
    }
}
