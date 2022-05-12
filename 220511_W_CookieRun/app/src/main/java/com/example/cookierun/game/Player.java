package com.example.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.cookierun.R;
import com.example.cookierun.framework.BoxCollidable;
import com.example.cookierun.framework.GameObject;
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
        run, jump, doubleJump, falling, COUNT;

        Rect[] srcRects() {
            return rects[this.ordinal()];
        }

        static Rect[][] rects;

        static void initRects() {
            int[][] indices = {
                    new int[] { 100, 101, 102, 103 }, // run
                    new int[] { 7, 8 }, // jump
                    new int[] { 1, 2, 3, 4 }, // doubleJump
                    new int[] { 0 }, // falling
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

    private final float jumpPower;
    private final float gravity;
    private float jumpSpeed;

    public Player(float x, float y, float w, float h) {
        super(R.mipmap.cookie, FRAMES_PER_SECOND);

        this.x = x;
        this.y = y;
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
        float foot = dstRect.bottom;

        switch (state) {
            case jump:
            case doubleJump:
            case falling:
                float dy = jumpSpeed * frameTime;
                jumpSpeed += gravity * frameTime;
                // Log.d(TAG, "y=" + y + " dy=" + dy + " js=" + jumpSpeed);

                if (jumpSpeed >= 0) {
                    float platformTop = findNearestPlatformTop(foot);
                    // Log.i(TAG, "foot="+foot + " ptop=" + platformTop);
                    if (foot + dy >= platformTop) {
                        dy = platformTop - foot;
                        setState(State.run);
                    }
                }
                y += dy;
                dstRect.offset(0, dy);
                break;
            case run:
                float platformTop = findNearestPlatformTop(foot);
                if (foot < platformTop) {
                    setState(State.falling);
                    jumpSpeed = 0;
                }
                break;
        }
    }

    private float findNearestPlatformTop(float foot) {
        MainGame game = MainGame.get();

        ArrayList<GameObject> platforms = game.objectsAt(MainGame.Layer.platform.ordinal());
        float top = Metrics.height;
        for (GameObject obj: platforms) {
            Platform platform = (Platform) obj;
            RectF rect = platform.getBoundingRect();
            if (rect.left > x || x > rect.right) {
                continue;
            }
            //Log.d(TAG, "foot:" + foot + " platform: " + rect);
            if (rect.top < foot) {
                continue;
            }
            if (top > rect.top) {
                top = rect.top;
            }
            //Log.d(TAG, "top=" + top + " gotcha:" + platform);
        }
        return top;
    }

    public void jump() {
        //Log.d(TAG, "Jump");

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