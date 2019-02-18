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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ReplyListBean;
import com.fy.baselibrary.entity.SecondRecommendBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/1/2.
 */

public class SonRecommendActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.edit_recommend)
    TextView mEditRecommend;
    private SecondRecommendAdapter mAdapter;
    private int mPageNo = 1;
    private int mId;

    @Override
    protected int getContentView() {
        return R.layout.activity_son_recommend;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("详情");
        tvBack.setText(R.string.back);
        tvMenu.setVisibility(View.GONE);

        ReplyListBean.RowsBean secondReplyListBean = (ReplyListBean.RowsBean) getIntent().getExtras().getSerializable("secondReplyListBean");
        mId = secondReplyListBean.getId();//该条评论的id
        initRecycler(secondReplyListBean);
        initRefresh();
    }

    @OnClick({R.id.edit_recommend})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.edit_recommend: //对父评论进行评论
                showRecommendDialog();
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
                getlistSecondSonByApp(mId, mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistSecondSonByApp(mId, 1);
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

    private void initRecycler(ReplyListBean.RowsBean secondReplyListBean) {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        View headerView = setHeaderView(secondReplyListBean);
//        List<ReplyListBean.RowsBean.SocialSonListBean> socialSonList = secondReplyListBean.getSocialSonList();
        mAdapter = new SecondRecommendAdapter(R.layout.item_second_recommend, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //条目点击事件 功能 对孙评论进行评论
//                showRecommendDialog();
                showItemRecommendDialog(position);
            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.addHeaderView(headerView);
        mRefreshLayout.autoRefresh();
    }

    private View setHeaderView(ReplyListBean.RowsBean secondReplyListBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.header_second_recommend, (ViewGroup) mRecycler.getParent(), false);

        AppCompatImageView head_portrait = view.findViewById(R.id.head_portrait);

        TextView tv_name = view.findViewById(R.id.tv_name);

        TextView tv_content = view.findViewById(R.id.tv_content);

        TextView tv_send_time = view.findViewById(R.id.tv_send_time);

        if (secondReplyListBean != null) {

            ImgLoadUtils.loadImage(mContext, ApiService.IMG_BASE_URL + secondReplyListBean.getTouxiangurl(), head_portrait);

            if (!TextUtils.isEmpty(secondReplyListBean.getStudentname())) {
                tv_name.setText(secondReplyListBean.getStudentname());
            }
            if (!TextUtils.isEmpty(secondReplyListBean.getSenddate())) {
                tv_send_time.setText(secondReplyListBean.getSenddate());
            }
            if (!TextUtils.isEmpty(secondReplyListBean.getContent())) {
                tv_content.setText(secondReplyListBean.getContent());
            }
        }

        return view;
    }

    private int maxLen = 500;

    private void showRecommendDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.recommend_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final TextView content_unm = holder.getView(R.id.content_unm);
                        final EditText editText = holder.getView(R.id.edit_input);
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
                                String mSend_recommend = editText.getText().toString();
                                if (!TextUtils.isEmpty(mSend_recommend)) {
                                    addSonToApp(mId, mSend_recommend, -1);

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

    private void showItemRecommendDialog(int position) {
        NiceDialog.init()
                .setLayoutId(R.layout.recommend_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final EditText editText = holder.getView(R.id.edit_input);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                                String studentname = mAdapter.getData().get(position).getStudentname();

                                editText.setHint("回复@" + studentname);
                            }
                        });
                        holder.setOnClickListener(R.id.tv_send_recommend, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String mSend_recommend = editText.getText().toString();
                                //该条评论学生的id
                                int studentid = mAdapter.getData().get(position).getStudentid();
                                addSonToApp(mId, mSend_recommend, studentid);

                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }


    //某个帖子回复列表
    public void getlistSecondSonByApp(int id, int pageNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("parentid", id);
        param.put("type", 2);   // 1代表回复的主帖子   2代表2级回复
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        mConnService.getlistSecondSonByApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<SecondRecommendBean>() {
                    @Override
                    protected void onSuccess(SecondRecommendBean rowsBean) {
                        if (rowsBean != null) {
                            L.d(rowsBean.toString());
                            //当前页
                            mPageNo = rowsBean.getPageNo();

                            List<SecondRecommendBean.RowsBean> rows = rowsBean.getRows();
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
                            } else {
                                if (mRefreshLayout.isLoading()) {
                                    mRefreshLayout.finishLoadmore();
                                } else if (mRefreshLayout.isRefreshing()) {
                                    mRefreshLayout.finishRefresh();
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
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
     * @param
     */
    private void addSonToApp(int socialid, String content, int studentid) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.publish_loading);
        int studentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("parentid", socialid); //帖子id
        if (studentid != -1) {
            param.put("targetid", studentid);//改条评论学生的id （被评论人id）
        }
        param.put("studentid", studentId); //回复人id
        param.put("content", content); //回复内容
        param.put("type", 2);   // 1代表回复的主帖子   2代表2级回复

        mConnService.addSonToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        mRefreshLayout.autoRefresh();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

}
