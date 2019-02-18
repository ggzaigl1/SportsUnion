package com.hjy.sports.student.datamodule.stature;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.sports.R;
import com.hjy.sports.student.bean.StatureBean;
import com.hjy.sports.widget.TimeLineMarker;

import java.util.List;

/**
 * 身高等adapter
 * Created by QKun on 2017/12/18.
 */

public class StaturAdapter extends BaseQuickAdapter<StatureBean, BaseViewHolder> {
    public StaturAdapter(int layoutResId, @Nullable List<StatureBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatureBean item) {
        helper.setText(R.id.tv_grade, item.getGrade())
                .setText(R.id.tv_stature, TextUtils.isEmpty(item.getStature()) ? "暂无该项目数据" : item.getStature());
        TimeLineMarker timeLineMarker = helper.getView(R.id.time_line);
        timeLineMarker.setBeginLine(mContext.getResources().getDrawable(R.mipmap.imaginary_line));
        timeLineMarker.setEndLine(mContext.getResources().getDrawable(R.mipmap.imaginary_line));
        if (helper.getAdapterPosition()==0){
            timeLineMarker.setBeginLine(mContext.getResources().getDrawable(R.drawable.shape_statur_time));
        }
    }
}
