package com.hjy.sports.student.homemodule.corporeity.staminasignal;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;
import com.hjy.sports.util.TextStyleUtils;
import com.hjy.sports.widget.CenterLayoutManager;
import com.hjy.sports.widget.weekcalendar.CalendarData;
import com.hjy.sports.widget.weekcalendar.WeekCalendarUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * 体能红绿灯
 * Created by QKun on 2018/1/24.
 */

public class StaminaSignalActivity extends BaseActivity {

    @BindView(R.id.tv_standard)
    TextView tv_standard;
    @BindView(R.id.tv_harm)
    TextView tv_harm;
    @BindView(R.id.tv_alarm)
    TextView tv_alarm;
    @BindView(R.id.tv_today_recommend)
    TextView tv_today_recommend;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
//    @BindView(R.id.calendarView)
//    CalendarView mCalendarView;

    private CalendarData today;
    private StaminaAdapter mAdapter;
    private int mSelectedPos = -1;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected int getContentView() {
        return R.layout.activity_stamina_signal;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("体能红绿灯");
        Typeface leiMiao = TextStyleUtils.getLeiMiao(this);
        Typeface wawati = TextStyleUtils.getW5(this);
        tv_standard.setTypeface(leiMiao);
        tv_harm.setTypeface(leiMiao);
        tv_alarm.setTypeface(wawati);
        tv_today_recommend.setTypeface(wawati);
//        mCalendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(Calendar calendar, boolean isClick) {
//                T.showShort(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
//            }
//        });


        CenterLayoutManager manager = new CenterLayoutManager(this);
        manager.setOrientation(HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new StaminaAdapter(R.layout.item_stamina, new ArrayList<>(), today);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //方法1
//                for (CalendarData calendarData : mAdapter.getData()) {
//                    calendarData.setSelect(false);
//                }
//                CalendarData calendarData = mAdapter.getData().get(position);
//                T.showShort("position= " + calendarData.year + "-" + calendarData.month + "-" + calendarData.day);
//                mRecyclerView.smoothScrollToPosition(position);
//
//                calendarData.setSelect(true);
//                mAdapter.notifyDataSetChanged();


                //方法2
                //如果勾选的不是已经勾选状态的Item
                if (mSelectedPos != position) {
                    //先取消上个item的勾选状态
                    mAdapter.getData().get(mSelectedPos).setSelect(false);
                    mAdapter.notifyItemChanged(mSelectedPos);

                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mAdapter.getData().get(mSelectedPos).setSelect(true);
                    mRecyclerView.smoothScrollToPosition(mSelectedPos);
                    mAdapter.notifyItemChanged(mSelectedPos);
                }


//                RecyclerView.ViewHolder viewHolder = mRecyclerView.findViewHolderForLayoutPosition(mSelectedPos);
//                if (viewHolder!=null){
//                    viewHolder.itemView.findViewById()
//                }

            }
        });

        mRecyclerView.setAdapter(mAdapter);

        initData();

    }

    private void initData() {
        getToday();
        List<CalendarData> wholeMonthDay = WeekCalendarUtil.getWholeMonthDay(today);
        mAdapter.setNewData(wholeMonthDay);
        for (int i = mAdapter.getData().size() - 1; i >= 0; i--) {
            CalendarData calendarData = mAdapter.getData().get(i);
            if (calendarData.getYear() == mYear && calendarData.getMonth() == mMonth && calendarData.getDay() == mDay) {
                mRecyclerView.smoothScrollToPosition(i);
                mSelectedPos = i;
                CalendarData calendarData1 = mAdapter.getData().get(i);
                calendarData1.setSelect(true);
                mAdapter.notifyDataSetChanged();
                break;
            }
        }
    }


    /**
     * 获取今天的参数
     */
    private void getToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        String currentDate = sdf.format(date);
        mYear = Integer.parseInt(currentDate.split("-")[0]);
        mMonth = Integer.parseInt(currentDate.split("-")[1]);
        mDay = Integer.parseInt(currentDate.split("-")[2]);
        today = new CalendarData(mYear, mMonth, mDay, WeekCalendarUtil.getWeek(mYear + "-" + mMonth + "-" + mDay));
    }


}
