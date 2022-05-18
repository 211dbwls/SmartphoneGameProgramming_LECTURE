package com.example.cookierun.game;

import com.example.cookierun.R;
import com.example.cookierun.framework.game.Scene;
import com.example.cookierun.framework.objects.Sprite;
import com.example.cookierun.framework.res.Metrics;

public class PausedScene extends Scene {
    private static PausedScene singleton;
    public static PausedScene get() {
        if (singleton == null) {
            singleton = new PausedScene();
        }
        return singleton;
    }

    public enum Layer {
        ui, touchUi, COUNT;
    }

    @Override
    public void init() {
        super.init();
        initLayers(Layer.COUNT.ordinal());

        add(Layer.ui.ordinal(), new Sprite(
                Metrics.width / 2, Metrics.height / 4,
                Metrics.width / 3, Metrics.width / 3 * 230 / 440,
                R.mipmap.game_paused)
        );
    }
}