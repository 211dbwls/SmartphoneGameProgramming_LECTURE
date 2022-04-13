package com.example.dragonflight.game;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Enemy extends Sprite {
    protected float dy;

    public static float size;

    public Enemy(float x, float speed) {
        super(x, -size, size, size, R.mipmap.f_01_01);

        dy = speed;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        setDstRectWithRadius();

        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }
}
