package com.hjy.sports.student.datamodule.stature;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.EachHealthInfoBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.hjy.sports.R;
import com.hjy.sports.student.bean.StatureBean;
import com.hjy.sports.util.chart.LineChartUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by QKun on 2017/12/18.
 * 身高等11项activity
 */
public class StatureActivity extends BaseActivity {

    @BindView(R.id.line_chart)
    LineChart mLineChart;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private String mTitle;
    private StaturAdapter mStaturAdapter;
    private String item;

//    String[] options = new String[]{"身高", "体重", "总分", "BMI", "肺活量", "50米", "坐位体前屈", "跳绳", "仰卧起坐", "往返跑", "立定跳远"};

    @Override
    protected int getContentView() {
        return R.layout.activity_stature;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mTitle = getIntent().getExtras().getString("title");
        if (!mTitle.isEmpty()) {
            tvTitle.setText(mTitle);
            if (mTitle.equals("跳绳")) {
                tvTitle.setText("一分钟跳绳");
            }
            switch (mTitle) {
                case "身高":
                    item = "height";
                    break;
                case "体重":
                    item = "weight";
                    break;
                case "总分":
                    item = "zongtidf";
                    break;
                case "BMI":
                    item = "bmidf";
                    break;
                case "肺活量":
                    item = "feihuoliang";
                    break;
                case "50米":
                    item = "wushimi";
                    break;
                case "坐位体前屈":
                    item = "zuotiweiqianqu";
                    break;
                case "跳绳":
                    item = "tiaosheng";
                    break;
                case "仰卧起坐":
                    item = "yangwoqizuo";
                    break;
                case "往返跑":
                    item = "wushimibawangfan";
                    break;
                case "立定跳远":
                    item = "lidingtiaoyuan";
                    break;

            }

        }

        tvMenu.setVisibility(View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStaturAdapter = new StaturAdapter(R.layout.item_statur, new ArrayList<>());
        mRecyclerView.setAdapter(mStaturAdapter);

        LineChartUtils.setDescription(mLineChart);
//        getItemScToApp(); //请求各项体质的数据
    }

//    public void getItemScToApp() {
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
//
//        int id = SpfUtils.getSpfSaveInt("studentId");
//        Map<String, Object> param = new HashMap<>();
//        param.put("token", ConstantUtils.token);
//        param.put("studentid", id);
//        param.put("item", item);
//        new RxNetCache.Builder()
//                .create()
//                .request(mConnService.getItemScToApp(param).compose(RxHelper.handleResult()))
//                .subscribe(new NetCallBack<EachHealthInfoBean>(progressDialog) {
//                    @Override
//                    protected void onSuccess(EachHealthInfoBean infoBeans) {
//                        if (infoBeans != null) {
//                            routeItem(infoBeans);
//                        }
//
//                    }
//
//                    @Override
//                    public void updataLayout(int flag) {
//                    }
//                });
//    }


    private List<ILineDataSet> routeItem(EachHealthInfoBean infoBeans) {
        List<Float> values = new ArrayList<>();
        //对象集合
        List<StatureBean> statureBeans = new ArrayList<>();
        // y轴 数据数据源
        List<Entry> yDataList = new ArrayList<>();
        //x轴 年级数据源
        List<String> xDatas = new ArrayList<>();
        //全国平均值
        double allavg = infoBeans.getAllavg();
        //学校平均值
        double schavg = infoBeans.getSchavg();

        if (infoBeans.getMeasurements() != null && infoBeans.getMeasurements().size() != 0) {
            List<EachHealthInfoBean.MeasurementsBean> measurements = infoBeans.getMeasurements();
            for (int i = 0; i < measurements.size(); i++) {
                //获取年级
                xDatas.add(measurements.get(i).getNname());

                switch (mTitle) {
                    case "身高":
                        if (TextUtils.isEmpty(measurements.get(i).getHeight())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getHeight())));
                        }
                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getHeightpd()));
                        break;
                    case "体重":
                        if (TextUtils.isEmpty(measurements.get(i).getWeight())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getWeight())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getWeightpd()));
                        break;
                    case "总分":
                        if (TextUtils.isEmpty(measurements.get(i).getZongtidf())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getZongtidf())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getZongtipd()));
                        break;
                    case "BMI":

                        if (TextUtils.isEmpty(measurements.get(i).getBmi())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getBmi())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getBmipd()));
                        break;
                    case "肺活量":
                        if (TextUtils.isEmpty(measurements.get(i).getFeihuoliang())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getFeihuoliang())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getFeihuoliangpd()));
                        break;
                    case "50米":
                        if (TextUtils.isEmpty(measurements.get(i).getWushimi())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getWushimi())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getWushimipd()));
                        break;
                    case "坐位体前屈":
                        if (TextUtils.isEmpty(measurements.get(i).getZuotiweiqianqu())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getZuotiweiqianqu())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getZuotiweiqianqupd()));
                        break;
                    case "跳绳":
                        if (TextUtils.isEmpty(measurements.get(i).getTiaosheng())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getTiaosheng())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getTiaoshengpd()));
                        break;
                    case "仰卧起坐":
                        if (TextUtils.isEmpty(measurements.get(i).getYangwoqizuo())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getYangwoqizuo())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getYangwoqizuopd()));
                        break;
                    case "往返跑":
                        if (TextUtils.isEmpty(measurements.get(i).getWushimibawangfan())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getWushimibawangfan())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getWushimibawangfanpd()));
                        break;
                    case "立定跳远":
                        if (TextUtils.isEmpty(measurements.get(i).getLidingtiaoyuan())) {
                            yDataList.add(new Entry(i, 0));
                        } else {
                            yDataList.add(new Entry(i, Float.parseFloat(measurements.get(i).getLidingtiaoyuan())));
                        }

                        statureBeans.add(new StatureBean(measurements.get(i).getNname(),
                                measurements.get(i).getLidingtiaoyuanpd()));
                        break;
                }
            }

            mStaturAdapter.addData(statureBeans);

            //判断是否全部为0
            boolean isZone = false;
            for (Entry entry : yDataList) {
                if (entry.getY() != 0f) {
                    isZone = true;
                    break;
                }
            }
            for (Entry entry : yDataList) {
                //获取y轴数据，
                values.add(entry.getY());
            }

            //获取y轴数据的最大值
            Float max = Collections.max(values);
            //获取y轴数据的最小值
            Float min = Collections.min(values);

            List<ILineDataSet> dataSets2 = new ArrayList<>();
            if (isZone) {
                LineDataSet set2 = LineChartUtils.setLineDataSet(yDataList, "",
                        getResources().getColor(R.color.login));


                dataSets2.add(set2);

                LineChartUtils.setInteractive(mContext, mLineChart);
                LineChartUtils.setYaxis(mLineChart, max, min, allavg, schavg);
                LineChartUtils.setXaxis(mLineChart, xDatas, 0f);

                LineChartUtils.canvasChart(mLineChart, dataSets2);

            } else {
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
            return dataSets2;
        } else {
            return new ArrayList<>();
        }

    }
}

