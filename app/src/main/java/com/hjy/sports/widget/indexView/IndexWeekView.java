package com.hjy.sports.widget.indexView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;
import com.hjy.sports.R;

/**
 * 下标周视图
 * Created by huanghaibin on 2017/11/29.
 */

public class IndexWeekView extends WeekView {
    private Paint mSchemeBasicPaint = new Paint();
    private int mPadding;
    private int mH, mW;
    private final Bitmap mBitmap;
    private float mRadius;

    public IndexWeekView(Context context) {
        super(context);
        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mPadding = dipToPx(getContext(), 4);
        mH = dipToPx(getContext(), 6);
        mW = dipToPx(getContext(), 20);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icons_praise);
    }

    @Override
    protected void onPreviewHook() {
        mRadius = (float) ((mItemHeight) / (3.5));
        mSelectTextPaint.setTextSize(dipToPx(getContext(), 16));
        mCurMonthTextPaint.setTextSize(dipToPx(getContext(), 16));
        mOtherMonthTextPaint.setTextSize(dipToPx(getContext(), 16));
        mSchemeTextPaint.setTextSize(dipToPx(getContext(), 16));
        mCurDayTextPaint.setTextSize(dipToPx(getContext(), 16));
        mCurMonthLunarTextPaint.setTextSize(dipToPx(getContext(), 12));
        mOtherMonthLunarTextPaint.setTextSize(dipToPx(getContext(), 12));
    }

    /**
     * 如果这里和 onDrawScheme 是互斥的，则 return false，
     * return true 会先绘制 onDrawSelected，再绘制onDrawSelected
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, boolean hasScheme) {
        mSelectedPaint.setColor(Color.rgb(51, 161, 250));
        int cx = x + mItemWidth / 2;
        int cy = (mItemHeight - mBitmap.getHeight()) / 2;
//        canvas.drawRect(x + mPadding, mPadding, x + mItemWidth - mPadding, mItemHeight - mPadding, mSelectedPaint);
        if (calendar.isCurrentDay()) {
            mSelectedPaint.setStyle(Paint.Style.FILL);
        } else {
            mSelectedPaint.setStyle(Paint.Style.STROKE);
        }

        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        return true;
    }


    /**
     * 绘制下标标记
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());
//        canvas.drawRect(x + mItemWidth / 2 - mW / 2,
//                mItemHeight - mH * 2 - mPadding,
//                x + mItemWidth / 2 + mW / 2,
//                mItemHeight - mH - mPadding, mSchemeBasicPaint);
        canvas.drawBitmap(mBitmap, x + mItemWidth / 2 - mBitmap.getWidth() / 2,
                mItemHeight - mBitmap.getHeight(),
                mSchemeBasicPaint);

    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int top = -mItemHeight / 6;
        mCurDayTextPaint.setColor(Color.rgb(0, 0, 0));
        if (isSelected) {
            mSelectTextPaint.setColor(Color.rgb(102, 102, 102));
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            mSelectTextPaint);
//            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10, mCurMonthLunarTextPaint);
        } else {
            mSelectTextPaint.setColor(Color.rgb(102, 102, 102));
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            mSelectTextPaint);
//            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10, mCurMonthLunarTextPaint);
        }

//        mSelectTextPaint.setColor(Color.rgb(102, 102, 102));
//        canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
//                calendar.isCurrentDay() ? mCurDayTextPaint :
//                        mSelectTextPaint);

    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
