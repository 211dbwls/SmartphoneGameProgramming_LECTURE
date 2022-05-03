package com.example.dragonflight.game;

import android.graphics.Canvas;

import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class VertScrollBackground extends Sprite {
    private final float speed;
    private final int height;

    public VertScrollBackground(int bitmapResId, float speed) {
        super(Metrics.width / 2, Metrics.height / 2,  Metrics.width, Metrics.height, bitmapResId);

        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();  // 이미지의 폭에 따른 높이를 구함
        // ImageWidth :  ImageHeight = ScreenWidth : ScreenHeight
        setDstRect(Metrics.width, height);

        this.speed = speed;
    }

    @Override
    public void update() {
        this.y += speed * MainGame.getInstance().frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        int curr = (int)y % height;
        if(curr > 0) {
            curr -= height;
        }

        while(curr < Metrics.height) {
            dstRect.set(0, curr,  Metrics.width, curr +height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += height;
        }
    }
}
