package com.example.samplegame;

import android.content.res.Resources;

public class Metrics {
    public static float size(int dimenRedId) {
        Resources res = GameView.view.getResources();
        float size = res.getDimension(dimenRedId);
        return size;
    }

}
