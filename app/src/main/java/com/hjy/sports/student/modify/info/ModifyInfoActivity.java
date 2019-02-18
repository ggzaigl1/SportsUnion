package com.hjy.sports.student.modify.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.KeyBoardUtils;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.Validator;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.ImagePicker;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageItem;
import com.fy.luban.Luban;
import com.hjy.sports.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 修改个人信息
 * Created by fangs on 2017/12/18.
 */
public class ModifyInfoActivity extends BaseActivity {

    @BindView(R.id.imgHeadModify)
    AppCompatImageView imgHeadModify;
    @BindView(R.id.tvSexModify)
    TextView tvSexModify;
    @BindView(R.id.tvBirthdayModify)
    TextView tvBirthdayModify;
    @BindView(R.id.tvGradeModify)
    TextView tvGradeModify;
    @BindView(R.id.tvClassGradeModify)
    TextView tvClassGradeModify;
    @BindView(R.id.tvNationModify)
    TextView tvNationModify;
    @BindView(R.id.tvSchoolModify)
    TextView tvSchoolModify;
    @BindView(R.id.tvAgeModify)
    TextView tvAgeModify;
    @BindView(R.id.tvHeightModify)
    TextView tvHeightModify;
    @BindView(R.id.tvWeightModify)
    TextView tvWeightModify;
    @BindView(R.id.tvParentPhoneModify)
    EditText tvParentPhoneModify;


    String phone;

    List<String> fileList;
    String imgHead = "";//头像地址

    @Override
    protected int getContentView() {
        return R.layout.activity_modify_info;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText(R.string.myData);
        tvMenu.setText(R.string.complete);

        boolean isPhone = getIntent().getExtras().getBoolean("isPhone");

        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
        if (null != studentInfo) {
            imgHead = TextUtils.isEmpty(studentInfo.getTouxiangurl()) ? "" : studentInfo.getTouxiangurl();
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + imgHead, imgHeadModify);
            tvSexModify.setText(studentInfo.getSex().equals("1") ? "男" : "女");
            tvBirthdayModify.setText(studentInfo.getBirthday());
            tvGradeModify.setText(studentInfo.getNname());
            tvClassGradeModify.setText(studentInfo.getBname());
//            tvNationModify.setText(studentInfo.getMinzu());
            tvSchoolModify.setText(studentInfo.getSchoolname());
            tvAgeModify.setText(studentInfo.getAge() + "");
            tvHeightModify.setText(studentInfo.getHeight() + "");
            tvWeightModify.setText(studentInfo.getWeight() + "");
            phone = tvParentPhoneModify.getText().toString();
            tvParentPhoneModify.setText(studentInfo.getJiazhangphone());
            if (!TextUtils.isEmpty(studentInfo.getJiazhangphone())) {
                tvParentPhoneModify.setSelection(studentInfo.getJiazhangphone().length());
                tvParentPhoneModify.clearFocus();
            }
        }

        if (isPhone) { //是进入H5之前来获取手机号码的 1.获取焦点 2.弹出软键盘
            tvParentPhoneModify.setFocusable(true);
            tvParentPhoneModify.setFocusableInTouchMode(true);
            tvParentPhoneModify.requestFocus();
//            KeyBoardUtils.openKeyBoard(ModifyInfoActivity.this);
            KeyBoardUtils.openKeyBoard(ModifyInfoActivity.this, tvParentPhoneModify);
        }

    }

    @OnClick({R.id.llHead, R.id.llSex, R.id.llBirthday, R.id.llGrade, R.id.llClassGrade,
            R.id.llNation, R.id.llSchool, R.id.llAge, R.id.llHeight, R.id.llWeight, R.id.Ll_phone})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvMenu://完成
                upload(fileList);
                break;
            case R.id.llHead://修改头像
                if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                    permissionChecker.requestPermissions();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(PickerConfig.KEY_MAX_COUNT, 1);
                    bundle.putBoolean(PickerConfig.KEY_ISTAKE_picture, true);
                    JumpUtils.jump(mContext, bundle, "com.fy.img.picker.multiselect.ImgPickerActivity", ImagePicker.Picture_Selection);
                }
                break;
            case R.id.Ll_phone://修改电话
                tvParentPhoneModify.setFocusable(true);
                tvParentPhoneModify.setFocusableInTouchMode(true);
                tvParentPhoneModify.requestFocus();
                KeyBoardUtils.openKeyBoard(ModifyInfoActivity.this);
//                phone = tvParentPhoneModify.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("phone", phone);
//                JumpUtils.jump(ModifyInfoActivity.this, bundle, "com.hjy.sports.student.modify.info.CustomerActivity", ImagePicker.Phone_Number);
                break;
            case R.id.tvParentPhoneModify://修改电话
                phone = tvParentPhoneModify.getText().toString();
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("phone", phone);
//                JumpUtils.jump(ModifyInfoActivity.this, bundle1, "com.hjy.sports.student.modify.info.CustomerActivity", ImagePicker.Phone_Number);
                break;
            case R.id.llSex://修改性别

                break;
            case R.id.llBirthday://修改生日
