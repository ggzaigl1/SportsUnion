package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.EmergencyTreatmentBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import butterknife.BindView;

/**
 * Created by Gab on 2018/2/24 0024.
 * 急救小技巧 详情
 */

public class ExerciseForeParticularsActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.image_pic)
    AppCompatImageView mImagePic;
    @BindView(R.id.tv_sportsMatter)
    TextView mTvSportsMatter;
    @BindView(R.id.tv_sportsStep)
    TextView mTvSportsStep;
    @BindView(R.id.tv_dispose)
    TextView mTvDispose;
    EmergencyTreatmentBean.RowsBean rowsBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_fore_particulars;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("急救小技巧");
        rowsBean = (EmergencyTreatmentBean.RowsBean) getIntent().getExtras().getSerializable("rowsBean");
        if (null != rowsBean) {
            initdata(rowsBean);
        }
    }

    private void initdata(EmergencyTreatmentBean.RowsBean rowsBean) {
        if (!TextUtils.isEmpty(rowsBean.getName())) {
            mTvName.setText(rowsBean.getName());
        }
        if (!TextUtils.isEmpty(rowsBean.getLevel())) {
            mTvSportsMatter.setText(rowsBean.getLevel());
        }
        if (!TextUtils.isEmpty(rowsBean.getExpression())) {
            mTvSportsStep.setText(rowsBean.getExpression());
        }
        if (!TextUtils.isEmpty(rowsBean.getDispose())) {
            mTvDispose.setText(rowsBean.getDispose());
        }
        ImgLoadUtils.loadRadiusImage(mContext, ApiService.IMG_BASE_URL + rowsBean.getImage(), mImagePic, 5);
    }

}
