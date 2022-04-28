package com.example.dragonflight.game;

import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Background extends Sprite {
    public Background(int bitmapResId) {
        super(Metrics.width / 2, Metrics.height / 2,  Metrics.width, Metrics.height, bitmapResId);

        float height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();  // 이미지의 폭에 따른 높이를 구함
        // ImageWidth :  ImageHeight = ScreenWidth : ScreenHeight
        setDstRect(Metrics.width, height);
    }
}
