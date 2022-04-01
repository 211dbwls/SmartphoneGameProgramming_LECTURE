package com.example.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Fighter {
    private Rect dstRect = new Rect();

    private static Bitmap bitmap;
    private static Rect srcRect =  new Rect();

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

    }

    public void setPosition(float x, float y) {
        int radius =  200 / 2;
        dstRect.set((int)x - radius, (int)y - radius, (int)x + radius, (int)y + radius);
    }
}
