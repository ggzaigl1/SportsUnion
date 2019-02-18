package com.hjy.sports.student.homemodule.quality.standard;

import android.os.Bundle;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;

/**
 * Created by Gab on 2018/3/7 0007.
 *  提升方案
 */

public class LiftingSchemeActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_lifting_scheme;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("提升方案");
    }
}
