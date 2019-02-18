package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.hjy.sports.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gab on 2018/1/29 0029.
 * 练习方法详情
 */

public class ExerciseOneParticularsActivity extends BaseActivity {
    //    @BindView(R.id.mRecyclerView_1)
//    RecyclerView mRecyclerView_1;
//    @BindView(R.id.mRecyclerView)
//    RecyclerView mRecyclerView;
    @BindView(R.id.tv_sportsMatter)
    TextView tv_sportsMatter;
    @BindView(R.id.tv_sportsStep)
    TextView tv_sportsStep;
    @BindView(R.id.tv_sportsContent)
    TextView tv_sportsContent;
    String sportsContent;//内容
    String sportsMatter;//练习方法
    String sportsStep;//安全要领

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        initRv();
        sportsContent = getIntent().getExtras().getString("sportsContent");
        sportsMatter = getIntent().getExtras().getString("sportsMatter");
        sportsStep = getIntent().getExtras().getString("sportsStep");
        tv_sportsContent.setText(sportsContent);
        tv_sportsMatter.setText(sportsMatter);
        tv_sportsStep.setText(sportsStep);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_exercise_one_particulars);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

//    private void initRv(){
//        mAdapter = new ExerciseOneParticularsAdapter(mContext, mStrings);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView_1.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView_1.setAdapter(mAdapter);
//    }

    @OnClick({R.id.tv_callback})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_callback:
                JumpUtils.exitActivity(this);
                break;
        }
    }
}
