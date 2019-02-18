package com.fy.video.scan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.popupwindow.CommonPopupWindow;
import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.PhotoUtils;
import com.fy.baselibrary.utils.T;
import com.fy.video.R;
import com.fy.video.recorder.VideoInputDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频选择器
 * Created by fangs on 2018/1/27.
 */
public class VideoPckerActivity extends BaseActivity{

    private int maxCount = 1;//最大选择数目
    private boolean isTAKE_picture = true;//是否显示拍照 按钮
    private Button btn_dir;//全部视频
    private Button btn_complete;//完成

    private ResourceFolder imgFolder;//当前界面显示的图片 文件夹
    private RecyclerView recycler;
    private ResourcePickersAdapter pickersAdapter;

    private CommonPopupWindow popupWindow;//图片文件夹的 弹窗
    private ResourceFolderAdapter mImageFolderAdapter;

    @Override
    protected int getContentView() {
        return R.layout.act_video_picker;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActTitle(R.string.select_video);
        setActMenu(R.string.preview);

        initView();
        setViewStutas(0);

        initRV();
        initImgFolder();
    }

    private void initView(){
        recycler = findViewById(R.id.recycler);
        btn_dir = findViewById(R.id.btn_dir);
        btn_complete = findViewById(R.id.btn_complete);
        btn_dir.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
    }

    /**
     * 设置 预览 按钮 和 完成按钮 内容 和状态
     * @param num
     */
    public void setViewStutas(int num){
        if (num > 0) {
            btn_complete.setTextColor(Color.WHITE);
            btn_complete.setEnabled(true);

            tvMenu.setEnabled(true);
            tvMenu.setTextColor(Color.BLACK);
        } else {
            btn_complete.setTextColor(Color.GRAY);
            btn_complete.setEnabled(false);

            tvMenu.setTextColor(Color.GRAY);
            tvMenu.setEnabled(false);
        }
    }

    @SuppressLint("StringFormatMatches")
    private void initRV() {
        //设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        //添加分割线
//        recycler.addItemDecoration(new DividerParams().create(this));

        //设置adapter
        pickersAdapter = new ResourcePickersAdapter(mContext, new ArrayList<>());
        pickersAdapter.setMax(1);
        pickersAdapter.setmRv(recycler);
        pickersAdapter.setVideoListener(path -> {
            Bundle bundle = new Bundle();
            bundle.putString("VideoPath", path);
            JumpUtils.jump(mContext, bundle, "com.fy.video.play.VideoPlayActivity");
        });
        pickersAdapter.setRecorderListener(() -> {
            VideoInputDialog.show(mContext, getSupportFragmentManager(), VideoInputDialog.Q720);
        });
        pickersAdapter.setClickListener(num -> {
            setViewStutas(num);
        });
        recycler.setAdapter(pickersAdapter);
    }

    //初始化图片文件夹 相关
    private void initImgFolder() {
        mImageFolderAdapter = new ResourceFolderAdapter(mContext, new ArrayList<>());

        new VideoDataSource(mContext, null, new VideoDataSource.OnVideoLoadedListener() {
            @Override
            public void onVideoLoaded(List<ResourceFolder> imageFolders) {
                if (imageFolders.size() == 0) {
                    List<ResourceItem> images = new ArrayList<>();
                    if (isTAKE_picture) {
                        images.add(new ResourceItem(0));
                    }
                    pickersAdapter.refreshData(images);
                } else {
                    if (isTAKE_picture) {
                        for (ResourceFolder folderItem : imageFolders) {
                            folderItem.resources.add(0, new ResourceItem(0));
                        }
                    }

                    imgFolder = imageFolders.get(0);
                    pickersAdapter.refreshData(imgFolder.resources);
                }

                mImageFolderAdapter.setData(imageFolders);
                mImageFolderAdapter.setSelectIndex(0, isTAKE_picture);
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.btn_dir) {//全部图片 按钮
            if (popupWindow != null && popupWindow.isShowing()) return;
            //计算popupWindow 高度（listview Item数量  * （Item高度 + 分割线高度））
            int itemCount = mImageFolderAdapter.getCount() > 3 ? 3 : mImageFolderAdapter.getCount();
            int pw_lv_height = 166 * itemCount;

            popupWindow = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_imgfolder)
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, pw_lv_height)
                    .setAnimationStyle(R.style.AnimDown)
                    .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                        @Override
                        public void getChildView(View view, int layoutResId) {
                            if (layoutResId == R.layout.pop_imgfolder) {
                                ListView lvImaFolder = (ListView) view.findViewById(R.id.lvImaFolder);
                                lvImaFolder.setAdapter(mImageFolderAdapter);

                                lvImaFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        imgFolder = (ResourceFolder) view.getTag();

                                        if (null != imgFolder) {
                                            T.showLong(imgFolder.name);

                                            pickersAdapter.refreshData(imgFolder.resources);
                                            btn_dir.setText(imgFolder.name);
                                            mImageFolderAdapter.setSelectIndex(position, isTAKE_picture);
                                        }

                                        popupWindow.dismiss();
                                    }
                                });
                            }
                        }
                    }).create();

            //得到button的左上角坐标
            int[] positions = new int[2];
            view.getLocationOnScreen(positions);
            popupWindow.showAtLocation(findViewById(android.R.id.content),
                    Gravity.NO_GRAVITY, positions[0], positions[1] - pw_lv_height);
        } else if (i == R.id.btn_complete) {//完成
            Bundle bundle = getIntent().getExtras();

            Bundle bundle1 = new Bundle();//此处必须 创建一个 Bundle对象 不能从 getIntent 中获取
            bundle1.putInt("position", bundle.getInt("position"));
            bundle1.putString("videoPath", pickersAdapter.getSelectPath());
            JumpUtils.jumpResult(mContext, bundle1);

        } else if (i == R.id.tvMenu) {//预览
            Bundle bundle = new Bundle();
            bundle.putString("VideoPath", pickersAdapter.getSelectPath());
            JumpUtils.jump(mContext, bundle, "com.fy.video.play.VideoPlayActivity");
        }
    }
}
