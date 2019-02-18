package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 每日食谱 Adapter
 * Created by Stefan on 2018/1/25.
 */

public class DailyRecipeAdapter extends RecyclerCommonAdapter<String> {
    DailyRecipeChildAdapter childAdapter;
    private List<String> dummyData;

    public DailyRecipeAdapter(Context context, List<String> datas) {
        super(context, R.layout.dailyrecipe_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, String t, int position) {

        ImageView image = holder.getView(R.id.image);
        if (position % 3 == 0) {
            image.setBackground(mContext.getResources().getDrawable(R.mipmap.xixi));
            holder.setText(R.id.tv_title,"优格水果燕麦");
        } else if (position % 3 == 1) {
            image.setBackground(mContext.getResources().getDrawable(R.mipmap.hehe));
            holder.setText(R.id.tv_title,"咖喱鸡饭");
        } else if (position % 3 == 2) {
            image.setBackground(mContext.getResources().getDrawable(R.mipmap.haha));
            holder.setText(R.id.tv_title,"牛肉芹菜水饺");
        }
        setDummyData();
//        RecyclerView recyclerView = holder.getView(R.id.mRecyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 6));
//        childAdapter = new DailyRecipeChildAdapter(mContext, dummyData);
//        recyclerView.setAdapter(childAdapter);

    }

    private void setDummyData() {
        dummyData = new ArrayList();
        dummyData.add("水果");
        dummyData.add("高蛋白");
        dummyData.add("早餐");

    }
}
