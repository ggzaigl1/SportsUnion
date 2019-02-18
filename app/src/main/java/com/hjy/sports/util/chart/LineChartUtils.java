package com.hjy.sports.util.chart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.hjy.sports.R;

import java.util.List;

/**
 * 图表库 之 折线图 工具类
 * Created by fangs on 2017/7/20.
 */
public class LineChartUtils {

    /**
     * 设置与图表交互
     *
     * @param lineChart
     */
    public static void setInteractive(Context mContext, LineChart lineChart) {

        MarkerView mv = new CustomMarkerView(mContext, R.layout.item_mptips);
        mv.setChartView(lineChart);//1
        lineChart.setMarker(mv); //2 加上1、2两句Marker点击时就可以正常的显示一般的向右 最右边时向左显示
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
        lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
//        Legend legend = lineChart.getLegend();
//        legend.setEnabled(false);

    }

    /**
     * 创建描述信息
     *
     * @param lineChart
     */
    public static void setDescription(LineChart lineChart) {
        Description description = new Description();
        description.setText("");
        description.setTextColor(Color.RED);
        description.setTextSize(20);

        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据喔~~");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
    }

    /**
     * 设置图例
     *
     * @param color 图例 文本颜色
     */
    public static void setLegent(LineChart lineChart, int color) {
        Legend l = lineChart.getLegend();//图例
        l.setEnabled(false);//是否显示 图例
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//设置图例的垂直对其方式
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//设置图例的水平对齐方式
        l.setTextSize(10f);//设置文字大小
        l.setTextColor(color);
        l.setForm(Legend.LegendForm.LINE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度
    }

    public static double getMax(double a, double b, double c) {
        return (a > b ? (a > c ? a : c) : (b > c ? b : c));
    }

    public static double getMin(double a, double b, double c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    /**
     * 设置Y 坐标轴 显示效果
     *
     * @param lineChart
     * @param maxYValue 设置警戒线 值
     */
    public static void setYaxis(LineChart lineChart, double maxYValue, double minYValue, double allavg, double schavg) {
        double max = getMax(maxYValue, allavg, schavg);
        double min = getMin(minYValue, allavg, schavg);
        /** Y轴默认显示左右两个轴线 */
        YAxis rightAxis = lineChart.getAxisRight();//获取右边的轴线
        rightAxis.setEnabled(false);//设置图表右边的y轴禁用

        YAxis axisLeft = lineChart.getAxisLeft();//获取左边的轴线
        axisLeft.setDrawZeroLine(true);//是否绘制0所在的网格线
        axisLeft.setTextColor(Color.BLACK); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        axisLeft.setStartAtZero(false);    //设置Y轴坐标是否从0开始


        axisLeft.setAxisMaximum((float) (max / 5 * 6)); //设置Y轴坐标最大为多少
        if (min > 0) {
            axisLeft.setAxisMinimum((float) (min / 5 * 3));   //设置Y轴坐标最小为多少
        } else {
            axisLeft.setAxisMinimum((float) (min / 5 * 6));   //设置Y轴坐标最小为多少
        }


        axisLeft.setSpaceTop(50);    //Y轴坐标距顶有多少距离，即留白
        axisLeft.setSpaceBottom(50f);    //Y轴坐标距底有多少距离，即留白
        axisLeft.setSpaceMin(10);
        //显示网格线虚线模式，"lineLength"控制短线条的长度，"spaceLength"控制两段线之间的间隔长度，"phase"控制开始的点。
        axisLeft.enableGridDashedLine(10f, 5f, 0f);
//        axisLeft.setLabelCount(7, true); //显示格数

//        //设置坐标轴最大值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMaximum(20f);
//        //重置已经设置的最大值，自动匹配最大值
//        axisLeft.resetAxisMaximum();
//        //设置坐标轴最小值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMinimum(5f);
//        //重置已经设置的最小值，自动匹配最小值
//        axisLeft.resetAxisMinimum();
//        //将图表中最高值的顶部间距（占总轴范围的百分比）与轴上的最高值相比较。
//        axisLeft.setSpaceMax(10);
//        //将图表中最低值的底部间距（占总轴范围的百分比）与轴上的最低值相比较。
//        axisLeft.setSpaceMin(10);
//        //设置标签个数以及是否精确（false为模糊，true为精确）
//        axisLeft.setLabelCount(20,false);
//        //如果设置为true，此轴将被反转，这意味着最高值将在底部，最低的顶部值。
//        axisLeft.setInverted(false);
//        //设置轴标签应绘制的位置。无论是inside_chart或outside_chart。
//        axisLeft.setPosition(OUTSIDE_CHART);
//        //如果设置为true那么下面方法设置最小间隔生效，默认为false
//        axisLeft.setGranularityEnabled(true);
//        //设置Y轴的值之间的最小间隔。这可以用来避免价值复制当放大到一个地步，小数设置轴不再数允许区分两轴线之间的值。
//        axisLeft.setGranularity(10f);

        //设置限制线 全国平均值
        LimitLine limitLine = new LimitLine((float) allavg);
        //设置限制线的宽
        limitLine.setLineWidth(1f);
        //设置限制线的颜色
        limitLine.setLineColor(Color.RED);
        limitLine.setTextSize(10f);
        //设置基线的位置
        if (allavg > schavg) {
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        } else {
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        }

        limitLine.setLabel("全国平均值:" + allavg);
        //设置限制线为虚线
        limitLine.enableDashedLine(10f, 10f, 0f);
        //左边Y轴添加限制线
        axisLeft.addLimitLine(limitLine);

        //设置限制线 学校平均值
        LimitLine limitLine2 = new LimitLine((float) schavg);
        //设置限制线的宽
        limitLine2.setLineWidth(1f);
        //设置限制线的颜色
        limitLine2.setLineColor(Color.RED);
        limitLine2.setTextSize(10f);
        //设置基线的位置
        if (allavg > schavg) {
            limitLine2.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        } else {
            limitLine2.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        }
        limitLine2.setLabel("学校平均值:" + schavg);
        //设置限制线为虚线
        limitLine2.enableDashedLine(10f, 10f, 0f);
        //左边Y轴添加限制线
        axisLeft.addLimitLine(limitLine2);

    }

    //体形 如果只有一条数据 显示警戒线
    public static void setYaxis_line(LineChart lineChart, double allavg, double schavg) {
        // double max = getMax(maxYValue, allavg, schavg);
        //double min = getMin(minYValue, allavg, schavg);
        /** Y轴默认显示左右两个轴线 */
        YAxis rightAxis = lineChart.getAxisRight();//获取右边的轴线
        rightAxis.setEnabled(false);//设置图表右边的y轴禁用

        YAxis axisLeft = lineChart.getAxisLeft();//获取左边的轴线
        axisLeft.setDrawZeroLine(true);//是否绘制0所在的网格线
        axisLeft.setTextColor(Color.BLACK); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        axisLeft.setStartAtZero(false);    //设置Y轴坐标是否从0开始


//        axisLeft.setAxisMaximum((float) (max / 5 * 6)); //设置Y轴坐标最大为多少
//        if (min > 0) {
//            axisLeft.setAxisMinimum((float) (min / 5 * 3));   //设置Y轴坐标最小为多少
//        } else {
//            axisLeft.setAxisMinimum((float) (min / 5 * 6));   //设置Y轴坐标最小为多少
//        }


        axisLeft.setSpaceTop(50);    //Y轴坐标距顶有多少距离，即留白
        axisLeft.setSpaceBottom(50f);    //Y轴坐标距底有多少距离，即留白
        axisLeft.setSpaceMin(10);
        //显示网格线虚线模式，"lineLength"控制短线条的长度，"spaceLength"控制两段线之间的间隔长度，"phase"控制开始的点。
        axisLeft.enableGridDashedLine(10f, 5f, 0f);
//        axisLeft.setLabelCount(7, true); //显示格数

//        //设置坐标轴最大值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMaximum(20f);
//        //重置已经设置的最大值，自动匹配最大值
//        axisLeft.resetAxisMaximum();
//        //设置坐标轴最小值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMinimum(5f);
//        //重置已经设置的最小值，自动匹配最小值
//        axisLeft.resetAxisMinimum();
//        //将图表中最高值的顶部间距（占总轴范围的百分比）与轴上的最高值相比较。
//        axisLeft.setSpaceMax(10);
//        //将图表中最低值的底部间距（占总轴范围的百分比）与轴上的最低值相比较。
//        axisLeft.setSpaceMin(10);
//        //设置标签个数以及是否精确（false为模糊，true为精确）
//        axisLeft.setLabelCount(20,false);
//        //如果设置为true，此轴将被反转，这意味着最高值将在底部，最低的顶部值。
//        axisLeft.setInverted(false);
//        //设置轴标签应绘制的位置。无论是inside_chart或outside_chart。
//        axisLeft.setPosition(OUTSIDE_CHART);
//        //如果设置为true那么下面方法设置最小间隔生效，默认为false
//        axisLeft.setGranularityEnabled(true);
//        //设置Y轴的值之间的最小间隔。这可以用来避免价值复制当放大到一个地步，小数设置轴不再数允许区分两轴线之间的值。
//        axisLeft.setGranularity(10f);

        //设置限制线 全国平均值
        LimitLine limitLine = new LimitLine((float) allavg);
        //设置限制线的宽
        limitLine.setLineWidth(1f);
        //设置限制线的颜色
        limitLine.setLineColor(Color.BLUE);
        limitLine.setTextSize(10f);
        //设置基线的位置
        if (allavg > schavg) {
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        } else {
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        }

        //  limitLine.setLabel("全国平均值:" + allavg);
        //设置限制线为虚线
        limitLine.enableDashedLine(10f, 10f, 0f);
        //左边Y轴添加限制线
        axisLeft.addLimitLine(limitLine);

        //设置限制线 学校平均值
        LimitLine limitLine2 = new LimitLine((float) schavg);
        //设置限制线的宽
        limitLine2.setLineWidth(1f);
        //设置限制线的颜色
        limitLine2.setLineColor(Color.RED);
        limitLine2.setTextSize(10f);
        //设置基线的位置
        if (allavg > schavg) {
            limitLine2.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        } else {
            limitLine2.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        }
        //  limitLine2.setLabel("学校平均值:" + schavg);
        //设置限制线为虚线
        limitLine2.enableDashedLine(10f, 10f, 0f);
        //左边Y轴添加限制线
        axisLeft.addLimitLine(limitLine2);

    }

    /**
     * 设置Y 坐标轴 显示效果
     *
     * @param lineChart
     */
    public static void setYaxis(LineChart lineChart) {
        /** Y轴默认显示左右两个轴线 */
        YAxis rightAxis = lineChart.getAxisRight();//获取右边的轴线
        rightAxis.setEnabled(false);//设置图表右边的y轴禁用

        YAxis axisLeft = lineChart.getAxisLeft();//获取左边的轴线
        axisLeft.setDrawZeroLine(true);//是否绘制0所在的网格线
        axisLeft.setTextColor(Color.BLACK); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        axisLeft.setStartAtZero(false);    //设置Y轴坐标是否从0开始


        axisLeft.setSpaceTop(50);    //Y轴坐标距顶有多少距离，即留白
        axisLeft.setSpaceBottom(50f);    //Y轴坐标距底有多少距离，即留白
        axisLeft.setSpaceMin(10);
        //显示网格线虚线模式，"lineLength"控制短线条的长度，"spaceLength"控制两段线之间的间隔长度，"phase"控制开始的点。
        axisLeft.enableGridDashedLine(10f, 5f, 0f);
//        axisLeft.setLabelCount(7, true); //显示格数

//        //设置坐标轴最大值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMaximum(20f);
//        //重置已经设置的最大值，自动匹配最大值
//        axisLeft.resetAxisMaximum();
//        //设置坐标轴最小值：如果设置那么轴不会根据传入数据自动设置
//        axisLeft.setAxisMinimum(5f);
//        //重置已经设置的最小值，自动匹配最小值
//        axisLeft.resetAxisMinimum();
//        //将图表中最高值的顶部间距（占总轴范围的百分比）与轴上的最高值相比较。
//        axisLeft.setSpaceMax(10);
//        //将图表中最低值的底部间距（占总轴范围的百分比）与轴上的最低值相比较。
//        axisLeft.setSpaceMin(10);
//        //设置标签个数以及是否精确（false为模糊，true为精确）
//        axisLeft.setLabelCount(20,false);
//        //如果设置为true，此轴将被反转，这意味着最高值将在底部，最低的顶部值。
//        axisLeft.setInverted(false);
//        //设置轴标签应绘制的位置。无论是inside_chart或outside_chart。
//        axisLeft.setPosition(OUTSIDE_CHART);
//        //如果设置为true那么下面方法设置最小间隔生效，默认为false
//        axisLeft.setGranularityEnabled(true);
//        //设置Y轴的值之间的最小间隔。这可以用来避免价值复制当放大到一个地步，小数设置轴不再数允许区分两轴线之间的值。
//        axisLeft.setGranularity(10f);

    }

    /**
     * 设置 X 坐标轴 显示效果
     *
     * @param lineChart
     * @param xAxisData 坐标轴 显示的文本 集合
     */
    public static void setXaxis(LineChart lineChart, List<String> xAxisData, float RotationAngle) {
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(Color.GRAY);    //字体的颜色
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.BLACK);//网格线颜色
        xAxis.setDrawGridLines(false); //是否显示网格线
        xAxis.enableGridDashedLine(10f, 5f, 0f);//设置竖线的显示样式为虚线
        int len = xAxisData.size();
//        if (len != 0) {
//            xAxis.setLabelCount(len < 10 ? len : 10);
//        }
        xAxis.setLabelCount(len, true);

//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(RotationAngle);//设置x轴标签的旋转角度

//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符

        //自定义 X 轴 显示内容 todo
        XaxisValueFormatter formatter = new XaxisValueFormatter(xAxisData);
        xAxis.setValueFormatter(formatter);
    }

    /**
     * 生成 一个连接线 LineDataSet
     *
     * @param values 坐标点对象数组  (Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点 )
     * @param name   生成的这条线的 图例名称
     * @param color  生成的这条线的 颜色、这条线图例前面的 圆点颜色、这条折线所有坐标点数字的颜色
     */
    public static LineDataSet setLineDataSet(List<Entry> values, String name, int color) {
        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1 = new LineDataSet(values, name);//参数1：数据源 参数2：图例名称

        set1.setCubicIntensity(0.2f);
//        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setDrawCircleHole(false);//设置曲线值的圆点是实心还是空心

        set1.setColor(color);  // 显示颜色
        set1.setCircleColor(color);  // 圆形的颜色
        set1.setValueTextColor(color);
        set1.setLineWidth(1f);//设置线宽
        set1.setCircleRadius(3f);//设置焦点圆心的大小
        set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
        set1.setHighlightLineWidth(1f);//设置点击交点后显示高亮线宽
        set1.setHighlightEnabled(true);//是否 不禁用点击高亮线
        set1.setHighLightColor(Color.TRANSPARENT);//设置点击交点后显示交高亮线的颜色
        set1.setValueTextSize(7f);//设置显示值的文字大小
        set1.setDrawValues(false); // 是否在点上绘制Value
//        set1.setDrawFilled(false);//设置禁用范围背景填充
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawFilled(true);
        return set1;
    }

    /**
     * 绘制 图表
     *
     * @param lineChart
     * @param dataSets
     */
    public static void canvasChart(LineChart lineChart, List<ILineDataSet> dataSets) {

        //创建LineData对象 属于LineChart折线图的数据集合
        LineData data = new LineData(dataSets);
        // 添加到图表中
        lineChart.setData(data);
        //添加动画
        lineChart.animateXY(800, 800);
        //绘制图表
        lineChart.postInvalidate();
    }
}
