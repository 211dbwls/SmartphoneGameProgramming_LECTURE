package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class GameView extends View {  // View 상속받음.

    private static final String TAG = GameView.class.getSimpleName();

    private Bitmap soccerBitmap;  // 축구공 이미지.
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();  // 초기화하는 함수.
    }

    private void initView() {  // 초기화하는 함수.
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);  // bitmap 로드.
        srcRect.set(0,  0, soccerBitmap.getWidth(), soccerBitmap.getWidth());  // srcRect 초기화.
        dstRect.set(0, 0, 100, 100);  // (0, 0)에 100, 100 크기로 축구공 그림
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        canvas.drawBitmap(soccerBitmap, srcRect, dstRect, null);
        Log.d(TAG, "onDraw()");
    }
}
