package com.culturer.yoo_home.util;

import android.content.Context;

import com.culturer.yoo_home.base.BaseApplication;


/**
 * 屏幕相关的辅助类
 */
public class DisplayUtil {


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param px （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(float px) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static float px2dip(int px, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dip （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(float dip) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    public static int dip2px(float dip, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = BaseApplication.getContext()
                .getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
