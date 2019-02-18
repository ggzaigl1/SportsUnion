package com.hjy.sports.student.homemodule.expanded.development;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ExerciseClubDetailBean;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.hjy.sports.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 课程介绍详情
 * Created by Stefan on 2018/3/9.
 */

public class CourseIntroDetailActivity extends BaseActivity {

    @BindView(R.id.Coach_intro)
    TextView Coach_intro;
    @BindView(R.id.needAttention)
    TextView needAttention;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_coach_name)
    TextView mTvCoachName;
    @BindView(R.id.im_image)
    ImageView mImImage;

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_course_intro_detail);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ExerciseClubDetailBean.ExerciseCurriculumListBean bean = (ExerciseClubDetailBean.ExerciseCurriculumListBean) getIntent().getExtras().getSerializable("Bean");
        if (null != bean) {
            mTvName.setText(bean.getName());//课程名
            mTvContent.setText(bean.getJieshao());//课程内容
            mTvCoachName.setText(bean.getJiaolian());//教练
            Coach_intro.setText(bean.getJiaolianJieshao());//教练介绍
            needAttention.setText(bean.getZhuyi());//注意事项
//            ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + bean.getImage(), mImImage);
        }
    }

    @OnClick({R.id.back})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        JumpUtils.exitActivity(this);
    }
}
