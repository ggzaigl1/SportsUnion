package com.hjy.sports.student.homemodule.quality.figure;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.BodyConToAppBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.hjy.sports.R;
import com.hjy.sports.util.chart.LineChartUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 体形activity
 * Created by QKun on 2018/1/24.
 */

public class FigureActivity extends BaseActivity {
    @BindView(R.id.line_chart)
    LineChart mLineChart;

    @BindView(R.id.body_analysis)
    TextView body_analysis;
//    @BindView(R.id.image_on)
//    ImageView image_on;
//    @BindView(R.id.jieshao)
//    TextView jieshao;
//    @BindView(R.id.image_down)
//    ImageView image_down;
    @BindView(R.id.Su_knowledge)
    TextView Su_knowledge;
    @BindView(R.id.image_1)
    TextView image_1;
    @BindView(R.id.image_2)
    TextView image_2;
    @BindView(R.id.image_3)
    TextView image_3;
    @BindView(R.id.image_4)
    TextView image_4;

    @BindView(R.id.tv_pingjia)
    TextView tv_pingjia;

    @BindView(R.id.rv_BCdata)
    RecyclerView rv_BCdata;


    @Override
    protected int getContentView() {
        return R.layout.activity_figure;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("体形");
        LineChartUtils.setDescription(mLineChart);
        //getGroupMultiToApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBodyConToApp();
    }

    //网络请求 体型界面数据
    private void getBodyConToApp() {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        mConnService.getBodyConToApp(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<BodyConToAppBean>() {
                    @Override
                    protected void onSuccess(BodyConToAppBean t) {
                        if(t!=null){
                            setBodyConData(t);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

    //设置界面数据
    private void setBodyConData(BodyConToAppBean bodyConToAppBean) {
        String pingjia = "";
        String pingjia2 = "";
        List<BodyConToAppBean.TendencyBean> tendency = bodyConToAppBean.getTendency();
        if (!TextUtils.isEmpty(bodyConToAppBean.getPingjia()) && !TextUtils.isEmpty(bodyConToAppBean.getPingjia())&&tv_pingjia!=null) {
            pingjia = bodyConToAppBean.getPingjia();
            pingjia2 = bodyConToAppBean.getPingjia2();
            tv_pingjia.setText(pingjia+": "+pingjia2);
            if (pingjia.equals("低体重型")) {
                image_1.setBackground(getResources().getDrawable(R.mipmap.thin_y));
            } else if (pingjia.equals("标准型")) {
                image_2.setBackground(getResources().getDrawable(R.mipmap.normal_y));
            } else if (pingjia.equals("超重型")) {
                image_3.setBackground(getResources().getDrawable(R.mipmap.littlefat_y));
            } else if (pingjia.equals("肥胖型")) {
                image_4.setBackground(getResources().getDrawable(R.mipmap.overweight_y));
            }
        }
        if (!TextUtils.isEmpty(bodyConToAppBean.getFenxi())&&body_analysis!=null) {
            body_analysis.setText(bodyConToAppBean.getFenxi());
        }
        setLineChart(tendency);

        setGroupMultiToAppData(bodyConToAppBean);
    }

    private void setLineChart(List<BodyConToAppBean.TendencyBean> tendency) {
        List<ILineDataSet> dataSets2 = new ArrayList<>();
        // y轴 数据数据源
        List<Entry> yDataList = new ArrayList<>();
        List<String> xDataList = new ArrayList<>();
        for (int i = 0; i < tendency.size(); i++) {
            if (!TextUtils.isEmpty(tendency.get(i).getValue())) {
                yDataList.add(new Entry(i, Float.parseFloat(tendency.get(i).getValue())));
                xDataList.add(tendency.get(i).getName());
            }
        }
        if (yDataList.size() == 1) {
            LineDataSet set2 = LineChartUtils.setLineDataSet(yDataList, "", getResources().getColor(R.color.login));
            dataSets2.add(set2);
            LineChartUtils.setInteractive(mContext, mLineChart);
            LineChartUtils.setYaxis_line(mLineChart, yDataList.get(0).getY(), yDataList.get(0).getY());
            LineChartUtils.setXaxis(mLineChart, xDataList, 0f);

            LineChartUtils.canvasChart(mLineChart, dataSets2);
        } else {
            LineDataSet set2 = LineChartUtils.setLineDataSet(yDataList, "", getResources().getColor(R.color.login));
            dataSets2.add(set2);
            LineChartUtils.setInteractive(mContext, mLineChart);
            LineChartUtils.setYaxis(mLineChart);
            LineChartUtils.setXaxis(mLineChart, xDataList, 0f);

            LineChartUtils.canvasChart(mLineChart, dataSets2);
        }
    }

    private void setGroupMultiToAppData(BodyConToAppBean bodyConToAppBean) {
        List<BodyConToAppBean.BuchongBean> buchong = bodyConToAppBean.getBuchong();
        if(buchong!=null){
            rv_BCdata.setLayoutManager(new LinearLayoutManager(this));
            rv_BCdata.setAdapter(new RecyclerCommonAdapter<BodyConToAppBean.BuchongBean>(mContext, R.layout.bc_data_item, buchong) {
                @Override
                public void convert(ViewHolder holder, BodyConToAppBean.BuchongBean multiBean, int position) {
                    ImageView image_on = holder.getView(R.id.image_on);
                    TextView jieshao = holder.getView(R.id.jieshao);
                    ImgLoadUtils.loadCircularBead(mContext, multiBean.getImage(), image_on, 8);
                    if(!TextUtils.isEmpty(multiBean.getSupplementaryContent())){
                        jieshao.setText(multiBean.getSupplementaryContent());
                    }
                }
            });
        }else {
            Su_knowledge.setVisibility(View.GONE);
        }
    }

}
