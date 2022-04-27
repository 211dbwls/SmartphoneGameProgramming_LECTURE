package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.R;

public class Bullet implements GameObject, BoxCollidable {
    private static final String TAG = Bullet.class.getSimpleName();

    protected float x, y;
    protected final float length;

    protected final float speed;

    protected static Paint paint;

    protected static float laserWidth;

    protected RectF boundgingBox = new RectF();

    public static Bullet get(float x, float y) {
        return new Bullet(x, y);
    }

    private Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        this.length = Metrics.size(R.dimen.laser_length);
        this.speed = Metrics.size(R.dimen.laser_speed);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.RED);
            laserWidth = Metrics.size(R.dimen.laser_width);
            paint.setStrokeWidth(laserWidth);
        }

        Log.d(TAG, "Created: " + this);
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();

        float frameTime = game.frameTime;
        y -= speed * frameTime;

        float hw = laserWidth / 2;
        boundgingBox.set(x - hw, y, x + hw, y + length);

        if (y < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y + length, paint);
    }

    @Override
    public RectF getBoundingRect() {
        return boundgingBox;
    }
}
