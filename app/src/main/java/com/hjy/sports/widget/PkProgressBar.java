package com.hjy.sports.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hjy.sports.R;

/**
 * PK progress 控件
 * Created by fangs on 2018/1/26.
 */
public class PkProgressBar extends FrameLayout {

    View viewLeft;
    Space spaceLeft;

    View viewRight;
    Space spaceRight;

    AppCompatImageView iv_fail_win;

    TextView tvLeftDesc;
    TextView tvCenter;
    TextView tvRightDesc;


    public PkProgressBar(Context context) {
        this(context, null);
    }

    public PkProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pk_progress, this, true);

        viewLeft = view.findViewById(R.id.viewLeft);
        viewRight = view.findViewById(R.id.viewRight);
        spaceLeft = view.findViewById(R.id.spaceLeft);
        spaceRight = view.findViewById(R.id.spaceRight);
        iv_fail_win = view.findViewById(R.id.iv_fail_win);

        tvLeftDesc = view.findViewById(R.id.tvLeftDesc);
        tvCenter = view.findViewById(R.id.tvCenter);
        tvRightDesc = view.findViewById(R.id.tvRightDesc);
    }


    /**
     * 设置左边进度条的 比例
     * @param proportion
     */
    public void setLeftProgress(float proportion){
        ConstraintLayout.LayoutParams lpPro = (ConstraintLayout.LayoutParams) viewLeft.getLayoutParams();
        lpPro.horizontalWeight = proportion;
        viewLeft.setLayoutParams(lpPro);


        ConstraintLayout.LayoutParams lpSeat = (ConstraintLayout.LayoutParams) spaceLeft.getLayoutParams();
        lpSeat.horizontalWeight = 100 - proportion;
        spaceLeft.setLayoutParams(lpSeat);
    }

    /**
     * 设置右边进度条的 比例
     * @param proportion
     */
    public void setRightProgress(float proportion){
        ConstraintLayout.LayoutParams lpPro = (ConstraintLayout.LayoutParams) viewRight.getLayoutParams();
        lpPro.horizontalWeight = proportion;
        viewRight.setLayoutParams(lpPro);


        ConstraintLayout.LayoutParams lpSeat = (ConstraintLayout.LayoutParams) spaceRight.getLayoutParams();
        lpSeat.horizontalWeight = 100 - proportion;
        spaceRight.setLayoutParams(lpSeat);
    }


    //提供get方法 获取对应的控件
    public AppCompatImageView getIv_fail_win() {
        return iv_fail_win;
    }

    public TextView getTvLeftDesc() {
        return tvLeftDesc;
    }

    public TextView getTvCenter() {
        return tvCenter;
    }

    public TextView getTvRightDesc() {
        return tvRightDesc;
    }
}
