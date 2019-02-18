package com.hjy.sports.student.socialmodule;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.utils.ScreenUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.bean.ImageItem;
import com.hjy.sports.R;

import java.util.List;

/**
 * 发布动态 上传附件列表
 * Created by fangs on 2017/12/28.
 */
public class AddAppendixAdapter extends RecyclerCommonAdapter<ImageItem> {

    OnItemClickListener listener;
    private int mImageSize;

    public AddAppendixAdapter(Context context, List<ImageItem> datas) {
        super(context, R.layout.item_add_social, datas);

        mImageSize = ScreenUtils.getImageItemWidth(context);
    }

    @Override
    public void convert(ViewHolder holder, ImageItem item, int position) {
        FrameLayout tvSelectFile = holder.getView(R.id.tvSelectFile);
        AppCompatImageView imgSelect = holder.getView(R.id.imgSelect);

        if (item.isShowCamera == 0){//1：不显示选择图片按钮；0：显示
            imgSelect.setVisibility(View.GONE);
            tvSelectFile.setVisibility(View.VISIBLE);
            tvSelectFile.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageSize));
            tvSelectFile.setOnClickListener(v -> {
                if (null != listener)listener.onClick(true, position);
            });
        } else {
            tvSelectFile.setVisibility(View.GONE);
            imgSelect.setVisibility(View.VISIBLE);
            imgSelect.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageSize)); //让图片是个正方形
            ImgLoadUtils.loadImage(mContext, item.getPath(), imgSelect);
            imgSelect.setOnClickListener(v -> {
                if (null != listener)listener.onClick(false, position);
            });
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener{
        void onClick(boolean isShowCamera, int position);
    }
}
