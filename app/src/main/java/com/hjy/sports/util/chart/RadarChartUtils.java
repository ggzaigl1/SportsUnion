package com.hjy.sports.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QKun on 2017/12/14.
 */

public class RadarChartUtils {


    private static Typeface mTfLight;

    public static void initRadarChart(Context mContext, RadarChart mChart, List<RadarEntry> mEntries, List<String> mParties) {
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");

        mChart.setBackgroundColor(mContext.getResources().getColor(R.color.white)); //背景颜色

        mChart.getDescription().setEnabled(false); //描述

        mChart.setWebLineWidth(1f);  // 绘制线条宽度，圆形向外辐射的线条
        mChart.setWebColor(Color.rgb(220, 220, 220));
        mChart.setWebLineWidthInner(1f);  // 内部线条宽度，外面的环状线条
        mChart.setWebColorInner(Color.rgb(220, 220, 220));
        mChart.setWebAlpha(100); // 所有线条WebLine透明度

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(mContext, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        setAnimtor(mChart, mParties);
        setRadarData(mChart, mEntries);
    }

    private static void setAnimtor(RadarChart mChart, List<String> mParties) {
        int size = mParties.size();
        String[] array = mParties.toArray(new String[size]);
        //XY动画
        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return array[(int) value % array.length];
            }
        });
        xAxis.setTextColor(Color.rgb(153, 153, 153));

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(6, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setEnabled(false);

    }

    private static void setRadarData(RadarChart mChart, List<RadarEntry> mEntries) {
        RadarDataSet set1 = new RadarDataSet(mEntries, "Last Week");
//        set1.setColor(Color.rgb(103, 110, 129)); //边框的颜色
        set1.setFillColor(Color.rgb(144, 205, 252)); //数据连成线包裹的颜色
        set1.setDrawFilled(true);
        set1.setFillAlpha(100);
        set1.setLineWidth(1f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        RadarData data = new RadarData(sets);
//        data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();
    }
}
