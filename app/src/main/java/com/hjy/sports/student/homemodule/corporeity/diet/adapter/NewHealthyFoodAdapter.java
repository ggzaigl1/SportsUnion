package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.NewHeathyFoodBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 每日食谱 Adapter
 * Created by Stefan on 2018/1/25.
 */

public class NewHealthyFoodAdapter extends RecyclerCommonAdapter<NewHeathyFoodBean.RowsBean> {
    DailyRecipeChildAdapter childAdapter;
    private List<String> dummyData;

    public NewHealthyFoodAdapter(Context context, List<NewHeathyFoodBean.RowsBean> datas) {
        super(context, R.layout.new_healthy_food_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, NewHeathyFoodBean.RowsBean t, int position) {
        TextView tv_title=holder.getView(R.id.tv_title);
        TextView tv_mark1=holder.getView(R.id.tv_mark1);
        TextView tv_mark2=holder.getView(R.id.tv_mark2);
        ImageView image = holder.getView(R.id.image);
//        if (position % 3 == 0) {
//            image.setBackground(mContext.getResources().getDrawable(R.mipmap.hjl));
//            tv_title.setText("黑加仑");
//        } else if (position % 3 == 1) {
//            image.setBackground(mContext.getResources().getDrawable(R.mipmap.mht));
//            tv_title.setText("猕猴桃");
//        } else if (position % 3 == 2) {
//            image.setBackground(mContext.getResources().getDrawable(R.mipmap.z));
//            tv_title.setText("枣");
//        }
        if(!TextUtils.isEmpty(t.getName())){
            tv_title.setText(t.getName());
        }
        if(!TextUtils.isEmpty(t.getZhongshu())){
            tv_mark1.setText(t.getZhongshu());
        }
        if(!TextUtils.isEmpty(t.getYingyangwuzhi())){
            tv_mark2.setText(t.getYingyangwuzhi());
        }
        ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL+t.getImage(),image);

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
