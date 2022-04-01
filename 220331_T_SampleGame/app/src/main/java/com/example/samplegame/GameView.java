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
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class GameView extends View implements Choreographer.FrameCallback {  // View 상속받음.

    public static GameView view;

    private static final String TAG = GameView.class.getSimpleName();

    // private Bitmap soccerBitmap;  // 축구공 이미지.
    // private Rect srcRect = new Rect();
    // private Rect dstRect = new Rect();
    // private Rect dstRect2 = new Rect();  // 축구공 추가.

    // private int ballDx, ballDy;  // 축구공 이동 크기 나타내는 변수.
    // private int ballDx2, ballDy2;  // 축구공 추가.

    private Ball ball1;
    private Ball ball2;

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

        ball1 = new Ball(10, 10);  // ball 2개 생성.
        ball2 = new Ball(7, 15);

        // srcRect.set(0,  0, soccerBitmap.getWidth(), soccerBitmap.getWidth());  // srcRect 초기화.
        // dstRect.set(0, 0, 100, 100);  // (0, 0)에 100, 100 크기로 축구공 그림.
        // dstRect2.set(0, 0, 100, 100);  // 축구공 추가.

        // ballDx = 10;  // 축구공 이동하는 크기 초기값 설정.
        // ballDy = 10;

        // ballDx2 = 20;  // 축구공 추가.
        // ballDy2 = 20;

        fpsPaint.setColor(Color.BLUE);  // text 색 설정.
        fpsPaint.setTextSize(50);  // text 크기 설정.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        ball1.draw(canvas);
        ball2.draw(canvas);
        // canvas.drawBitmap(soccerBitmap, srcRect, dstRect, null);
        // canvas.drawBitmap(soccerBitmap, srcRect, dstRect2, null);  // 축구공 추가.

        // canvas.drawText(String.valueOf(framePerSecond), 0, 100, fpsPaint);
        canvas.drawText("" + framePerSecond, 0, 100, fpsPaint);  // fps text 그림.

        Log.d(TAG, "onDraw()");
    }

    private void update() {  // 게임 내용 업데이트하는 함수.
        ball1.update();
        ball2.update();
        /* dstRect.offset(ballDx, ballDy);  // (ballDx, ballDy) 크기로 이동.
        if(ballDx < 0) {  // 왼쪽으로 움직일때.
            if(dstRect.left < 0) {  // 벽에 부딪힐 경우.
                ballDx = -ballDx;  // 방향 바꿈.
            }
        }
        else {  // 오른쪽으로 움직일때.
            if(dstRect.right > getWidth()) {  // 벽에 부딪힐 경우.
                ballDx = -ballDx;  // 방향 바꿈.
            }
        }

        if(ballDy < 0) {  // 아래로 움직일때
            if(dstRect.top < 0) {  // 벽에 부딪힐 경우.
                ballDy = -ballDy;  // 방향 바꿈.
            }
        }
        else {  // 위로 움직일때
            if(dstRect.bottom > getHeight()) {   // 벽에 부딪힐 경우.
                ballDy = -ballDy;  // 방향 바꿈.
            }
        }

        // 축구공 추가.
        dstRect2.offset(ballDx2, ballDy2);  // (ballDx2, ballDy2) 크기로 이동.
        if(ballDx2 < 0) {  // 왼쪽으로 움직일때.
            if(dstRect2.left < 0) {  // 벽에 부딪힐 경우.
                ballDx2 = -ballDx2;  // 방향 바꿈.
            }
        }
        else {  // 오른쪽으로 움직일때.
            if(dstRect2.right > getWidth()) {  // 벽에 부딪힐 경우.
                ballDx2 = -ballDx2;  // 방향 바꿈.
            }
        }

        if(ballDy2 < 0) {  // 아래로 움직일때
            if(dstRect2.top < 0) {  // 벽에 부딪힐 경우.
                ballDy2 = -ballDy2;  // 방향 바꿈.
            }
        }
        else {  // 위로 움직일때
            if(dstRect2.bottom > getHeight()) {   // 벽에 부딪힐 경우.
                ballDy2 = -ballDy2;  // 방향 바꿈.
            }
        } */
    }
}
