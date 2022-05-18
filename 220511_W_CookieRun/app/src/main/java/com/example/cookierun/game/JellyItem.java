package com.example.cookierun.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.cookierun.R;
import com.example.cookierun.framework.res.BitmapPool;
import com.example.cookierun.framework.game.RecycleBin;

public class JellyItem extends MapSprite {
    private static final String TAG = JellyItem.class.getSimpleName();

    public static final int JELLY_COUNT = 60;
    private static final int SIZE = 66;
    private static final int BORDER = 2;
    private static final int ITEMS_IN_A_ROW = 30;

    private final float inset;
    protected Rect srcRect = new Rect();

    protected RectF collisionBox = new RectF();
    public int index;

    public static JellyItem get(int index, float unitLeft, float unitTop) {
        JellyItem item = (JellyItem) RecycleBin.get(JellyItem.class);
        if (item == null) {
            item = new JellyItem();
        }
        item.init(index, unitLeft, unitTop);
        return item;
    }

    private void init(int index, float unitLeft, float unitTop) {
        this.index = index;
        int srcLeft = BORDER + (index % ITEMS_IN_A_ROW) * (SIZE + BORDER);
        int srcTop = BORDER + (index / ITEMS_IN_A_ROW) * (SIZE + BORDER);
        srcRect.set(srcLeft, srcTop, srcLeft + SIZE, srcTop + SIZE);
        //Log.d(TAG, "index=" + index + " rect=" + srcRect);
        setUnitDstRect(unitLeft, unitTop, 1, 1);
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);
        collisionBox.set(dstRect);
        collisionBox.inset(inset, inset);
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    private JellyItem() {
        bitmap = BitmapPool.get(R.mipmap.jelly);
        inset = MainScene.get().size(0.15f);
    }
}
