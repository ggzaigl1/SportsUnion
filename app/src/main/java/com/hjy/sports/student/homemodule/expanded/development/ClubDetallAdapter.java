package com.hjy.sports.student.homemodule.expanded.development;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.ExerciseClubDetailBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2018/3/8.
 * 俱乐部详情 训练课程列表
 */

public class ClubDetallAdapter extends BaseQuickAdapter<ExerciseClubDetailBean.ExerciseCurriculumListBean, BaseViewHolder> {

    public ClubDetallAdapter(int layoutResId, @Nullable List<ExerciseClubDetailBean.ExerciseCurriculumListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ExerciseClubDetailBean.ExerciseCurriculumListBean item) {
        helper.setText(R.id.tv_name, item.getName());
        ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.iv_course_one));
    }
}
