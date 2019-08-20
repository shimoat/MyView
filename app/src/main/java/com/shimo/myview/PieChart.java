package com.shimo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: xiaoA
 * @ClassName: PieChart
 * @Date: 2019/8/20 15:57
 * @Description: 饼状图
 */
public class PieChart extends View {

    private static final int RADIUS = (int) Utils.dp2px(150);
    private static final int LENGTH = (int) Utils.dp2px(20);
    private static final int PULLED_OUT_INDEX = 2;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();
    private int[] angles = {60, 100, 120, 80};
    private int[] colors = {Color.parseColor("#2979FF"),
            Color.parseColor("#C2185b"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF8F00")};


    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            mPaint.setColor(colors[i]);
            canvas.save();
            if (i == PULLED_OUT_INDEX) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(bounds, currentAngle, angles[i], true, mPaint);
            canvas.restore();
            currentAngle += angles[i];
        }


    }
}
