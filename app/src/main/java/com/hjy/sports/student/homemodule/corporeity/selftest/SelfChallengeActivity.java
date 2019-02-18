package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SelfChallenge;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.util.TextStyleUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by fangs on 2018/1/27 0027.
 * 自我评价 测试结果
 */
public class SelfChallengeActivity extends BaseActivity {

    @BindView(R.id.rvSelfDetection)
    RecyclerView rvSelfDetection;
    @BindView(R.id.tv_sensory_grade)
    TextView tv_sensory_grade;
    @BindView(R.id.tv_sensory_integral)
    TextView tv_sensory_integral;
    @BindView(R.id.tv_score)
    TextView tv_score;
//    @BindView(R.id.tv_no)
//    TextView tv_no;
    @BindView(R.id.challenge_ll)
    LinearLayout challenge_ll;
    SelfChallengeAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_self_challenge;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("测试结果");
        Typeface leiMiao = TextStyleUtils.getW12(this);
        tv_sensory_grade.setTypeface(leiMiao);
        tv_sensory_integral.setTypeface(leiMiao);
        getStandardsToApp();
        initRv();
    }

    private void initRv() {
        rvSelfDetection.setLayoutManager(new LinearLayoutManager(mContext));
//        rvSelfDetection.addItemDecoration(new ListItemDecoration(mContext, 4));
        mAdapter = new SelfChallengeAdapter(R.layout.item_self_challenge_result, new ArrayList<>());
        rvSelfDetection.setAdapter(mAdapter);
    }

    public void getStandardsToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        new RxNetCache.Builder().create()
                .request(mConnService.getSelfChallenge(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<SelfChallenge>(progressDialog) {
                    @Override
                    protected void onSuccess(SelfChallenge bean) {
                        if (null != bean) {
                            if (bean.getDatalist().equals("")){
                                challenge_ll.setVisibility(View.GONE);
//                                        tv_no.setVisibility(View.VISIBLE);
                            }else {
                                mAdapter.setNewData(bean.getDatalist());
                                tv_score.setText("此次得分：" + bean.getCurrscore());
                                tv_sensory_grade.setText("总分累计：" + bean.getTotalcore());
                            }
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }
}
