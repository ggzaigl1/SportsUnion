package com.fy.crop;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.library.imgpicker.R;

/**
 * 图片裁剪 activity
 * Created by fangs on 2017/12/20.
 */
public class UCropActivity extends BaseActivity{

    private static final String TAG = "UCropActivity";

    private Uri mOutputUri;

    @Override
    protected int getContentView() {
        return R.layout.activity_crop;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        tvTitle.setText(R.string.photo_crop);
        tvMenu.setText(R.string.complete);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tvMenu){

        }
    }


}
