package com.hjy.sports.student.homemodule.corporeity.staminasignal;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.StaminaSignalBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/3/6 0006.
 * 体能红绿灯
 */

public class StaminaSignalsAdapter extends BaseQuickAdapter<StaminaSignalBean.LianxidataBean, BaseViewHolder> {

    public StaminaSignalsAdapter(int layoutResId, @Nullable List<StaminaSignalBean.LianxidataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StaminaSignalBean.LianxidataBean item) {
        helper.setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_content, item.getContent());
        if (null != item.getImage()) {
            ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getImage(), helper.getView(R.id.imgHead));
        }
    }
}
