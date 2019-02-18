package com.hjy.sports.student.homemodule.expanded.development;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Config;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ExerciseClubDetailBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/3/8.
 * 体能拓展课程
 */

public class ClubDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.Rl_drill)
    RecyclerView Rl_drill;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_club_name)
    TextView mTvClubName;

    private ClubDetallAdapter mDetallAdapter;
    private ClubDetailAdapter mAdapter;
    private int mExerciseClubId;

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
        setContentView(R.layout.activity_club_detail2);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mExerciseClubId = getIntent().getExtras().getInt("exerciseClubId");
        initRecycle();
        initRecycle1();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getExerciseClubDetails(mExerciseClubId);
    }

    @OnClick({R.id.tv_back, R.id.Relative_club})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.Relative_club:
                Intent intent = new Intent();
                intent.setClassName(mContext, "com.hjy.sports.student.homemodule.expanded.development.ClubIntroductionActivity");//打开一个activity
                if (mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        if (Config.LOGD) {
                            L.d("activity not found for " + " over ", "e");
                        }
                    }
                }
                mContext.overridePendingTransition(R.anim.activity_open, 0);
                break;
        }
    }

    /**
     * 训练课程列表
     */
    private void initRecycle1() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        Rl_drill.setLayoutManager(manager);
        Rl_drill.setHasFixedSize(true);
        mDetallAdapter = new ClubDetallAdapter(R.layout.club_detail1_recycle_item, new ArrayList<>());
        mDetallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Bean", mDetallAdapter.getData().get(position));
                JumpUtils.jump(mContext, CourseIntroDetailActivity.class, bundle);
            }
        });
        Rl_drill.setAdapter(mDetallAdapter);
        Rl_drill.setNestedScrollingEnabled(false);
    }

    /**
     * 用户评价 列表
     */
    private void initRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ClubDetailAdapter(R.layout.club_detail_recycle_item, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }


    /**
     * 課程列表 网络请求
     *
     * @param exerciseClubId
     */
    private void getExerciseClubDetails(int exerciseClubId) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("exerciseClubId", exerciseClubId);
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_get);
        mConnService.getExerciseClubDetails(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<ExerciseClubDetailBean>(progressDialog) {
                    @Override
                    protected void onSuccess(ExerciseClubDetailBean bean) {
                        if (null != bean) {
                            mDetallAdapter.setNewData(bean.getExerciseCurriculumList());
                            mDetallAdapter.notifyDataSetChanged();
                            mAdapter.setNewData(bean.getExerciseCommentList());
                            mAdapter.notifyDataSetChanged();

                            if (!TextUtils.isEmpty(bean.getName())) {
                                mTvClubName.setText(bean.getName());
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


}
