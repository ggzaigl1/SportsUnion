package com.hjy.sports.student.homemodule.quality.standard;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.StandardsBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 达标activity
 * Created by QKun on 2018/1/24.
 */

public class StandardActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView rvStaminaItem;
    StandardAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_standard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("体测达标成绩");
        initrvItemRv();

    }

    private void initrvItemRv() {
        List<StandardsBean.ItemsBean> data = new ArrayList<>();
        mAdapter = new StandardAdapter(mContext, data);
        rvStaminaItem.setLayoutManager(new LinearLayoutManager(mContext));
//        rvStaminaItem.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvStaminaItem.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getStaminaToApp();
    }

    //网络请求 体能界面数据
    private void getStaminaToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        param.put("type", "1");
        param.put("pageNo", "1");
        param.put("pageSize", "2");

        new RxNetCache.Builder().create()
                .request(mConnService.getStandardsToApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(new Consumer<Disposable>() {
                    //RxJava 的取消Rx 链式调用 可以避免一些 莫名奇妙的 空指针
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .subscribe(new NetCallBack<StandardsBean>(progressDialog) {
                    @Override
                    protected void onSuccess(StandardsBean standardsBean) {
                        if (standardsBean != null) {
                            List<StandardsBean.ItemsBean> data = new ArrayList<>();
                            data.add(new StandardsBean.ItemsBean(1));
                            data.addAll(standardsBean.getItems());
                            data.add(new StandardsBean.ItemsBean(2));

                            mAdapter.setWeak(standardsBean.getWeak());
                            mAdapter.setmDatas(data);
                            mAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }
}
