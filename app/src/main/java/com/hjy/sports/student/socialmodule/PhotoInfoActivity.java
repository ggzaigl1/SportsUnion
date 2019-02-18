package com.hjy.sports.student.socialmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.hjy.sports.R;
import com.hjy.sports.widget.PhotoViewPager;

import java.util.ArrayList;

/**
 * 社区item中图片预览
 * Created by QKun on 2017/12/27.
 */

public class PhotoInfoActivity extends AppCompatActivity {

    private int mCurrentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MdStatusBarCompat.setOrdinaryToolBar(this, com.fy.baselibrary.R.color.black);
        setContentView(R.layout.activity_camera_info);
        mCurrentPosition = getIntent().getExtras().getInt("position");
        ArrayList<String> imageurlList = getIntent().getExtras().getStringArrayList("ImageurlList");

        PhotoViewPager viewPager = findViewById(R.id.view_page);
        TextView tv_image_count = findViewById(R.id.tv_image_count);

        tv_image_count.setText((mCurrentPosition+1)+"/"+imageurlList.size());

        PhotoAdapter adapter =new PhotoAdapter(imageurlList,PhotoInfoActivity.this);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(mCurrentPosition,false);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition =position;
                tv_image_count.setText((mCurrentPosition+1)+"/"+imageurlList.size());
            }
        });

    }


}
