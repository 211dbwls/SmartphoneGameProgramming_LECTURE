package com.example.dragonflight.game;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Enemy extends Sprite {
    protected float dy;

    public Enemy(float x, float speed) {
        super(x, 0, R.dimen.enemy_radius, R.mipmap.f_01_01);

        y -= radius;
        setDstRectWithRadius();

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
