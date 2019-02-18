package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.EmergencyTreatmentBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2018/2/24 0024.
 * 急救小技巧
 */

public class ExerciseForeActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    ExerciseForeAdapter mAdapter;
    private int mPageNo;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise_fore;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("急救小技巧");
        initRecycler();
        initRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp(1);
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

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getStandardsToApp(mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getStandardsToApp(1);
            }
        });
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ExerciseForeAdapter(R.layout.item_exercise_fore_contact, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EmergencyTreatmentBean.RowsBean rowsBean = mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("rowsBean",rowsBean);
                JumpUtils.jump(mContext, ExerciseForeParticularsActivity.class, bundle);//运动小技巧 详情
            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    public void getStandardsToApp( int pageNo) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        Map<String, Object> param = new HashMap<>();
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        param.put("token", ConstantUtils.token);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        param.put("studentid", studentId);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.emergencyTreatment(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<EmergencyTreatmentBean>(progressDialog) {
                    @Override
                    protected void onSuccess(EmergencyTreatmentBean bean) {
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
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }
                });
    }

}
