package com.hjy.sports.student.homemodule.expanded.development;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.ExerciseItemsBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * 技能拓展 列表适配器
 * Created by Stefan on 2018/3/6.
 */

public class SkillDeAdapter extends BaseQuickAdapter<ExerciseItemsBean.RowsBean, BaseViewHolder> {


    public SkillDeAdapter(int layoutResId, @Nullable List<ExerciseItemsBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExerciseItemsBean.RowsBean item) {

        ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.image));

        if (!item.getItemName().isEmpty())
            helper.setText(R.id.tv_projects, item.getItemName());
    }


}

