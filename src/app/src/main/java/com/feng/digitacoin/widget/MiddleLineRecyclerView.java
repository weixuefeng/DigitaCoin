package com.feng.digitacoin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/20--5:38 PM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class MiddleLineRecyclerView extends View {

    private int measuredHeight;
    private int measuredWidth;
    private int left;
    private int top;
    private String TAG = "MiddleLine";
    private Paint mPaint;
    public MiddleLineRecyclerView(Context context) {
        this(context, null);
    }

    public MiddleLineRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiddleLineRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Color.RED);
        mPaint.setTextSize(200);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();
        left = getLeft();
        top = getTop();
    }

    @Override
    public void onDraw(Canvas c) {
        //super.onDraw(c);
        float y1 = top + 1.0f / 3.0f * measuredHeight;
        float y2 = top + 2.0f / 3.0f * measuredHeight;
        float right = left + measuredWidth;
        Log.e(TAG, "y1:" + y1 + "  left:" + left + "  right:" + right);
        Log.e(TAG, "y2:" + y2);
        c.drawLine(left,  y1, right, y1, mPaint);
        c.drawLine(left,  y2, right, y2, mPaint);
    }
}
