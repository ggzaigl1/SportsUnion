package com.hjy.sports.student.socialmodule.friendsocial;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QKun on 2018/3/1.
 */

public class FriendSocialAdapter extends BaseQuickAdapter<SocialAllListBean.RowsBean,BaseViewHolder> {
    public FriendSocialAdapter(int layoutResId, @Nullable List<SocialAllListBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SocialAllListBean.RowsBean item) {
        ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL+item.getTouxiangurl(),helper.getView(R.id.iv_user_head));

        if (!TextUtils.isEmpty(item.getStudentname())){
            helper.setText(R.id.tv_social_name,item.getStudentname());
        }
        if (!TextUtils.isEmpty(item.getSenddate())){
            helper.setText(R.id.tv_social_time,item.getSenddate());
        }
        if (!TextUtils.isEmpty(item.getContent())){
            helper.getView(R.id.tv_social_content).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_social_content,item.getContent());
        }else {
            helper.getView(R.id.tv_social_content).setVisibility(View.GONE);
        }


        //点赞状态
        int islike = item.getIslike();
        if (islike==1){//此条是否已经被赞过
            helper.setImageDrawable(R.id.iv_praise,mContext.getResources().getDrawable(R.mipmap.icon_praise_selected));
        }else {
            helper.setImageDrawable(R.id.iv_praise,mContext.getResources().getDrawable(R.mipmap.icon_praise_normal));
        }

        helper.setText(R.id.tv_praise_num,item.getLikenumber()+"");

        helper.setText(R.id.comment_num,item.getSonnumber()+"");

        helper.addOnClickListener(R.id.ll_praise); //点赞

        helper.addOnClickListener(R.id.ll_comment); //评论


        //当我们数据获取时
        NineGridView nineGridView = helper.getView(R.id.nine_grid_image);
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        List<String> imageurlList = item.getImageurlList();
        if (imageurlList != null) {
            for (String s : imageurlList) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(ApiService.IMG_BASE_URL + s);
                imageInfo.setThumbnailUrl(ApiService.IMG_BASE_URL + s);
                imageInfos.add(imageInfo);
            }
        }
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfos));
    }
}
