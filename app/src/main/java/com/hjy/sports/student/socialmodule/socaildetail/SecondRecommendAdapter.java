package com.hjy.sports.student.socialmodule.socaildetail;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SecondRecommendBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * 某个评论的评论列表（二级评论）
 * Created by QKun on 2018/1/2.
 */

public class SecondRecommendAdapter extends BaseQuickAdapter<SecondRecommendBean.RowsBean, BaseViewHolder> {
    public SecondRecommendAdapter(int layoutResId, @Nullable List<SecondRecommendBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondRecommendBean.RowsBean item) {
        ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + item.getTouxiangurl(), helper.getView(R.id.head_portrait));

        if (!TextUtils.isEmpty(item.getStudentname())) {
            helper.setText(R.id.tv_name, item.getStudentname());
        }

        if (!TextUtils.isEmpty(item.getContent())) {
            if (!TextUtils.isEmpty(item.getTargetname())) {
//                int length = item.getTargetname().length();
//                SpannableStringBuilder builder = new SpannableStringBuilder(item.getContent());
//                ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
//                builder.setSpan(blueSpan, 2, 2 + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                helper.setText(R.id.tv_content, builder);
                String content = "回复@" + item.getTargetname() + ":" + item.getContent();
                helper.setText(R.id.tv_content, content);
            } else {
                helper.setText(R.id.tv_content, item.getContent());
            }
        }

//        if (!TextUtils.isEmpty(item.getContent())) {
//            String content = item.getContent();
//            if (content.contains("回复@")){
//                int i = content.indexOf(":");
//                SpannableStringBuilder builder = new SpannableStringBuilder(item.getContent());
//                ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
//                builder.setSpan(blueSpan, 2,i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                helper.setText(R.id.tv_content, builder);
//            }else {
//                helper.setText(R.id.tv_content, content);
//            }
//
//
//        }

        if (!TextUtils.isEmpty(item.getSenddate())) {
            helper.setText(R.id.tv_send_time, item.getSenddate());
        }
    }


}
