package com.hjy.sports.student.homemodule.quality.sensory;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.util.TextStyleUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 感统activity
 * Created by QKun on 2018/1/24.
 */

public class SensoryActivity extends BaseActivity {

    @BindView(R.id.tv_sensory_grade)
    TextView tv_sensory_grade;
    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    SensoryAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_sensory;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("感统");
        Typeface leiMiao = TextStyleUtils.getLeiMiao(this);
        tv_sensory_grade.setTypeface(leiMiao);
        initRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp();
    }

    private void initRv(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mAdapter = new SensoryMainAdapter(R.layout.item_sensory_main, new ArrayList<>());
        mAdapter = new SensoryAdapter(R.layout.sensory_set_headview, new ArrayList<>());
//        mRecyclerView.addItemDecoration(new ListItemDecoration(mContext, 0));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getStandardsToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        param.put("type", "2");
        param.put("pageNo", "1");
        param.put("pageSize", "1");
        new RxNetCache.Builder().create()
                .request(mConnService.getSensoryListToApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .subscribe(new NetCallBack<SensoryListToApp>(progressDialog) {
                    @Override
                    protected void onSuccess(SensoryListToApp bean) {
                        if (null != bean) {
                            mAdapter.setNewData(bean.getSensoryPage());
                            mAdapter.notifyDataSetChanged();
                            tv_sensory_grade.setText(bean.getAppraise());
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }

    @OnClick({R.id.tv_sensory_more, R.id.tv_sensory_main,R.id.tv_score})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_sensory_more: //感统的意义更多
                JumpUtils.jump(mContext, SensoryMainActivity.class, null);
                break;
            case R.id.tv_sensory_main: //感统练习方式
                JumpUtils.jump(mContext, SensoryMoreActivity.class, null);
                break;
        }
    }

}