//                DateSelectUtils.getDatePicker(mContext, date ->
//                    tvBirthdayModify.setText(TimeUtils.Data2String(date, "yyyyMMdd"))
//                ).show();
                break;
            case R.id.llGrade://修改年级

                break;
            case R.id.llClassGrade://修改班级

                break;
            case R.id.llNation://修改民族

                break;
            case R.id.llSchool://修改学校

                break;
            case R.id.llAge://修改年龄

                break;
            case R.id.llHeight://修改身高

                break;
            case R.id.llWeight://修改体重

                break;
//            case R.id.llParentPhone://修改家长电话
//
//                showEditDialog();
//                break;
//            case R.id.tvParentPhoneModify:  //修改家长电话 防止点击没有反应
//                showEditDialog();
//                break;
        }
    }

    private void showEditDialog() {
        new EditDialog.Bundler()
                .setTitleId(R.string.parentPhone)
                .setListener((dialog, editText) -> {
                    String inputContent = editText.getText().toString().trim();
                    if (Validator.isMobile(inputContent)) {
                        tvParentPhoneModify.setText(inputContent);

                        dialog.dismiss();

                    } else {
//                                editText.setText("");
                        T.showLong("请输入正确的手机号");
                    }

                }).create()
                .show(getSupportFragmentManager());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            Bundle bundle = data.getExtras();
            switch (requestCode) {
                case ImagePicker.Picture_Selection:
                    ImageItem imgItem = (ImageItem) bundle.getSerializable("pickerImgData");
                    if (null == imgItem) return;
                    ImgLoadUtils.loadCircleImg(imgItem.getPath(), imgHeadModify);
                    fileList = new ArrayList<>();
                    fileList.add(imgItem.getPath());
                    break;
                case ImagePicker.Phone_Number:
                    String context = bundle.getString("context", "-1");//得到新Activity关闭后返回的数据
                    tvParentPhoneModify.setText(context);
                    break;
            }
        }
    }

    //修改学生个人信息
    private void modifyInfo(String phone, String imgHead) {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext)
                .setDialogMsg(R.string.loading_modify);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", ConstantUtils.studentID);
        param.put("jiazhangphone", phone);
        param.put("touxiangurl", imgHead);//头像URL

        new RxNetCache.Builder().create().request(mConnService.updateInfo(param)
                .compose(RxHelper.handleResult()).doOnSubscribe(disposable -> mCompositeDisposable.add(disposable)))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    public void onSuccess(Object bean) {
                        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
                        studentInfo.setJiazhangphone(phone);
                        studentInfo.setTouxiangurl(imgHead);

                        mCache.put(ConstantUtils.student, studentInfo);
                        //方便首页获取 不需要重新请求接口
                        SpfUtils.saveStrToSpf("touxiangurl", studentInfo.getTouxiangurl());
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    public void updataLayout(int flag) {
                    }
                });
    }

    //修改头像
    private void upload(List<String> lists) {
        String phoneNum = tvParentPhoneModify.getText().toString().trim();
        if (!TextUtils.isEmpty(phoneNum) && !Validator.isMobile(phoneNum)) {
            T.showShort("请输入正确的手机号");
            return;
        }

        if (null == lists) {//没有修改头像
            modifyInfo(phoneNum, imgHead);
            return;
        }

        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_modify);
        //选择了 头像 1、压缩；2、上传头像图片；3、修改学生信息
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

                        return mConnService.uploadPostFile(
                                RequestBody.create(MediaType.parse("multipart/form-data"), ConstantUtils.token),
                                RequestBody.create(MediaType.parse("multipart/form-data"), "touxiang"),
                                NetUtils.fileToMultipartBodyPart(files)
                        ).compose(RxHelper.handleResult());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(String headImg) throws Exception {
                        if (!TextUtils.isEmpty(headImg)) imgHead = headImg;

                        Map<String, Object> param = new HashMap<>();
                        param.put("token", ConstantUtils.token);
                        param.put("studentid", ConstantUtils.studentID);
                        param.put("jiazhangphone", tvParentPhoneModify.getText().toString().trim());
                        param.put("touxiangurl", imgHead);//头像URL
                        return mConnService.updateInfo(param).compose(RxHelper.handleResult());
                    }
                })
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object t) {
                        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
                        studentInfo.setJiazhangphone(tvParentPhoneModify.getText().toString().trim());
                        studentInfo.setTouxiangurl(imgHead);

                        mCache.put(ConstantUtils.student, studentInfo);
                        //方便首页获取 不需要重新请求接口
                        SpfUtils.saveStrToSpf("touxiangurl", studentInfo.getTouxiangurl());
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    protected void updataLayout(int flag) {
                    }
                });
    }
}
