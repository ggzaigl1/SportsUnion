package com.hjy.sports.widget.indexView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.hjy.sports.R;

/**
 * 下标标记的日历控件
 * Created by huanghaibin on 2017/11/15.
 */

public class IndexMonthView extends MonthView {
    private Paint mSchemeBasicPaint = new Paint();
    private int mPadding;
    private int mH, mW;
    private final Bitmap mBitmap;
    private float mRadius;

    public IndexMonthView(Context context) {
        super(context);

        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mPadding = dipToPx(getContext(), 4);
        mH = dipToPx(getContext(), 2);
        mW = dipToPx(getContext(), 8);
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

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        mSelectedPaint.setColor(Color.rgb(51, 161, 250));
        mSelectedPaint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding, mSelectedPaint);
        int cx = x + mItemWidth / 2;
        int cy = y + (mItemHeight - mBitmap.getHeight()) / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        return true;
    }

    /**
     * onDrawSelected
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());
//        canvas.drawRect(x + mItemWidth / 2 - mW / 2,
//                y + mItemHeight - mH * 2 - mPadding,
//                x + mItemWidth / 2 + mW / 2,
//                y + mItemHeight - mH - mPadding, mSchemeBasicPaint);

        canvas.drawBitmap(mBitmap, x + mItemWidth / 2 - mBitmap.getWidth() / 2,
                y + mItemHeight - mBitmap.getHeight() - mPadding,
                mSchemeBasicPaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;
        mCurDayTextPaint.setColor(Color.rgb(0, 0, 0));
        if (isSelected) {
            mSelectTextPaint.setColor(Color.WHITE);
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
