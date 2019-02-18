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
 * 俱乐部详情 用户评价 列表
 */

public class ClubDetailAdapter extends BaseQuickAdapter<ExerciseClubDetailBean.ExerciseCommentListBean, BaseViewHolder> {


    public ClubDetailAdapter(int layoutResId, @Nullable List<ExerciseClubDetailBean.ExerciseCommentListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExerciseClubDetailBean.ExerciseCommentListBean item) {
        if (null != item.getClubName() || null != item.getSendDate() || null != item.getContent()) {
            helper.setText(R.id.tv_name, item.getClubName())
                    .setText(R.id.tv_sendDate, item.getSendDate())
                    .setText(R.id.tv_evaluate_content, item.getContent());
        }
        ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.iv_user_head));
    }
}
