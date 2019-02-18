package com.hjy.sports.student.homemodule.expanded.ornamental;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.CourseDetails;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/3/13 0013.
 */

public class OrnamentalContextAdapter extends BaseQuickAdapter<CourseDetails.DataBean.GroupsBean.ActionsBean, BaseViewHolder> {

    public OrnamentalContextAdapter(int layoutResId, @Nullable List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetails.DataBean.GroupsBean.ActionsBean item) {
        helper.setText(R.id.tv_name, item.getTitle());
        ImgLoadUtils.loadImage(mContext, item.getPic(), helper.getView(R.id.iv_course_one));
    }
}
