package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.R;

public class Bullet implements GameObject, BoxCollidable {
    protected float x, y;
    protected final float length;
    protected final float dx, dy;
    protected final float ex, ey;
    protected static Paint paint;

    protected static float laserWidth;

    protected RectF boundgingBox = new RectF();

    public Bullet(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.length = Metrics.size(R.dimen.laser_length);
        float speed = Metrics.size(R.dimen.laser_speed);
        this.dx = (float) (speed * Math.cos(angle));
        this.dy = (float) (speed * Math.sin(angle));
        this.ex = (float) (length * Math.cos(angle));
        this.ey = (float) (length * Math.sin(angle));

        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.RED);
            laserWidth = Metrics.size(R.dimen.laser_width);
            paint.setStrokeWidth(laserWidth);
        }
    }
    @Override
    public void update() {
        MainGame game = MainGame.getInstance();

        float frameTime = game.frameTime;
        x += dx * frameTime;
        y += dy * frameTime;

        float hw = laserWidth / 2;
        boundgingBox.set(x - hw, y, x + hw, y + ey);

        if(y < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x + ex, y + ey, paint);
    }

    @Override
    public RectF getBoundingRect() {
        return boundgingBox;
    }
}
