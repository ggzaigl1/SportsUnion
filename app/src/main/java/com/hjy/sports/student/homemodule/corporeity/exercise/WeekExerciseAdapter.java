package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SportMethodBean;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2018/1/26.
 * 打卡adapter
 */

public class WeekExerciseAdapter extends BaseQuickAdapter<SportMethodBean.RowsBean, BaseViewHolder> {
    public WeekExerciseAdapter(int layoutResId, @Nullable List<SportMethodBean.RowsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SportMethodBean.RowsBean item) {
        //练习项目
        if (!TextUtils.isEmpty(item.getSportsName())) {
            helper.setText(R.id.tv_projects, item.getSportsName());
        }

        //打开textview
        TextView tv_status = helper.getView(R.id.tv_status);

        boolean daka = item.isDaka();
        if (daka) {
            tv_status.setBackground(mContext.getResources().getDrawable(R.drawable.shape_week_exercise_finish));
            tv_status.setText("已打卡");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            tv_status.setBackground(mContext.getResources().getDrawable(R.drawable.shape_week_exercise));
            tv_status.setText("打卡");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.pk_mean_color));
        }

        helper.addOnClickListener(R.id.tv_status);
    }
}
