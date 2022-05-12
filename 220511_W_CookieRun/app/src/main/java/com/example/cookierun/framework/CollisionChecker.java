package com.example.cookierun.framework;

import android.graphics.Canvas;

import com.example.cookierun.game.MainGame;
import com.example.cookierun.game.Player;

import java.util.ArrayList;

public class CollisionChecker implements GameObject {

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.get();
        Player player = (Player) game.objectsAt(MainGame.Layer.player.ordinal()).get(0);
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