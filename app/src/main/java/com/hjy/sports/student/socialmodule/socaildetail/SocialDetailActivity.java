package com.hjy.sports.student.socialmodule.socaildetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.fy.baselibrary.eventbus.PraiseEvent;
import com.fy.baselibrary.eventbus.RecommendNumEvent;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
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
import butterknife.OnClick;

/**
 * 社交详情界面 好友发布的消息  自动刷新
 * Created by QKun on 2017/12/27.
 */

public class SocialDetailActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_praise)
    TextView mTvpraise;
    private int mPageNo = 1;
    private SocialDetailAdapter mAdapter;
    private int mSocialid;
    private SocialAllListBean.RowsBean mRowsBean;
    private ImageView mIv_praise;
    private int mIslike;
    private TextView mTv_praise_num;
    private int mStudentId;
//    private int mPosition;
    private TextView mComment_num;

    @Override
    protected int getContentView() {
        return R.layout.activity_social_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        tvTitle.setText("详情");
        tvBack.setText(R.string.back);
        tvMenu.setVisibility(View.GONE);

        mStudentId = SpfUtils.getSpfSaveInt("studentId");
        mRowsBean = (SocialAllListBean.RowsBean) getIntent().getExtras().getSerializable("rowsBean");
//        mPosition = getIntent().getExtras().getInt("position");//这个item所在的位置
        mIslike = mRowsBean.getIslike();
        if (mIslike == 1) {
            mTvpraise.setText("已点赞");
        } else {
            mTvpraise.setText("点赞");
        }
        // 帖子ID
        mSocialid = mRowsBean.getSocialid();
        initRefresh();
        initRecycler(mRowsBean);

    }


    @OnClick({R.id.ll_comment, R.id.ll_praise})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_comment: //评论
                showRecommendDialog(1, 0);
                break;
            case R.id.ll_praise: //点赞

                if (mIslike == 1) { //自己已经点过赞的

                    deleteLikeToApp(mSocialid, mStudentId);
                } else {

                    addLikeToApp(mSocialid, mStudentId);
                }
                break;
        }
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
                showRecommendDialog(2, position); //type 1代表回复的主帖子   2代表2级回复
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
        //修改为不需要自动刷新
