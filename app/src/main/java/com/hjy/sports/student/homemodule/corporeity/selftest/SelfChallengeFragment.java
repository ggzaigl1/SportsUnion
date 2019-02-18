package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.SaveSelfChallenge;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.rv.decoration.ListItemDecoration;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.GsonUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.img.picker.ImagePicker;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageItem;
import com.fy.img.picker.multiselect.ImgPickerActivity;
import com.fy.luban.Luban;
import com.hjy.sports.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 自我挑战 fragment
 * Created by fangs on 2018/1/25.
 */
public class SelfChallengeFragment extends BaseFragment {

    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.rvSelfDetection)
    RecyclerView rvSelfDetection;
    SelfChallengeFmAdapter adapter;
    private int position;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_self_challenge;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        btnSave.setText("查看结果");
        initRv();
    }

    private List<SelfChallengeBean> getListData() {
        List<SelfChallengeBean> data = new ArrayList<>();
        data.add(new SelfChallengeBean("俯卧撑", ""));
        data.add(new SelfChallengeBean("引体向上", ""));
        data.add(new SelfChallengeBean("仰卧起坐", ""));
        data.add(new SelfChallengeBean("30秒双摇绳", ""));
        data.add(new SelfChallengeBean("倒立", ""));
        data.add(new SelfChallengeBean("原地纵跳摸高", ""));

        return data;
    }

    private void initRv() {
        adapter = new SelfChallengeFmAdapter(mContext, getListData());
        adapter.setListener(new SelfChallengeFmAdapter.OnVideoClickListener() {
            @Override
            public void onVideoClick(SelfChallengeBean item, int position) {
                SelfChallengeFragment.this.position = position;
                Bundle bundle = new Bundle();
                bundle.putInt(PickerConfig.KEY_MAX_COUNT, 1);
                bundle.putBoolean(PickerConfig.KEY_ISTAKE_picture, true);
                JumpUtils.jump(SelfChallengeFragment.this, bundle, ImgPickerActivity.class, ImagePicker.Picture_Selection);

            }
        });

        rvSelfDetection.setLayoutManager(new LinearLayoutManager(mContext));
        rvSelfDetection.addItemDecoration(new ListItemDecoration(mContext, 0));
        rvSelfDetection.setAdapter(adapter);
    }

    @OnClick({R.id.btnSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSave://确认提交
                if (btnSave.getText().toString().equals("确认提交")) {
                    saveSelfChallenge();
                } else {
                    JumpUtils.jump(mContext, SelfChallengeActivity.class, null);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && null != data) {
            switch (requestCode) {
                case ImagePicker.Picture_Selection:
                    Bundle bundle = data.getExtras();
                    ImageItem imgItem = (ImageItem) bundle.getSerializable("pickerImgData");
                    if (null == imgItem) return;
                    SelfChallengeBean bean = adapter.getmDatas().get(position);
                    bean.setVideoPath(imgItem.getPath());
                    adapter.notifyItemChanged(position, bean);
                    btnSave.setText("确认提交");
                    break;
            }
        }
    }

    /**
     * 变量adapter 获取 选择的视频列表
     *
     * @return
     */
    public List<String> getVideoList() {
        List<String> videoPathList = new ArrayList<>();

        if (null != adapter) {
            List<SelfChallengeBean> data = adapter.getmDatas();
            for (SelfChallengeBean bean : data) {
                if (!TextUtils.isEmpty(bean.getVideoPath())) videoPathList.add(bean.getVideoPath());
            }
        }

        return videoPathList;
    }

    /**
     * 自我挑战 保存接口
     */
    private void saveSelfChallenge() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.upLoading);
        //选择了 头像 1、压缩；2、上传头像图片；3、修改学生信息
        Observable.just(getVideoList())
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(mContext)
                                .load(list)
                                .ignoreBy(100)
                                .setTargetDir(FileUtils.getPath("head.img.temp"))
                                .get();
                    }
                })
                .flatMap(new Function<List<File>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(List<File> files) throws Exception {

                        return mConnService.uploadPostFile(
                                RequestBody.create(MediaType.parse("multipart/form-data"), ConstantUtils.token),
                                RequestBody.create(MediaType.parse("multipart/form-data"), "selfchallenge"),
                                NetUtils.fileToMultipartBodyPart(files)
                        ).compose(RxHelper.handleResult())
                                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(String strings) throws Exception {

                        if (TextUtils.isEmpty(strings)) strings = "";

                        String[] imgArray = strings.split(",");
                        List<SaveSelfChallenge> list = new ArrayList<>();
                        int counter = 0;
                        List<SelfChallengeBean> data = adapter.getmDatas();

                        for (int i = 0; i < data.size(); i++) {
                            if (!TextUtils.isEmpty(data.get(i).getVideoPath())) {
                                list.add(new SaveSelfChallenge(ConstantUtils.studentID,
                                        data.get(i).getTypeName(),
                                        imgArray[counter++]));
                            }
                        }

                        String json = GsonUtils.listToJson(list);

                        return mConnService.saveSelfChallengeList(ConstantUtils.token, json)
                                .compose(RxHelper.handleResult())
                                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable));
                    }
                })
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        List<SelfChallengeBean> data = adapter.getmDatas();
                        for (int position = 0; position < data.size(); position++) {
                            SelfChallengeBean bean = adapter.getmDatas().get(position);
                            if (!TextUtils.isEmpty(bean.getVideoPath())) {
                                bean.setVideoPath("");
                                adapter.setData(position, bean);
                                adapter.notifyItemChanged(position);
                            }
                        }
                        btnSave.setText("查看结果");
                        JumpUtils.jump(mContext, SelfChallengeActivity.class, null);
                    }

                    @Override
                    protected void updataLayout(int flag) {
                    }
                });
    }

    /**
     * 多视频上传
     * 自我挑战 保存
     *
     * @param data
     */
