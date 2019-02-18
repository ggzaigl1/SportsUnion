package com.hjy.sports.student.socialmodule.socaildetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ReplyListBean;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.eventbus.DeletedEvent;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by QKun on 2018/1/3.
 */

public class MySocialDetailActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mSocialid;
    private SocialDetailAdapter mAdapter;
    private int mPageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_social_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("详情");
        tvBack.setText(R.string.back);
        tvMenu.setVisibility(View.GONE);

        SocialAllListBean.RowsBean rowsBean = (SocialAllListBean.RowsBean) getIntent().getExtras().getSerializable("rowsBean");
        // 帖子ID
        mSocialid = rowsBean.getSocialid();
        initRefresh();
        initRecycler(rowsBean);
        getlistSonByApp(mSocialid, 1);

    }

    private void initRefresh() {
//        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
//        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getlistSonByApp(mSocialid, mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistSonByApp(mSocialid, 1);
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

    private void initRecycler(SocialAllListBean.RowsBean rowsBean) {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        View headerView = setHeaderView(rowsBean);

        mAdapter = new SocialDetailAdapter(R.layout.item_social_detail, new ArrayList<>());

        //点击条目事件 回复二级评论
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                shouRecommendDialog(2, position); //type 1代表回复的主帖子   2代表2级回复
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_comment_to_user: //点击跳转进入子评论列表
                        ReplyListBean.RowsBean rowsBean1 = mAdapter.getData().get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("secondReplyListBean", rowsBean1);
                        JumpUtils.jump(mContext, SonRecommendActivity.class, bundle);
                        break;
                }
            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.addHeaderView(headerView);
    }

    private View setHeaderView(SocialAllListBean.RowsBean rowsBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_social_contact, (ViewGroup) mRecycler.getParent(), false);
        //删除图标
        AppCompatImageView iv_delete = view.findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(rowsBean.getSocialid());
            }
        });
        //头像
        AppCompatImageView iv_user_head = view.findViewById(R.id.iv_user_head);
        //name
        TextView tv_social_name = view.findViewById(R.id.tv_social_name);
        //sendtime
        TextView tv_social_time = view.findViewById(R.id.tv_social_time);
        //content
        TextView tv_social_content = view.findViewById(R.id.tv_social_content);
        //nineImageview
        NineGridView nine_grid_image = view.findViewById(R.id.nine_grid_image);

        //comment_num
        TextView comment_num = view.findViewById(R.id.comment_num);
        //tv_praise_num
        TextView tv_praise_num = view.findViewById(R.id.tv_praise_num);
        ImageView mIv_praise = view.findViewById(R.id.iv_praise);

        if (rowsBean.getIslike() == 1) {
            mIv_praise.setImageResource(R.mipmap.icon_praise_selected);
        } else {
            mIv_praise.setImageResource(R.mipmap.icon_praise_normal);
        }

        if (rowsBean != null) {

            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + rowsBean.getTouxiangurl(), iv_user_head);

            if (!TextUtils.isEmpty(rowsBean.getStudentname())) {
                tv_social_name.setText(rowsBean.getStudentname());
            }
            if (!TextUtils.isEmpty(rowsBean.getSenddate())) {
                tv_social_time.setText(rowsBean.getSenddate());
            }
            if (!TextUtils.isEmpty(rowsBean.getContent())) {
                tv_social_content.setText(rowsBean.getContent());
            }

//            nine_grid_image.setAdapter(new NineGridImageViewAdapter<String>() {
//                @Override
//                protected void onDisplayImage(Context context, ImageView imageView, String s) {
//                    ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + s, imageView);
//                }
//            });
//            nine_grid_image.setImagesData(rowsBean.getImageurlList());
//
//            nine_grid_image.setItemImageClickListener(new ItemImageClickListener() {
//                @Override
//                public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
//
//                    Intent intent = new Intent(mContext, PhotoInfoActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("position", index);
//                    bundle.putStringArrayList("ImageurlList", (ArrayList<String>) rowsBean.getImageurlList());
//                    intent.putExtras(bundle);
//                    mContext.startActivity(intent);
//                }
//            });

            ArrayList<ImageInfo> imageInfos = new ArrayList<>();
            List<String> imageurlList = rowsBean.getImageurlList();
            if (imageurlList != null) {
                for (String s : imageurlList) {
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(ApiService.IMG_BASE_URL + s);
                    imageInfo.setThumbnailUrl(ApiService.IMG_BASE_URL + s);
                    imageInfos.add(imageInfo);
                }
            }
            nine_grid_image.setAdapter(new NineGridViewClickAdapter(mContext, imageInfos));

            comment_num.setText(rowsBean.getSonnumber() + "");

            tv_praise_num.setText(rowsBean.getLikenumber() + "");

        }
        return view;
    }

    private void shouRecommendDialog(int type, int position) {
        NiceDialog.init()
                .setLayoutId(R.layout.recommend_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final EditText editText = holder.getView(R.id.edit_input);
                        if (type == 2) {
                            String studentname = mAdapter.getData().get(position).getStudentname();

                            editText.setHint("回复@" + studentname);
                        }
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);

                            }
                        });
                        holder.setOnClickListener(R.id.tv_send_recommend, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String mSend_recommend = editText.getText().toString();
                                if (type == 2) {
                                    int id = mAdapter.getData().get(position).getId();
                                    addSonToApp(mSocialid, mSend_recommend, type, id);
                                }
                                addSonToApp(mSocialid, mSend_recommend, type, 0);

                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }


    //某个帖子回复列表
    public void getlistSonByApp(int socialid, int pageNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("parentid", socialid);
        param.put("type", 1);   // 1代表回复的主帖子   2代表2级回复
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        new RxNetCache.Builder().create()
                .request(mConnService.getlistSonByApp(param))
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<ReplyListBean>() {
                    @Override
                    protected void onSuccess(ReplyListBean replyListBean) {
                        if (replyListBean != null) {
                            L.d(replyListBean.toString());
                            //当前页
                            mPageNo = replyListBean.getPageNo();

                            List<ReplyListBean.RowsBean> rows = replyListBean.getRows();
                            if (!rows.isEmpty()) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(rows);
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(rows);
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.setNewData(rows);
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        if (null != mRefreshLayout) {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            }
                            if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadmore();
                            }
                        }
                    }
                });

    }


    /**
     * //回复帖子
     *
     * @param socialid
     * @param content
     * @param type
     * @param
     */
    private void addSonToApp(int socialid, String content, int type, int id) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.publish_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
//        param.put("parentid", socialid); //帖子id
        if (type == 2) {
            param.put("parentid", id); //帖子id
        } else if (type == 1) {
            param.put("parentid", socialid); //帖子id
        }
        param.put("studentid", studentId); //回复人id
        param.put("content", content); //回复内容
        param.put("type", type);   // 1代表回复的主帖子   2代表2级回复

        mConnService.addSonToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        mRefreshLayout.autoRefresh();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }


    //删除帖子
    private void deleteToApp(int socialid) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.deleteing);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("socialid", socialid);
        mConnService.deleteToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        EventBus.getDefault().post(new DeletedEvent(socialid));
                        JumpUtils.exitActivity(MySocialDetailActivity.this);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    //删除提醒框
    private void showDeleteDialog(int socialid) {
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

                                dialog.dismiss();
                                //调用删除接口
                                deleteToApp(socialid);
                            }
                        });
                    }
                }).setWidth(200)
                .setOutCancel(true)
                .show(mContext.getSupportFragmentManager());
    }
}
