package com.example.cookierun.game;

import android.view.MotionEvent;

import com.example.cookierun.R;
import com.example.cookierun.framework.BaseGame;
import com.example.cookierun.framework.Metrics;
import com.example.cookierun.framework.Sprite;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();

    public static final String PARAM_STAGE_INDEX = "stage_index";

    private Player player;

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

    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }

    protected int mapIndex;

    public void init() {
        super.init();
        showsBoxCollidables = true;

        initLayers(Layer.COUNT.ordinal());

        player = new Player(size(2), size(7), size(2), size(2));
        add(Layer.player.ordinal(), player);

        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_1, Metrics.size(R.dimen.bg_scroll_1)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_2, Metrics.size(R.dimen.bg_scroll_2)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_3, Metrics.size(R.dimen.bg_scroll_3)));

        MapLoader.get().init(mapIndex);
        add(Layer.controller.ordinal(), MapLoader.get());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump();
            return true;
        }
        return false;
    }

}
