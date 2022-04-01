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
    private static final int BALL_COUNT = 10;  // View 상속받음.

    public static GameView view;

    private static final String TAG = GameView.class.getSimpleName();

    private ArrayList<Ball> balls = new ArrayList<>();

    private Fighter fighter;  // fighter.

    private long lastTimeNanos;  // 기억하는 시각.

    private int framePerSecond;  // fps.
    private Paint fpsPaint = new Paint();  // fps 그릴 때 사용할 Paint.

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        view = this;

        initView();  // 초기화하는 함수.

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {  // 매 프레임마다 불리는 함수
        long now = currentTimeNanos;  // 현재 시각.
        int elapsed = (int) (now - lastTimeNanos);  // 전 프레임부터 현재 프레임까지 흐른 시간 구함.
        framePerSecond = 1_000_000_000 / elapsed;  // fps 구함.
        lastTimeNanos = now;  // 현재 시각 저장.

        update();  // 게임 내용 업데이트하는 함수.

        invalidate();  // 다시 그려지는 것을 예약하는 함수.

        Choreographer.getInstance().postFrameCallback(this);
    }

    private void initView() {  // 초기화하는 함수.
        Resources res = getResources();
        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);  // bitmap 로드.
        Ball.setBitmap(soccerBitmap);

        Random random = new Random();
        for(int i = 0;i < BALL_COUNT; i++) {  // 공 여러개 생성
            int dx = random.nextInt(10) + 5;
            int dy = random.nextInt(10) + 5;
            Ball ball = new Ball(dx, dy);
            balls.add(ball);
        }

        Bitmap fighterBitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);  // fighter bitmap 생성
        Fighter.setBitmap(fighterBitmap);
        fighter = new Fighter();  // fighter 생성

        fpsPaint.setColor(Color.BLUE);  // text 색 설정.
        fpsPaint.setTextSize(50);  // text 크기 설정.
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {  // 터치했을 경우 해당 위치로 이동하도록.
            float x = event.getX();
            float y = event.getY();
            fighter.setPosition(x, y);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        for(Ball ball : balls) {  // 공 여러개 그리기.
            ball.draw(canvas);
        }

        fighter.draw(canvas);  // fighter 그리기.

        canvas.drawText("" + framePerSecond, 0, 100, fpsPaint);  // fps text 그림.

        Log.d(TAG, "onDraw()");
    }

    private void update() {  // 게임 내용 업데이트하는 함수.
        for(Ball ball : balls) {   // 공 업데이트.
            ball.update();
        }

        fighter.update();  // fighter 업데이트.
    }
}
