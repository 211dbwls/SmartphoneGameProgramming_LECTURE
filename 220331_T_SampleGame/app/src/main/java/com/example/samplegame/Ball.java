package com.example.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Ball extends Sprite {
    /* 공이 가지고 있어야 하는 정보 */
    private float dx, dy;

    public Ball(float dx, float dy) {
        super(100, 100, R.dimen.ball_radius, R.mipmap.soccer_ball_240);

        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        float dx = this.dx * frameTime;
        float dy = this.dy * frameTime;

        dstRect.offset(dx, dy);  // (dx, dy) 크기로 이동.

        if(dx < 0) {  // 왼쪽으로 움직일때.
            if(dstRect.left < 0) {  // 벽에 부딪힐 경우.
                this.dx = -this.dx;
            }
        }
        else {  // 오른쪽으로 움직일때.
            if(dstRect.right > Metrics.width) {  // 벽에 부딪힐 경우.
                this.dx = -this.dx;
            }
        }

        if(dy < 0) {  // 아래로 움직일때
            if(dstRect.top < 0) {  // 벽에 부딪힐 경우.
                this.dy = -this.dy;
            }
        }
        else {  // 위로 움직일때
            if(dstRect.bottom > Metrics.height) {   // 벽에 부딪힐 경우.
                this.dy = -this.dy;
            }
        }
    }
}
