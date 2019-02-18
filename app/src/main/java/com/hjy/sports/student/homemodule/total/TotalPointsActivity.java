package com.hjy.sports.student.homemodule.total;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.TotalCountBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarEntry;
import com.hjy.sports.R;
import com.hjy.sports.util.TextStyleUtils;
import com.hjy.sports.util.chart.RadarChartUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 总分
 * Created by QKun on 2018/1/24.
 */

public class TotalPointsActivity extends BaseActivity {
    @BindView(R.id.tv_type)
    TextView mTvType;
    //    @BindView(R.id.ll_expand)
//    LinearLayout mLlExpand;
//    @BindView(R.id.tab_layout)
//    TableLayout mTabLayout;
//    @BindView(R.id.tv_expand)
//    TextView mTvExpand;
    @BindView(R.id.radarChart)
    RadarChart mRadarChart;
    @BindView(R.id.tv_nationwide)
    TextView mTvNationwide;
    @BindView(R.id.tv_nationwide_rank)
    TextView mTvNationwideRank;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_city_rank)
    TextView mTvCityRank;
    @BindView(R.id.tv_grade)
    TextView mTvGrade;
    @BindView(R.id.tv_grade_rank)
    TextView mTvGradeRank;
    @BindView(R.id.tv_class)
    TextView mTvClass;
    @BindView(R.id.tv_class_rank)
    TextView mTvClassRank;
    //判断是否展开
    private boolean isExpand;

    @Override
    protected int getContentView() {
        return R.layout.activity_total_points;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActTitle(R.string.data3);
        tvBack.setText(R.string.back);
        tvMenu.setVisibility(View.GONE);
        getTotalCountToApp();
    }

