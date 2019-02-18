package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SelfChallengeRecord;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/2/1 0001.
 *
 */

public class SelfChallengeRecordAdapter extends BaseQuickAdapter<SelfChallengeRecord.RowsBean, BaseViewHolder> {

    public SelfChallengeRecordAdapter(int layoutResId, @Nullable List<SelfChallengeRecord.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelfChallengeRecord.RowsBean item) {
        helper.setText(R.id.tv_question, item.getXiangmu()).setText(R.id.tv_content, item.getCreatedtime());
        TextView textView = helper.getView(R.id.tv_score);
        if (item.getStatus().equals("0")){
            textView.setText("待审核");
        }else if (item.getStatus().equals("1")){
            textView.setText(item.getXiangmuscore());
        }else {
            textView.setText("不通过");
        }
    }
}
