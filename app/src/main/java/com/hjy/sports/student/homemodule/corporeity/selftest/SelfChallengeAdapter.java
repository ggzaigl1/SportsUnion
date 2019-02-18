package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SelfChallenge;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/2/2 0002.
 * 自我评价 测试结果 adapter
 */

public class SelfChallengeAdapter extends BaseQuickAdapter<SelfChallenge.DatalistBean, BaseViewHolder> {

    public SelfChallengeAdapter(int layoutResId, @Nullable List<SelfChallenge.DatalistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelfChallenge.DatalistBean item) {
        helper.setText(R.id.tvSelfItem, item.getXiangmu());
        TextView textView = helper.getView(R.id.tvSelfItem_performance);
        textView.setText(item.getXiangmuscore());
        if (!TextUtils.isEmpty(item.getStatus())) {
            if (item.getStatus().equals("0")) {
                textView.setText("待审核");
                textView.setTextColor(ContextCompat.getColor(mContext,R.color.button_normal));
            } else if (item.getStatus().equals("1")) {
                textView.setText(item.getXiangmuscore());
            } else {
                textView.setText("不通过");
            }
        }
    }
}
