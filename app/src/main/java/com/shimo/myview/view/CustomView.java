package com.shimo.myview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.shimo.myview.Utils;

/**
 * @Author: xiaoA
 * @ClassName: CustomView
 * @Date: 2019/8/21 14:17
 * @Description:
 */
public class CustomView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(0, 0, 200, 200);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 0, 0, mPaint);
    }
}
