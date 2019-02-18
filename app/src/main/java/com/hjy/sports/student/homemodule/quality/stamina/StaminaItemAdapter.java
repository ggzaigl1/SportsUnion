package com.hjy.sports.student.homemodule.quality.stamina;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fy.baselibrary.base.recyclerv.adapter.MultiItemCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.MultiItemTypeSupport;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.StaminaToAppBean;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * 体能 分项得分 item布局
 * Created by fangs on 2018/1/27.
 */
public class StaminaItemAdapter extends MultiItemCommonAdapter<StaminaToAppBean.StaminalistBean> {

    List<StaminaToAppBean.WeakBean> weak;

    public StaminaItemAdapter(Context context, List<StaminaToAppBean.StaminalistBean> datas) {
        super(context, datas, new MultiItemTypeSupport<StaminaToAppBean.StaminalistBean>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 0) {
                    return R.layout.item_stamina_scre;//主体布局
                } else if (itemType == 1){
                    return R.layout.item_stamina_scre;//头布局
                } else {
                    return R.layout.item_stamina_foot;//尾部 布局
                }
            }

            @Override
            public int getItemViewType(int position, StaminaToAppBean.StaminalistBean imageItem) {
                return imageItem.getItemType();
            }
        });
    }


    @Override
    public void convert(ViewHolder holder, StaminaToAppBean.StaminalistBean item, int position) {
        TextView tvItemTitle = holder.getView(R.id.tvItemTitle);
        TextView tvItemAppraise = holder.getView(R.id.tvItemAppraise);
        RatingBar rbarScre = holder.getView(R.id.rbarScre);

        if (position == 0) {//头
            tvItemTitle.setText("分项得分");
            tvItemTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//粗体
            tvItemTitle.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            TintUtils.setCompoundDrawable(tvItemTitle, R.mipmap.sensory_blue, 1);
            ResourceUtils.setTvColor(tvItemTitle, R.color.txtSuperColor);
            tvItemAppraise.setText("评价");
            tvItemAppraise.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//粗体
            ResourceUtils.setTvColor(tvItemAppraise, R.color.txtSecondColor);
            rbarScre.setVisibility(View.GONE);
        } else {
            tvItemTitle.setText(item.getName());
            rbarScre.setRating(Integer.parseInt(item.getType()));

            if ("优秀".equals(item.getValue())) {
                tvItemAppraise.setText("优秀");
                ResourceUtils.setTvColor(tvItemAppraise, R.color.excellent);
            } else if ("良好".equals(item.getValue())) {
                tvItemAppraise.setText("良好");
                ResourceUtils.setTvColor(tvItemAppraise, R.color.fine);
            } else if ("及格".equals(item.getValue())) {
                tvItemAppraise.setText("及格");
                ResourceUtils.setTvColor(tvItemAppraise, R.color.pass);
            } else if ("不及格".equals(item.getValue())) {
                tvItemAppraise.setText("不及格");
                ResourceUtils.setTvColor(tvItemAppraise, R.color.noPass);
            }
        }
    }

    //设置尾部 数据
    private void setFootData(ViewHolder holder, StaminaToAppBean.StaminalistBean item, int position){

        RecyclerView rvFoot = holder.getView(R.id.rvFoot);
        rvFoot.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvFoot.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;//重写此方法 禁止RV 滑动
            }
        });

        rvFoot.setAdapter(new RecyclerCommonAdapter<StaminaToAppBean.WeakBean>(mContext, R.layout.item_stamina_foot_item, weak) {
            @Override
            public void convert(ViewHolder holder, StaminaToAppBean.WeakBean item, int position) {
                if (position == 0) {
                    TextView tvHint = holder.getView(R.id.tvHint);
                    tvHint.setVisibility(View.VISIBLE);
                    Spannable sp = new SpannableString("温馨提示：体能较弱项目");
                    sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtHint)),
                            0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                    sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtSuperColor)),
                            5, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    tvHint.setText(sp);
                }

                holder.setText(R.id.tvHintTitle, item.getName());
                holder.setText(R.id.tvHintContent, item.getValue());

            }
        });
    }


    public void setWeak(List<StaminaToAppBean.WeakBean> weak) {
        this.weak = weak;
    }
}
