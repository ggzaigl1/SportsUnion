package com.hjy.sports.student.datamodule.bodydetection;

import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.sports.R;

import java.util.List;

/**
 * 形体机体adapter
 * Created by QKun on 2017/12/19.
 */

public class BodyDetectionAdapter extends BaseQuickAdapter<BodyDetectionBean, BaseViewHolder> {
    public BodyDetectionAdapter(int layoutResId, @Nullable List<BodyDetectionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BodyDetectionBean item) {
        helper.setText(R.id.tv_type, item.getBody() + ":");

        helper.setText(R.id.tv_detection, item.getDescribe());
        ProgressBar progressBar = helper.getView(R.id.progress_bar);
        double curData = item.getCurData();//学生的身高
        double datapd = item.getDatapd();//全国的平均身高
        if (curData > datapd) {
            progressBar.setProgress(100);
        } else {
            progressBar.setProgress((int) (curData / datapd * 100));
        }


    }
}
