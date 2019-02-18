package com.hjy.sports.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 获取字体工具类  但什么样的字体我也不知道
 * Created by QKun on 2018/1/24.
 */

public class TextStyleUtils {

    public static Typeface getW5(Context mContext) {
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/华康娃娃体W5(P).TTF");
        return tf;
    }

//
//public static Typeface getW9(Context mContext) {
//        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/华康海报体W9-B5.TTF");
//        return tf;
//    }
    public static Typeface getW12(Context mContext) {
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/华康海报体W12(P).ttf");
        return tf;
    }

    public static Typeface getLeiMiao(Context mContext) {
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/汉仪乐喵体.ttf");
        return tf;
    }
}