//    private void saveSelfChallengeList(List<String> data) {
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.upLoading);
//
//        mConnService.uploadVideo(
//                RequestBody.create(MediaType.parse("multipart/form-data"), ConstantUtils.token),
//                RequestBody.create(MediaType.parse("multipart/form-data"), "media"),
//                NetUtils.fileToMultipartBodyParts("video/", "imgFile", data)).compose(RxHelper.handleResult())
//                .flatMap(new Function<List<String>, ObservableSource<Object>>() {
//                    @Override
//                    public ObservableSource<Object> apply(List<String> strings) throws Exception {
//                        List<SaveSelfChallenge> list = new ArrayList<>();
//
//                        int counter = 0;
//                        List<SelfChallengeBean> data = adapter.getmDatas();
//                        for (int i = 0; i < data.size(); i++) {
//                            if (!TextUtils.isEmpty(data.get(i).getVideoPath())) {
//                                list.add(new SaveSelfChallenge(ConstantUtils.studentID,
//                                        data.get(i).getTypeName(),
//                                        strings.get(counter++)));
//                            }
//                        }
//
//                        String json = GsonUtils.listToJson(list);
//                        L.e("Fragment", json);
//
//                        return mConnService.saveSelfChallengeList(ConstantUtils.token, json)
//                                .compose(RxHelper.handleResult());
//                    }
//                })
//                .subscribe(new NetCallBack<Object>(progressDialog) {
//                    @Override
//                    public void onSuccess(Object homeBean) {
//                        L.e("提交成功");
//
//                        List<SelfChallengeBean> data = adapter.getmDatas();
//                        for (int position = 0; position < data.size(); position++) {
//                            SelfChallengeBean bean = adapter.getmDatas().get(position);
//                            if (!TextUtils.isEmpty(bean.getVideoPath())) {
//                                bean.setVideoPath("");
//                                adapter.setData(position, bean);
//                                adapter.notifyItemChanged(position);
//                            }
//                        }
//                        btnSave.setText("查看结果");
//                        JumpUtils.jump(mContext, SelfChallengeActivity.class, null);
//                    }
//
//                    @Override
//                    public void updataLayout(int flag) {
//                    }
//                });
//    }
}
