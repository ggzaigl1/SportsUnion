package com.hjy.sports.student.homemodule.corporeity.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.popupwindow.CommonPopupWindow;
import com.fy.baselibrary.entity.PunchClockBean;
import com.fy.baselibrary.entity.SportMethodBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.GsonUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ScreenUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.TimeUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Gab on 2018/1/24 0024..
 * 每周锻炼
 */

public class ExerciseTwoActivity extends BaseActivity implements CalendarView.OnDateSelectedListener {

    @BindView(R.id.tv_callback)
    TextView mTvCallback;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.iv_calendar)
    AppCompatImageView mIvCalendar;
    private CommonPopupWindow popupWindow;//
    private int mHeight;
    private int mDiagnosticId;

    //练习项目集合
    private List<SportMethodBean.RowsBean> projectList = new ArrayList<>();

    //某日打卡记录
    private List<PunchClockBean> mPunchClockBeanList = new ArrayList<>();
    private WeekExerciseAdapter mWeekExerciseAdapter;

    private List<String> porjectName = new ArrayList<>();
    private String mYesterday;
    private String mSelectedTime;
    private String mStrCurrentTime;

    //某月打卡记录日期集合
    private List<Calendar> schemes = new ArrayList<>();

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_exercise_two);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //诊断id
        mDiagnosticId = getIntent().getExtras().getInt("mDiagnosticId");


        initRecycler();
        //请求获取某月打卡记录日期  第一次进入都是使用当前时间
        String strYearMonth = TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy-MM");
        getPunchClockByMonthToApp(strYearMonth);

        //获取当前时间
        long currentTimeMillis = System.currentTimeMillis();
        long yesterdayTimeMillis = currentTimeMillis - 24 * 60 * 60 * 1000;
        mStrCurrentTime = TimeUtils.Long2DataString(currentTimeMillis, "yyyy-MM-dd");
        //获取昨天时间
        mYesterday = TimeUtils.Long2DataString(yesterdayTimeMillis, "yyyy-MM-dd");

        //获取某日打卡记录 和 练习方法 一起请求
        getPunchClockAndSportsMethod(mStrCurrentTime);



        mCalendarView.setOnDateSelectedListener(this);
        ViewTreeObserver viewTreeObserver = mIvCalendar.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mIvCalendar.getViewTreeObserver().removeOnDrawListener(this::onGlobalLayout);
                mHeight = mIvCalendar.getHeight();
            }
        });


    }


    //获取  练习方法 一起请求 和  某日打卡记录
    private void getPunchClockAndSportsMethod(String clockDate) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("diagnosticPrescriptionId", mDiagnosticId);
        param.put("pageNo", 1);
        param.put("pageSize", 20);
        mConnService.getSportsMethodToApp(param)
                .compose(RxHelper.handleResult())
                .flatMap(new Function<SportMethodBean, ObservableSource<List<PunchClockBean>>>() {
                    @Override
                    public ObservableSource<List<PunchClockBean>> apply(SportMethodBean t) throws Exception {
                        if (t != null) {
                            if (t.getRows() != null && t.getRows().size() != 0) {
                                projectList.clear();
                                projectList.addAll(t.getRows());
                            }
                        }

                        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
                        Map<String, Object> param = new HashMap<>();
                        param.put("token", ConstantUtils.token);
                        param.put("studentid", mStudentId);
                        param.put("clockDate", clockDate);
                        //获取某日打卡记录
                        return mConnService.getPunchClockToApp(param).compose(RxHelper.handleResult());
                    }
                })
                .subscribe(new NetCallBack<List<PunchClockBean>>(progressDialog) {
                    @Override
                    protected void onSuccess(List<PunchClockBean> t) {
                        if (!t.isEmpty()) {
                            //用一个集合承载每日打卡情况集合
                            mPunchClockBeanList.clear();
                            mPunchClockBeanList.addAll(t);
                            for (int i = projectList.size() - 1; i >= 0; i--) {
                                for (int j = t.size() - 1; j >= 0; j--) {
                                    if (projectList.get(i).getId() == t.get(j).getSportsMethodId()) {
                                        projectList.get(i).setDaka(true);
                                    }
                                }
                            }
                        }
                        mWeekExerciseAdapter.setNewData(projectList);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });


    }


    //请求获取某月打卡记录日期
    private void getPunchClockByMonthToApp(String strTime) {
        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("clockDate", strTime);

        new RxNetCache.Builder().create()
                .request(mConnService.getPunchClockByMonthToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<List<String>>() {
                    @Override
                    protected void onSuccess(List<String> t) {
                        if (t != null && t.size() != 0) {
                            for (String s : t) {
                                String[] split = s.split("-");
                                schemes.add(getSchemeCalendar(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
                            }
                            mCalendarView.setSchemeDate(schemes);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });


    }


    private Calendar getSchemeCalendar(int year, int month, int day) {
        Calendar calendar = new Calendar();

        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(getResources().getColor(R.color.appHeadBg));//如果单独标记颜色、则会使用这个颜色
        return calendar;
    }


    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        View head_view = LayoutInflater.from(mContext).inflate(R.layout.week_exercise_headview, (ViewGroup) mRecycler.getParent(), false);
        mWeekExerciseAdapter = new WeekExerciseAdapter(R.layout.item_week_exercise, new ArrayList<>());
        mWeekExerciseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_status: //打卡与否
                        //需要判断 是不是今天和昨天
                        //mSelectedTime代表是切换了时间 如果为空 说明没有切换时间就是当天  不为空说名切换了打卡时需要判断下是不是昨天
                            if (!TextUtils.isEmpty(mSelectedTime)) {
                                //打昨天的卡
                                if (mSelectedTime.equals(mYesterday)) {
                                    //mPunchClockBeanList 获取某日打卡记录 不为空 不能打卡
                                    if (!mPunchClockBeanList.isEmpty()) {
                                        T.showLong("今日不能打卡了哦~");
                                    } else {// 为空 可以打一项

                                        if (!mWeekExerciseAdapter.getData().get(position).isDaka()) {
                                            //进行打卡提交请求
                                            int id = mWeekExerciseAdapter.getData().get(position).getId();
                                            String sportsName = mWeekExerciseAdapter.getData().get(position).getSportsName();
                                            int score = mWeekExerciseAdapter.getData().get(position).getScore();
                                            savePunchClock(id, sportsName, score, mSelectedTime);
                                        } else {
                                            T.showLong("已打卡");
                                        }

                                    }

                                } else if (mSelectedTime.equals(mStrCurrentTime)) {
                                    if (!mWeekExerciseAdapter.getData().get(position).isDaka()) {
                                        //进行打卡提交请求
                                        int id = mWeekExerciseAdapter.getData().get(position).getId();
                                        String sportsName = mWeekExerciseAdapter.getData().get(position).getSportsName();
                                        int score = mWeekExerciseAdapter.getData().get(position).getScore();
                                        savePunchClock(id, sportsName, score, mSelectedTime);
                                    } else {
                                        T.showLong("已打卡");
                                    }

                                } else {
                                    T.showLong("不好意思哦，只能补卡昨天的哦");
                                }

                            } else {
                                if (!mWeekExerciseAdapter.getData().get(position).isDaka()) {
                                    //进行打卡提交请求
                                    int id = mWeekExerciseAdapter.getData().get(position).getId();
                                    String sportsName = mWeekExerciseAdapter.getData().get(position).getSportsName();
                                    int score = mWeekExerciseAdapter.getData().get(position).getScore();
                                    savePunchClock(id, sportsName, score, mStrCurrentTime);
                                } else {
                                    T.showLong("已打卡");
                                }
                            }
                        break;
                }
            }
        });
        mRecycler.setAdapter(mWeekExerciseAdapter);
        mWeekExerciseAdapter.addHeaderView(head_view);
    }

    /**
     * 保存打卡的记录  Q:能不能打卡问题  只能打今天和昨天的
     */
    private void savePunchClock(int id, String sportsName, int score, String clockDate) {
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_save);
        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();

        param.put("studentid", mStudentId);
        param.put("sportsMethodId", id);
        param.put("sportsMethodName", sportsName);
        param.put("score", score);
        param.put("clockDate", clockDate);


        mConnService.savePunchClock(ConstantUtils.token, GsonUtils.mapToJsonStr(param))
                .compose(RxHelper.handleResult())
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new NetCallBack<SportMethodBean>() {
                    @Override
                    protected void onSuccess(SportMethodBean t) {
                        //请求获取某月打卡记录日期  第一次进入都是使用当前时间
                        String strYearMonth = TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy-MM");
                        getPunchClockByMonthToApp(strYearMonth);

                        getPunchClockAndSportsMethod(clockDate);

                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }


    @OnClick({R.id.tv_callback, R.id.iv_calendar})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_callback: //返回
                JumpUtils.exitActivity(this);
                break;
            case R.id.iv_calendar: //点击日历 显示整月的
                if (popupWindow != null && popupWindow.isShowing()) return;

                //弹pop
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pw_month_calendar)
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight(mContext) - (ScreenUtils.getStatusHeight(mContext) + mHeight + 15))
                        .setAnimationStyle(R.style.AnimTop)
                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                            @Override
                            public void getChildView(View view, int layoutResId) {
                                if (layoutResId == R.layout.pw_month_calendar) {

                                    CalendarView calendarView = view.findViewById(R.id.calendarView);
                                    calendarView.scrollToCurrent();
                                    calendarView.setSchemeDate(schemes);

                                    //年月
                                    TextView tv_year_month = view.findViewById(R.id.tv_year_month);
                                    tv_year_month.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");

                                    calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
                                        @Override
                                        public void onDateSelected(Calendar calendar, boolean isClick) {

                                            tv_year_month.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
                                            int mPop_year = calendar.getYear();
                                            int mPop_month = calendar.getMonth();
                                            int mPop_day = calendar.getDay();

                                            if (isClick) {
                                                mCalendarView.scrollToCalendar(mPop_year, mPop_month, mPop_day);
                                                //获取某日打卡记录 和 练习方法 一起请求
                                                String strCalendar = calendar.toString();
                                                StringBuffer buffer = new StringBuffer(strCalendar);
                                                //切换时间的时间 年月日  这的数据是为了判断是打卡使用
                                                mSelectedTime = buffer.insert(4, "-").insert(7, "-").toString();
                                                getPunchClockAndSportsMethod(mSelectedTime);

                                                mPunchClockBeanList.clear();

                                                if (popupWindow != null) {
                                                    popupWindow.dismiss();
                                                }
                                            }

                                        }
                                    });
                                    View bg_view = view.findViewById(R.id.bg_view);
                                    bg_view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (popupWindow != null) {
                                                popupWindow.dismiss();
                                            }
                                        }
                                    });

                                }
                            }
                        }).create();
                if (popupWindow != null) {
                    int[] positions = new int[2];
                    view.getLocationOnScreen(positions);
                    popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + mIvCalendar.getHeight());
                }

                break;
        }
    }


    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        //但切换时间时  就记住切换的时间  若没有切换的就说明就是当天时间
        if (isClick) {
            //点击时
            //获取某日打卡记录 和 练习方法 一起请求
            String strCalendar = calendar.toString();
            StringBuffer buffer = new StringBuffer(strCalendar);
            //切换时间的时间 年月日  这的数据是为了判断是打卡使用
            mSelectedTime = buffer.insert(4, "-").insert(7, "-").toString();
            getPunchClockAndSportsMethod(mSelectedTime);

            mPunchClockBeanList.clear();
        }

    }
}
