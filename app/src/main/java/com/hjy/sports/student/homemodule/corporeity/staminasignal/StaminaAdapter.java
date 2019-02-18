package com.hjy.sports.student.homemodule.corporeity.staminasignal;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.utils.ScreenUtils;
import com.hjy.sports.R;
import com.hjy.sports.widget.weekcalendar.CalendarData;

import java.util.List;

/**
 * Created by QKun on 2018/1/28.
 */

public class StaminaAdapter extends BaseQuickAdapter<CalendarData, BaseViewHolder> {

    private final int mItem_width;
    private CalendarData today;

    public StaminaAdapter(int layoutResId, @Nullable List<CalendarData> data, CalendarData today) {
        super(layoutResId, data);
        this.today = today;
        //获取屏幕的宽
        int screenWidth = ScreenUtils.getScreenWidth(BaseApplication.getApplication().getApplicationContext());
        mItem_width = screenWidth / 7;

    }

    @Override
    protected void convert(BaseViewHolder helper, CalendarData item) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.width = mItem_width;
        helper.setText(R.id.tv_week, item.weeks)
                .setText(R.id.tv_day, String.valueOf(item.day))
                .getView(R.id.item_stamina_ll).setLayoutParams(params);
        if (item.isSelect) {
            LinearLayout layout = helper.getView(R.id.item_view);
            layout.setBackground(mContext.getResources().getDrawable(R.drawable.shape_stamina_calendar));
            helper.setTextColor(R.id.tv_week, Color.WHITE);
            helper.setTextColor(R.id.tv_day, Color.WHITE);
        } else {
            helper.setBackgroundColor(R.id.item_view, Color.WHITE);
            helper.setTextColor(R.id.tv_week, mContext.getResources().getColor(R.color.calendar_week_color));
            helper.setTextColor(R.id.tv_day, mContext.getResources().getColor(R.color.calendar_day_color));
        }


    }
}
