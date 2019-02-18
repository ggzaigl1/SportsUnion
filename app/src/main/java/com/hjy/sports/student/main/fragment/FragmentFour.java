package com.hjy.sports.student.main.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.eventbus.DeletedEvent;
import com.fy.baselibrary.eventbus.PraiseEvent;
import com.fy.baselibrary.eventbus.RecommendNumEvent;
import com.fy.baselibrary.eventbus.RxConstants;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.ScreenUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.socialmodule.AddSocialActivity;
import com.hjy.sports.student.socialmodule.SocialAdapter;
import com.hjy.sports.student.socialmodule.friendsocial.FriendSocialActivity;
import com.hjy.sports.student.socialmodule.mysocial.MySocialActivity;
import com.hjy.sports.student.socialmodule.socaildetail.MySocialDetailActivity;
import com.hjy.sports.student.socialmodule.socaildetail.SocialDetailActivity;
import com.hjy.sports.widget.OnDoubleClickListener;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 修改：一般情况下都不需要自动刷新 只有自己发布消息回来本界面时才自动刷新
 * Created by Administrator on 2017/12/12.
 */
public class FragmentFour extends BaseFragment implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvBack)
    TextView mTvBack;
    @BindView(R.id.tvMenu)
    TextView mTvMenu;
    @BindView(R.id.inHead)
    View inHead;
    private AppCompatImageView mSocial_header_img;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private int mPageNo = 1;
    private SocialAdapter mAdapter;
    private LoginBean.StudentBean mStudentInfo;
    private AppCompatImageView mImgHeader;
    private TextView mUser_name;
    private int mStudentId;
    private int mLikenumber;


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_four;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void baseInit() {
        EventBus.getDefault().register(this);
//        动态设置状态栏高度
        MdStatusBarCompat.setStatusView(mContext, statusView);
        mTvTitle.setText(getString(R.string.mainTabFour));
        mTvBack.setVisibility(View.GONE);
        mTvMenu.setText("");
        TintUtils.setCompoundDrawable(mTvMenu, R.drawable.vector_add, 1);

        initRefresh();
        initRecycler();

        mStudentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
        if (null != mStudentInfo) {
            //姓名
            mUser_name.setText(mStudentInfo.getName());
            //头像
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + mStudentInfo.getTouxiangurl(), mImgHeader);
        }
        getlistAllByApp(1);
        inHead.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.DoubleClickCallback() {
            @Override
            public void onDoubleClick() {
                mRecycler.smoothScrollToPosition(0);
                getlistAllByApp(1);
            }
        }));
    }

    @OnClick({R.id.tvMenu})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvMenu:
                JumpUtils.jump(mContext, AddSocialActivity.class, null);
                break;
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

    /**
     * 发布消息事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void PublishEvent(String event) {
        if (!TextUtils.isEmpty(event) && event.equals(RxConstants.STRING_PUBLISH_SUCCEED)) {
            if (null != mRefreshLayout)
                mRefreshLayout.autoRefresh();
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
     * 登录之后自动刷新  以保证是当前学生的信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginEvent(String event) {
        if (!TextUtils.isEmpty(event) && event.equals(RxConstants.LOGIN_SUCCEED)) {
            mAdapter.getData().clear();
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mStudentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
            if (null != mStudentInfo) {
                //姓名
                mUser_name.setText(mStudentInfo.getName());
                //头像
                ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + mStudentInfo.getTouxiangurl(), mImgHeader);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getlistAllByApp(int pageNo) {
        mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);

        new RxNetCache.Builder().create()
                .request(mConnService.getlistAllByApp(param).compose(RxHelper.handleResult()))
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<SocialAllListBean>() {
                    @Override
                    protected void onSuccess(SocialAllListBean allListBean) {
                        if (allListBean != null) {
                            L.d(allListBean.toString());
                            //当前页
                            mPageNo = allListBean.getPageNo();
                            List<SocialAllListBean.RowsBean> rows = allListBean.getRows();
                            if (!rows.isEmpty()) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(rows);
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(rows);
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
//                                            mAdapter.addData(rows);
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


    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getlistAllByApp(mPageNo);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistAllByApp(1);
            }
        });
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        View view = LayoutInflater.from(mContext).inflate(R.layout.social_header_view, (ViewGroup) mRecycler.getParent(), false);
        //背景图
//        mSocial_header_img = view.findViewById(R.id.social_header_img);
//        mSocial_header_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTakePhotoDialog();
//            }
//        });
        //头像
        mImgHeader = view.findViewById(R.id.social_header_photo);
        mImgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转进入我的发过出的信息界面
                Bundle bundle = new Bundle();
                bundle.putSerializable("StudentInfo", mStudentInfo);
                JumpUtils.jump(mContext, MySocialActivity.class, bundle);
            }
        });
        //姓名
        mUser_name = view.findViewById(R.id.tv_user_name);

        mAdapter = new SocialAdapter(R.layout.item_my_social_contact, new ArrayList<>());
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
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
                        if (mStudentId == mAdapter.getData().get(position).getStudentid()) {
                            //这是我发布的信息
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("rowsBean", rowsBean);
                            JumpUtils.jump(mContext, MySocialDetailActivity.class, bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("rowsBean", rowsBean);
//                            bundle.putInt("position", position);
                            JumpUtils.jump(mContext, SocialDetailActivity.class, bundle);
                        }
                        break;
                    case R.id.iv_delete:  //删除自己发的帖子
                        showDeleteDialog(position);
                        break;
                    case R.id.iv_user_head: //点击头像 根据studentId 来跳转自己或者是朋友的个人圈
                        if (mStudentId == mAdapter.getData().get(position).getStudentid()) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("StudentInfo", mStudentInfo);
                            JumpUtils.jump(mContext, MySocialActivity.class, bundle);
                        } else {
                            SocialAllListBean.RowsBean friend_rowsBean = mAdapter.getData().get(position);
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", position);
                            bundle.putSerializable("StudentInfo", friend_rowsBean);
                            JumpUtils.jump(mContext, FriendSocialActivity.class, bundle);
                        }
                        break;
                    default:
                        break;

                }
            }
        });
        //条目点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //进入我详情界面 有可能是我的发的说说 也有可能是好友发的说说 需要根据studentid 来判断下
                SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                if (mStudentId == mAdapter.getData().get(position).getStudentid()) {
                    //这是我发布的信息
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rowsBean", rowsBean);
                    JumpUtils.jump(mContext, MySocialDetailActivity.class, bundle);
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rowsBean", rowsBean);
                    bundle.putInt("position", position);
                    JumpUtils.jump(mContext, SocialDetailActivity.class, bundle);
                }

            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.setHeaderView(view);

    }

    //删除提醒框
    private void showDeleteDialog(int position) {
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
                .show(mContext.getSupportFragmentManager());
    }

    //删除帖子
    private void deleteToApp(int socialid, int position) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.delete);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("socialid", socialid);
        mConnService.deleteToApp(param).compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mAdapter.getData().remove(position);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

    //点赞
    private void addLikeToApp(int socialid, View view, int position) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("socialid", socialid);

        mConnService.addLikeToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        rowsBean.setLikenumber(mLikenumber + 1);
                        rowsBean.setIslike(1);
                        mAdapter.notifyItemChanged(position + 1);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

    //取消点赞
    private void deleteLikeToApp(int socialid, View view, int position) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        param.put("socialid", socialid);
        mConnService.deleteLikeToApp(param)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        SocialAllListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                        rowsBean.setLikenumber(mLikenumber - 1);
                        rowsBean.setIslike(0);
                        mAdapter.notifyItemChanged(position + 1);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });

    }

    //**************更换背景 开始*******************************************
    private void showTakePhotoDialog() {

        final String items[] = {"拍照", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("选择背景");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                takeOrPickPhoto(which == 0);
            }
        });
        builder.create().show();

    }

    private void takeOrPickPhoto(boolean isTakePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        TakePhoto takePhoto = getTakePhoto();

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        if (isTakePhoto) {//拍照
            if (true) {//是否裁剪
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
            } else {
                takePhoto.onPickFromCapture(imageUri);
            }
        } else {//选取图片
            int limit = 1;//选择图片的个数。
            if (limit > 1) {
                //当选择图片大于1个时是否裁剪
                if (true) {
                    takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
                } else {
                    takePhoto.onPickMultiple(limit);
                }
                return;
            }
            //是否从文件中选取图片
            if (false) {
                if (true) {//是否裁剪
                    takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromDocuments();
                }
                return;
            } else {
                if (true) {//是否裁剪
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromGallery();
                }
            }
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    /***********************************InvokeListener 权限开始**********/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(mContext, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(mContext), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }
    /***********************************InvokeListener 权限结束**********/

    /***********************************TakePhoto.TakeResultListener**********/
    @Override
    public void takeSuccess(TResult result) {
        L.i("takeSuccess：" + result.getImage().getCompressPath());
        String compressPath = result.getImage().getCompressPath();
        String originalPath = result.getImage().getOriginalPath();
        Observable.just(compressPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Glide.with(mContext).load(new File(s)).into(mSocial_header_img);
                    }
                });
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    /***********************************TakePhoto.TakeResultListener****************************************/


    /**
     * 拍照相关的配置
     *
     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        //是否使用Takephoto自带的相册
        if (false) {
            builder.setWithOwnGallery(true);
        }
        //纠正拍照的旋转角度
        if (true) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());
    }

    /**
     * 配置 压缩选项
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 102400;
        int width = 800;
        int height = 800;
        //是否显示 压缩进度条
        boolean showProgressBar = true;
        //压缩后是否保存原图
        boolean enableRawFile = true;
        CompressConfig config;
        if (false) {
            //使用自带的压缩工具
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            //使用开源的鲁班压缩工具
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config, showProgressBar);
    }

    /**
     * 配置 裁剪选项
     *
     * @return
     */
    private CropOptions getCropOptions() {
        int height = 400;
        int width = ScreenUtils.getScreenWidth(mContext);

        CropOptions.Builder builder = new CropOptions.Builder();

        //按照宽高比例裁剪
        builder.setAspectX(width).setAspectY(height);
        //是否使用Takephoto自带的裁剪工具
        builder.setWithOwnCrop(false);
        return builder.create();
    }


    //**************更换背景 结束*******************************************

}
