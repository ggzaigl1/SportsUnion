package com.hjy.sports.student.homemodule.expanded.ornamental;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.CourseDetails;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2018/3/13 0013.
 * 运动课程内容
 */

public class OrnamentalContextActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContext;
    @BindView(R.id.tv_announcements)
    TextView tv_announcements;//注意事项
    @BindView(R.id.club_details_bg)
    AppCompatImageView mClubDetailsBg;
    @BindView(R.id.tv_do)
    TextView mTvDo;
    @BindView(R.id.tv_point)
    TextView mTvPoint;

    private OrnamentalContextAdapter mAdapter;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_ornamental_context);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        String id = getIntent().getExtras().getString("id");
        getCourseDetails(id);
        initRecycle();

    }

    private void getCourseDetails(String id) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        mConnService.getCourseDetails(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .subscribe(new NetCallBack<CourseDetails>(progressDialog) {
                    @Override
                    protected void onSuccess(CourseDetails t) {
                        L.e(t.getMessage());
                        if (t.getResult() == 1) {
                            CourseDetails.DataBean data = t.getData();
                            if (null != data) {
                                mTvTitle.setText(data.getTitle());
                                mTvContext.setText(data.getSubTitle());
                                tv_announcements.setText(data.getNotes());
                                ImgLoadUtils.loadImage(mContext, data.getPic(), mClubDetailsBg);
                                mAdapter.setNewData(data.getGroups().get(0).getActions());
                                mTvPoint.setText("注意事项");
                                mTvDo.setText("训练动作");
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @OnClick({R.id.tv_back})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_back:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }

    /**
     * 训练课程列表
     */
    private void initRecycle() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new OrnamentalContextAdapter(R.layout.ornamental_context_recycle_item, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CourseDetails.DataBean.GroupsBean.ActionsBean> data = mAdapter.getData();
                OrListBean orListBean = new OrListBean(data);
                Bundle bundle = new Bundle();
                bundle.putSerializable("actionsBean", orListBean);
                bundle.putInt("position", position);
                JumpUtils.jump(mContext, OrnamentalMotionActivity.class, bundle);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
