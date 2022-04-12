package com.example.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;

public class Fighter implements GameObject {
    private static final String TAG = Fighter.class.getSimpleName();

    private RectF dstRect = new RectF();  // float로 변경.
    private RectF targetRect = new RectF();  // targetImage Rect

    private static Bitmap bitmap;
    private static Bitmap targetBitmap;  // target Bitmap

    private float x, y;  // 현재 좌표.
    private float dx, dy;  // 얼마나 이동할 것인지.
    private float tx, ty;  // 타겟의 좌표.
    private float radius;

    public Fighter(float x, float y) {
        this.x = x;  // 초기값 설정.
        this.y = y;

        // Resources res = GameView.view.getResources();
        radius = Metrics.size(R.dimen.fighter_radius);

        dstRect.set(x - radius, y - radius, x + radius, y + radius);

        this.tx = x;
        this.ty = y;
        targetRect.set(dstRect);

        if (bitmap == null) {  // 리소스 한번만 로드하도록.
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            targetBitmap = BitmapFactory.decodeResource(res, R.mipmap.target);
        }
    }

    public void draw(Canvas canvas) {  // 그리는 함수.
        canvas.drawBitmap(bitmap, null, dstRect, null);

        if (dx != 0 && dy != 0) {
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
    }

    public void update() {
        if (dx == 0 && dy == 0)
            return;

        if ((dx > 0 && x + dx > tx) || (dx < 0 && x + dx < tx)) {
            dx = tx - x;
            x = tx;
        }
        else {
            x += dx;
        }

        if ((dy > 0 && y + dy > ty) || (dy < 0 && y + dy < ty)) {
            dy = ty - y;
            y = ty;
        }
        else {
            y += dy;
        }
        
        dstRect.offset(dx, dy);  // 이동.
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = ty;

        targetRect.set(tx - radius/2, ty - radius/2, tx + radius/2, ty + radius/2);

        float angle = (float) Math.atan2(ty - y, tx - x);
        float speed = Metrics.size(R.dimen.fighter_speed);
        float dist = speed * MainGame.getInstance().frameTime;

        dx = (float) (dist * Math.cos(angle));
        dy = (float) (dist * Math.sin(angle));
    }
}
