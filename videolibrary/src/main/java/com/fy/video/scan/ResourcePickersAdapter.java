package com.fy.video.scan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fy.baselibrary.base.recyclerv.adapter.MultiItemCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.MultiItemTypeSupport;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.utils.ScreenUtils;
import com.fy.baselibrary.utils.T;
import com.fy.video.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频选择（单选、多选） RecyclerView 适配器
 * Created by fangs on 2017/8/7.
 */
public class ResourcePickersAdapter extends MultiItemCommonAdapter<ResourceItem> {

    private int mImageSize;

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置
    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;

    private int max;//最大选择数目
    protected List<ResourceItem> selectedImages;    //所有已经选中的图片
    private OnCheckClickListener clickListener;     //checkBox点击监听
    private OnVideoClickListener videoListener;     //item点击事件
    private OnRecorderListener recorderListener;  //拍视频 点击事件监听

    public ResourcePickersAdapter(Context context, List<ResourceItem> datas) {
        super(context, datas, new MultiItemTypeSupport<ResourceItem>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 1) {
                    return R.layout.item_select_video;
                } else {
                    return R.layout.item_resource;
                }
            }

            @Override
            public int getItemViewType(int position, ResourceItem imageItem) {
                return imageItem.isShowCamera();
            }
        });

        mSinglePositions = new SparseBooleanArray();
        mImageSize = ScreenUtils.getImageItemWidth(context);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void convert(ViewHolder holder, ResourceItem item, int position) {
        if (position == 0 && item.isShowCamera() == 0) {//判断 是否显示 视频录制 按钮
            FrameLayout camera = holder.getView(R.id.camera);
            //让图片是个正方形
            camera.setLayoutParams(new FrameLayout.LayoutParams(mImageSize, mImageSize));

            camera.setOnClickListener(v -> {
                //视频录制
                if (null != recorderListener) recorderListener.onClick();
            });
        } else {
            ImageView ivThumb = holder.getView(R.id.iv_thumb);
            //让图片是个正方形
            ivThumb.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageSize));
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(item.path, MediaStore.Video.Thumbnails.MINI_KIND);
            ivThumb.setImageBitmap(bitmap);

            ivThumb.setOnClickListener(v -> {//点击 播放视频
                if (null != videoListener) videoListener.onClick(item.path);
            });

            CheckBox cbCheck = holder.getView(R.id.cb_check);
            cbCheck.setChecked(isItemChecked(mSinglePositions, position));

            if (max == 1) {//单选
                cbCheck.setOnClickListener(v -> {
                    if (null != mRv) {
                        //每次点击选择时，把看见的，看不见的的项目设置为非选择状态
                        ViewHolder holder1 = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                        if (null != holder1) {
                            CheckBox tvBedId1 = holder1.getView(R.id.cb_check);
                            tvBedId1.setChecked(isItemChecked(mSinglePositions, position));
                        } else {
                            notifyItemChanged(mSelectedPos);
                        }

                        if (mSelectedPos == position) {
                            cbCheck.setChecked(false);
                            mSelectedPos = -1;
                        } else {
                            cbCheck.setChecked(true);

                            mSelectedPos = position;
                        }
                    }

                    if (isItemChecked(mSinglePositions, position)) {
                        mSinglePositions.clear();
                        setItemChecked(mSinglePositions, position, false);
                    } else {
                        mSinglePositions.clear();
                        setItemChecked(mSinglePositions, position, true);
                    }

                    if (null != clickListener) clickListener.onClick(mSelectedPos);
                });
            } else {
                //多选
            }
        }
    }


    public void refreshData(List<ResourceItem> images) {
        if (null == images || images.size() == 0) {
            setmDatas(new ArrayList<>());
        } else {
            setmDatas(images);
        }

        notifyDataSetChanged();
    }

    public void setClickListener(OnCheckClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setVideoListener(OnVideoClickListener videoListener) {
        this.videoListener = videoListener;
    }

    public void setRecorderListener(OnRecorderListener recorderListener) {
        this.recorderListener = recorderListener;
    }


    public void setMax(int max) {
        this.max = max;
    }

    public void setmRv(RecyclerView mRv) {
        this.mRv = mRv;
    }

    /**
     * 获取单选的资源 路径
     *
     * @return
     */
    public String getSelectPath() {
        String path = "";
        if (mSelectedPos != -1 && getItemCount() > 0) {
            path = mDatas.get(mSelectedPos).getPath();
        }
        return path;
    }

    /**
     * 拍视频控件 点击事件监听
     */
    interface OnRecorderListener {
        void onClick();
    }

    /**
     * 定义 item 点击事件监听
     */
    interface OnVideoClickListener {
        void onClick(String path);
    }

    /**
     * 定义 item checkBox点击事件回调接口
     */
    interface OnCheckClickListener {
        void onClick(int num);
    }
}
