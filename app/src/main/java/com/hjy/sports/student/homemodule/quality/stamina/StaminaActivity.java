package com.hjy.sports.student.homemodule.quality.stamina;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.StaminaToAppBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 体能activity
 * Created by QKun on 2018/1/24.
 */
public class StaminaActivity extends BaseActivity {
    Integer[] personArray = {R.drawable.selector_stamina_person_one,
            R.drawable.selector_stamina_person_two,
            R.drawable.selector_stamina_person_three,
            R.drawable.selector_stamina_person_four,
            R.drawable.selector_stamina_person_five,
            R.drawable.selector_stamina_person_six,};

    @BindView(R.id.rvIcon)
    RecyclerView rvIcon;

    @BindView(R.id.rvStaminaItem)
    RecyclerView rvStaminaItem;

    @BindView(R.id.viewProgress)
    AppCompatImageView viewProgress;
    @BindView(R.id.spaceSeat)
    Space spaceSeat;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.llBg)
    LinearLayout llBg;
    StaminaItemAdapter itemAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_stamina;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setActTitle(R.string.tv_stamina);
        hideMenu();
        initrvItemRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStaminaToApp();
    }

    //网络请求 体能界面数据
    private void getStaminaToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        new RxNetCache.Builder().create()
                .request(mConnService.getStaminaToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<StaminaToAppBean>(progressDialog) {
                    @Override
                    protected void onSuccess(StaminaToAppBean staminaToAppBean) {
                        if (staminaToAppBean != null) {
                            int stamina = Integer.parseInt(staminaToAppBean.getStamina());
                            initPersonRv(stamina);

                            List<StaminaToAppBean.StaminalistBean> data = new ArrayList<>();
                            data.add(new StaminaToAppBean.StaminalistBean(1));
                            data.addAll(staminaToAppBean.getStaminalist());
//                            data.add(new StaminaToAppBean.StaminalistBean(2));

                            itemAdapter.setmDatas(data);

                            setStaminaData(stamina);
                            initVerticalLine(stamina);
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {
                    }
                });

    }

    private void setStaminaData(int stamina) {

        ConstraintLayout.LayoutParams lpPro = (ConstraintLayout.LayoutParams) viewProgress.getLayoutParams();
        lpPro.horizontalWeight = stamina;
        viewProgress.setLayoutParams(lpPro);

        ConstraintLayout.LayoutParams lpSeat = (ConstraintLayout.LayoutParams) spaceSeat.getLayoutParams();
        lpSeat.horizontalWeight = 100 - stamina;
        spaceSeat.setLayoutParams(lpSeat);

        tvScore.setText(stamina + "分");

        int color = 0;
        if (stamina >= 0 && stamina < 60){
            color = R.color.noPass;
        } else if (stamina >= 60 && stamina < 80){
            color = R.color.pass;
        } else if (stamina >= 80 && stamina < 90){
            color = R.color.fine;
        } else if (stamina >= 90 && stamina < 101){
            color = R.color.excellent;
        }

        Drawable drawable = TintUtils.getTintDrawable(R.drawable.shape_radian_bg_excellent, color);
        viewProgress.setImageDrawable(drawable);
    }

    //动态添加布局 生成对应的 垂直分割线
    private void initVerticalLine(int score) {
        llBg.removeAllViews();

        int len = personArray.length - 1;
        int checkNum = score / (100 / len);

        LinearLayout.LayoutParams paramsSpace = new LinearLayout.LayoutParams(0, -1);
        paramsSpace.weight = 0.5f;
        llBg.addView(new Space(mContext), paramsSpace);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, -1);
        params.weight = 1;
        for (int i = 0; i < len; i++) {
            CheckBox box = new CheckBox(mContext);
            box.setClickable(false);
            box.setGravity(Gravity.CENTER);
            box.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));// 通过透明度的方式隐藏掉Checkbox的默认图标
            box.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            box.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_stamina_vertical_line));

            if (i <= checkNum) box.setChecked(true);

            llBg.addView(box, params);
        }

        llBg.addView(new Space(mContext), paramsSpace);
    }

    private void initPersonRv(int score) {

        List<Integer> data = new ArrayList<>(personArray.length);
        Collections.addAll(data, personArray);
        rvIcon.setLayoutManager(new GridLayoutManager(mContext, 6) {
            @Override
            public boolean canScrollVertically() {
                return false;//重写此方法 禁止RV 滑动
            }
        });
        rvIcon.setAdapter(new RecyclerCommonAdapter<Integer>(mContext, R.layout.item_stamina_person, data) {
            @Override
            public void convert(ViewHolder holder, Integer t, int position) {
                CheckBox boxPerson = holder.getView(R.id.boxPerson);
                boxPerson.setClickable(false);
                TintUtils.setCompoundDrawable(boxPerson, t, 1);
                if (position + 1 <= score / (100 / 6)) {
                    boxPerson.setChecked(true);
                }
            }
        });
    }

    private void initrvItemRv() {
        List<StaminaToAppBean.StaminalistBean> data = new ArrayList<>();
        itemAdapter = new StaminaItemAdapter(mContext, data);
        rvStaminaItem.setLayoutManager(new LinearLayoutManager(mContext));
        rvStaminaItem.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvStaminaItem.setAdapter(itemAdapter);


    }
}
