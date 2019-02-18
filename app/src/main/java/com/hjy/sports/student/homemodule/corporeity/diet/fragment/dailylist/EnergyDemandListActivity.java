package com.hjy.sports.student.homemodule.corporeity.diet.fragment.dailylist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.EnergyDemandListBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.adapter.EnergyDemandListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 能量需要量List界面 Fragment
 * Created by Stefan on 2018/1/25.
 */

public class EnergyDemandListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    EnergyDemandListAdapter adapter;
    int mPageNo = 1;
    private int type;

    @Override
    protected int getContentView() {
        return R.layout.activity_energy_demand_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        Bundle extras = getIntent().getExtras();
        type = extras.getInt("position");
        switch (type){
            case 1:tvTitle.setText("能量需要量");
                break;
            case 2:tvTitle.setText("食材需要量");
                break;
            case 3:tvTitle.setText("矿物质需要量");
                break;
            case 4:tvTitle.setText("维生素需要量");
                break;
        }
        getListToApp(1, type);
        initRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new EnergyDemandListAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void getListToApp(int pageNo, int type) {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        param.put("type", type);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);

        mConnService.getListToApp(param).compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<EnergyDemandListBean>() {
                    @Override
                    protected void onSuccess(EnergyDemandListBean motionToAppBean) {
                        if (motionToAppBean != null) {
                            // 当前页
                            mPageNo = motionToAppBean.getPageNo();
                            adapter.setmDatas(motionToAppBean.getRows());
                            adapter.notifyDataSetChanged();
                            if (mRefreshLayout != null) {
                                mRefreshLayout.finishRefresh();
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
                getListToApp(mPageNo,type);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getListToApp(1,type);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
