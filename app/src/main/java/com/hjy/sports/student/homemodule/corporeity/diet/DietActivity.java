package com.hjy.sports.student.homemodule.corporeity.diet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.PhysicalDiagnosisBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 饮食处方
 * Created by QKun on 2018/1/24.
 */

public class DietActivity extends BaseActivity {

//    @BindView(R.id.tv_content)
//    TextView tv_content;

    @BindView(R.id.cookbook)
    ImageView cookbook;

    @BindView(R.id.weeklyRecipe)
    ImageView weeklyRecipe;

    @BindView(R.id.healthyFood)
    ImageView healthyFood;

    @BindView(R.id.foodFestival)
    ImageView foodFestival;
//    @BindView(R.id.tv_type)
//    TextView mTvType;
//    @BindView(R.id.tv_diagnosticContent)
//    TextView mTvDiagnosticContent;
    private int diagnosticId;

    @Override
    protected int getContentView() {
        return R.layout.activity_diet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("饮食建议");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getGroupMultiToApp();
    }

    private void getGroupMultiToApp() {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);

        mConnService.groupMultipleToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<PhysicalDiagnosisBean>() {
                    @Override
                    protected void onSuccess(PhysicalDiagnosisBean bean) {
                        List<PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean> physicalDiagnosisEntity = bean.getPhysicalDiagnosisEntity();
                                if (physicalDiagnosisEntity!=null && physicalDiagnosisEntity.size()!=0) {
                                    for (int i = physicalDiagnosisEntity.size() - 1; i >= 0; i--) {
                                        String diagnosticType = physicalDiagnosisEntity.get(i).getDiagnosticType();
                                        if (!TextUtils.isEmpty(diagnosticType)) {
                                            if (Integer.parseInt(diagnosticType) == 1) { //运动诊断

                                                continue;
                                            } else if (Integer.parseInt(diagnosticType) == 2) { //膳食诊断
                                                PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean physicalDiagnosisEntityBean = physicalDiagnosisEntity.get(i);
                                                bindData(physicalDiagnosisEntityBean);
                                                continue;
                                            }
                                        }
                                    }
                                }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
//        new NetRequest.Builder().create()
//                .requestDate(mConnService.groupMultipleToApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<PhysicalDiagnosisBean>() {
//                            @Override
//                            protected void onSuccess(PhysicalDiagnosisBean bean) {
//
//                                }
//
//                            }
//
//                            @Override
//                            public void updataLayout(int flag) {
//                            }
//                        });
    }

    private void bindData(PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean bean) {
        if (bean != null) {
//            if (!TextUtils.isEmpty(bean.getPhysicalContent()))
//                tv_content.setText(bean.getPhysicalContent());

            PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean.DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity = bean.getDiagnosticPrescriptionEntity();
            if (diagnosticPrescriptionEntity != null) {
                //诊断表id
                diagnosticId = diagnosticPrescriptionEntity.getDiagnosticId();
                //类型
                String diagnosticPurpose = diagnosticPrescriptionEntity.getDiagnosticPurpose();
//                if (!TextUtils.isEmpty(diagnosticPurpose)) {
//                    mTvType.setText(diagnosticPurpose);
//                }

//                if (!TextUtils.isEmpty(diagnosticPrescriptionEntity.getDiagnosticContent()))
//                    mTvDiagnosticContent.setText(diagnosticPrescriptionEntity.getDiagnosticContent());
            }
        }
    }

    @OnClick({R.id.cookbook, R.id.weeklyRecipe, R.id.healthyFood, R.id.foodFestival})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.cookbook:
                Bundle bundle1 = new Bundle();
                bundle1.putString("mark", "EnergyDemandFragment");//每日食谱
                bundle1.putInt("diagnosticId",diagnosticId);
                JumpUtils.jump(mContext, DietListActivity.class, bundle1);
                break;
            case R.id.weeklyRecipe:
                Bundle bundle2 = new Bundle();
                bundle2.putString("mark", "WeeklyRecipeFragment");//每周食谱
                bundle2.putInt("diagnosticId",diagnosticId);
                JumpUtils.jump(mContext, DietListActivity.class, bundle2);
                break;
            case R.id.healthyFood:
                Bundle bundle3 = new Bundle();
                bundle3.putString("mark", "HealthyFoodFragment");//健康食材
                bundle3.putInt("diagnosticId",diagnosticId);
                JumpUtils.jump(mContext, DietListActivity.class, bundle3);
                break;
            case R.id.foodFestival:
                Bundle bundle4 = new Bundle();
                bundle4.putString("mark", "FoodFestivalFragment");//健康美食
                bundle4.putInt("diagnosticId",diagnosticId);
                JumpUtils.jump(mContext, DietListActivity.class, bundle4);
                break;
        }
    }


}
