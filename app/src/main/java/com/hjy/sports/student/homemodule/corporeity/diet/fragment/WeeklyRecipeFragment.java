package com.hjy.sports.student.homemodule.corporeity.diet.fragment;

import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.base.popupwindow.CommonPopupWindow;
import com.fy.baselibrary.entity.WeeklyRecipeBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.TimeUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.hjy.sports.R;
import com.hjy.sports.student.homemodule.corporeity.diet.DietListActivity;
import com.hjy.sports.student.homemodule.corporeity.diet.adapter.WeeklyRecipeAdapter;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
import com.hjy.sports.widget.weekcalendar.WeekCalendarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 每周食谱 Fragment
 * Created by Stefan on 2018/1/25.
 */

public class WeeklyRecipeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.image_calendar)
    TextView image_calendar;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.tv_month)
    TextView mTvMonth;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.ll)
    RelativeLayout mLl;
   // @BindView(R.id.refreshLayout)
   // SmartRefreshLayout mRefreshLayout;

    WeeklyRecipeAdapter adapter;
    //DailyRecipeAdapter adapter;
    private int diagnosticId;
    private int mPageNo = 1;

    private List<String> dummyData;
    private CommonPopupWindow popupWindow;
    private int mHeight; //日历图片的高度
    private int mActionBarSize;
    private int mRl_height;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_weeklyrecipe;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        DietListActivity activity = (DietListActivity) getActivity();
        diagnosticId = activity.getDiagnosticId();
        ViewTreeObserver viewTreeObserver = image_calendar.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                image_calendar.getViewTreeObserver().removeOnDrawListener(this::onGlobalLayout);
                mHeight = image_calendar.getHeight();

            }
        });
        ViewTreeObserver observer = mLl.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLl.getViewTreeObserver().removeOnDrawListener(this::onGlobalLayout);
                mRl_height = mLl.getHeight();
            }
        });

        mCalendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {
//                T.showShort(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());

                //通过日历获取对应时间的 食谱
                //initRefresh(calendar.timeformat());
                getWeeklyRecipeApp(1,calendar.timeformat());
                if(adapter!=null) adapter.notifyDataSetChanged();
                String chinaMonth = WeekCalendarUtil.getChinaMonth(calendar.getMonth());
                mTvMonth.setText(chinaMonth);
            }
        });
        setDummyData();
        //首次进入界面 获取当天食谱
        //getWeeklyRecipeApp(1, TimeUtils.Long2DataString(System.currentTimeMillis(),"yyyy-MM-dd"));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //adapter = new DailyRecipeAdapter(getActivity(), dummyData);
        adapter = new WeeklyRecipeAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        mActionBarSize = getActionBarSize();
    }

    private void getWeeklyRecipeApp(int pageNo, String ymd) {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        param.put("ymd", ymd);
        //param.put("diagnosticPrescriptionId", diagnosticId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        // param.put("type", 2);

        mConnService.getWeeklyRecipeApp(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<WeeklyRecipeBean>() {
                    @Override
                    protected void onSuccess(WeeklyRecipeBean weeklyRecipeBean) {
                        L.d("参数: ", param.toString());
                        if (weeklyRecipeBean != null) {
                            //当前页
                            mPageNo = weeklyRecipeBean.getPageNo();
                            adapter.setmDatas(weeklyRecipeBean.getRows());
                            adapter.notifyDataSetChanged();
//                                    if(mRefreshLayout!=null) {
//                                        mRefreshLayout.finishRefresh();
//                                    }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
    private void initRefresh(String ymd) {
       // getWeeklyRecipeApp(1,ymd);
//        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh();
//            }
//
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore();
//                mPageNo += 1;
//                getWeeklyRecipeApp(mPageNo,ymd);
//            }
//        });
    }


    @OnClick({R.id.image_calendar})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.image_calendar:
                NiceDialog.init()
                        .setLayoutId(R.layout.pw_month_simple_calendar)
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                CalendarView calendarView = holder.getView(R.id.calendarView);
                                TextView tv_year_month = holder.getView(R.id.tv_year_month);
                                tv_year_month.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");
                                calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
                                    @Override
                                    public void onDateSelected(Calendar calendar, boolean isClick) {
                                        tv_year_month.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
                                        //通过日历获取对应时间的 食谱
                                       // initRefresh(calendar.timeformat());
                                        getWeeklyRecipeApp(1,calendar.timeformat());
                                        if(adapter!=null) adapter.notifyDataSetChanged();
                                        if (isClick) {
                                            mCalendarView.scrollToCalendar(calendar.getYear(), calendar.getMonth(), calendar.getDay());
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }
                        })
                        .setAnimStyle(R.style.AnimTop)
                        .show(mContext.getSupportFragmentManager());
//                if (popupWindow != null && popupWindow.isShowing()) return;
//                //弹pop
//                popupWindow = new CommonPopupWindow.Builder(mContext)
//                        .setView(R.layout.pw_month_simple_calendar)
//                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight(mContext) - (ScreenUtils.getStatusHeight(mContext) + mRl_height+mActionBarSize+96+16))
//                        .setAnimationStyle(R.style.AnimTop)
//                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
//                            @Override
//                            public void getChildView(View view, int layoutResId) {
//                                if (layoutResId == R.layout.pw_month_simple_calendar) {
//
//                                    CalendarView calendarView = view.findViewById(R.id.calendarView);
//                                    //年月
//                                    TextView tv_year_month = view.findViewById(R.id.tv_year_month);
//                                    tv_year_month.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");
//
//                                    calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
//                                        @Override
//                                        public void onDateSelected(Calendar calendar, boolean isClick) {
//
//                                            tv_year_month.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
//                                            int mPop_year = calendar.getYear();
//                                            int mPop_month = calendar.getMonth();
//                                            int mPop_day = calendar.getDay();
//                                            String chinaMonth = WeekCalendarUtil.getChinaMonth(calendar.getMonth());
//                                            mTvMonth.setText(chinaMonth);
//
//                                            T.showShort(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
//
//                                            if (isClick) {
//                                                mCalendarView.scrollToCalendar(mPop_year, mPop_month, mPop_day);
//
//                                                if (popupWindow != null) {
//                                                    popupWindow.dismiss();
//                                                }
//                                            }
//
//                                        }
//                                    });
////                                    View bg_view = view.findViewById(R.id.bg_view);
////                                    bg_view.setOnClickListener(new View.OnClickListener() {
////                                        @Override
////                                        public void onClick(View v) {
////                                            if (popupWindow != null) {
////                                                popupWindow.dismiss();
////                                            }
////                                        }
////                                    });
//
//                                }
//                            }
//                        }).create();
//                if (popupWindow != null) {
//                    int[] positions = new int[2];
//                    view.getLocationOnScreen(positions);
//                    popupWindow.showAtLocation(mContext.findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + image_calendar.getHeight());
//                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getWeeklyRecipeApp(1, TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy-MM-dd"));
        //mRefreshLayout.autoRefresh();
    }

    private void setDummyData() {
        dummyData = new ArrayList();
        for (int i = 0; i < 5; i++) {
            dummyData.add("");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        if (mRefreshLayout.isRefreshing()) {
//            mRefreshLayout.finishRefresh();
//        }
//        if (mRefreshLayout.isLoading()) {
//            mRefreshLayout.finishLoadmore();
//        }
    }

    public int getActionBarSize() {

        int[] attrs = {android.R.attr.actionBarSize};
        TypedArray values = mContext.getTheme().obtainStyledAttributes(attrs);
        try {
            return values.getDimensionPixelSize(0, 0);//第一个参数数组索引，第二个参数 默认值
        } finally {
            values.recycle();
        }

    }


}
