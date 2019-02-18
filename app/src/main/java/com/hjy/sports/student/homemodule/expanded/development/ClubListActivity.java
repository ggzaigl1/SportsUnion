package com.hjy.sports.student.homemodule.expanded.development;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ExerciseClubBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
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
 * 技能拓展 列表界面
 * Created by Stefan on 2018/3/6.
 */

public class ClubListActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ClubListAdapter mAdapter;
    private int mItemId;
    private int mPageNo;

    @Override
    protected int getContentView() {
        return R.layout.activity_club_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("技能拓展");
        mItemId = getIntent().getExtras().getInt("itemId");
        initRefresh();
        initRecycle();
        getExerciseClub(mItemId, 1);

    }

    private void getExerciseClub(int itemId, int pageNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("itemId", itemId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_get);
        mConnService.getExerciseClub(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<ExerciseClubBean>(progressDialog) {
                    @Override
                    protected void onSuccess(ExerciseClubBean bean) {
                        if (null != bean) {
                            mPageNo = bean.getPageNo();

                            List<ExerciseClubBean.RowsBean> rows = bean.getRows();
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

    private void initRefresh() {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getExerciseClub(mItemId, mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getExerciseClub(mItemId, 1);
            }
        });
    }

    private void initRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ClubListAdapter(R.layout.club_recycle_item, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("exerciseClubId", mAdapter.getData().get(position).getExerciseClubId());
                JumpUtils.jump(mContext, ClubDetailActivity.class, bundle);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
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
