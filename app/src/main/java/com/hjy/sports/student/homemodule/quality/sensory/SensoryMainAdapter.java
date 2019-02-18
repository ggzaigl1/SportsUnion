package com.hjy.sports.student.homemodule.quality.sensory;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/1/29 0029.
 * 感统adapter
 */

public class SensoryMainAdapter extends BaseQuickAdapter<SensoryListToApp.SensoryPageBean, BaseViewHolder> {

    public SensoryMainAdapter(int layoutResId, @Nullable List<SensoryListToApp.SensoryPageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SensoryListToApp.SensoryPageBean item) {
        helper.setText(R.id.tv_question, item.getTitle())
                .setText(R.id.tv_content, item.getContent());
        View view = helper.getView(R.id.view);
        int layoutPosition = helper.getLayoutPosition();
        if (layoutPosition == 0) {
            view.setVisibility(View.GONE);
        }
    }
}
