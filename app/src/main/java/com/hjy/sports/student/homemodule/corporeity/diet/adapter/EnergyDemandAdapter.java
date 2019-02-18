package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Stefan on 2018/2/8.
 */

public class EnergyDemandAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public EnergyDemandAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getPosition();
        helper.setText(R.id.text_name,item);
        if(position==0){
            helper.setBackgroundRes(R.id.image,R.mipmap.image_one);
        }else if(position==1){
            helper.setBackgroundRes(R.id.image,R.mipmap.image_two);
        }else if(position==2){
            helper.setBackgroundRes(R.id.image,R.mipmap.image_three);
        }else if(position==3){
            helper.setBackgroundRes(R.id.image,R.mipmap.image_four);
        }
    }

}
