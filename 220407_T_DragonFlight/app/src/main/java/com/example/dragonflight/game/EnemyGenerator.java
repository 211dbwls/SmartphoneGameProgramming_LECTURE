package com.example.dragonflight.game;

import android.graphics.Canvas;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.Metrics;

import java.util.Random;

public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private final float fallSpeed;
    private float elapsedTime;

    private int wave;  // enemy spawn할 때마다 증가

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.fallSpeed = Metrics.size(R.dimen.enemy_initial_speed);

        Enemy.size = Metrics.width / 5.0f * 0.9f;

        wave = 0;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        elapsedTime += frameTime;

        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
        wave++;

        Random r = new Random();

        float tenth = Metrics.width / 10;  // 화면을 10개로 나눈 후
        for (int i = 1; i <= 9; i += 2) {  // 1, 3, 5, 7, 9 위치에 spawn되도록
            int level = wave / 10 - r.nextInt(3);  // wave 10마다 3개의 값이 랜덤하게 나오도록 설정
            if(level <  Enemy.MIN_LEVEL) {
                level = Enemy.MIN_LEVEL;  // level이 음수가 되지 않도록
            }
            if(level > Enemy.MAX_LEVEL) {
                level = Enemy.MAX_LEVEL;
            }
            Enemy enemy = new Enemy(level, tenth * i, fallSpeed);
            MainGame.getInstance().add(enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }

}
