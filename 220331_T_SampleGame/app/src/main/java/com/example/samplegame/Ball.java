package com.example.samplegame;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Ball {
    /* 공이 가지고 있어야 하는 정보 */
    private int dx, dy;
    private Rect dstRect = new Rect();

    private static Bitmap bitmap;
    private static Rect srcRect =  new Rect();
}
