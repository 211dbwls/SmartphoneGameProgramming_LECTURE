package com.example.morecontrols;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

    private static final String TAG = GameView.class.getSimpleName();

    private Paint paint;
    private Paint textPaint;

    private Bitmap soccerBitmap;

    public GameView(Context context) {  // 생성자
        super(context);
        Log.d(TAG, "GameView cons");

        initView();
    }

    public GameView(Context context, AttributeSet as) {  // xml로 편집 가능하도록 하는 생성자
        super(context, as);
        Log.d(TAG, "GameView cons with as");

        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#c5d1c5"));

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("blue"));
        textPaint.setTextSize(50);

        Resources res = getResources();  // view가 가진 getResources()함수를 통해 Resources 얻음
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        /* RoundRect 그리기 */
        int left = getPaddingLeft();
        int top  = getPaddingTop();
        int right =  getPaddingRight();
        int bottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();

        float rx =  width / 10, ry = height / 10;
        canvas.drawRoundRect(left, top, width - right, height - bottom, rx, ry, paint);  // Rect의 모서리를 rx, ry의 크기를 갖는 타원의 모양

        Log.d(TAG, "size: " + width + ", " + height + " padding: " + left + ", " + top + ", " + right + ", " + bottom);

        /* GameView 중앙에 축구공 그리기 */
        int cx = left + (width - left - right) / 2;  // 중앙 x값 구하기
        int cy = top + (height - top - bottom) / 2;  // 중앙 y값 구하기

        int ballRadius = width / 10;  // 반지름

        Rect src = new Rect(0, 0, soccerBitmap.getWidth(), soccerBitmap.getHeight());  // 사용할 비트맵
        RectF dst =  new RectF();  // 그릴 때 사용
        dst.left = cx - ballRadius;
        dst.top = cy - ballRadius;
        dst.right = cx + ballRadius;
        dst.bottom = cy + ballRadius;
        canvas.drawBitmap(soccerBitmap, src, dst, null);  // 비트맵 그림

        /* Soccer 텍스트 그리기 */
        String text = "Soccer";
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float tx = cx - bounds.width() / 2;
        float ty = cy + height / 4;
        canvas.drawText(text, tx, ty, textPaint);  // 텍스트 그림
    }

}
