package com.hjy.sports.student.datamodule.physicalfitness;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 体能测试结果分析
 * Created by QKun on 2017/12/19.
 */

public class PhysicalFitnessActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private PhysicalAdapter mAdapter;
    private TextView mTv_grade;
    private ImageView mIv_sex;

    @Override
    protected int getContentView() {
        return R.layout.activity_physical_fitness;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText(getString(R.string.physical_fitness));
        tvMenu.setVisibility(View.GONE);

        initRecycler();
//        getCompareAllToApp();

    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        View headerVeiw = LayoutInflater.from(mContext).inflate(R.layout.physical_fitness_header, (ViewGroup) mRecyclerView.getParent(), false);
        mTv_grade = headerVeiw.findViewById(R.id.tv_grade);

        mIv_sex = headerVeiw.findViewById(R.id.iv_boy);

        mAdapter = new PhysicalAdapter(R.layout.item_body_detection, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setHeaderView(headerVeiw);
    }


  /*  public void getCompareAllToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);

        int id = SpfUtils.getSpfSaveInt("studentId");
        String ncode = SpfUtils.getSpfSaveStr("ncode");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        param.put("ncode", ncode);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.getCompareAllToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<StudentCompareBean>(progressDialog) {
                    @Override
                    protected void onSuccess(StudentCompareBean studentCompareBean) {
                        if (studentCompareBean != null) {

                            String nname = studentCompareBean.getNname();
                            if (!TextUtils.isEmpty(nname)) {
                                mTv_grade.setText(nname);
                            }
                            String sex = studentCompareBean.getSex();
                            if (sex.equals("2")) { //女孩子
                                mIv_sex.setImageResource(R.mipmap.icon_girl);
                            } else {
                                mIv_sex.setImageResource(R.mipmap.icon_boy);
                            }

                            routeItem(studentCompareBean);
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {
                    }
                });
    }*/

    private void routeItem(StudentCompareBean studentCompareBean) {
        List<PhysicalFitnessBean> bodyDetections = new ArrayList<>();
        bodyDetections.add(new PhysicalFitnessBean("总分", studentCompareBean.getZongtipd(), studentCompareBean.getZongtidf()));
        bodyDetections.add(new PhysicalFitnessBean("BMI", studentCompareBean.getBmipd(), studentCompareBean.getBmidf()));
        bodyDetections.add(new PhysicalFitnessBean("肺活量", studentCompareBean.getFeihuoliangpd(), studentCompareBean.getFeihuoliangdf()));
        bodyDetections.add(new PhysicalFitnessBean("50米", studentCompareBean.getWushimipd(), studentCompareBean.getWushimidf()));
        bodyDetections.add(new PhysicalFitnessBean("坐位体", studentCompareBean.getZuotiweiqianqupd(), studentCompareBean.getZuotiweiqianqudf()));
        bodyDetections.add(new PhysicalFitnessBean("跳绳", studentCompareBean.getTiaoshengpd(), studentCompareBean.getTiaoshengdf()));
        bodyDetections.add(new PhysicalFitnessBean("仰卧起坐", studentCompareBean.getYangwoqizuopd(), studentCompareBean.getYangwoqizuodf()));
        bodyDetections.add(new PhysicalFitnessBean("往返跑", studentCompareBean.getWushimibawangfanpd(), studentCompareBean.getWushimibawangfandf()));
        bodyDetections.add(new PhysicalFitnessBean("立定跳远", studentCompareBean.getLidingtiaoyuanpd(), studentCompareBean.getLidingtiaoyuandf()));
        mAdapter.addData(bodyDetections);
    }
}
