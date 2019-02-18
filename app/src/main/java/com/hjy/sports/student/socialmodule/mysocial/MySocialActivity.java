package com.hjy.sports.student.socialmodule.mysocial;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.eventbus.DeletedEvent;
import com.fy.baselibrary.eventbus.PraiseEvent;
import com.fy.baselibrary.eventbus.RxConstants;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.socialmodule.AddSocialActivity;
import com.hjy.sports.student.socialmodule.socaildetail.MySocialDetailActivity;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
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
 * 我发送过的社交信息列表
 * Created by QKun on 2017/12/28.
 */

public class MySocialActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mPageNo = 1;
    private AppCompatImageView mSocial_header_img;
    private AppCompatImageView mImgHeader;
    private TextView mUser_name;
    private MySocialAdapter mAdapter;
    private int mLikenumber;
    private int mIslike;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_social;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        tvTitle.setText(getString(R.string.my_social));
        tvBack.setText(getString(R.string.back));
        tvMenu.setText("");
        TintUtils.setCompoundDrawable(tvMenu, R.drawable.vector_add, 1);

        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) getIntent().getExtras().getSerializable("StudentInfo");

        initRefresh();
        initRecycler(studentInfo);
        getlistMyByApp(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void PublishEvent(String event) {
        if (!TextUtils.isEmpty(event) && event.equals(RxConstants.STRING_PUBLISH_SUCCEED)) {
            if (null != mRefreshLayout)
                mRefreshLayout.autoRefresh();
        }
    }

    /**
     * 我发布列表中删除了 本页需要自动刷新下
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DeletedEvent(DeletedEvent event) {
//        if (!TextUtils.isEmpty(event) && event.equals(RxConstants.DELETED_SUCCEED)) {
//            if (null != mRefreshLayout)
//                mRefreshLayout.autoRefresh();
//        }
        if (null != event) {
            int socialid = event.getSocialid();
            for (int i = mAdapter.getData().size() - 1; i >= 0; i--) {
                if (mAdapter.getData().get(i).getSocialid() == socialid) {
                    mAdapter.getData().remove(i);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvMenu:
                JumpUtils.jump(mContext, AddSocialActivity.class, null);
                break;
        }
    }


    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getlistMyByApp(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistMyByApp(1);
            }
        });
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

    private void initRecycler(LoginBean.StudentBean studentInfo) {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        View view = LayoutInflater.from(mContext).inflate(R.layout.social_header_view, (ViewGroup) mRecycler.getParent(), false);
        //背景图
        mSocial_header_img = view.findViewById(R.id.social_header_img);
        //头像
        mImgHeader = view.findViewById(R.id.social_header_photo);
        //姓名
        mUser_name = view.findViewById(R.id.tv_user_name);
        if (studentInfo != null) {
            mUser_name.setText(studentInfo.getName());
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + studentInfo.getTouxiangurl(), mImgHeader);
        }

        mAdapter = new MySocialAdapter(R.layout.item_my_social_contact, new ArrayList<>());
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        NiceDialog.init()
                                .setLayoutId(R.layout.layout_delete)
                                .setConvertListener(new ViewConvertListener() {
                                    @Override
                                    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                        holder.setText(R.id.message, "确认删除此条动态吗?");
                                        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dialog.dismiss();
                                            }
                                        });
                                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int socialid = mAdapter.getData().get(position).getSocialid();
                                                dialog.dismiss();
                                                //调用删除接口
                                                deleteToApp(socialid, position);
                                            }
                                        });
                                    }
                                }).setWidth(200)
                                .setOutCancel(true)
                                .show(getSupportFragmentManager());

                        break;
                    case R.id.ll_comment:
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("rowsBean", rowsBean);
                        JumpUtils.jump(mContext, MySocialDetailActivity.class, bundle);
                        break;
                    case R.id.ll_praise: //点赞
                        int socialid = mAdapter.getData().get(position).getSocialid();

                        mIslike = mAdapter.getData().get(position).getIslike();
                        mLikenumber = mAdapter.getData().get(position).getLikenumber();
                        if (mIslike == 1) { //自己已经点过赞的
                            deleteLikeToApp(socialid, view, position);
                        } else if (mIslike == 0) {
                            addLikeToApp(socialid, view, position);
                        }
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
                JumpUtils.jump(mContext, MySocialDetailActivity.class, bundle);
            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.addHeaderView(view);
    }

    //删除帖子
    private void deleteToApp(int socialid, int position) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.deleteing);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("socialid", socialid);
        mConnService.deleteLikeToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        mAdapter.getData().remove(position);
                        mAdapter.notifyDataSetChanged();
//                                EventBus.getDefault().post(RxConstants.DELETED_SUCCEED);
                        EventBus.getDefault().post(new DeletedEvent(socialid));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
//        new NetRequest.Builder().create()
//                .requestDate(mConnService.deleteToApp(param).compose(RxHelper.handleResult()),
//                        new NetCallBack<Object>(progressDialog) {
//                            @Override
//                            protected void onSuccess(Object t) {
//                                mAdapter.getData().remove(position);
//                                mAdapter.notifyDataSetChanged();
////                                EventBus.getDefault().post(RxConstants.DELETED_SUCCEED);
//                                EventBus.getDefault().post(new DeletedEvent(socialid));
//                            }
//
//                            @Override
//                            protected void updataLayout(int flag) {
//
//                            }
//                        });
    }

    //获取我的发帖列表
    public void getlistMyByApp(int pageNo) {
        int id = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", id);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        new RxNetCache.Builder().create()
                .request(mConnService.getlistMyByApp(param))
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
                        EventBus.getDefault().post(new PraiseEvent(mIslike, (mLikenumber + 1), socialid));
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

                        EventBus.getDefault().post(new PraiseEvent(mIslike, (mLikenumber - 1), socialid));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
}
