package com.hjy.sports.student.socialmodule.friendsocial;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.eventbus.PraiseEvent;
import com.fy.baselibrary.eventbus.RecommendNumEvent;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.socialmodule.socaildetail.SocialDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by QKun on 2018/3/1.
 */

public class FriendSocialActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private FriendSocialAdapter mAdapter;
    private int mPageNo;
    private int mLikenumber;
//    private int mPosition;
    private int mIslike;


    @Override
    protected int getContentView() {
        return R.layout.activity_friend_social;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);

        tvTitle.setText("好友个人动态");
        tvBack.setText(getString(R.string.back));
        tvMenu.setVisibility(View.GONE);

        SocialAllListBean.RowsBean studentInfo = (SocialAllListBean.RowsBean) getIntent().getExtras().getSerializable("StudentInfo");
//        mPosition = getIntent().getExtras().getInt("position");//这个item所在的位置
        mIslike = studentInfo.getIslike();
        if (null != studentInfo) {
            int studentid = studentInfo.getStudentid();
            initRecycler(studentInfo);

            getlistMyByApp(1, studentid);

            initRefresh(studentid);
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }

    /**
     * 评论的条数
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RecommendNumEvent(RecommendNumEvent event) {
        if (event != null) {
            int recommendNum = event.getRecommendNum();
            int socialid = event.getSocialid();
            for (int i = mAdapter.getData().size() - 1; i >= 0; i--) {
                if (mAdapter.getData().get(i).getSocialid() == socialid) {
                    mAdapter.getData().get(i).setSonnumber(recommendNum);
                    mAdapter.notifyItemChanged(i + 1);
                }
            }

        }
    }

    /**
     * 点赞事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPraiseEvent(PraiseEvent event) {
        if (event != null) {
            int islike = event.getIslike();
            int praise_num = event.getPraise_num();
            int socialid = event.getSocialid();
            for (int i = mAdapter.getData().size() - 1; i >= 0; i--) {
                if (mAdapter.getData().get(i).getSocialid() == socialid) {
                    mAdapter.getData().get(i).setIslike(islike);
                    mAdapter.getData().get(i).setLikenumber(praise_num);
                    mAdapter.notifyItemChanged(i + 1);
                }
            }



        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initRefresh(int studentid) {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getlistMyByApp(mPageNo, studentid);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistMyByApp(1, studentid);
            }
        });
    }

    private void initRecycler(SocialAllListBean.RowsBean studentInfo) {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        View view = LayoutInflater.from(mContext).inflate(R.layout.social_header_view, (ViewGroup) mRecycler.getParent(), false);

        //背景图
        AppCompatImageView mSocial_header_img = view.findViewById(R.id.social_header_img);
        //头像
        AppCompatImageView mImgHeader = view.findViewById(R.id.social_header_photo);
        //姓名
        TextView mUser_name = view.findViewById(R.id.tv_user_name);
        if (studentInfo != null) {
            mUser_name.setText(studentInfo.getStudentname());
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + studentInfo.getTouxiangurl(), mImgHeader);
        }

        mAdapter = new FriendSocialAdapter(R.layout.friend_social_recycle_item, new ArrayList<>());
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_praise: //点赞

                        int socialid = mAdapter.getData().get(position).getSocialid();

                        int mIslike = mAdapter.getData().get(position).getIslike();
                        mLikenumber = mAdapter.getData().get(position).getLikenumber();
                        if (mIslike == 1) { //自己已经点过赞的
                            deleteLikeToApp(socialid, view, position);
                        } else if (mIslike == 0) {
                            addLikeToApp(socialid, view, position);
                        }
                        break;
                    case R.id.ll_comment: //评论
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("rowsBean", rowsBean);
                        bundle.putInt("position", position);
                        JumpUtils.jump(mContext, SocialDetailActivity.class, bundle);
                        break;

                }
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("rowsBean", rowsBean);
                bundle.putInt("position", position);
                JumpUtils.jump(mContext, SocialDetailActivity.class, bundle);
            }
        });

        mRecycler.setAdapter(mAdapter);
        mAdapter.addHeaderView(view);

    }

    private void getlistMyByApp(int pageNo, int studentid) {
        int currstudentid = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentid);
        param.put("currstudentid", currstudentid);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        new RxNetCache.Builder().create()
                .request(mConnService.getlistStudentByApp(param))
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<SocialAllListBean>() {
                    @Override
                    protected void onSuccess(SocialAllListBean allListBean) {
                        if (allListBean != null) {
                            L.d(allListBean.toString());
                            //当前页
                            mPageNo = allListBean.getPageNo();

                            List<SocialAllListBean.RowsBean> rows = allListBean.getRows();
                            if (rows != null && rows.size() != 0) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(rows);
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(rows);
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.addData(rows);
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        if (mRefreshLayout.isRefreshing()) {
                            mRefreshLayout.finishRefresh();
                        }
                        if (mRefreshLayout.isLoading()) {
                            mRefreshLayout.finishLoadmore();
                        }
                    }
                });

    }


    //点赞
    private void addLikeToApp(int socialid, View view, int position) {
        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("socialid", socialid);
        mConnService.addLikeToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mIslike = 1;
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        rowsBean.setLikenumber(mLikenumber + 1);
                        rowsBean.setIslike(1);
                        mAdapter.notifyItemChanged(position + 1);

                        //本界面的功能完成  需要发送到主界面中
                        EventBus.getDefault().post(new PraiseEvent( mIslike, (mLikenumber + 1),socialid));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

    //取消点赞
    private void deleteLikeToApp(int socialid, View view, int position) {
        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("socialid", socialid);

        mConnService.deleteLikeToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mIslike = 0;
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        rowsBean.setLikenumber(mLikenumber - 1);
                        rowsBean.setIslike(0);
                        mAdapter.notifyItemChanged(position + 1);

                        EventBus.getDefault().post(new PraiseEvent(mIslike, (mLikenumber - 1),socialid));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

}
