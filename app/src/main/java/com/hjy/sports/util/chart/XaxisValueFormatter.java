package com.hjy.sports.util.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * 图表库 x轴中显示字符串，自定义X轴format
 * Created by fangs on 2017/7/20.
 */
public class XaxisValueFormatter implements IAxisValueFormatter {

    private static final String TAG = "MyXFormatter";

    private List<String> mXValue;

    public XaxisValueFormatter(List<String> mXValue) {
        this.mXValue = mXValue;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (null != mXValue && mXValue.size() > 0) {
            String data = "";

            int i = (int) (value);
            if (i < 0){
                i = 0;
            } else if (i == mXValue.size()){
                i = mXValue.size() - 1;
            }else if (i >mXValue.size()){
                return "";
            }

            data = mXValue.get(i);

            return data;
        } else {
            return "";
        }
    }
}
