package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GameView extends View implements Choreographer.FrameCallback {
    public static GameView view;

    private static final String TAG = GameView.class.getSimpleName();

    private long lastTimeNanos;  // 기억하는 시각.

    private int framePerSecond;  // fps.
    private Paint fpsPaint = new Paint();  // fps 그릴 때 사용할 Paint.

    private boolean initialized;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        view = this;

        // initView();  // 초기화하는 함수.

        // Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  // GameView 사이즈가 결정될 때
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.width = w;
        Metrics.height = h;

        if(!initialized) {
            initView();  // 초기화하는 함수.

            initialized = true;

            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override
    public void doFrame(long currentTimeNanos) {  // 매 프레임마다 불리는 함수
        long now = currentTimeNanos;  // 현재 시각.
        int elapsed = (int) (now - lastTimeNanos);  // 전 프레임부터 현재 프레임까지 흐른 시간 구함.
        if(elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;  // fps 구함.
            lastTimeNanos = now;  // 현재 시각 저장.

            MainGame game = MainGame.getInstance();
            game.update(elapsed);  // 게임 내용 업데이트하는 함수.

            invalidate();  // 다시 그려지는 것을 예약하는 함수.
        }

        Choreographer.getInstance().postFrameCallback(this);
    }

    private void initView() {  // 초기화하는 함수.
        MainGame.getInstance().init();

        fpsPaint.setColor(Color.BLUE);  // text 색 설정.
        fpsPaint.setTextSize(100);  // text 크기 설정.
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.getInstance().onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        MainGame.getInstance().draw(canvas);

        canvas.drawText("FPS:" + framePerSecond, framePerSecond * 10, 100, fpsPaint);

        // Log.d(TAG, "onDraw()");
    }
}
