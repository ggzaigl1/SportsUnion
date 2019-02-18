package com.hjy.sports.student.homemodule.corporeity.staminasignal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.StaminaSignalBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.quality.sensory.SensoryMainActivity;
import com.hjy.sports.student.homemodule.quality.sensory.SensoryMoreActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2018/3/6 0006.
 * 体能红绿灯
 */

public class StaminaSignalsActivity extends BaseActivity {

//    @BindView(R.id.tv_sensory_grade)
//    TextView tv_sensory_grade;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.tv_score_content)
    TextView tv_score_content;
    @BindView(R.id.tv_sensory_content)
    TextView tv_sensory_content;
    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    StaminaSignalsAdapter mAdapter;
    String item;


    @Override
    protected int getContentView() {
        return R.layout.activity_stamina_signals;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("体能红绿灯");
//        Typeface leiMiao = TextStyleUtils.getLeiMiao(this);
//        tv_sensory_grade.setTypeface(leiMiao);
        initRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp();
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new StaminaSignalsAdapter(R.layout.sensory_set_headview, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getStandardsToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        new RxNetCache.Builder().create()
                .request(mConnService.getsenseMainToApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<StaminaSignalBean>(progressDialog) {
                    @Override
                    protected void onSuccess(StaminaSignalBean bean) {
                        if (null != bean) {
                            List<StaminaSignalBean.LianxidataBean> beans = new ArrayList<>();
                            beans.add(bean.getLianxidata());
                            mAdapter.setNewData(beans);
                            mAdapter.notifyDataSetChanged();
//                            tv_sensory_grade.setText(bean.getAppraise());
                            tv_score.setText(bean.getYiyi());
                            tv_sensory_content.setText(bean.getLianxi());
                            if (!bean.getYiyicontent().equals("")) {
                                tv_score_content.setText(bean.getYiyicontent());
                            } else {
                                tv_score_content.setText("");
                            }
                            item = bean.getItem();
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }

    @OnClick({R.id.tv_sensory_more, R.id.tv_sensory_main})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_sensory_more: //体能红绿灯的意义 更多
                Bundle bundle = new Bundle();
                bundle.putInt("position", 1);
                bundle.putString("item",item);
                JumpUtils.jump(mContext, SensoryMainActivity.class, bundle);
                break;
            case R.id.tv_sensory_main: //体能红绿灯 方式
                Bundle bundle1 = new Bundle();
                bundle1.putInt("position", 2);
                bundle1.putString("item",item);
                JumpUtils.jump(mContext, SensoryMoreActivity.class, bundle1);
                break;
        }
    }

}
