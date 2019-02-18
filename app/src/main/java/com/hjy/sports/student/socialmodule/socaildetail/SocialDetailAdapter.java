package com.hjy.sports.student.socialmodule.socaildetail;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.ReplyListBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2017/12/29.
 */

public class SocialDetailAdapter extends BaseQuickAdapter<ReplyListBean.RowsBean, BaseViewHolder> {

    public SocialDetailAdapter(int layoutResId, @Nullable List<ReplyListBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReplyListBean.RowsBean item) {
        if (!TextUtils.isEmpty(item.getTouxiangurl())) {
            ImgLoadUtils.loadImages(ApiService.IMG_BASE_URL + item.getTouxiangurl(), helper.getView(R.id.head_portrait));
        }

        if (!TextUtils.isEmpty(item.getStudentname())) {
            helper.setText(R.id.tv_name, item.getStudentname());
        }

        if (!TextUtils.isEmpty(item.getContent())) {
            helper.setText(R.id.tv_content, item.getContent());
        }

        if (!TextUtils.isEmpty(item.getSenddate())) {
            helper.setText(R.id.tv_send_time, item.getSenddate());
        }


        List<ReplyListBean.RowsBean.SocialSonListBean> socialSonList = item.getSocialSonList();
        if (socialSonList != null && socialSonList.size() != 0) {
            helper.getView(R.id.ll_comment_to_user).setVisibility(View.VISIBLE);
            if (socialSonList.size() == 1) {
                helper.getView(R.id.first_recommend).setVisibility(View.VISIBLE);
                helper.getView(R.id.second_recommend).setVisibility(View.GONE);
                helper.getView(R.id.tv_all).setVisibility(View.GONE);

                String studentname = socialSonList.get(0).getStudentname();
                String content_one = socialSonList.get(0).getContent();
                if (!TextUtils.isEmpty(studentname) && !TextUtils.isEmpty(content_one)) {
                    helper.setText(R.id.first_recommend, studentname + ":" + content_one);
                }
                helper.addOnClickListener(R.id.ll_comment_to_user);
            } else {
                helper.getView(R.id.first_recommend).setVisibility(View.VISIBLE);
                helper.getView(R.id.second_recommend).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_all).setVisibility(View.VISIBLE);
                String studentname = socialSonList.get(0).getStudentname();
                String content_one = socialSonList.get(0).getContent();
                if (!TextUtils.isEmpty(studentname) && !TextUtils.isEmpty(content_one)) {
                    helper.setText(R.id.first_recommend, studentname + ":" + content_one);
                }
                String studentname1 = socialSonList.get(1).getStudentname();
                String content_two = socialSonList.get(1).getContent();
                if (!TextUtils.isEmpty(studentname1) && !TextUtils.isEmpty(content_two)) {
                    helper.setText(R.id.second_recommend, studentname1 + ":" + content_two);
                }
                helper.addOnClickListener(R.id.ll_comment_to_user);
            }
        } else {
            helper.getView(R.id.ll_comment_to_user).setVisibility(View.GONE);
        }
    }
}
