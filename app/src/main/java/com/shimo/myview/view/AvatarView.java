package com.shimo.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.shimo.myview.R;
import com.shimo.myview.Utils;

/**
 * @Author: xiaoA
 * @ClassName: AvatarView
 * @Date: 2019/8/20 16:34
 * @Description:
 */
public class AvatarView extends View {

    private static final float WIDTH = Utils.dp2px(200);
    private static final float PADDING = Utils.dp2px(50);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private Bitmap mBitmap;

    private RectF savedAres = new RectF();

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mBitmap = getAvatar((int) WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        savedAres.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int saved = canvas.saveLayer(savedAres, mPaint);
        canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmap, PADDING, PADDING, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }


    private Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.icon_bitmap, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.icon_bitmap, options);
    }

}