//    @OnClick({R.id.ll_expand})
//    @Override
//    public void onClick(View view) {
//        super.onClick(view);
//        switch (view.getId()) {
//            case R.id.ll_expand:
//                if (isExpand) {
//                    mTabLayout.setVisibility(View.GONE);
//                    mTvExpand.setText(getString(R.string.no_expand));
//                    mTvExpand.setBackground(getResources().getDrawable(R.mipmap.pull_down_gray_bg));
//                    mLlExpand.setBackgroundColor(getResources().getColor(R.color.white));
//                    isExpand = false;
//                } else {
//                    mTabLayout.setVisibility(View.VISIBLE);
//                    mTvExpand.setText(getString(R.string.is_expand));
//                    mTvExpand.setBackground(getResources().getDrawable(R.mipmap.pull_down_white_bg));
//                    mLlExpand.setBackgroundColor(getResources().getColor(R.color.BgColor));
//                    isExpand = true;
//                }
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getTotalCountToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentId);
        new RxNetCache.Builder().create()
                .request(mConnService.getTotalCountToApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<TotalCountBean>(progressDialog) {
                    @Override
                    protected void onSuccess(TotalCountBean bean) {
                        if (null != bean) {
                            if (null != bean.getAppraise()) {

                                TotalCountBean.AppraiseBean appraise = bean.getAppraise();
                                if (!TextUtils.isEmpty(appraise.getName()) && !TextUtils.isEmpty(appraise.getValue())) {
                                    //类型
                                    String name = appraise.getName();
                                    int nameLength = name.length();
                                    //建议
                                    String value = appraise.getValue();
                                    String StrAppraise = "你是" + name + "，" + value;

                                    SpannableString spannableString = new SpannableString(StrAppraise);
                                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#fea939")), 2, 2 + nameLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    mTvType.setText(spannableString);
                                    Typeface leiMiao = TextStyleUtils.getLeiMiao(mContext);
                                    mTvType.setTypeface(leiMiao);
                                }
                            }

                            //雷达图
                            List<TotalCountBean.SzentirylistBean> szentirylist = bean.getSzentirylist();
                            //value
                            ArrayList<RadarEntry> entries = new ArrayList<>();
                            //key
                            ArrayList<String> nameList = new ArrayList<>();
                            if (szentirylist != null && szentirylist.size() != 0) {
                                for (TotalCountBean.SzentirylistBean szentirylistBean : szentirylist) {
                                    if (!TextUtils.isEmpty(szentirylistBean.getValue())) {
                                        entries.add(new RadarEntry(Float.parseFloat(szentirylistBean.getValue())));
                                        nameList.add(szentirylistBean.getName() + szentirylistBean.getValue());
                                    }

                                }
                                RadarChartUtils.initRadarChart(mContext, mRadarChart, entries, nameList);

                            }

                            //打败全国百分比
                            if (!TextUtils.isEmpty(bean.getAllRanking())) {
                                String allRanking = bean.getAllRanking();
                                int index = allRanking.length();
                                String allStr = "你已经击败全国" + allRanking + "%的同龄人";
                                SpannableString spannableString = new SpannableString(allStr);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#61b8fb")), 5, 7 + index + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvNationwide.setText(spannableString);
                            }
                            //全国排名
                            if (!TextUtils.isEmpty(bean.getAllCount())) {
                                String allCount = bean.getAllCount();
                                int length = allCount.length();
                                String allStrcount = "排名第" + allCount;
                                SpannableString spannableString = new SpannableString(allStrcount);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7eaa")), 3, 3 + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvNationwideRank.setText(spannableString);

                            }

                            //打败全市百分比
                            if (!TextUtils.isEmpty(bean.getTrialplotRanking())) {
                                String trialplotRanking = bean.getTrialplotRanking();
                                int length = trialplotRanking.length();
                                String triStr = "你已经击败全市" + trialplotRanking + "%的同龄人";
                                SpannableString spannableString = new SpannableString(triStr);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#61b8fb")), 5, 7 + length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvCity.setText(spannableString);
                            }

                            //全市排名
                            if (!TextUtils.isEmpty(bean.getTrialplotCount())) {
                                String trialplotCount = bean.getTrialplotCount();
                                int length = trialplotCount.length();
                                String triStrcount = "排名第" + trialplotCount;
                                SpannableString spannableString = new SpannableString(triStrcount);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7eaa")), 3, 3 + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvCityRank.setText(spannableString);
                            }

                            //打败全年级百分比
                            if (!TextUtils.isEmpty(bean.getGradeRanking())) {
                                String gradeRanking = bean.getGradeRanking();
                                int length = gradeRanking.length();
                                String gradeStr = "你已经击败全年级" + gradeRanking + "%的同龄人";
                                SpannableString spannableString = new SpannableString(gradeStr);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#61b8fb")), 5, 8 + length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvGrade.setText(spannableString);
                            }

                            //全年级排名
                            if (!TextUtils.isEmpty(bean.getGradeCount())) {
                                String gradeCount = bean.getGradeCount();
                                int length = gradeCount.length();
                                String gradeStrcount = "排名第" + gradeCount;
                                SpannableString spannableString = new SpannableString(gradeStrcount);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7eaa")), 3, 3 + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvGradeRank.setText(spannableString);
                            }

                            //打败全班百分比
                            if (!TextUtils.isEmpty(bean.getClassRanking())) {
                                String classRanking = bean.getClassRanking();
                                int length = classRanking.length();
                                String classRankingStr = "你已经击败全班" + classRanking + "%的同龄人";
                                SpannableString spannableString = new SpannableString(classRankingStr);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#61b8fb")), 5, 7 + length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvClass.setText(spannableString);
                            }

                            //全班排名
                            if (!TextUtils.isEmpty(bean.getClassCount())) {
                                String classCount = bean.getClassCount();
                                int length = classCount.length();
                                String classCountStr = "排名第" + classCount;
                                SpannableString spannableString = new SpannableString(classCountStr);
                                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7eaa")), 3, 3 + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                mTvClassRank.setText(spannableString);

                            }

                        }
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }
}
