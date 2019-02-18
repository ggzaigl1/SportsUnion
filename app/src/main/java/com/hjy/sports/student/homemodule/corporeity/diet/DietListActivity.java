package com.hjy.sports.student.homemodule.corporeity.diet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.fragment.EnergyDemandFragment;
import com.hjy.sports.student.homemodule.corporeity.diet.fragment.FoodFestivalFragment;
import com.hjy.sports.student.homemodule.corporeity.diet.fragment.HealthyFoodFragment;
import com.hjy.sports.student.homemodule.corporeity.diet.fragment.WeeklyRecipeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 饮食处方 列表界面
 * Created by Stefan on 2018/1/25.
 */

public class DietListActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<Fragment> mFragments;
    private int diagnosticId;

    @Override
    protected int getContentView() {
        return R.layout.activity_diet_list;
    }

    public int getDiagnosticId() {
        return diagnosticId;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("饮食建议");
        Bundle bundle = getIntent().getExtras();
        String mark = bundle.getString("mark");
        diagnosticId = bundle.getInt("diagnosticId");

        mFragments = new ArrayList<>();
        mFragments.add(new EnergyDemandFragment());
        mFragments.add(new WeeklyRecipeFragment());
        mFragments.add(new HealthyFoodFragment());
        mFragments.add(new FoodFestivalFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getStringArray(R.array.diet_list)[position];
            }
        });
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);

        switch (mark){
            case "EnergyDemandFragment":
                mViewPager.setCurrentItem(0);
                break;
            case "WeeklyRecipeFragment":
                mViewPager.setCurrentItem(1);
                break;
            case "HealthyFoodFragment":
                mViewPager.setCurrentItem(2);
                break;
            case "FoodFestivalFragment":
                mViewPager.setCurrentItem(3);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0 || positionOffsetPixels == 0) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
