package com.hjy.sports.student.socialmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.eventbus.RxConstants;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.rv.decoration.SpaceItemDecoration;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.baselibrary.utils.T;
import com.fy.img.picker.ImagePicker;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.img.picker.bean.ImageItem;
import com.fy.img.picker.preview.PicturePreviewActivity;
import com.fy.luban.Luban;
import com.hjy.sports.R;
import com.hjy.sports.widget.dialog.BaseNiceDialog;
import com.hjy.sports.widget.dialog.NiceDialog;
import com.hjy.sports.widget.dialog.ViewConvertListener;
import com.hjy.sports.widget.dialog.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
 * 社交 发布动态 界面
 * Created by fangs on 2017/12/28.
 */
public class AddSocialActivity extends BaseActivity {

    @BindView(R.id.editDynamicDesc)
    EditText editDynamicDesc;
    @BindView(R.id.rvAppendix)
    RecyclerView rvAppendix;
    AddAppendixAdapter adapter;
    @BindView(R.id.content_unm)
    TextView mContentUnm;
    /**
     * 最多选择图片数目
     */
    static final int MAX_SELECT = 9;
    private int maxLen = 500;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_social;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText(R.string.publishingDynamics);
        tvBack.setText(R.string.cancel);
        tvMenu.setText(R.string.publishing);
        initEditText();
        initRv();
    }

    private void initEditText() {
        editDynamicDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Editable editable = editDynamicDesc.getText();
                int length = editable.length();
                if (length > maxLen) {
                    int selectionEnd = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, maxLen);
                    editDynamicDesc.setText(newStr);
                    editable = editDynamicDesc.getText();
                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selectionEnd > newLen) {
                        selectionEnd = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selectionEnd);
                } else {
                    mContentUnm.setText(length + "");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.tvBack})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMenu:
                ArrayList<String> imgPath = getAlreadySelect();
                if (imgPath.size() > 0) {
                    uploadImg(imgPath);
                } else {
                    String content = editDynamicDesc.getText().toString().trim();
                    if (!TextUtils.isEmpty(content)) {
                        addCard("");
                    } else {
                        T.showLong("请输入文本或选择图片");
                    }
                }
                break;
            case R.id.tvBack:
                if (TextUtils.isEmpty(editDynamicDesc.getText().toString().trim()) && adapter.getmDatas().size() == 1) {
                    JumpUtils.exitActivity(this);
                } else {
                    showDeleteDialog();
                }
                break;
        }
    }

    private void showDeleteDialog() {

        NiceDialog.init()
                .setLayoutId(R.layout.layout_delete)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setText(R.id.message, "确认取消此次编辑吗?");
                        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                JumpUtils.exitActivity(AddSocialActivity.this);
                                dialog.dismiss();
                            }
                        });
                    }
                }).setWidth(200)
                .setOutCancel(true)
                .show(mContext.getSupportFragmentManager());
    }

    private void initRv() {
        List<ImageItem> data = new ArrayList<>();
        data.add(new ImageItem(0));
        adapter = new AddAppendixAdapter(mContext, data);
        rvAppendix.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvAppendix.addItemDecoration(new SpaceItemDecoration(24));
        rvAppendix.setAdapter(adapter);

        adapter.setListener(new AddAppendixAdapter.OnItemClickListener() {
            @Override
            public void onClick(boolean isShowCamera, int position) {
                if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                    permissionChecker.requestPermissions();
                } else {
                    List<ImageItem> selectData = new ArrayList<>();
                    for (ImageItem imgItem : adapter.getmDatas()) {
                        if (imgItem.isShowCamera != 0) selectData.add(imgItem);
                    }
                    if (isShowCamera) {//选择图片
                        Bundle bundle = new Bundle();
                        bundle.putInt(PickerConfig.KEY_MAX_COUNT, MAX_SELECT);
                        bundle.putBoolean(PickerConfig.KEY_ISTAKE_picture, true);
                        bundle.putSerializable(PickerConfig.KEY_ALREADY_SELECT, new ImageFolder(selectData));
                        JumpUtils.jump(mContext, bundle, "com.fy.img.picker.multiselect.ImgPickerActivity", ImagePicker.Picture_Selection);
                    } else {
                        AddSocialActivity.this.previewPicture(position, MAX_SELECT, new ImageFolder(selectData));
                        //TODO 需求更改 注释
//                DeleteTipsDialog dialog = new DeleteTipsDialog();
//                dialog.setListener(() -> {
//                    adapter.removeData(position);
//                    adapter.notifyDataSetChanged();
//                });
//                dialog.show(getSupportFragmentManager());
                    }
                }
            }
        });
    }

    /**
     * 预览大图
     *
     * @param position  首先显示的图片
     * @param maxCount  最多选择图片数目
     * @param imgFolder 已经选择的图片 文件夹
     */
    private void previewPicture(int position, int maxCount, ImageFolder imgFolder) {
        Bundle bundle = new Bundle();
        bundle.putInt(PickerConfig.KEY_MAX_COUNT, maxCount);
        bundle.putInt(PickerConfig.KEY_CURRENT_POSITION, position);
        bundle.putSerializable(PickerConfig.KEY_IMG_FOLDER, imgFolder);
        bundle.putSerializable(PickerConfig.KEY_ALREADY_SELECT, imgFolder);

        JumpUtils.jump(mContext, PicturePreviewActivity.class, bundle, ImagePicker.Picture_Preview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            Bundle bundle = data.getExtras();
            ImageFolder imgFolder = (ImageFolder) bundle.getSerializable("ImageFolder");

            switch (requestCode) {
                case ImagePicker.Picture_Selection:
                    if (null != imgFolder.images) {
                        if (imgFolder.images.size() < MAX_SELECT) {
                            imgFolder.images.add(new ImageItem(0));
                            adapter.setmDatas(imgFolder.images);
                        } else if (imgFolder.images.size() == MAX_SELECT) {
                            adapter.setmDatas(imgFolder.images);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    break;
                case ImagePicker.Picture_Preview:
                    if (null != imgFolder.images) {
                        if (imgFolder.images.size() < MAX_SELECT) {
                            imgFolder.images.add(new ImageItem(0));
                            adapter.setmDatas(imgFolder.images);
                        } else if (imgFolder.images.size() == MAX_SELECT) {
                            adapter.setmDatas(imgFolder.images);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    //上传图片
    private void uploadImg(List<String> lists) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.upLoading);
        Observable.just(lists)
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
                        return mConnService.uploadPostFile(RequestBody.create(MediaType.parse("multipart/form-data"),
                                ConstantUtils.token),
                                RequestBody.create(MediaType.parse("multipart/form-data"), "social"),
                                NetUtils.fileToMultipartBodyPart(files))
                                .compose(RxHelper.handleResult());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(String s) throws Exception {
                        String trim = "";
                        if (null == editDynamicDesc || TextUtils.isEmpty(s)) {

                        }
                        if (!TextUtils.isEmpty(editDynamicDesc.getText().toString().trim())) {
                            trim = editDynamicDesc.getText().toString().trim();
                        }

                        Map<String, Object> param = new HashMap<>();
                        param.put("token", ConstantUtils.token);
                        param.put("studentid", ConstantUtils.studentID);
                        param.put("content", trim);
                        param.put("imageurl", s);

                        return mConnService.addToApp(param).compose(RxHelper.handleResult());
                    }
                })
                .throttleFirst(2, TimeUnit.SECONDS)  //仿抖动 2s内只请求一次
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    public void onSuccess(Object bean) {
                        T.showShort("上传成功");
//                                RxBus.getDefault().postWithCode(RxConstants.PUBLISH_SUCCEED,RxConstants.STRING_PUBLISH_SUCCEED);
                        EventBus.getDefault().post(RxConstants.STRING_PUBLISH_SUCCEED);
                        JumpUtils.exitActivity(mContext);

                    }

                    @Override
                    public void updataLayout(int flag) {
                        L.e("login_失败");
                    }
                });
    }

    private void addCard(String pathList) {
        String trim = "";
        if (null != editDynamicDesc) {
            if (!TextUtils.isEmpty(editDynamicDesc.getText().toString().trim())) {
                trim = editDynamicDesc.getText().toString().trim();
            }
        }

        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", ConstantUtils.studentID);
        param.put("content", trim);
        param.put("imageurl", pathList);

        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.upLoading);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.addToApp(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    public void onSuccess(Object bean) {
                        T.showShort("上传成功");
//                                RxBus.getDefault().postWithCode(RxConstants.PUBLISH_SUCCEED,RxConstants.STRING_PUBLISH_SUCCEED);
                        EventBus.getDefault().post(RxConstants.STRING_PUBLISH_SUCCEED);
                        JumpUtils.exitActivity(mContext);

                    }

                    @Override
                    public void updataLayout(int flag) {
                        L.e("login_失败");
                    }
                });
    }

    private ArrayList<String> getAlreadySelect() {
        ArrayList<String> imgPath = new ArrayList<>();
        for (ImageItem item : adapter.getmDatas()) {
            if (!TextUtils.isEmpty(item.path)) {
                imgPath.add(item.path);
            }
        }

        return imgPath;
    }

}
