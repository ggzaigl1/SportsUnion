package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SensoryOne;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/1/24 0024.
 * 练习方法一 adapter
 */

public class ExerciseAdapter extends BaseQuickAdapter<SensoryOne.RowsBean, BaseViewHolder> {

    public ExerciseAdapter(int layoutResId, @Nullable List<SensoryOne.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SensoryOne.RowsBean item) {
        helper.setText(R.id.tv_social_name, item.getSportsName())
                .setText(R.id.tv_social_time, item.getSportsTime() + "分钟/难度");
        ImageView iv_sportsDifficulty1 = helper.getView(R.id.iv_sportsDifficulty1);
        ImageView iv_sportsDifficulty2 = helper.getView(R.id.iv_sportsDifficulty2);
        ImageView iv_sportsDifficulty3 = helper.getView(R.id.iv_sportsDifficulty3);
        if (item.getSportsDifficulty().equals("0")) {
            iv_sportsDifficulty1.setImageResource(R.mipmap.icon_drop_gray);
            iv_sportsDifficulty2.setImageResource(R.mipmap.icon_drop_gray);
            iv_sportsDifficulty3.setImageResource(R.mipmap.icon_drop_gray);
        } else if (item.getSportsDifficulty().equals("1")) {
            iv_sportsDifficulty1.setImageResource(R.mipmap.icon_drop_blue);
            iv_sportsDifficulty2.setImageResource(R.mipmap.icon_drop_gray);
            iv_sportsDifficulty3.setImageResource(R.mipmap.icon_drop_gray);
        } else if (item.getSportsDifficulty().equals("2")) {
            iv_sportsDifficulty1.setImageResource(R.mipmap.icon_drop_blue);
            iv_sportsDifficulty2.setImageResource(R.mipmap.icon_drop_blue);
            iv_sportsDifficulty3.setImageResource(R.mipmap.icon_drop_gray);
        } else if (item.getSportsDifficulty().equals("3")) {
            iv_sportsDifficulty1.setImageResource(R.mipmap.icon_drop_blue);
            iv_sportsDifficulty2.setImageResource(R.mipmap.icon_drop_blue);
            iv_sportsDifficulty3.setImageResource(R.mipmap.icon_drop_blue);
        }
//        ImgLoadUtils.loadImage(mContext,item.get(), view);

    }

}
