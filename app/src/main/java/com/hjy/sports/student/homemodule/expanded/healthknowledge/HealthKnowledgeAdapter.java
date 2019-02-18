package com.hjy.sports.student.homemodule.expanded.healthknowledge;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.NewsBean;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/3/6 0006.
 *
 */

public class HealthKnowledgeAdapter extends BaseQuickAdapter<NewsBean.DataBean, BaseViewHolder> {

    public HealthKnowledgeAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
        if (null != item) {
            if (!TextUtils.isEmpty(item.getTitle())) {
                helper.setText(R.id.tv_social_name, item.getTitle());
            }
            if (!TextUtils.isEmpty(item.getBody())) {
                helper.setText(R.id.tv_social_time, item.getBody());
            }
            ImageView imageView = helper.getView(R.id.iv_user_head);
            ImgLoadUtils.loadImage(mContext, item.getMainImage(), imageView);
        }
    }
}
