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
        return (MainGame) singleton;
    }

    public enum Layer {
        bg, platform, item, player, controller, COUNT
    }

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

    public void init() {
        super.init();
        showsBoxCollidables = true;

        initLayers(Layer.COUNT.ordinal());

        Sprite player = new Sprite(
                size(2), size(7),
                size(2), size(2),
                R.mipmap.cookie);
        add(Layer.player.ordinal(), player);

        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_1, Metrics.size(R.dimen.bg_scroll_1)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_2, Metrics.size(R.dimen.bg_scroll_2)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_3, Metrics.size(R.dimen.bg_scroll_3)));

        MapLoader.get().init();
        add(Layer.controller.ordinal(), MapLoader.get());}
}
