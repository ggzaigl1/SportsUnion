package com.hjy.sports.student.homemodule.expanded.ornamental;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * 运动课程
 * Created by QKun on 2018/1/24.
 */

public class OrnamentalActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getContentView() {
        return R.layout.activity_ornamental;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("运动课程");
    }
}
