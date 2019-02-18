package com.hjy.sports.student.homemodule.quality.sensory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
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
 * Created by Gab on 2018/1/24 0024.
 * 感统练习方式 和 体能红绿灯方式
 */

public class SensoryMoreActivity extends BaseActivity {

    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    SensoryMoreAdapter mAdapter;
    private int mPageNo;
    private int position;
    private String item;

    @Override
    protected int getContentView() {
        return R.layout.activity_sensory_more;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("感统练习方法");
        initRefresh();
        initRv();
        position = getIntent().getExtras().getInt("position");
        item = getIntent().getExtras().getString("item");
        if (!TextUtils.isEmpty(item))
            tvTitle.setText(item + "练习方式");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp(1);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SensoryMoreAdapter(R.layout.sensory_more_headview, new ArrayList<>());
        mRecyclerView.addItemDecoration(new ListItemDecoration(mContext, 4));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void getStandardsToApp(int page) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        if (position == 2) {
            int studentId = SpfUtils.getSpfSaveInt("studentId");
            Map<String, Object> param = new HashMap<>();
            param.put("token", ConstantUtils.token);
            param.put("studentid", studentId);
            param.put("type", "2");
            param.put("item", item);
            param.put("pageNo", page);
            param.put("pageSize", "10");
            //体育红绿灯
            new RxNetCache.Builder().create()
                    .request(mConnService.getsenseListToApp(param).compose(RxHelper.handleResult()))
                    .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                    .subscribe(new NetCallBack<SensoryListToApp>(progressDialog) {
                        @Override
                        protected void onSuccess(SensoryListToApp bean) {
                            if (null != bean) {
                                mPageNo = Integer.parseInt(bean.getPageNo());
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(bean.getSensoryPage());
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(bean.getSensoryPage());
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
//                                        mAdapter.addData(bean.getSensoryPage());
                                    mAdapter.setNewData(bean.getSensoryPage());
                                }
                            }
                        }

                        @Override
                        public void updataLayout(int flag) {

                        }
                    });
        } else {
            int studentId = SpfUtils.getSpfSaveInt("studentId");
            Map<String, Object> param = new HashMap<>();
            param.put("token", ConstantUtils.token);
            param.put("studentid", studentId);
            param.put("type", "2");
            param.put("pageNo", page);
            param.put("pageSize", "10");
            new RxNetCache.Builder().create()
                    .request(mConnService.getSensoryListMoreToApp(param).compose(RxHelper.handleResult()))
                    .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                    .subscribe(new NetCallBack<SensoryListToApp>(progressDialog) {
                        @Override
                        protected void onSuccess(SensoryListToApp bean) {
                            if (null != bean) {
                                mPageNo = Integer.parseInt(bean.getPageNo());
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(bean.getSensoryPage());
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(bean.getSensoryPage());
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
//                                        mAdapter.addData(bean.getSensoryPage());
                                    mAdapter.setNewData(bean.getSensoryPage());
                                }
                            }
                        }

                        @Override
                        public void updataLayout(int flag) {

                        }
                    });
        }

    }
}
