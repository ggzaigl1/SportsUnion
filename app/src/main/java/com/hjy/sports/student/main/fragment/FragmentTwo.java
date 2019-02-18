package com.hjy.sports.student.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.eventbus.RxConstants;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.github.mikephil.charting.charts.RadarChart;
import com.hjy.sports.R;
import com.hjy.sports.student.bean.ImageBean;
import com.hjy.sports.student.bean.MySection;
import com.hjy.sports.student.bean.SectionAdapter;
import com.hjy.sports.student.datamodule.bodydetection.BodyDetectionActivity;
import com.hjy.sports.student.datamodule.physicalfitness.PhysicalFitnessActivity;
import com.hjy.sports.student.datamodule.stature.StatureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 * 数据
 */
public class FragmentTwo extends BaseFragment {

    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    SectionAdapter adapter;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvBack)
    TextView mTvBack;
    @BindView(R.id.tvMenu)
    TextView mTvMenu;

    private String[] mParties = new String[6];
    public static final String FRAGMENTTWO = "fragmentTwo";
    private float mult = 80;
    private float min = 20;
    RadarChart mChart;
    private String mArgument;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_two;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void baseInit() {
        //        动态设置状态栏高度
        MdStatusBarCompat.setStatusView(mContext, statusView);
        mTvTitle.setText(getResources().getString(R.string.mainTabTwo));
        mTvMenu.setVisibility(View.GONE);
        mTvBack.setVisibility(View.GONE);

        EventBus.getDefault().register(this);

        mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        View headerVeiw = LayoutInflater.from(mContext)
                .inflate(R.layout.radar_chart_header, (ViewGroup) mRecycler.getParent(), false);
        RelativeLayout rl_hreader = headerVeiw.findViewById(R.id.rl_hreader);
        rl_hreader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mChart = headerVeiw.findViewById(R.id.radarChart);
        adapter = new SectionAdapter(R.layout.item_section_content, R.layout.item_section_head, new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection mySection = (MySection) adapter.getData().get(position);
                if (mySection.isHeader) {
                    if (mySection.header.equals("形体机体检测")) {
                        JumpUtils.jump(mContext, BodyDetectionActivity.class, null);
                    }
                    if (mySection.header.equals("体能测试结果(个人体质)")) {
                        JumpUtils.jump(mContext, PhysicalFitnessActivity.class, null);
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", mySection.t.getName());
                    JumpUtils.jump(mContext, StatureActivity.class, bundle);
                }
            }
        });

        mRecycler.setAdapter(adapter);
        adapter.setHeaderView(headerVeiw);

        initData();
//        getItemToApp();
    }

    /**
     * 登录之后自动刷新  以保证是当前学生的信息 LoginActivity
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginEvent(String event) {
        if (!TextUtils.isEmpty(event) && event.equals(RxConstants.LOGIN_SUCCEED)) {
//            getItemToApp();
        }
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            L.i("onHiddenChanged", "hidden");
//        } else {
//            L.i("onHiddenChanged", "show");
//            getItemToApp();
//        }
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {
        List<MySection> mList = new ArrayList<>();
        mList.add(new MySection(true, "形体机体检测"));
        mList.add(new MySection(new ImageBean(R.drawable.vector_stature, getResources().getString(R.string.data1))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_weight, getResources().getString(R.string.data2))));
        mList.add(new MySection(true, "体能测试结果(个人体质)"));
        mList.add(new MySection(new ImageBean(R.drawable.vector_total_points, getResources().getString(R.string.data3))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_bmi, getResources().getString(R.string.data4))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_vital_capacity, getResources().getString(R.string.data5))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_50_meters, getResources().getString(R.string.data6))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_sit_and_reach, getResources().getString(R.string.data7))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_rope_skipping, getResources().getString(R.string.data8))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_sit_up, getResources().getString(R.string.data9))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_shuttle_run, getResources().getString(R.string.data10))));
        mList.add(new MySection(new ImageBean(R.drawable.vector_long_jump, getResources().getString(R.string.data11))));

        adapter.addData(mList);
    }


//    private void getItemToApp() {
//        int id = SpfUtils.getSpfSaveInt("studentId");
//        Map<String, Object> param = new HashMap<>();
//        param.put("token", ConstantUtils.token);
//        param.put("studentid", id);
//        new NetRequest.Builder().create()
//                .requestDate(mConnService.getItemToApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<TotalHealthInfoBean>() {
//                            @Override
//                            protected void onSuccess(TotalHealthInfoBean totalHealthInfoBean) {
//                                if (totalHealthInfoBean != null) {
//                                    double zongtidf = totalHealthInfoBean.getZongtidf();
//                                    double zuotiweiqianqudf = totalHealthInfoBean.getZuotiweiqianqudf();
//                                    double tiaoshengdf = totalHealthInfoBean.getTiaoshengdf();
//                                    double bmidf = totalHealthInfoBean.getBmidf();
//                                    double wushimidf = totalHealthInfoBean.getWushimidf();
//                                    double feihuoliangdf = totalHealthInfoBean.getFeihuoliangdf();
//                                    ArrayList<RadarEntry> entries = new ArrayList<>();
//                                    entries.add(new RadarEntry((float) zongtidf));
//                                    entries.add(new RadarEntry((float) zuotiweiqianqudf));
//                                    entries.add(new RadarEntry((float) tiaoshengdf));
//                                    entries.add(new RadarEntry((float) bmidf));
//                                    entries.add(new RadarEntry((float) wushimidf));
//                                    entries.add(new RadarEntry((float) feihuoliangdf));
//                                    // "总分", "柔韧", "力量", "身体形态", "速度", "身体机能"
//                                    mParties[0] = "总分" + String.valueOf(zongtidf);
//                                    mParties[1] = "柔韧" + String.valueOf(zuotiweiqianqudf);
//                                    mParties[2] = "力量" + String.valueOf(tiaoshengdf);
//                                    mParties[3] = "身体形态" + String.valueOf(bmidf);
//                                    mParties[4] = "速度" + String.valueOf(wushimidf);
//                                    mParties[5] = "身体机能" + String.valueOf(feihuoliangdf);
//
//                                    RadarChartUtils.initRadarChart(mContext, mChart, entries, mParties);
//                                }
//                            }
//
//                            @Override
//                            public void updataLayout(int flag) {
//                            }
//                        });
//    }

}
