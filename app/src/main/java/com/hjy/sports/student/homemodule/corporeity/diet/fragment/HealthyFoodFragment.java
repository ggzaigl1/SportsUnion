package com.hjy.sports.student.homemodule.corporeity.diet.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.NewHeathyFoodBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.DietListActivity;
import com.hjy.sports.student.homemodule.corporeity.diet.adapter.NewHealthyFoodAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 健康食材 Fragment
 * Created by Stefan on 2018/1/25.
 */

public class HealthyFoodFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    //HealthyFoodAdapter adapter;
    NewHealthyFoodAdapter adapter;
    private int diagnosticId;
    private int mPageNo = 1;

    private List<String> dummyData;
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_healthyfood;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRefresh();
        setDummyData();
        DietListActivity activity = (DietListActivity) getActivity();
        diagnosticId = activity.getDiagnosticId();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //adapter=new HealthyFoodAdapter(getActivity(),new ArrayList<>());
        adapter=new NewHealthyFoodAdapter(getActivity(),new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void getlistToApp(int pageNo) {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        //param.put("diagnosticPrescriptionId", diagnosticId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
       // param.put("type", 3);
        mConnService.getlistToApp(param).compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<NewHeathyFoodBean>() {
                    @Override
                    protected void onSuccess(NewHeathyFoodBean healthyFoodBean) {
                        L.d("参数: ",param.toString());
                                if (healthyFoodBean != null) {
                                    //当前页
                                    mPageNo=healthyFoodBean.getPageNo();
                                    adapter.setmDatas(healthyFoodBean.getRows());
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
//                .requestDate(mConnService.getHealthyFoodApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<HealthyFoodBean>() {
//                            @Override
//                            protected void onSuccess(HealthyFoodBean healthyFoodBean) {
//                                L.d("参数: ",param.toString());
//                                if (healthyFoodBean != null) {
//                                    //当前页
//                                    mPageNo=healthyFoodBean.getPageNo();
//                                    //adapter.setmDatas(healthyFoodBean.getRows());
//                                    adapter.notifyDataSetChanged();
//                                    if(mRefreshLayout!=null) {
//                                        mRefreshLayout.finishRefresh();
//                                    }
//                                }
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
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                getlistToApp(1);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
//                mPageNo += 1;
//                getlistToApp(mPageNo);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getlistToApp(1);
    }

    private void setDummyData() {
        dummyData = new ArrayList();
        for (int i=0;i<5;i++){
            dummyData.add("");
        }
    }
}
