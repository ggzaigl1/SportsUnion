package com.hjy.sports.student.main.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.DietActivity;
import com.hjy.sports.student.homemodule.corporeity.exercise.ExerciseActivity;
import com.hjy.sports.student.homemodule.corporeity.selftest.SelfTestingActivity;
import com.hjy.sports.student.homemodule.corporeity.staminasignal.StaminaSignalsActivity;
import com.hjy.sports.student.homemodule.expanded.healthknowledge.HealthKnowledgeActivity;
import com.hjy.sports.student.homemodule.expanded.ornamental.OrnamentalListContextActivity;
import com.hjy.sports.student.homemodule.quality.figure.FigureActivity;
import com.hjy.sports.student.homemodule.quality.sensory.SensoryActivity;
import com.hjy.sports.student.homemodule.quality.stamina.StaminaActivity;
import com.hjy.sports.student.homemodule.quality.standard.StandardActivity;
import com.hjy.sports.student.homemodule.total.TotalPointsActivity;
import com.hjy.sports.student.main.web.HealthRecordActivity;
import com.hjy.sports.student.modify.info.ModifyInfoActivity;
import com.hjy.sports.util.TextStyleUtils;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
import com.kmwlyy.core.net.HttpCode;
import com.kmwlyy.login.sdk.H5WebViewActivity;
import com.kmwlyy.login.sdk.SdkCallBack;
import com.kmwlyy.login.sdk.WLYYSDK;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/12.
 * 首页
 */
public class FragmentOne extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.tv_student_name)
    TextView mTvStudentName;
    @BindView(R.id.tv_student_grade)
    TextView mTvStudentGrade;
    @BindView(R.id.tv_student_class)
    TextView mTvStudentClass;
    @BindView(R.id.tv_total_grade)
    TextView mTvTotalGrade;
    @BindView(R.id.iv_student_photo)
    AppCompatImageView mIvStudentPhoto;
    @BindView(R.id.ll_personal_data)
    LinearLayout mLlPersonalData;
    @BindView(R.id.tv_figure)
    TextView mTvFigure;
    @BindView(R.id.tv_stamina)
    TextView mTvStamina;
    @BindView(R.id.tv_standard)
    TextView mTvStandard;
    @BindView(R.id.tv_sensory_integration)
    TextView mTvSensoryIntegration;
    @BindView(R.id.ll_sport)
    LinearLayout mLlSport;
    @BindView(R.id.ll_diet)
    LinearLayout mLlDiet;
    @BindView(R.id.ll_self_test)
    LinearLayout mLlSelfTest;
    @BindView(R.id.ll_traffic_signal)
    LinearLayout mLlTrafficSignal;
    @BindView(R.id.ll_expanded_one)
    LinearLayout mLlExpandedOne;
    @BindView(R.id.ll_expanded_two)
    LinearLayout mLlExpandedTwo;
//    @BindView(R.id.ll_expanded_three)
//    LinearLayout mLlExpandedThree;
    @BindView(R.id.ll_expanded_four)
    LinearLayout mLlExpandedFour;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.fl_title)
    FrameLayout mFlTitle;
    @BindView(R.id.tv_hight_light)
    TextView mTvHightLight;
    private Animator anim;

    private String[] images = {
            ApiService.BASE_URL + "images/banner/android/mipmap-xhdpi/banner1.png"
    };

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_one;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void baseInit() {
        initBanner();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MdStatusBarCompat.StatusBarLightMode(getActivity());
            mContext.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        Typeface typeface = TextStyleUtils.getW5(getActivity());
        tv_title.setTypeface(typeface);
        tv_content.setTypeface(typeface);

    }

    @OnClick({R.id.tv_figure, R.id.tv_stamina, R.id.tv_standard, R.id.tv_sensory_integration,
            R.id.ll_sport, R.id.ll_diet, R.id.ll_self_test, R.id.ll_traffic_signal,
            R.id.ll_expanded_one, R.id.ll_expanded_two, R.id.ll_expanded_four,
            R.id.ll_personal_data, R.id.fl_title}) //R.id.ll_expanded_three
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_personal_data: //总分
                JumpUtils.jump(mContext, TotalPointsActivity.class, null);
                break;
            case R.id.tv_figure: //体形
                JumpUtils.jump(mContext, FigureActivity.class, null);
                break;
            case R.id.tv_stamina: //体能
                JumpUtils.jump(mContext, StaminaActivity.class, null);
                break;
            case R.id.tv_standard: //达标
                JumpUtils.jump(mContext, StandardActivity.class, null);
                break;
            case R.id.tv_sensory_integration: //感统
                JumpUtils.jump(mContext, SensoryActivity.class, null);
                break;
            case R.id.ll_sport: //运动处方
                JumpUtils.jump(mContext, ExerciseActivity.class, null);
                break;
            case R.id.ll_diet: //饮食处方
                JumpUtils.jump(mContext, DietActivity.class, null);
                break;
            case R.id.ll_self_test: //自我评测
                JumpUtils.jump(mContext, SelfTestingActivity.class, null);
                break;
            case R.id.ll_traffic_signal: //体能红绿灯
//                JumpUtils.jump(mContext, StaminaSignalActivity.class, null);
                JumpUtils.jump(mContext, StaminaSignalsActivity.class, null);
                break;
            case R.id.ll_expanded_one: //专家咨询  需要手机号码
