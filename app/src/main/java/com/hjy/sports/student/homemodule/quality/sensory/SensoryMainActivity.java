package com.hjy.sports.student.homemodule.quality.sensory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SensoryListToApp;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2018/1/29 0029.
 * 感统的意义 和 体能红绿灯意义
 */

public class SensoryMainActivity extends BaseActivity {

    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    SensoryMainAdapter mAdapter;
    private List<String> mStrings;
    private int mPageNo;
    private int position;
    private String item;

    @Override
    protected int getContentView() {
        return R.layout.activity_sensory_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("感统的意义");
        initRefresh();
        initRv();
        position = getIntent().getExtras().getInt("position");
        item = getIntent().getExtras().getString("item");
        if (!TextUtils.isEmpty(item))
            tvTitle.setText(item + "的意义");
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
//        View view = LayoutInflater.from(mContext).inflate(R.layout.sensory_headview, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter = new SensoryMainAdapter(R.layout.item_sensory_main, new ArrayList<>());
//        mRecyclerView.addItemDecoration(new ListItemDecoration(mContext, 0));
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.addHeaderView(view);

    }

    public void getStandardsToApp(int page) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        if (position == 1) {
            int studentId = SpfUtils.getSpfSaveInt("studentId");
            Map<String, Object> param = new HashMap<>();
            param.put("token", ConstantUtils.token);
            param.put("studentid", studentId);
            param.put("type", "1");
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
            param.put("type", "1");
            param.put("pageNo", page);
            param.put("pageSize", "10");
            new RxNetCache.Builder().create()
                    .request(mConnService.getSensoryListToApp(param).compose(RxHelper.handleResult()))
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

    private void setDummyData() {
        mStrings = new ArrayList();
        for (int i = 0; i < 5; i++) {
            mStrings.add("");
        }
    }


}
