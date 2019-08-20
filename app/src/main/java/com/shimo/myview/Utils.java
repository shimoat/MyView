package com.shimo.myview;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @Author: xiaoA
 * @ClassName: Utils
 * @Date: 2019/8/20 10:00
 * @Description:
 */
public class Utils {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
