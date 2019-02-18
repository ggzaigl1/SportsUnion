package com.hjy.sports.student.homemodule.quality.standard;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fy.baselibrary.base.recyclerv.adapter.MultiItemCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.MultiItemTypeSupport;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.StandardsBean;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
import com.fy.baselibrary.utils.T;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by Gab on 2018/2/6 0006.
 * 达标
 */

public class StandardAdapter extends MultiItemCommonAdapter<StandardsBean.ItemsBean> {

    List<StandardsBean.WeakBean> weak;

    public StandardAdapter(Context context, List<StandardsBean.ItemsBean> datas) {
        super(context, datas, new MultiItemTypeSupport<StandardsBean.ItemsBean>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 0) {
                    return R.layout.item_standar;//主体布局
                } else if (itemType == 1) {
                    return R.layout.standar_headview;//头布局
                } else {
                    return R.layout.item_stamina_foot;//尾部 布局
                }
            }

            @Override
            public int getItemViewType(int position, StandardsBean.ItemsBean imageItem) {
                return imageItem.getItemType();
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, StandardsBean.ItemsBean item, int position) {
        TextView tvItemTitle = holder.getView(R.id.tv_options);//项目名称
        TextView tvItemAppraise = holder.getView(R.id.tvSchoolNum);//分数
        View view_context = holder.getView(R.id.view_context);
        LinearLayout title_ll = holder.getView(R.id.title_ll);
        if (position == 0) {//头

        } else if (position == getItemCount() - 1) {//尾
            setFootData(holder, item, position);
            Button btHint = holder.getView(R.id.bt_hint);
            btHint.setVisibility(View.VISIBLE);
            btHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort("此功能开发中,敬请期待！");
//                    JumpUtils.jump((AppCompatActivity) mContext, LiftingSchemeActivity.class, null);
                }
            });
        } else {
            tvItemTitle.setText(item.getName());
            tvItemAppraise.setText(item.getValue());
            if (position == 1) {
                view_context.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(item.getValue())) {
                title_ll.setVisibility(View.GONE);
                view_context.setVisibility(View.GONE);
            }

            if (item.getValue().equals("优秀")) {
                tvItemAppraise.setTextColor(ContextCompat.getColor(mContext, R.color.excellent));
            } else if (item.getValue().equals("良好")) {
                tvItemAppraise.setTextColor(ContextCompat.getColor(mContext, R.color.fine));
            } else if (item.getValue().equals("及格")) {
                tvItemAppraise.setTextColor(ContextCompat.getColor(mContext, R.color.pass));
            } else if (item.getValue().equals("不及格")) {
                tvItemAppraise.setTextColor(ContextCompat.getColor(mContext, R.color.noPass));
            }

            if (item.getName().equals("达标等级")) {
                Glide.with(mContext).load(R.mipmap.icons_level).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("达标成绩")) {
                Glide.with(mContext).load(R.mipmap.icons_standards).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("跳绳")) {
                Glide.with(mContext).load(R.mipmap.icons_rope).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("肺活量")) {
                Glide.with(mContext).load(R.mipmap.icons_pulmonary).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("50米")) {
                Glide.with(mContext).load(R.mipmap.icons_meters_50).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("坐位体前屈")) {
                Glide.with(mContext).load(R.mipmap.icons_sit).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("仰卧起坐")) {
                Glide.with(mContext).load(R.mipmap.icons_sit_up).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("往返跑")) {
                Glide.with(mContext).load(R.mipmap.icons_gobackrun).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("立定跳远")) {
                Glide.with(mContext).load(R.mipmap.icons_standing).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("引体向上")) {
                Glide.with(mContext).load(R.mipmap.icons_pull_up).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("1000米")) {
                Glide.with(mContext).load(R.mipmap.icons_meters_1000).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("800米")) {
                Glide.with(mContext).load(R.mipmap.icons_meters_800).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("BMI")) {
                Glide.with(mContext).load(R.mipmap.icons_bmi).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("达标趋势")) {
                Glide.with(mContext).load(R.mipmap.icons_tendency).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("长板")) {
                Glide.with(mContext).load(R.mipmap.icons_forte).into((ImageView) holder.getView(R.id.iv_standards));
            }
            if (item.getName().equals("短板")) {
                Glide.with(mContext).load(R.mipmap.icons_demerit).into((ImageView) holder.getView(R.id.iv_standards));
            }
        }
    }

    //设置尾部 数据
    private void setFootData(ViewHolder holder, StandardsBean.ItemsBean item, int position) {

        RecyclerView rvFoot = holder.getView(R.id.rvFoot);
        rvFoot.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvFoot.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;//重写此方法 禁止RV 滑动
            }
        });

        rvFoot.setAdapter(new RecyclerCommonAdapter<StandardsBean.WeakBean>(mContext, R.layout.item_stamina_foot_item, weak) {
            @Override
            public void convert(ViewHolder holder, StandardsBean.WeakBean item, int position) {
                if (position == 0) {
                    TextView tvHint = holder.getView(R.id.tvHint);
//                    Button btHint = holder.getView(R.id.bt_hint);
                    tvHint.setVisibility(View.VISIBLE);
//                    btHint.setVisibility(View.VISIBLE);
//                    btHint.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            JumpUtils.jump((AppCompatActivity) mContext, LiftingSchemeActivity.class, null);
//                        }
//                    });
                    Spannable sp = new SpannableString("温馨提示：体能较弱项目");
                    sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtHint)), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                    sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtSuperColor)), 5, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    tvHint.setText(sp);
                }

                holder.setText(R.id.tvHintTitle, item.getName());
                holder.setText(R.id.tvHintContent, item.getValue());

            }
        });
    }


    public void setWeak(List<StandardsBean.WeakBean> weak) {
        this.weak = weak;
    }

}
