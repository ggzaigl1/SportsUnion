package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.content.Context;
import android.widget.TextView;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 每日食谱 ChildAdapter
 * Created by Stefan on 2018/1/25.
 */

public class DailyRecipeChildAdapter extends RecyclerCommonAdapter<String> {
    List<StringBuffer>child_data=new ArrayList<>();
    private StringBuffer sb;

    public DailyRecipeChildAdapter(Context context, List<String> datas) {
        super(context, R.layout.daily_recipe_child_item, datas);
        //child_data=datas;
    }

    @Override
    public void convert(ViewHolder holder, String t, int position) {
        TextView tv_mark=holder.getView(R.id.tv_mark);
        String s = t.toString();
        tv_mark.setText(s);
    }

//    private void into(String params){
//        sb = new StringBuffer();
//
//        boolean is = false;
//        int len = params.length();
//        for (int i = 0; i < len; i++){
//            String chart = String.valueOf(params.charAt(i));
//
//            if (chart.equals(",")){
//                is = true;
//                continue;
//            } else if (chart.equals(",")){
//                is = false;
//                break;
//            }
//            if (is){
//                sb.append(chart);
//            }
//            child_data.add(sb);
//        }
//
//        Log.e("aaa", sb.toString());
//    }

}