//                T.showShort("此功能开发中,敬请期待！");

                LoginBean.StudentBean studentBean = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
                if (!TextUtils.isEmpty(studentBean.getJiazhangphone())) {
                    String jiazhangphone = studentBean.getJiazhangphone();
                    BaseNiceDialog baseNiceDialog = showLoadDialog();

                    WLYYSDK.getInstance().startConsult(mContext, jiazhangphone, "", "", "", "", "", new SdkCallBack() {
                        @Override
                        public void onError(int code, String msg) {
                            baseNiceDialog.dismiss();
                            //服务机构代码失效时不需要提示登录失败
                            if (code != HttpCode.ORG_EXPIRE) {
                                T.showShort("访问不成功!");
                            }
                        }

                        @Override
                        public void onSuccess() {
                            baseNiceDialog.dismiss();
                            H5WebViewActivity.startH5WebViewActivity(mContext, jiazhangphone, "", "", "", "", "");
                        }
                    });
                } else {
                    showConfirmDialog();
                }

                break;
            case R.id.ll_expanded_two: //健康常识
//                T.showShort("此功能开发中,敬请期待！");
                JumpUtils.jump(mContext, HealthKnowledgeActivity.class, null);
                break;
//            case R.id.ll_expanded_three: //我的私教
//                T.showShort("此功能开发中,敬请期待！");
////                JumpUtils.jump(mContext, DevelopmentActivity.class, null);
//                break;
            case R.id.ll_expanded_four: //运动课程
                JumpUtils.jump(mContext, OrnamentalListContextActivity.class, null);
                break;
            case R.id.fl_title: //个人健康档案  必须有手机号码
//                T.showShort("此功能开发中,敬请期待！");
                LoginBean.StudentBean studentBeans = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
                //必须要获取手机号码
                if (!TextUtils.isEmpty(studentBeans.getJiazhangphone())) {
                    JumpUtils.jump(mContext, HealthRecordActivity.class, null);
                } else {
                    showConfirmDialog();
                }
                break;
            default:
                break;
        }
    }

    private BaseNiceDialog showLoadDialog() {
        BaseNiceDialog baseNiceDialog = NiceDialog.init()
                .setLayoutId(R.layout.loading_layout)
                .setWidth(100)
                .setHeight(100)
                .setDimAmount(0.5f)
                .show(mContext.getSupportFragmentManager());
        return baseNiceDialog;
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
                                JumpUtils.jump(mContext, ModifyInfoActivity.class, bundle);
                            }
                        });
                    }
                })
                .setMargin(50)
                .setDimAmount(0.5f)
                .setOutCancel(true)
                .show(mContext.getSupportFragmentManager());

    }

    private void initBanner() {
        List<String> networkImages = Arrays.asList(images);
        banner.setPages(() -> new NetworkImageHolderView(), networkImages)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                })
                .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
                .setPointViewVisible(true)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//设置指示器的方向
                .setPageTransformer(new AccordionTransformer())
                .setcurrentitem(0);
    }


    @Override
    public void onResume() {
        super.onResume();
        //从登录获取的缓存
        bindData((LoginBean) mCache.getAsObject("LoginBean"));
        //首页的头像
        String touxiangurl = SpfUtils.getSpfSaveStr("touxiangurl");
        ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + touxiangurl, mIvStudentPhoto);
        // banner.startTurning(2000);//开始翻页
        SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean show = mPerferences.getBoolean("show", true);
        if (show && !TextUtils.isEmpty(ConstantUtils.isfirstOpenApp)) {
            showKnownTipView();
            SharedPreferences.Editor mEditor = mPerferences.edit();
            mEditor.putBoolean("show", false);
            mEditor.commit();
        } else {
            mTvHightLight.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
//        banner.stopTurning();//停止翻页
    }

    private void bindData(LoginBean loginBean) {
        if (null == loginBean || null == loginBean.getStudent()) return;

        //姓名
        if (!TextUtils.isEmpty(loginBean.getStudent().getName()))
            mTvStudentName.setText(loginBean.getStudent().getName());
        //年级
        if (!TextUtils.isEmpty(loginBean.getStudent().getNname()))
            mTvStudentGrade.setText(loginBean.getStudent().getNname());
        //班级
        if (!TextUtils.isEmpty(loginBean.getStudent().getBname()))
            mTvStudentClass.setText(loginBean.getStudent().getBname());
        //得分
        if (!TextUtils.isEmpty(loginBean.getStudent().getZongtidf()) && !TextUtils.isEmpty(loginBean.getStudent().getZongtipd())) {
            String strTotal = "总评分" + loginBean.getStudent().getZongtidf() + "分，" + loginBean.getStudent().getZongtipd();
            int totalLength = strTotal.length();
            int dflength = loginBean.getStudent().getZongtidf().length();
            SpannableString spannableString = new SpannableString(strTotal);
            int index = strTotal.indexOf("，");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7eaa")), 3, 3 + dflength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffbd66")), index + 1, totalLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvTotalGrade.setText(spannableString);
            Typeface w5 = TextStyleUtils.getW5(mContext);
            mTvTotalGrade.setTypeface(w5);
        }
    }


    /**
     * app指向性功能
     */
    private void showKnownTipView() {

        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        // get the center for the clipping circle
                        int cx = (mTvHightLight.getRight() - mTvHightLight.getLeft()) / 2;
                        int cy = (mTvHightLight.getBottom() - mTvHightLight.getTop()) / 2;
                        // get the final radius for the clipping circle
                        int initRadius = Math.max(mTvHightLight.getWidth(), mTvHightLight.getHeight());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // create the animator for this view (the start radius is zero)
                            anim = ViewAnimationUtils.createCircularReveal(mTvHightLight, cx, cy, initRadius, 0);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    // make the view visible and start the animation
                                    mTvHightLight.setVisibility(View.GONE);
                                }
                            });
                            anim.start();
                        } else {
                            mTvHightLight.setVisibility(View.GONE);
                        }
                    }
                });
    }
}