//        mRefreshLayout.autoRefresh();
        getlistSonByApp(mSocialid, 1);
    }

    private int maxLen = 500;

    private void showRecommendDialog(int type, int position) {
        NiceDialog.init()
                .setLayoutId(R.layout.recommend_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final TextView content_unm = holder.getView(R.id.content_unm);
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
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                Editable editable = editText.getText();
                                int length = editable.length();
                                if (length > maxLen) {
                                    int selectionEnd = Selection.getSelectionEnd(editable);
                                    String str = editable.toString();
                                    //截取新字符串
                                    String newStr = str.substring(0, maxLen);
                                    editText.setText(newStr);
                                    editable = editText.getText();
                                    //新字符串的长度
                                    int newLen = editable.length();
                                    //旧光标位置超过字符串长度
                                    if (selectionEnd > newLen) {
                                        selectionEnd = editable.length();
                                    }
                                    //设置新光标所在的位置
                                    Selection.setSelection(editable, selectionEnd);
                                } else {
                                    content_unm.setText(length + "");
                                }

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        holder.setOnClickListener(R.id.tv_send_recommend, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String mSend_recommend = editText.getText().toString().trim();
                                if (!TextUtils.isEmpty(mSend_recommend)) {
                                    if (type == 2) {
                                        int id = mAdapter.getData().get(position).getId();
                                        addSonToApp(mSocialid, mSend_recommend, type, id);
                                    }
                                    addSonToApp(mSocialid, mSend_recommend, type, 0);

                                    dialog.dismiss();
                                } else {
                                    T.showShort("请输入");
                                }

                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    //设置头部布局及数据
    private View setHeaderView(SocialAllListBean.RowsBean rowsBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_social_contact, (ViewGroup) mRecycler.getParent(), false);
        view.findViewById(R.id.iv_delete).setVisibility(View.GONE);
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
        mComment_num = view.findViewById(R.id.comment_num);
        ImageView iv_comment = view.findViewById(R.id.iv_comment);

        //tv_praise_num
        mTv_praise_num = view.findViewById(R.id.tv_praise_num);
        mIv_praise = view.findViewById(R.id.iv_praise);


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


            mComment_num.setText(rowsBean.getSonnumber() + "");

            mTv_praise_num.setText(rowsBean.getLikenumber() + "");

        }
        return view;
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
                .request(mConnService.getlistSonByApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<ReplyListBean>() {
                    @Override
                    protected void onSuccess(ReplyListBean replyListBean) {
                        if (replyListBean != null) {
                            L.d(replyListBean.toString());
                            //当前页
                            mPageNo = replyListBean.getPageNo();

                            List<ReplyListBean.RowsBean> rows = replyListBean.getRows();
                            if (rows != null && rows.size() != 0) {
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
                    public void updataLayout(int flag) {
                        if (mRefreshLayout.isRefreshing()) {
                            mRefreshLayout.finishRefresh();
                        }
                        if (mRefreshLayout.isLoading()) {
                            mRefreshLayout.finishLoadmore();
                        }
                    }

                });
    }

    /**
     * //回复帖子
     *
     * @param socialid
     * @param content
     * @param type  1代表回复的主帖子   2代表2级回复
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

        new RxNetCache.Builder().create()
                .request(mConnService.addSonToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        if (type == 1) {
                            String string = mComment_num.getText().toString();
                            int index = Integer.parseInt(string);
                            mComment_num.setText((index + 1) + "");
                            EventBus.getDefault().post(new RecommendNumEvent(index + 1, socialid));
                        }
                        getlistSonByApp(mSocialid, 1);
//                                mRefreshLayout.autoRefresh();
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });

    }
    //点赞
    private void addLikeToApp(int socialid, int studentid) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.delete);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentid);
        param.put("socialid", socialid);
        new RxNetCache.Builder().create()
                .request(mConnService.addLikeToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object bean) {
                        mIslike = 1;
                        mIv_praise.setImageResource(R.mipmap.icon_praise_selected);
                        String string = mTv_praise_num.getText().toString();
                        int praise_num = Integer.valueOf(string);
                        mTv_praise_num.setText((praise_num + 1) + "");
                        mTvpraise.setText("已点赞");

                        EventBus.getDefault().post(new PraiseEvent(mIslike, (praise_num + 1),socialid));
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });

    }


    //取消点赞
    private void deleteLikeToApp(int socialid, int studentid) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.delete);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", studentid);
        param.put("socialid", socialid);
        new RxNetCache.Builder().create()
                .request(mConnService.deleteLikeToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object bean) {
                        mIslike = 0;
                        mIv_praise.setImageResource(R.mipmap.icon_praise_normal);
                        String string = mTv_praise_num.getText().toString();
                        int praise_num = Integer.valueOf(string);
                        mTv_praise_num.setText((praise_num - 1) + "");
                        mTvpraise.setText("点赞");

                        EventBus.getDefault().post(new PraiseEvent(mIslike, (praise_num - 1),socialid));
                    }

                    @Override
                    public void updataLayout(int flag) {

                    }

                });
    }


    @Override
    protected void onDestroy() {
//        L.i("nihao", "onDestroy" + "   " + mTvpraise.getText());
//        String isPraise = mTvpraise.getText().toString();
//        int praise_num = Integer.valueOf(mTv_praise_num.getText().toString());
//        if (isPraise.equals("点赞")) { //表示没有点赞 islike=0
//            mIslike = 0;
////            RxBus.getDefault().postWithCode(RxConstants.PRAISE, new PraiseEvent(mPosition, mIslike,praise_num));
//
//        } else if (isPraise.equals("已点赞")) { //表示点赞过了 islike=1
//            mIslike = 1;
//            RxBus.getDefault().postWithCode(RxConstants.PRAISE, new PraiseEvent(mPosition, mIslike,praise_num));
//        }
        super.onDestroy();


    }
}
