package com.hjy.sports.student.datamodule.bodydetection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.StudentCompareBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.widget.RulerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 形体机体检测
 * Created by QKun on 2017/12/18.
 */

public class BodyDetectionActivity extends BaseActivity {
    @BindView(R.id.ll_gradient)
    LinearLayout mLlGradient;
    @BindView(R.id.tv_grade)
    TextView mTvGrade;
    @BindView(R.id.iv_boy)
    ImageView mIvBoy;
    @BindView(R.id.tv_weight)
    TextView mTvWeight;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_height)
    TextView mTvHeight;
    @BindView(R.id.height_ruler)
    RulerView mHeightRuler;
    private BodyDetectionAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_body_detection;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText(getString(R.string.body_detection));
        tvMenu.setVisibility(View.GONE);
        mHeightRuler.setSelectedValue(100);
        mTvHeight.setText("");
//        mHeightRuler.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
//            @Override
//            public void onChange(RulerView view, float value) {
//                mTvHeight.setText(value + "");
//            }
//        });
        initRecycler();

//        getCompareAllToApp();
    }

    private void initRecycler() {
//        List<BodyDetectionBean> bodyDetections = getBodyDetections();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BodyDetectionAdapter(R.layout.item_body_detection, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }


//    public void getCompareAllToApp() {
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
//
//        int id = SpfUtils.getSpfSaveInt("studentId");
//        String ncode = SpfUtils.getSpfSaveStr("ncode");
//        Map<String, Object> param = new HashMap<>();
//        param.put("token", ConstantUtils.token);
//        param.put("studentid", id);
//        param.put("ncode", ncode);
//        new RxNetCache.Builder()
//                .create()
//                .request(mConnService.getCompareAllToApp(param).compose(RxHelper.handleResult()))
//                .subscribe(new NetCallBack<StudentCompareBean>(progressDialog) {
//                    @Override
//                    protected void onSuccess(StudentCompareBean studentCompareBean) {
//                        if (studentCompareBean != null) {
//                            double height = studentCompareBean.getHeight();
//                            mTvHeight.setText((int) height + "");
//                            String nname = studentCompareBean.getNname();
//                            if (!TextUtils.isEmpty(nname)) {
//                                mTvGrade.setText(nname);
//                            }
//                            mHeightRuler.setSelectedValue((float) height);
//
//                            //体重
//                            double weight = studentCompareBean.getWeight();
//                            mTvWeight.setText(weight + "kg");
//
//                            String sex = studentCompareBean.getSex();
//                            if (sex.equals("2")) { //女孩子
//                                mIvBoy.setImageResource(R.mipmap.icon_girl);
//                            } else {
//                                mIvBoy.setImageResource(R.mipmap.icon_boy);
//                            }
//
//                            routeItem(studentCompareBean);
//                        }
//                    }
//
//                    @Override
//                    public void updataLayout(int flag) {
//                    }
//                });
//    }

    private void routeItem(StudentCompareBean studentCompareBean) {
        List<BodyDetectionBean> bodyDetections = new ArrayList<>();
        bodyDetections.add(new BodyDetectionBean("身高", studentCompareBean.getHeightpd(), studentCompareBean.getHeight(), studentCompareBean.getHeightdf()));
        bodyDetections.add(new BodyDetectionBean("体重", studentCompareBean.getWeightpd(), studentCompareBean.getWeight(), studentCompareBean.getWeightdf()));
        mAdapter.addData(bodyDetections);
    }
}
