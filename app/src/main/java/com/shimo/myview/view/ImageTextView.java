package com.shimo.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.shimo.myview.R;
import com.shimo.myview.Utils;


/**
 * @Author: xiaoA
 * @ClassName: SportsView
 * @Date: 2019/8/21 9:16
 * @Description: 圆环进度条
 */
public class ImageTextView extends View {

    private static final float RADIUS = Utils.dp2px(150);
    private static final int CIRCLE_COLOR = Color.RED;
    private static final float RING_WIDTH = Utils.dp2px(20);

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap mBitmap;

//    TextPaint mTextPaint = new TextPaint();

    private static final String str = "说一下「高级」这个词。同样的一个词，不同的人有不同的理解，不同的位置和环境有不同的定义。A 公司的「高级」，在 B 公司也许叫「中级」，到了 C 公司可能又成了「资深」。想来我最后一次换工作时，薪资涨了不少，职位却是从 「高级 Android 工程师」「降级」成了 「Android Engineer」。所以为了理解的统一，我先明确一下我所指的「高级 Android 工程师」的具体是谁：我这里说的「高级 Android 工程师」，主要指的就是国内大多数小型和微型公司里的 Android 骨干或 Android Leader。这些人在公司的职位通常叫做「高级 Android 工程师」，技术也很不错，但和一些有技术积淀的大公司中的高级工程师相比，他们中的多数人往往（注意是「多数人」「往往」，不是全部，谢绝学我老婆抬杠）底子不够扎实，基础相对薄弱，所以很容易在到达一个还不算很高的技术水平之后，就感到难以继续提升了。他们并不是不想上进，而是不知道应该怎么上进，很多人都已经尝试过很多学习方法，但都好像没有刚入行时那样进步神速，感觉每天都是一个全新的自己了。据我了解，现在中国的程序员中，这样的人非常多。他们是每个公司的骨干，但技术水平却没有达到自己期望的高度（甚至有不少人，也没有达到公司同事以为的高度）。我在这里所说的「高级」，指的就是这些人。";
    private StaticLayout mStaticLayout;

    private float[] cutWidth = new float[1];

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
//        mTextPaint.setTextSize(Utils.dp2px(12));
        mPaint.setTextSize(Utils.dp2px(12));

        mBitmap = getAvatar((int) Utils.dp2px(100));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mStaticLayout = new StaticLayout(str, mTextPaint, getWidth(),
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
//        mStaticLayout.draw(canvas);

        canvas.drawBitmap(mBitmap, getWidth() - (int) Utils.dp2px(100), 100, mPaint);
//        canvas.drawText(str, 0, 50, mPaint);
        int index = mPaint.breakText(str, true, getWidth(), cutWidth);
        canvas.drawText(str, 0, index, mPaint);

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
