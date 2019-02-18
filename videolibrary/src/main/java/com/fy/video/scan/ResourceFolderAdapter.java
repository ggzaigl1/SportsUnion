package com.fy.video.scan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.CommonAdapter;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.video.R;

import java.util.List;

/**
 * 文件夹列表 的适配器
 * Created by fangs on 2017/6/30.
 */
public class ResourceFolderAdapter extends CommonAdapter<ResourceFolder> {

    private int lastSelected = 0;
    /** 是否显示 拍照按钮 */
    private boolean isShowPicture = false;

    public ResourceFolderAdapter(Context context, List<ResourceFolder> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        View itemView = getViewCache().get(position);

        if (null == itemView) {
            final ResourceFolder item = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_folder, null);

            ImageView iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            ImageView iv_folder_check = (ImageView) itemView.findViewById(R.id.iv_folder_check);
            TextView tv_folder_name = (TextView) itemView.findViewById(R.id.tv_folder_name);
            TextView tv_image_count = (TextView) itemView.findViewById(R.id.tv_image_count);

            ResourceUtils.setText(tv_image_count, R.string.folder_video_count,
                    isShowPicture ? item.resources.size() - 1 : item.resources.size());

            tv_folder_name.setText(item.name);
            ImgLoadUtils.loadImage(getContext(), item.cover.path, iv_cover);

            if (lastSelected == position) {
                iv_folder_check.setVisibility(View.VISIBLE);
            } else {
                iv_folder_check.setVisibility(View.INVISIBLE);
            }
            
            itemView.setTag(item);
            getViewCache().put(position, itemView);
        }

        return itemView;
    }

    public void setSelectIndex(int i, boolean isShowPicture) {
        lastSelected = i;
        this.isShowPicture = isShowPicture;
        clearCache();
    }
}
