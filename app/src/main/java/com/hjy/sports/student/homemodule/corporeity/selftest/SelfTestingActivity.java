package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ViewUtils;
import com.hjy.sports.R;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自我测评
 * Created by QKun on 2018/1/24.
 */
public class SelfTestingActivity extends BaseActivity {

    @BindView(R.id.rgSelf)
    RadioGroup rgSelf;
    @BindView(R.id.rbSelfTest)
    RadioButton rbSelfTest;
    @BindView(R.id.rbSelfChallenge)
    RadioButton rbSelfChallenge;
    @BindView(R.id.tabL)
    TabLayout tabL;
    @BindView(R.id.vpContent)
    ViewPager vpContent;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_selftest;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActMenu(R.string.record);
        setActTitle(R.string.selfEvaluation);
        initContent();
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("");
        tabIndicators.add("");

        tabFragments = new ArrayList<>();
        tabFragments.add(new SelfDetectionFragment());
        tabFragments.add(new SelfChallengeFragment());

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(contentAdapter);
        tabL.setupWithViewPager(vpContent);

        //修改 tablayout 下划线长度
        tabL.post(() -> ViewUtils.setIndicator(tabL, 80, 80));
        //监听RadioButton 点击事件
        rgSelf.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbSelfTest:
                    vpContent.setCurrentItem(0);
                    break;
                case R.id.rbSelfChallenge:
                    vpContent.setCurrentItem(1);
                    break;
            }
        });
        //添加viewPager 滑动事件监听
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {//每次滑动结束后 更新 radiobutton 的状态（是否选中）
                switch (position) {
                    case 0:
                        rbSelfTest.setChecked(true);
                        break;
                    case 1:
                        rbSelfChallenge.setChecked(true);
                        break;
                }
                vpContent.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    //套路 不解释
    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMenu:
                JumpUtils.jump(mContext, SelfChallengeRecordActivity.class, null);
                break;
            case R.id.tvBack:
                isExit();
                break;
        }
    }

    private void isExit(){
        SelfChallengeFragment fragment = (SelfChallengeFragment) tabFragments.get(1);
        List<String> list = fragment.getVideoList();
        if (list.size() == 0) {
            JumpUtils.exitActivity(mContext);
        } else {
            showDeleteDialog();
        }
    }


    private void showDeleteDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.layout_delete)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setText(R.id.message, "确认取消此次编辑吗?");
                        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                JumpUtils.exitActivity(mContext);
                                dialog.dismiss();
                            }
                        });
                    }
                }).setWidth(200)
                .setOutCancel(true)
                .show(mContext.getSupportFragmentManager());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isExit();
        }
        return false;
    }


}
