package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.EmergencyTreatmentBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/2/24 0024.
 * 急救小技巧
 */

public class ExerciseForeAdapter extends BaseQuickAdapter<EmergencyTreatmentBean.RowsBean, BaseViewHolder> {

    public ExerciseForeAdapter(int layoutResId, @Nullable List<EmergencyTreatmentBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EmergencyTreatmentBean.RowsBean item) {
        if (null != item) {
            if (!TextUtils.isEmpty(item.getName()) && !TextUtils.isEmpty(item.getExpression())) {
                helper.setText(R.id.tv_social_name, item.getName()).setText(R.id.tv_social_time, item.getExpression());
            }
            ImgLoadUtils.loadRadiusImage(mContext, ApiService.IMG_BASE_URL + item.getThumImage(), helper.getView(R.id.iv_user_head), 5);
        }
    }
}
