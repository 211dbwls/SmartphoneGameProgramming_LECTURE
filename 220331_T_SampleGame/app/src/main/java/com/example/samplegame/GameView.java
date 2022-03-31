package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class GameView extends View {  // View 상속받음.

    private static final String TAG = GameView.class.getSimpleName();

    private final Handler handler;

    private Bitmap soccerBitmap;  // 축구공 이미지.
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    private int ballDx, ballDy;  // 축구공 이동 크기 나타내는 변수.

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();  // 초기화하는 함수.

        handler = new Handler();
        updateGame();  // View를 다시 그리는 함수.
    }

    private void updateGame() {  // View를 다시 그리는 함수.
        update();  // 게임 내용 업데이트하는 함수.

        invalidate();  // 다시 그려지는 것을 예약하는 함수.

        handler.post(new Runnable() {  // 할 일을 한 후 updateGame() 호출되도록.
            @Override
            public void run() {
                updateGame();  // handler 리턴 후 시간이 지난 후 updateGame() 호출됨.
            }
        });
    }

    private void initView() {  // 초기화하는 함수.
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);  // bitmap 로드.
        srcRect.set(0,  0, soccerBitmap.getWidth(), soccerBitmap.getWidth());  // srcRect 초기화.
        dstRect.set(0, 0, 100, 100);  // (0, 0)에 100, 100 크기로 축구공 그림.

        ballDx = 10;  // 축구공 이동하는 크기 초기값 설정.
        ballDy = 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        canvas.drawBitmap(soccerBitmap, srcRect, dstRect, null);
        Log.d(TAG, "onDraw()");
    }

    private void update() {  // 게임 내용 업데이트하는 함수.
        dstRect.offset(ballDx, ballDy);  // (ballDx, ballDy) 크기로 이동.
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
    }
}