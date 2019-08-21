package com.shimo.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.shimo.myview.Utils;


/**
 * @Author: xiaoA
 * @ClassName: SportsView
 * @Date: 2019/8/21 9:16
 * @Description: 圆环进度条
 */
public class SportsView extends View {

    private static final float RADIUS = Utils.dp2px(150);
    private static final int CIRCLE_COLOR = Color.RED;
    private static final float RING_WIDTH = Utils.dp2px(20);

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mRect = new Rect();
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(Utils.dp2px(100));
//        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "")); // 字体
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制环
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(CIRCLE_COLOR);
        mPaint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mPaint);
        // 绘制进度条
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                -90,
                225,
                false,
                mPaint);
        // 绘制文字
        // 方法一: 计算文字偏移值
//        mPaint.getTextBounds("asd", 0, "asd".length(), mRect); // "asd的左上右下信息传到mRect"
//        int offset = (mRect.top + mRect.bottom) / 2; // 偏移值
        // 方法二:
        mPaint.getFontMetrics(mFontMetrics);
        float offset = (mFontMetrics.ascent + mFontMetrics.descent) / 2;
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("asd",
                getWidth() / 2,
                getHeight() / 2 - offset,
                mPaint);

    }
}
