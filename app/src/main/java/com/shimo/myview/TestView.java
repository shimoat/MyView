package com.shimo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: xiaoA
 * @ClassName: TestView
 * @Date: 2019/8/20 9:19
 * @Description: 仪表盘
 */
public class TestView extends View {

    private static final int ANGLE = 120;
    private static final float RADIUS = Utils.dp2px(150);
    private static final float LENGTH = Utils.dp2px(100);

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 抗锯齿, 使图形和文字的边缘更加平滑

//    Path mPath = new Path();

    Path dash = new Path();
    private PathDashPathEffect mEffect;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        mPath.reset();
//        mPath.addRect(getWidth() / 2 - 150,
//                getHeight() / 2 - 300,
//                getWidth() / 2 + 150,
//                getHeight() / 2,
//                Path.Direction.CCW); // CCW 逆时针
//
//        mPath.addCircle(
//                getWidth() / 2,
//                getHeight() / 2,
//                150,
//                Path.Direction.CW); // 顺时针
    }

    // super 执行完之后执行
    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2px(2));
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
//        pathMeasure.getLength()
        mEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawLine(100, 100, 200, 200, mPaint);

//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Utils.dp2px(150), mPaint);

//        canvas.drawPath(mPath, mPaint);


        // 画线
        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE,
                false,
                mPaint);
        // 画刻度
        mPaint.setPathEffect(mEffect);
        // 画线
        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                90 + ANGLE / 2,
                360 - ANGLE,
                false,
                mPaint);
        mPaint.setPathEffect(null);

        // 画指针
        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() / 2,
                mPaint);
    }

    private int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }

}
