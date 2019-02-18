package com.hjy.sports.president.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.AnimUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.hjy.sports.R;
import com.hjy.sports.president.main.fragment.FragmentFour;
import com.hjy.sports.president.main.fragment.FragmentOne;
import com.hjy.sports.president.main.fragment.FragmentThree;
import com.hjy.sports.president.main.fragment.FragmentTwo;
import com.hjy.sports.util.SelectUtils;

import butterknife.BindView;

/**
 * 校长 主界面
 * Created by fangs on 2018/4/3.
 */
public class PresidentMainActivity extends BaseActivity{

    private static final String[] FRAGMENT_TAG = {"FragmentOne", "FragmentTwo", "FragmentThree", "FragmentFour"};
    private FragmentManager fragmentManageer;
    private Fragment mCurrentFrgment;//当前显示的fragment
    private int currentIndex = 0;    //当前显示的fragment的下标

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;

    @BindView(R.id.radioGlayout)
    RadioGroup radioGlayout;
    @BindView(R.id.rBtnOne)
    RadioButton rBtnOne;
    @BindView(R.id.rBtnTwo)
    RadioButton rBtnTwo;
    @BindView(R.id.rBtnThree)
    RadioButton rBtnThree;
    @BindView(R.id.rBtnFour)
    RadioButton rBtnFour;

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.president_main_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRadioGroup();
    }

    //初始化底部导航按钮
    private void initRadioGroup() {
        //设置 选择器
        TintUtils.setTxtIconLocal(rBtnOne, SelectUtils.getSelector(R.drawable.svg_main_home), 1);
        TintUtils.setTxtIconLocal(rBtnTwo, SelectUtils.getSelector(R.drawable.svg_president_main_two), 1);
        TintUtils.setTxtIconLocal(rBtnThree, SelectUtils.getSelector(R.drawable.svg_president_main_three), 1);
        TintUtils.setTxtIconLocal(rBtnFour, SelectUtils.getSelector(R.drawable.svg_main_my), 1);

        radioGlayout.setOnCheckedChangeListener((group, checkedId) -> {
            int position = 0;
            switch (checkedId) {
                case R.id.rBtnOne:
                    position = 0;
                    break;
                case R.id.rBtnTwo:
                    position = 1;
                    break;
                case R.id.rBtnThree:
                    position = 2;
                    break;
                 case R.id.rBtnFour:
                    position = 3;
                    break;
            }
            beginTransaction(position);
        });

        rBtnOne.setChecked(true);
    }

    private void beginTransaction(int position) {
        if (null == fragmentManageer) {
            fragmentManageer = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManageer.beginTransaction();
        AnimUtils.setFragmentTransition(fragmentTransaction, currentIndex, position);

        Fragment showfragment = null;
        switch (position) {
            case 0:
                if (null == fragmentOne) fragmentOne = new FragmentOne();
                showfragment = fragmentOne;
                break;
            case 1:
                if (null == fragmentTwo) fragmentTwo = new FragmentTwo();
                showfragment = fragmentTwo;
                break;
            case 2:
                if (null == fragmentThree) fragmentThree = new FragmentThree();
                showfragment = fragmentThree;
                break;
            case 3:
                if (null == fragmentFour) fragmentFour = new FragmentFour();
                showfragment = fragmentFour;
                break;
        }

        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            fragmentTransaction.hide(mCurrentFrgment);
        }

        if (null == showfragment) return;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!showfragment.isAdded()) {
            fragmentTransaction.add(R.id.main_fragemnet_container, showfragment, FRAGMENT_TAG[position]);
        } else {
            fragmentTransaction.show(showfragment);
        }

        //保存当前显示的那个Fragment
        mCurrentFrgment = showfragment;
        currentIndex = position;
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
