package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.sports.R;
import com.hjy.sports.widget.PkProgressBar;

import java.util.List;

/**
 * Created by QKun on 2018/1/26.
 */

public class PkAdapter extends BaseQuickAdapter<PkBean, BaseViewHolder> {
    public PkAdapter(int layoutResId, @Nullable List<PkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PkBean item) {
        PkProgressBar pkProgress = helper.getView(R.id.pkProgress);

        //中间的自己的值与全国的比较结果
        AppCompatImageView iv_fail_win = pkProgress.getIv_fail_win();

        //自己的得分
        TextView tvLeftDesc = pkProgress.getTvLeftDesc();

        //项目名字
        TextView tvCenter = pkProgress.getTvCenter();

        //全国的平均得分
        TextView tvRightDesc = pkProgress.getTvRightDesc();


        if (item.getLeftProgress() != 0 && item.getRightProgress() != 0) {
            if (item.getLeftProgress() > 100f) {
                pkProgress.setLeftProgress(100f);
            } else {
                pkProgress.setLeftProgress(item.getLeftProgress());
            }
            if (item.getRightProgress() > 100f) {
                pkProgress.setRightProgress(100f);
            } else {
                pkProgress.setRightProgress(item.getRightProgress());
            }


            //自己比全国平均高
            if (item.getLeftProgress() > item.getRightProgress()) {
                Glide.with(mContext).load(R.mipmap.icon_win).into(iv_fail_win);
            } else {
                Glide.with(mContext).load(R.mipmap.icon_fail).into(iv_fail_win);
            }
            tvLeftDesc.setText(item.getLeftProgress() + "");
            tvRightDesc.setText(item.getRightProgress() + "");
            tvCenter.setText(item.getName());
        }


    }
}
