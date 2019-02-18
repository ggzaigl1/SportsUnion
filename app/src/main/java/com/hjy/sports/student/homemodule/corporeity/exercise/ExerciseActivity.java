package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.PhysicalDiagnosisBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.hjy.sports.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 运动处方
 * Created by QKun on 2018/1/24.
 */

public class ExerciseActivity extends BaseActivity {

    @BindView(R.id.tv_diagnose)
    TextView tv_diagnose;
    @BindView(R.id.tv_physicalContent)
    TextView tv_physicalContent;
//    @BindView(R.id.tv_diagnosticPurpose)
//    TextView tv_diagnosticPurpose;
    @BindView(R.id.tv_diagnosticContent)
    TextView tv_diagnosticContent;
    private int mId;

    @Override
    protected int getContentView() {
        return R.layout.activity_exercise;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("运动处方");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStandardsToApp();
    }

    public void getStandardsToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        new RxNetCache.Builder().create()
                .request(mConnService.groupMultipleToApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<PhysicalDiagnosisBean>(progressDialog) {
                    @Override
                    protected void onSuccess(PhysicalDiagnosisBean bean) {
                        List<PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean> physicalDiagnosisEntity = bean.getPhysicalDiagnosisEntity();
                        if (physicalDiagnosisEntity != null && physicalDiagnosisEntity.size() != 0) {
                            for (int i = physicalDiagnosisEntity.size() - 1; i >= 0; i--) {
                                String diagnosticType = physicalDiagnosisEntity.get(i).getDiagnosticType();
                                if (!TextUtils.isEmpty(diagnosticType)) {
                                    if (Integer.parseInt(diagnosticType) == 1) { //运动诊断
                                        PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean physicalDiagnosisEntityBean = physicalDiagnosisEntity.get(i);
                                        bindData(physicalDiagnosisEntityBean);
                                        break;
                                    } else if (Integer.parseInt(diagnosticType) == 2) { //膳食诊断
                                        continue;
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });

    }

    private void bindData(PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean bean) {
        if (bean != null) {
            if (!TextUtils.isEmpty(bean.getPhysicalContent()))
                tv_physicalContent.setText(bean.getPhysicalContent());

            PhysicalDiagnosisBean.PhysicalDiagnosisEntityBean.DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity = bean.getDiagnosticPrescriptionEntity();
            if (diagnosticPrescriptionEntity != null) {
                //诊断表id
//                mDiagnosticId = diagnosticPrescriptionEntity.getDiagnosticId();
                mId = diagnosticPrescriptionEntity.getId();
                //类型
//                String diagnosticPurpose = diagnosticPrescriptionEntity.getDiagnosticPurpose();
//                if (!TextUtils.isEmpty(diagnosticPurpose)) {
//                    tv_diagnosticPurpose.setText("处方：" + diagnosticPurpose);
//                }

                if (!TextUtils.isEmpty(diagnosticPrescriptionEntity.getDiagnosticContent()))
                    tv_diagnosticContent.setText(diagnosticPrescriptionEntity.getDiagnosticContent());
            }

        }

    }

    @OnClick({R.id.iv_exercise_one, R.id.iv_exercise_two, R.id.exercise_tip, R.id.first_aid})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_exercise_one: //练习方法
                Bundle bundle = new Bundle();
                bundle.putInt("mDiagnosticId", mId);
                JumpUtils.jump(mContext, ExerciseOneActivity.class, bundle);
                break;
            case R.id.iv_exercise_two: //每周锻炼
                Bundle bundle1 = new Bundle();
                bundle1.putInt("mDiagnosticId", mId);
                JumpUtils.jump(mContext, ExerciseTwoActivity.class, bundle1);
                break;
            case R.id.exercise_tip: //运动小tip
                Snackbar.make(view, "运动小tip即将开启....", Snackbar.LENGTH_SHORT)
                        .setActionTextColor(ContextCompat.getColor(mContext, R.color.button_press))
                        .setAction("点击我试试", v -> T.showShort("程序员正在建设中!")).show();
                break;
            case R.id.first_aid: //急救小技巧
                JumpUtils.jump(mContext, ExerciseForeActivity.class, null);
                break;
            default:
                break;
        }
    }
}
