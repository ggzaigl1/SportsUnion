package com.hjy.sports.student.homemodule.expanded.ornamental;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.CourseDetails;
import com.fy.baselibrary.entity.CourseList;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.quality.sensory.SensoryMainAdapter;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/9 0009.
 * 运动课程列表
 */

public class OrnamentalListContextActivity extends BaseActivity {

    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    OrnamentalListContextAdapter mAdapter;
    private int mPageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_ornamental_list_context;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("运动课程");
        initRefresh();
        initRv();
        getCourseDetails(mPageNo);
    }

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getCourseDetails(mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getCourseDetails(1);
            }
        });
    }

    private void getCourseDetails(int page) {
        Map<String, Object> param = new HashMap<>();
        param.put("pageNo", page);
        mConnService.getCourseList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .subscribe(new NetCallBack<CourseList>() {
                    @Override
                    protected void onSuccess(CourseList t) {
                        L.e(t.getMessage());
                        if (t.getResult() == 1) {
                            if (!t.getData().isEmpty()){
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(t.getData());
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(t.getData());
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.setNewData(t.getData());
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        if (null != mRefreshLayout) {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            }
                            if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadmore();
                            }
                        }
                    }
                });
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new OrnamentalListContextAdapter(R.layout.item_ornamental_list_context, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CourseList.DataBean> data = mAdapter.getData();
                Bundle bundle = new Bundle();
                bundle.putString("id", data.get(position).getId());
                JumpUtils.jump(mContext, OrnamentalContextActivity.class, bundle);
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
