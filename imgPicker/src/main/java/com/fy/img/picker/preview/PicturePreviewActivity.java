package com.fy.img.picker.preview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.T;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.img.picker.bean.ImageItem;
import com.fy.library.imgpicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片预览activity
 * Created by fangs on 2017/7/6.
 */
public class PicturePreviewActivity extends BaseActivity {

    protected FrameLayout pickerBottom;         //底部布局
    protected ConstraintLayout rlHead;          //头部布局
    protected TextView tvTitle;                 //显示当前图片的位置  例如  5/31
    protected TextView tvBack;
    protected TextView tvMenu;
    protected ConvenientBanner viewPager;
    protected CheckBox original_checkbox;
    protected TextView send;

    protected ImageFolder imgFolder;            //跳转进 PicturePreviewActivity 的图片文件夹
    protected List<ImageItem> selectedImages;   //所有已经选中的图片
    protected int mCurrentPosition = 0;         //跳转进PicturePreviewActivity时的序号，第几个图片; 当前显示的图片下标
    protected int max = 12;                     //最大选择图片数目

    /**
     * 当前屏幕状态 全屏or显示菜单
     */
    private int currentState = PickerConfig.STATE_SHOW_MENU;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_preview);
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    protected void init(Bundle savedInstanceState) {
        rlHead       = findViewById(R.id.rlHead);
        rlHead.setBackgroundColor(getResources().getColor(R.color.imgPreviewHeadBg));

        viewPager = findViewById(R.id.bannerViewPager);
        tvTitle = findViewById(R.id.tvTitle);
        tvBack = findViewById(R.id.tvBack);
        tvMenu = findViewById(R.id.tvMenu);
        original_checkbox = findViewById(R.id.original_checkbox);
        pickerBottom = findViewById(R.id.pickerBottom);
        pickerBottom.setOnClickListener(this);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        original_checkbox.setOnClickListener(this);

        getTransmitData();
        //初始化当前页面的状态
        tvTitle.setText(getString(R.string.preview_image_count, mCurrentPosition + 1, imgFolder.images.size()));
        ResourceUtils.setTvColor(tvTitle, R.color.white);
        original_checkbox.setChecked(imgFolder.images.get(mCurrentPosition).isSelect);
        tvBack.setOnClickListener(this);
        tvMenu.setVisibility(View.INVISIBLE);

        //本地图片例子 new LocalImageHolderView(), imgFolder.images
        viewPager.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView(PicturePreviewActivity.this);
            }
        }, imgFolder.images)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        mCurrentPosition = position;
                        original_checkbox.setChecked(imgFolder.images.get(position).isSelect);
                        tvTitle.setText(getString(R.string.preview_image_count, position + 1, imgFolder.images.size()));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                })
                .setPageTransformer(new AccordionTransformer())
                .setcurrentitem(mCurrentPosition);
    }

    /**
     * 获取传递的数据
     */
    private void getTransmitData() {
        Bundle bundle = getIntent().getExtras();
        mCurrentPosition = bundle.getInt(PickerConfig.KEY_CURRENT_POSITION, 0);
        max = bundle.getInt(PickerConfig.KEY_MAX_COUNT, -1);
        if (max > 0 && max == 1){
            original_checkbox.setVisibility(View.GONE);
        }

        imgFolder = (ImageFolder) bundle.getSerializable(PickerConfig.KEY_IMG_FOLDER);

        ImageFolder folder = (ImageFolder) bundle.getSerializable(PickerConfig.KEY_ALREADY_SELECT);
        if (null != folder)selectedImages = folder.images;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.send) {//完成
            Bundle bundle = new Bundle();
            bundle.putSerializable("pickerImgData", imgFolder.images.get(mCurrentPosition));
            bundle.putSerializable("ImageFolder", new ImageFolder(selectedImages));
            JumpUtils.jumpResult(mContext, bundle);
        } else if(i == R.id.original_checkbox){
            if (original_checkbox.isChecked() && selectedImages.size() >= max){
                T.showLong(mContext.getString(R.string.select_limit, max));
                original_checkbox.setChecked(false);
            } else {
                ImageItem imgItem = imgFolder.images.get(mCurrentPosition);
                if (original_checkbox.isChecked()) {
                    imgItem.setSelect(true);//设置状态 属性为 选中
                    selectedImages.add(imgItem);
                } else {
                    selectedImages.remove(imgItem);
                    imgItem.setSelect(false);//设置状态 属性为 未选中
                }
            }
        }
    }

    /**
     * 隐藏 或显示 状态栏，标题栏，底部栏
     */
    public void toggleStateChange() {
        if (currentState == PickerConfig.STATE_SHOW_MENU) {
            currentState = PickerConfig.STATE_FULLSCREEN;
            pickerBottom.setVisibility(View.GONE);
            rlHead.setVisibility(View.GONE);
        } else {
            currentState = PickerConfig.STATE_SHOW_MENU;
            pickerBottom.setVisibility(View.VISIBLE);
            rlHead.setVisibility(View.VISIBLE);
        }
    }
}
