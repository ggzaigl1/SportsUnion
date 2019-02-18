package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SelfChallengeRecord;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
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
 * Created by Gab on 2018/2/1 0001.
 * 测试记录
 */
public class SelfChallengeRecordActivity extends BaseActivity {

    @BindView(R.id.recycler_record)
    RecyclerView mRecyclerRecord;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mPageNo;
    SelfChallengeRecordAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_self_challenge_record;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getStandardsToApp(1);
        initRefresh();
        initRv();
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("测试记录");
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

    private void initRv() {
        mRecyclerRecord.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SelfChallengeRecordAdapter(R.layout.item_record, new ArrayList<>());
//        mRecyclerRecord.addItemDecoration(new ListItemDecoration(mContext, 0));
        mRecyclerRecord.setAdapter(mAdapter);
    }

    public void getStandardsToApp(int page) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        param.put("pageNo", page);
        param.put("pageSize", "20");
        new RxNetCache.Builder().create()
                .request(mConnService.getHistoryListToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<SelfChallengeRecord>(progressDialog) {
                    @Override
                    protected void onSuccess(SelfChallengeRecord bean) {
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


//    private List<String> mStrings;
//
//    private void setDummyData() {
//        mStrings = new ArrayList();
//        for (int i = 0; i < 5; i++) {
//            mStrings.add("");
//        }
//    }
}
