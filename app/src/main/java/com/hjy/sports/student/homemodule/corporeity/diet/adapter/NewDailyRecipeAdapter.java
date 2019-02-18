package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.RecipesMotionToAppBean;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 每日食谱 Adapter
 * Created by Stefan on 2018/1/25.
 */

public class NewDailyRecipeAdapter extends RecyclerCommonAdapter<RecipesMotionToAppBean.RowsBean> {
    DailyRecipeChildAdapter childAdapter;
    private List<String> dummyData;

    public NewDailyRecipeAdapter(Context context, List<RecipesMotionToAppBean.RowsBean> datas) {
        super(context, R.layout.new_dailyrecipe_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, RecipesMotionToAppBean.RowsBean t, int position) {
        TextView tv_title=holder.getView(R.id.tv_title);
        TextView recipesContent=holder.getView(R.id.recipesContent);
        ImageView image = holder.getView(R.id.image);
        TextView tv_mark1=holder.getView(R.id.tv_mark1);
        TextView tv_mark2=holder.getView(R.id.tv_mark2);
        ImgLoadUtils.loadImage(mContext,t.getImage(),image);

        tv_title.setText(t.getRecipesName());
        recipesContent.setText(t.getRecipesContent());
        if(t.getMealType().equals("1")){
            tv_mark1.setText("早餐");
        }else if(t.getMealType().equals("2")){
            tv_mark1.setText("中餐");
        }else if(t.getMealType().equals("3")){
            tv_mark1.setText("午餐");
        }
        if(t.getRecipesType().equals("1")){
            tv_mark2.setText("水果");
        }else if(t.getRecipesType().equals("2")){
            tv_mark2.setText("肉类");
        }else if(t.getRecipesType().equals("3")){
            tv_mark2.setText("主食");
        }

//        if (position % 3 == 0) {
//            image.setBackground(mContext.getResources().getDrawable(R.drawable.xixi));
//        } else if (position % 3 == 1) {
//            image.setBackground(mContext.getResources().getDrawable(R.drawable.hehe));
//        } else if (position % 3 == 2) {
//            image.setBackground(mContext.getResources().getDrawable(R.drawable.haha));
//        }
        //setDummyData();

    }

    private void setDummyData() {
        dummyData = new ArrayList();
        dummyData.add("水果");
        dummyData.add("高蛋白");
        dummyData.add("早餐");

    }
}
