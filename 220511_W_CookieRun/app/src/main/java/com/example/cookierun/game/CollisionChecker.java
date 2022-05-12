package com.example.cookierun.game;

import android.graphics.Canvas;

import com.example.cookierun.framework.interfaces.BoxCollidable;
import com.example.cookierun.framework.interfaces.GameObject;
import com.example.cookierun.framework.util.CollisionHelper;
import com.example.cookierun.game.MainGame;
import com.example.cookierun.game.Player;

import java.util.ArrayList;

public class CollisionChecker implements GameObject {
    private final Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }
    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.get();
        // Player player = (Player) game.objectsAt(MainGame.Layer.player.ordinal()).get(0);
        ArrayList<GameObject> items = game.objectsAt(MainGame.Layer.item.ordinal());

        for (GameObject item: items) {
            if (!(item instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (BoxCollidable) item)) {
                //Log.d(TAG, "Collision: " + item);
                game.remove(item);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}