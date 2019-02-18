package com.hjy.sports.student.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.AnimUtils;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.TintUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.main.fragment.FragmentFive;
import com.hjy.sports.student.main.fragment.FragmentFour;
import com.hjy.sports.student.main.fragment.FragmentOne;
import com.hjy.sports.student.main.fragment.FragmentThree;
import com.hjy.sports.student.main.fragment.FragmentTwo;
import com.hjy.sports.student.modify.info.ModifyInfoActivity;
import com.hjy.sports.util.SelectUtils;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;

import butterknife.BindView;

/**
 * 体育联盟 主界面
 * Created by fangs on 2017/12/12.
 */
public class StudentMainActivity extends BaseActivity {

    private static final String[] FRAGMENT_TAG = {"FragmentOne", "FragmentTwo", "FragmentThree", "FragmentFour", "FragmentFive"};
    private FragmentManager fragmentManageer;
    private Fragment mCurrentFrgment;//当前显示的fragment
    private int currentIndex = 0;    //当前显示的fragment的下标

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private FragmentFive fragmentFive;

    @BindView(R.id.radioGlayout)
    RadioGroup radioGlayout;
    @BindView(R.id.rBtnOne)
    RadioButton rBtnOne;
    @BindView(R.id.rBtnThree)
    RadioButton rBtnThree;
    @BindView(R.id.rBtnFour)
    RadioButton rBtnFour;
    @BindView(R.id.rBtnFive)
    RadioButton rBtnFive;

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
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void init(Bundle savedInstanceState) {
        initRadioGroup();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //从登录界面 进入 首页导航按钮选中
        boolean flag = SpfUtils.getSpfSaveBoolean("loginShowHome");
        if (flag) {
            SpfUtils.saveBooleanToSpf("loginShowHome", false);
            rBtnOne.setChecked(true);
            radioGlayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 解决 activity 启动模式为 singleTask时，intent传值 接收不到问题
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        //获取 Intent而不是用 方法传递的Intent，是因为在 Application 中 设置了activity
        // 生命周期回调时候 用到Intent 缓存了配置信息
        Intent intent1 = getIntent();
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            intent1 = intent1.putExtras(bundle);
            super.onNewIntent(intent1);
            setIntent(intent1);//intent传值 接收不到问题，关键在这句
        } else {
            super.onNewIntent(intent);
        }
    }

    //初始化底部导航按钮
    private void initRadioGroup() {
        TintUtils.setTxtIconLocal(rBtnOne, SelectUtils.getSelector(R.drawable.svg_main_home), 1);
        TintUtils.setTxtIconLocal(rBtnThree, SelectUtils.getSelector(R.drawable.svg_main_recommend), 1);
        TintUtils.setTxtIconLocal(rBtnFour, SelectUtils.getSelector(R.drawable.svg_main_circle), 1);
        TintUtils.setTxtIconLocal(rBtnFive, SelectUtils.getSelector(R.drawable.svg_main_my), 1);

        radioGlayout.setOnCheckedChangeListener((group, checkedId) -> {
            int position = 0;
            switch (checkedId) {
                case R.id.rBtnOne:
                    position = 0;
                    break;
                case R.id.rBtnTwo:
                    T.showShort("此功能正在开发中");
                    position = 1;
                    break;
                case R.id.rBtnThree:
                    position = 2;
                    break;
                case R.id.rBtnFour:
                    position = 3;
                    break;
                case R.id.rBtnFive:
                    position = 4;
                    break;
            }

            if (position == 2) {
                LoginBean.StudentBean studentBean = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
                if (!studentBean.getName().isEmpty() && !studentBean.getJiazhangphone().isEmpty()) {
                    radioGlayout.setVisibility(View.GONE);
                } else {
                    showConfirmDialog();
                    setRbtnChecked(fragmentManageer);
                    return;
                }
            }

            if (position == 1) {
                return; //TODO 此功能正在开发中
            }
            beginTransaction(position);
        });

        rBtnOne.setChecked(true);
    }

    private void beginTransaction(int position) {
        if (null == fragmentManageer) fragmentManageer = getSupportFragmentManager();

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
            case 4:
                if (null == fragmentFive) fragmentFive = new FragmentFive();
                showfragment = fragmentFive;
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

//            添加到回退栈,并定义标记
            fragmentTransaction.addToBackStack(FRAGMENT_TAG[position]);
        } else {
            fragmentTransaction.show(showfragment);
        }

        //保存当前显示的那个Fragment
        mCurrentFrgment = showfragment;
        currentIndex = position;
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showConfirmDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dailog_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("isPhone", true);
                                JumpUtils.jump(mContext, ModifyInfoActivity.class, null);
                            }
                        });
                    }
                })
                .setMargin(50)
                .setDimAmount(0.5f)
                .setOutCancel(true)
                .show(mContext.getSupportFragmentManager());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentIndex == 2) {//商城 fragment
                clickFr();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void clickFr() {
        //商城 fragment
        if (fragmentThree.onBackPressed()) return;

        radioGlayout.setVisibility(View.VISIBLE);

        // 立即回退一步
        fragmentManageer.popBackStackImmediate();
        fragmentThree = null;

        setRbtnChecked(fragmentManageer);
    }

    private void setRbtnChecked(FragmentManager fragmentManageer) {
        // 获取当前退到了哪一个Fragment上,重新获取当前的Fragment回退栈中的个数
        FragmentManager.BackStackEntry backStack = fragmentManageer
                .getBackStackEntryAt(fragmentManageer.getBackStackEntryCount() - 1);

        // 获取当前栈顶的Fragment的标记值
        String tag = backStack.getName();

        if (FRAGMENT_TAG[0].equals(tag)) {
            // 设置首页选中
            rBtnOne.setChecked(true);
        } else if (FRAGMENT_TAG[1].equals(tag)) {
        } else if (FRAGMENT_TAG[2].equals(tag)) {
            // 设置购物车的tag
            rBtnThree.setChecked(true);
        } else if (FRAGMENT_TAG[3].equals(tag)) {
            rBtnFour.setChecked(true);
        } else if (FRAGMENT_TAG[4].equals(tag)) {
            rBtnFive.setChecked(true);
        }
    }
}
