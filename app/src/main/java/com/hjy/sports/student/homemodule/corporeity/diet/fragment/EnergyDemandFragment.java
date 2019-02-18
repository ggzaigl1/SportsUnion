package com.hjy.sports.student.homemodule.corporeity.diet.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.utils.JumpUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.adapter.EnergyDemandAdapter;
import com.hjy.sports.student.homemodule.corporeity.diet.fragment.dailylist.EnergyDemandListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 每日营养 Fragment
 * Created by Stefan on 2018/1/25.
 */

public class EnergyDemandFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    EnergyDemandAdapter adapter;
    private List<String> dummyData;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_dailyrecipe;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        setDummyData();

//        DietListActivity activity = (DietListActivity) getActivity();
//        diagnosticId = activity.getDiagnosticId();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new EnergyDemandAdapter(R.layout.energy_demand, dummyData);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position + 1);
                JumpUtils.jump(mContext, EnergyDemandListActivity.class, bundle);
            }
        });
    }

//    private void getGroupMultiToApp(int pageNo) {
//        int id = SpfUtils.getSpfSaveInt("studentId");
//        Map<String, Object> param = new HashMap<>();
//        param.put("token", ConstantUtils.token);
//        param.put("studentid", id);
//        param.put("type", 1);
//        param.put("diagnosticPrescriptionId", diagnosticId);
//        param.put("pageNo", pageNo);
//        param.put("pageSize", 10);
//
//        new NetRequest.Builder().create()
//                .requestDate(mConnService.getListToApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<RecipesMotionToAppBean>() {
//                            @Override
//                            protected void onSuccess(RecipesMotionToAppBean motionToAppBean) {
//                                if (motionToAppBean != null) {
//                                    //当前页
//                                    mPageNo=motionToAppBean.getPageNo();
//                                    adapter.setmDatas(motionToAppBean.getRows());
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
//
//    }
//
//    private void initRefresh() {
//        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                mPageNo += 1;
//                getGroupMultiToApp(mPageNo);
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                getGroupMultiToApp(1);
//            }
//        });
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setDummyData() {
        dummyData = new ArrayList();
        dummyData.add("能量需要量");
        dummyData.add("食材需要量");
        dummyData.add("矿物质需要量");
        dummyData.add("维生素需要量");

    }

    @Override
    public void onPause() {
        super.onPause();
//        if (mRefreshLayout.isRefreshing()) {
//            mRefreshLayout.finishRefresh();
//        }
//        if (mRefreshLayout.isLoading()) {
//            mRefreshLayout.finishLoadmore();
//        }
    }
}
