package com.hjy.sports.student.datamodule.physicalfitness;

import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2017/12/25.
 */

public class PhysicalAdapter extends BaseQuickAdapter<PhysicalFitnessBean,BaseViewHolder> {

    public PhysicalAdapter(int layoutResId, @Nullable List<PhysicalFitnessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhysicalFitnessBean item) {
        helper.setText(R.id.tv_type, item.getBody() + ":");

        helper.setText(R.id.tv_detection,"得分:"+item.getCurData()+"，"+ item.getDescribe());
        ProgressBar progressBar = helper.getView(R.id.progress_bar);
        progressBar.setProgress((int) item.getCurData());
    }
}
