package com.hjy.sports.student.homemodule.corporeity.diet.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.FoodFestivalBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.DietListActivity;
import com.hjy.sports.student.homemodule.corporeity.diet.adapter.DailyRecipeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 健康美食 Fragment
 * Created by Stefan on 2018/1/25.
 */

public class FoodFestivalFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //FoodFestivalAdapter adapter;
    DailyRecipeAdapter adapter;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int diagnosticId;
    private int mPageNo = 1;

    private List<String> dummyData;
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_foodfestival;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        setDummyData();
        initRefresh();
        DietListActivity activity = (DietListActivity) getActivity();
        diagnosticId = activity.getDiagnosticId();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new DailyRecipeAdapter(getActivity(),dummyData);
        recyclerView.setAdapter(adapter);
    }

    private void getGroupMultiToApp(int pageNo) {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        param.put("diagnosticPrescriptionId", diagnosticId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        param.put("type", 4);
        mConnService.getFoodFestivalApp(param).compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<FoodFestivalBean>() {
                    @Override
                    protected void onSuccess(FoodFestivalBean festivalBean) {
                        if (festivalBean != null) {
                                    //当前页
                                    mPageNo=festivalBean.getPageNo();
                                    //adapter.setmDatas(festivalBean.getRows());
                                    adapter.notifyDataSetChanged();
                                    if(mRefreshLayout!=null) {
                                        mRefreshLayout.finishRefresh();
                                    }
                                }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
//        new NetRequest.Builder().create()
//                .requestDate(mConnService.getFoodFestivalApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<FoodFestivalBean>() {
//                            @Override
//                            protected void onSuccess(FoodFestivalBean festivalBean) {
//
//                            }
//
//                            @Override
//                            public void updataLayout(int flag) {
//                                if(mRefreshLayout!=null){
//                                    mRefreshLayout.finishRefresh();
//                                }
//                            }
//                        });

    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
               // getGroupMultiToApp(mPageNo);
                refreshlayout.finishLoadmore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                //getGroupMultiToApp(1);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //getGroupMultiToApp(1);
    }

    private void setDummyData() {
        dummyData = new ArrayList();
        for (int i=0;i<5;i++){
            dummyData.add("");
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
}
