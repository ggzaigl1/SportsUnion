package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SensoryOne;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.hjy.sports.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2018/1/24 0024.
 * 练习方法一
 */

public class ExerciseOneActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ExerciseAdapter mAdapter;
    private List<String> mStrings;
    private int mPageNo;
    int mDiagnosticId;
    private String sportsContent;//内容
    private String sportsMatter;//练习方法
    private String sportsStep;//安全要领

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_one;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("练习方法");
        mDiagnosticId = getIntent().getExtras().getInt("mDiagnosticId");
        initRecycler();
        initRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp(mDiagnosticId, 1);
    }

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getStandardsToApp(mDiagnosticId, mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getStandardsToApp(mDiagnosticId,1);
            }
        });
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ExerciseAdapter(R.layout.item_exercise_contact, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("sportsContent", sportsContent);
                bundle.putString("sportsMatter", sportsMatter);
                bundle.putString("sportsStep", sportsStep);
                JumpUtils.jump(mContext, ExerciseOneParticularsActivity.class, bundle);//练习方法详情
            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    public void getStandardsToApp(int mDiagnosticId, int pageNo) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("diagnosticPrescriptionId", mDiagnosticId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        new RxNetCache.Builder().create()
                .request(mConnService.getSportsMotionToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<SensoryOne>(progressDialog) {
                    @Override
                    protected void onSuccess(SensoryOne bean) {
                        if (null != bean) {
                            mPageNo = bean.getPageNo();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(bean.getRows());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(bean.getRows());
                                mRefreshLayout.finishLoadmore();
                                mAdapter.notifyDataSetChanged();
                            } else {
//                                        mAdapter.addData(bean.getSensoryPage());
                                mAdapter.setNewData(bean.getRows());
                            }
                        }
                        if (!bean.getRows().isEmpty()){
                            sportsContent = bean.getRows().get(0).getSportsContent();
                            sportsMatter = bean.getRows().get(0).getSportsMatter();
                            sportsStep = bean.getRows().get(0).getSportsStep();
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });

    }

    private void setDummyData() {
        mStrings = new ArrayList();
        for (int i = 0; i < 5; i++) {
            mStrings.add("");
        }
    }
}
