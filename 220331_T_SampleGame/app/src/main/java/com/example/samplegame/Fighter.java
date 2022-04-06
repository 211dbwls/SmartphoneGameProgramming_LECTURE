package com.example.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Fighter implements GameObject {
    private static final String TAG = Fighter.class.getSimpleName();

    private RectF dstRect = new RectF();  // float로 변경.

    private static Bitmap bitmap;
    private static Rect srcRect =  new Rect();

    private float x, y;  // 현재 좌표.
    private float dx, dy;  // 얼마나 이동할 것인지.
    private float tx, ty;  // 타겟의 좌표.

    public Fighter() {
        dstRect.set(0, 0, 200, 200);

        if (bitmap == null) {  // 리소스 한번만 로드하도록.
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getWidth());  // srcRect 초기화.
        }
    }

    public void draw(Canvas canvas) {  // 그리는 함수.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void update() {
        float angle = (float) Math.atan2(ty - y, tx - x);  // 현재 위치와 타겟 위치의 각도 구함.

        float speed = 1000;
        float dist = speed * MainGame.getInstance().frameTime;  // 이번 frame에 움직여야할 거리.

        dx = (float) (dist * Math.cos(angle));  // 얼마나 이동할 것인지 설정.
        dy = (float) (dist * Math.sin(angle));

        Log.d(TAG, "x="+x+" y="+y+" dx="+dx+" dy="+dy);
        
        x += dx;
        y += dy;
        
        dstRect.offset(dx, dy);  // 이동.
    }

    /*public void setPosition(float x, float y) {
        int radius =  200 / 2;
        dstRect.set((int)x - radius, (int)y - radius, (int)x + radius, (int)y + radius);
    }*/

    public void setTargetPosition(float x, float y) {  // 타겟 위치 저장.
        tx = x;
        ty = y;
    }
}
