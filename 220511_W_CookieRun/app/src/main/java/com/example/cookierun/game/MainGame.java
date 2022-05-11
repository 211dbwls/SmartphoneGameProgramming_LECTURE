package com.example.cookierun.game;

import com.example.cookierun.R;
import com.example.cookierun.framework.BaseGame;
import com.example.cookierun.framework.Metrics;
import com.example.cookierun.framework.Sprite;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();

    public static MainGame get() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame)singleton;
    }
    public enum Layer {
        player, COUNT
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        add(Layer.player.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, 500, 500, R.mipmap.cookie));
    }

}
