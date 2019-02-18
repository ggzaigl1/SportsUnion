package com.hjy.sports.student.homemodule.expanded.development;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.ExerciseClubBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2018/3/8.
 */

public class ClubListAdapter extends BaseQuickAdapter<ExerciseClubBean.RowsBean, BaseViewHolder> {
    public ClubListAdapter(int layoutResId, @Nullable List<ExerciseClubBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExerciseClubBean.RowsBean item) {
        if (!item.getName().isEmpty()) {
            helper.setText(R.id.tv_club_name, item.getName());
        }

        if (!item.getJieshao().isEmpty()) {
            helper.setText(R.id.tv_presentation, item.getJieshao());
        }
        if (TextUtils.isEmpty(item.getImage())){
            helper.getView(R.id.iv_club_bg).setBackgroundResource(R.mipmap.icon_color);
        }else {
            ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL_THUM + item.getImage(), helper.getView(R.id.iv_club_bg));
        }
    }

}
