package com.hjy.sports.student.homemodule.expanded.ornamental;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.CourseList;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 初夏小溪 on 2018/4/9 0009.
 *
 */

public class OrnamentalListContextAdapter extends BaseQuickAdapter<CourseList.DataBean, BaseViewHolder> {

    public OrnamentalListContextAdapter(int layoutResId, @Nullable List<CourseList.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseList.DataBean item) {
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_joinnum, item.getJoinnum()+"人已参加");
        ImgLoadUtils.loadRadiusImage(mContext,item.getIcon(),helper.getView(R.id.imgHead),5);
        MaterialRatingBar materialRatingBar = helper.getView(R.id.Rb_trainlevel);
        materialRatingBar.setNumStars(( item.getTrainlevel()));
    }
}
