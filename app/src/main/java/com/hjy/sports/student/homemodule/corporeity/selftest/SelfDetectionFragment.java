package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.SelfDetection;
import com.fy.baselibrary.entity.SelfDetectionAdd;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自我检测 fragment
 * Created by fangs on 2018/1/25.
 */
public class SelfDetectionFragment extends BaseFragment {

    @BindView(R.id.rvSelfDetection)
    RecyclerView rvSelfDetection;
    SelfDetectionAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_self_detection;
    }

    @Override
    protected void baseInit() {
        initRv();
        getSelfItemInfo();
    }

    private void initRv() {
        adapter = new SelfDetectionAdapter(mContext, new ArrayList<>());
        rvSelfDetection.setLayoutManager(new LinearLayoutManager(mContext));
        rvSelfDetection.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvSelfDetection.setAdapter(adapter);
    }

    @OnClick({R.id.btnSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSave:
                selfAddToApp();
                break;
        }
    }

    /**
     * 自我检测 获取条目数据
     */
    private void getSelfItemInfo() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.getSelfItemInfo(getParams()).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<List<SelfDetection>>(progressDialog) {
                    @Override
                    protected void onSuccess(List<SelfDetection> data) {
                        if (null != data && data.size() > 0) {
                            adapter.setmDatas(data);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 自我检测 提交数据
     */
    private void selfAddToApp() {
        Map<String, Object> params = getParams();

        int counter = 0;
        List<SelfDetection> listData = adapter.getmDatas();
        for (SelfDetection bean : listData) {

            if (!TextUtils.isEmpty(bean.getInputContent())) {
                params.put(bean.getValue(), bean.getInputContent());
                counter++;
            }
        }

        if (counter < listData.size()) {
            T.showLong("请填写完整的检测数据...");
            return;
        }

        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_build);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.selfAddToApp(params).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<SelfDetectionAdd>(progressDialog) {
                    @Override
                    protected void onSuccess(SelfDetectionAdd data) {
                        if (null != data) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("data", data);
                            JumpUtils.jump(mContext, PKActivity.class, bundle);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("token", ConstantUtils.token);
        params.put("studentid", ConstantUtils.studentID);

        return params;
    }
}
