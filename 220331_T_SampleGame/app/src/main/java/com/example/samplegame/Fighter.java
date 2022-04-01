package com.example.samplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Fighter {
    private Rect dstRect = new Rect();

    private static Bitmap bitmap;
    private static Rect srcRect =  new Rect();

    public Fighter() {
        dstRect.set(0, 0, 200, 200);
    }

    public static void setBitmap(Bitmap bitmap) {  // bitmap 설정하는 함수.
        Fighter.bitmap = bitmap;
        srcRect.set(0,  0, bitmap.getWidth(), bitmap.getWidth());  // srcRect 초기화.
    }

    public void draw(Canvas canvas) {  // 그리는 함수.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void update() {

    }
}
