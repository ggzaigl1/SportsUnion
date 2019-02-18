package com.hjy.sports.student.homemodule.corporeity.diet.adapter;

import android.content.Context;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.EnergyDemandListBean;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Stefan on 2018/2/8.
 */

public class EnergyDemandListAdapter extends RecyclerCommonAdapter<EnergyDemandListBean.RowsBean>{
    public EnergyDemandListAdapter(Context context, List<EnergyDemandListBean.RowsBean> datas) {
        super(context, R.layout.energy_demand_list_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, EnergyDemandListBean.RowsBean t, int position) {
        holder.setText(R.id.tv_name,t.getName());
        holder.setText(R.id.tv_value,t.getValue());
    }
}
