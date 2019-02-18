package com.hjy.sports.student.homemodule.expanded.development;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ExerciseItemsBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 原运动拓展  现在技能拓展
 * Created by QKun on 2018/1/24.
 */

public class DevelopmentActivity extends BaseActivity {


    SkillDeAdapter mAdapter;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mPageNo;

    @Override
    protected int getContentView() {
        return R.layout.activity_developmetn;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("技能拓展");
        initRecycle();
        initRefresh();
        getExerciseItems(1);

    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getExerciseItems(mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getExerciseItems(1);
            }
        });
    }

    private void initRecycle() {
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SkillDeAdapter(R.layout.skill_de_item, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("itemId", mAdapter.getData().get(position).getId());
                JumpUtils.jump(mContext, ClubListActivity.class, bundle);
            }
        });
        mRvList.setAdapter(mAdapter);
    }

    private void getExerciseItems(int pageNo) {
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_get);
        mConnService.getExerciseItems(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<ExerciseItemsBean>(progressDialog) {
                    @Override
                    protected void onSuccess(ExerciseItemsBean bean) {
                        if (null != bean) {
                            mPageNo = bean.getPageNo();
                            List<ExerciseItemsBean.RowsBean> rows = bean.getRows();
                            if (!rows.isEmpty()) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(rows);
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(rows);
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.setNewData(rows);
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }

}
