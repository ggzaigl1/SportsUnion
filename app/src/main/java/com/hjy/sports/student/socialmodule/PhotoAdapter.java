package com.hjy.sports.student.socialmodule;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;


/**
 * Created by QKun on 2017/12/28.
 */

public class PhotoAdapter extends PagerAdapter {

    private List<String> imageUrls;
    private AppCompatActivity activity;

    public PhotoAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
//        Glide.with(activity)
//                .load(Api.IMG_BASE_URL + url)
//                .into(photoView);
        ImgLoadUtils.loadImage(activity, ApiService.IMG_BASE_URL + url,photoView);
        container.addView(photoView);

        photoView.setOnPhotoTapListener((view, x, y) -> activity.finish());
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
