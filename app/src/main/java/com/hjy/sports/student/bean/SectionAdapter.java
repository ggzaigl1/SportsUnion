package com.hjy.sports.student.bean;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * Created by QKun on 2017/12/15.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     *
     */

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.section_header_name, item.header);
        helper.addOnClickListener(R.id.iv_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        ImageBean t = item.t;

        TextView view = helper.getView(R.id.sport_name);
        view.setText(t.getName());

        TintUtils.setCompoundDrawable(view, t.getDrawable(), 2);
//        Glide.with(mContext).load(t.getDrawable()).into((ImageView) helper.getView(R.id.iv_sport));

    }
}
