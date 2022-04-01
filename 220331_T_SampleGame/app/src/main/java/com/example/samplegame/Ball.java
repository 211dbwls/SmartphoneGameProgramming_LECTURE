package com.example.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Ball {
    /* 공이 가지고 있어야 하는 정보 */
    private int dx, dy;
    private Rect dstRect = new Rect();

    private static Bitmap bitmap;
    private static Rect srcRect =  new Rect();

    public Ball(int dx, int dy) {
        this.dx = dx;  // 공 이동 속도 초기화.
        this.dy = dy;

        dstRect.set(0, 0, 200, 200);

        if (bitmap == null) {  // 리소스 한번만 로드하도록.
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getWidth());  // srcRect 초기화.
        }
    }

    public void draw(Canvas canvas) {  // 그리는 함수.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void update() {
        dstRect.offset(dx, dy);  // (dx, dy) 크기로 이동.

        if(dx < 0) {  // 왼쪽으로 움직일때.
            if(dstRect.left < 0) {  // 벽에 부딪힐 경우.
                dx = -dx;  // 방향 바꿈.
            }
        }
        else {  // 오른쪽으로 움직일때.
            if(dstRect.right > GameView.view.getWidth()) {  // 벽에 부딪힐 경우.
                dx = -dx;  // 방향 바꿈.
            }
        }

        if(dy < 0) {  // 아래로 움직일때
            if(dstRect.top < 0) {  // 벽에 부딪힐 경우.
                dy = -dy;  // 방향 바꿈.
            }
        }
        else {  // 위로 움직일때
            if(dstRect.bottom > GameView.view.getHeight()) {   // 벽에 부딪힐 경우.
                dy = -dy;  // 방향 바꿈.
            }
        }
    }
}
