package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SelfDetectionAdd;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.util.TextStyleUtils;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by QKun on 2018/1/17.
 */

public class PKActivity extends BaseActivity {


    @BindView(R.id.iv_user_img)
    AppCompatImageView mIvUserImg;
    @BindView(R.id.tv_me)
    TextView mTvMe;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.iv_fail_win)
    AppCompatImageView mIvFailWin;
    private PkAdapter mPkAdapter;
    private SelfDetectionAdd mData;
    private BaseNiceDialog mShow;


    @Override
    protected int getContentView() {
        return R.layout.activity_pk;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvBack.setText("返回");
        tvTitle.setText("PK");
        tvMenu.setVisibility(View.GONE);

        String PortraitUrl = SpfUtils.getSpfSaveStr("touxiangurl");
        if (!TextUtils.isEmpty(PortraitUrl))
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + PortraitUrl, mIvUserImg);
//        if (savedInstanceState != null) {
//            mData = (SelfDetectionAdd) savedInstanceState.getSerializable("SelfDetectionAdd");
//        } else {
//            mData = (SelfDetectionAdd) getIntent().getExtras().getSerializable("data");
//        }
        mData = (SelfDetectionAdd) getIntent().getExtras().getSerializable("data");

        if (mData != null) {
            if (!TextUtils.isEmpty(mData.getResult()) && !TextUtils.isEmpty(mData.getResultmsg())) {
                String result = mData.getResult();
                String resultmsg = mData.getResultmsg();
                showPkDialog(result, resultmsg);
                if (result.equals("lose")) {
                    Glide.with(PKActivity.this).load(R.mipmap.icon_fail).into(mIvFailWin);
                } else {
                    Glide.with(PKActivity.this).load(R.mipmap.icon_win).into(mIvFailWin);
                }
            }
            initRecycler();
            getData(mData);
        }
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPkAdapter = new PkAdapter(R.layout.item_pk, new ArrayList<>());
        mRecycler.setAdapter(mPkAdapter);
    }

    public void getData(SelfDetectionAdd data) {
        List<PkBean> pkBeanList = new ArrayList<>();

        if (data.getItems() != null && data.getItems().size() != 0) {
            List<SelfDetectionAdd.ItemsBean> items = data.getItems();
            for (SelfDetectionAdd.ItemsBean item : items) {
                if (!TextUtils.isEmpty(item.getType()) && !TextUtils.isEmpty(item.getValue()))
                    pkBeanList.add(new PkBean(Float.parseFloat(item.getType()), Float.parseFloat(item.getValue()), item.getName()));
            }
            mPkAdapter.setNewData(pkBeanList);
        }


    }


    /**
     * 进入请求显示成功失败的结果
     */
    private void showPkDialog(String result, String resultmsg) {
        mShow = NiceDialog.init()
                .setLayoutId(R.layout.dialog_pk)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        ImageView iv_star_one = holder.getView(R.id.iv_star_one);
                        ImageView iv_star_two = holder.getView(R.id.iv_star_two);
                        ImageView iv_star_three = holder.getView(R.id.iv_star_three);
                        ImageView iv_star_fore = holder.getView(R.id.iv_star_fore);
                        ImageView iv_star_five = holder.getView(R.id.iv_star_five);

                        if (!TextUtils.isEmpty(result)) {
                            switch (result) {
                                case "1":
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_five);
                                    break;
                                case "2":
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_five);
                                    break;
                                case "3":
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_five);
                                    break;
                                case "4":
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_five);
                                    break;
                                case "5":
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk).into(iv_star_five);
                                    break;
                                default:
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_one);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_two);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_three);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_fore);
                                    Glide.with(mContext).load(R.mipmap.star_pk_gray).into(iv_star_five);
                                    break;
                            }

                        }
                        TextView tv_msg = holder.getView(R.id.tv_msg);
                        Typeface typeface = TextStyleUtils.getLeiMiao(mContext);
                        tv_msg.setText(resultmsg);
                        tv_msg.setTypeface(typeface);

                        holder.setOnClickListener(R.id.iv_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        holder.setOnClickListener(R.id.btn_exercise, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                T.showShort("练习方法");
                                dialog.dismiss();
                            }
                        });

                        holder.setOnClickListener(R.id.btn_equipment, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                T.showShort("器材推荐");
                                dialog.dismiss();
                            }
                        });

                        holder.setOnClickListener(R.id.btn_scheme, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                T.showShort("方案教学");
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setDimAmount(0.7f)
                .setOutCancel(false)
                .show(mContext.getSupportFragmentManager());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mShow.dismiss();
//        outState.putSerializable("SelfDetectionAdd", mData);
        super.onSaveInstanceState(outState);
    }

}