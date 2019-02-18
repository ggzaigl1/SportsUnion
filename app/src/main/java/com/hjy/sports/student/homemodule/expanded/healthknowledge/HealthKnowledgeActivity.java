package com.hjy.sports.student.homemodule.expanded.healthknowledge;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.NewsBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 健康常识
 * Created by QKun on 2018/1/24.
 */

public class HealthKnowledgeActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private HealthKnowledgeAdapter mAdapter;
    private int mPageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_knowledge;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("健康常识");
        initRecycler();
        initRefresh();
        getStandardsToApp(mPageNo);
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
                mPageNo = 1;
                getStandardsToApp(1);
            }
        });
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HealthKnowledgeAdapter(R.layout.item_exercise_fore_contact, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsBean.DataBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("URL", bean.getURL());
            JumpUtils.jump(mContext, HealthKnowledgeParticularsActivity.class, bundle);// 详情
        });
        mRecycler.setAdapter(mAdapter);
    }

    private void getStandardsToApp(int pageNo) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        Map<String, Object> param = new HashMap<>();
        param.put("CurrentPage", pageNo);
        param.put("PageSize ", 10);
        mConnService.News(param)
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<NewsBean>(progressDialog) {
                    @Override
                    protected void onSuccess(NewsBean bean) {
                        if (null != bean && null != bean.getData()) {
//                            mPageNo = bean();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(bean.getData());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(bean.getData());
                                mRefreshLayout.finishLoadmore();
                                mAdapter.notifyDataSetChanged();
                            } else {
//                              mAdapter.addData(bean.getSensoryPage());
                                mAdapter.setNewData(bean.getData());
                            }
                        }
//
                        L.e(bean.toString());
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
