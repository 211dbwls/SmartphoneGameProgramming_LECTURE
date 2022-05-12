package com.example.cookierun.game;

import com.example.cookierun.R;
import com.example.cookierun.framework.game.BaseGame;
import com.example.cookierun.framework.res.Metrics;
import com.example.cookierun.framework.objects.Button;
import com.example.cookierun.framework.objects.HorzScrollBackground;

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
        bg, platform, item, player, ui, touchUi, controller, COUNT
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

        initLayers(Layer.COUNT.ordinal());

        player = new Player(size(2), size(2), size(2), size(2));
        add(Layer.player.ordinal(), player);

        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_1, Metrics.size(R.dimen.bg_scroll_1)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_2, Metrics.size(R.dimen.bg_scroll_2)));
        add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_3, Metrics.size(R.dimen.bg_scroll_3)));

        MapLoader mapLoader = MapLoader.get();
        mapLoader.init(mapIndex);
        add(Layer.controller.ordinal(), mapLoader);
        add(Layer.controller.ordinal(), new CollisionChecker(player));

        float btn_x = size(1.5f);
        float btn_y = size(8.75f);
        float btn_w = size(8.0f / 3.0f);
        float btn_h = size(1.0f);
        add(Layer.touchUi.ordinal(), new Button(btn_x, btn_y, btn_w, btn_h, R.mipmap.btn_jump_n, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action != Button.Action.pressed) return false;
                player.jump();
                return true;
            }
        }));
        add(Layer.touchUi.ordinal(), new Button(Metrics.width - btn_x, btn_y, btn_w, btn_h, R.mipmap.btn_slide_n, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                //if (action != Button.Action.pressed) return false;
                //player.slide();
                return true;
            }
        }));
    }

    protected int getTouchLayerIndex() {
        return Layer.touchUi.ordinal();
    }
}
