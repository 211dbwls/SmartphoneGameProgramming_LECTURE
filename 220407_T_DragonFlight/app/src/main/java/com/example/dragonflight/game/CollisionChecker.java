package com.example.dragonflight.game;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.graphics.Canvas;
import android.util.Log;

import com.example.dragonflight.framework.CollisionHelper;
import com.example.dragonflight.framework.GameObject;

import java.util.ArrayList;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();

        ArrayList<GameObject> bullets = game.objectsAt(MainGame.Layer.bullet);
        ArrayList<GameObject> enemies = game.objectsAt(MainGame.Layer.enemy);

        for(GameObject o1 : enemies) {
            if(!(o1 instanceof Enemy)) {  // Enemy가 아닌 경우 무시.
                continue;
            }
            Enemy enemy = (Enemy) o1;
            boolean removed = false;

            for(GameObject o2 : bullets) {
                if(!(o2 instanceof Bullet)) {  // Bullet이 아닌 경우 무시.
                    continue;
                }
                Bullet bullet = (Bullet) o2;

                if(CollisionHelper.collides(enemy, bullet)) {  // enemy와 bullet이 충돌했을 경우
                    Log.d(TAG, "Collision");
                    game.remove(bullet);
                    game.remove(enemy);
                    game.score.add(enemy.getScore());  // 점수 추가.
                    removed = true;
                    break;
                }
            }
            if(removed) {
                continue;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
