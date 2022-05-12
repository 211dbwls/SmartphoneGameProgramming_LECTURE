package com.example.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.cookierun.R;
import com.example.cookierun.framework.BoxCollidable;
import com.example.cookierun.framework.Metrics;
import com.example.cookierun.framework.SheetSprite;

import java.util.ArrayList;

public class Player extends SheetSprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();

    private static final float FRAMES_PER_SECOND = 8f;  // 1초에 8장

    static {
        State.initRects();
    }

    private enum State {
        run, jump, doubleJump, COUNT;

        Rect[] srcRects() {
            return rects[this.ordinal()];
        }

        static Rect[][] rects;

        static void initRects() {
            int[][] indices = {
                    new int[] { 100, 101, 102, 103 }, // run
                    new int[] { 7, 8 }, // jump
                    new int[] { 1, 2, 3, 4 }, // doubleJump
            };

            ArrayList<Rect[]> rectsList = new ArrayList<>();
            for (int[] ints : indices) {
                Rect[] rects = new Rect[ints.length];
                for (int i = 0; i < ints.length; i++) {
                    int idx = ints[i];
                    int l = 72 + (idx % 100) * 272;
                    int t = 132 + (idx / 100) * 272;
                    Rect rect = new Rect(l, t, l + 140, t + 140);
                    rects[i] = rect;
                }
                rectsList.add(rects);
            }
            rects = rectsList.toArray(new Rect[rectsList.size()][]);
        }
    }

    private State state = State.run;

    private final float ground;
    private final float jumpPower;
    private final float gravity;
    private float jumpSpeed;

    public Player(float x, float y, float w, float h) {
        super(R.mipmap.cookie, FRAMES_PER_SECOND);

        this.x = x;
        this.y = y;
        this.ground = y;
        jumpPower = Metrics.size(R.dimen.player_jump_power);
        gravity = Metrics.size(R.dimen.player_gravity);

        setDstRect(w, h);

        setState(State.run);
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }

    @Override
    public void update(float frameTime) {
        if (state == State.jump || state == State.doubleJump) {
            float dy = jumpSpeed * frameTime;
            jumpSpeed += gravity * frameTime;
            // Log.d(TAG, "y=" + y + " dy=" + dy + " js=" + jumpSpeed);
            if (y + dy >= ground) {
                dy = ground - y;
                setState(State.run);
            }
            y += dy;
            dstRect.offset(0, dy);
        }
    }

    public void jump() {
        Log.d(TAG, "Jump");

        if (state == State.run) {
            setState(State.jump);
            jumpSpeed = -jumpPower;
        }
        else if (state == State.jump){
            setState(State.doubleJump);
            jumpSpeed = -jumpPower;
        }
    }

    private void setState(State state) {
        this.state = state;
        srcRects = state.srcRects();
    }
}