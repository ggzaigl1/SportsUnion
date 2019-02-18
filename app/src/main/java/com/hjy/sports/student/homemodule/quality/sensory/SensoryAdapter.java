package com.hjy.sports.student.homemodule.quality.sensory;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/2/6 0006.
 * 感统 adapter
 */

public class SensoryAdapter extends BaseQuickAdapter<SensoryListToApp.SensoryPageBean, BaseViewHolder> {

    public SensoryAdapter(int layoutResId, @Nullable List<SensoryListToApp.SensoryPageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SensoryListToApp.SensoryPageBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent());
        if (null != item.getImage()) {
            ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.imgHead));
        }
    }
}
