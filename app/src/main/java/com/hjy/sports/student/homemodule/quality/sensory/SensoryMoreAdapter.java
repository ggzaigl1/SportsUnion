package com.hjy.sports.student.homemodule.quality.sensory;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/1/30 0030.
 *
 */

public class SensoryMoreAdapter extends BaseQuickAdapter<SensoryListToApp.SensoryPageBean, BaseViewHolder> {


    public SensoryMoreAdapter(int layoutResId, @Nullable List<SensoryListToApp.SensoryPageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SensoryListToApp.SensoryPageBean item) {
        helper.setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_content, item.getContent());
        ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.imgHead));
        int layoutPosition = helper.getLayoutPosition();
        if (layoutPosition == 0) {
            Glide.with(mContext).load(R.mipmap.sensory_1).into((ImageView) helper.getView(R.id.imgHead));
        }
    }
}
