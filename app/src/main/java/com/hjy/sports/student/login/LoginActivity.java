package com.hjy.sports.student.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.eventbus.RxConstants;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.KeyBoardUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.permission.PermissionChecker;
import com.githang.statusbar.StatusBarCompat;
import com.hjy.sports.R;
import com.hjy.sports.util.ActJump;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by fangs on 2017/12/12.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.cBoxPass)
    CheckBox cBoxPass;
    @BindView(R.id.tvLoginTitle)
    TextView tvLoginTitle;

    @BindView(R.id.editUser)
    EditText editUser;
    @BindView(R.id.editPass)
    EditText editPass;
    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.iv_account_delete)
    AppCompatImageView mIvAccountDelete;
    @BindView(R.id.iv_password_delete)
    AppCompatImageView mIvPasswordDelete;
    private String mUserName;
    private String mPassWord;
    private boolean mUntoken;

    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void setStatusBarType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MdStatusBarCompat.setImageTransparent(this);
            MdStatusBarCompat.StatusBarLightMode(this);
            mContext.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            StatusBarCompat.setStatusBarColor(this, R.color.black);
        }
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        permissionChecker = new PermissionChecker(mContext);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (!permissionChecker.isLackPermissions(PERMISSIONS)) {
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                    .setTitle(R.string.require_permission)
                    .setMessage(R.string.check_permission)
                    .setCancelable(false)
                    .setNegativeButton(getString(R.string.next), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                                permissionChecker.requestPermissions();
                            }
                        }
                    });
            builder.show();
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mUntoken = getIntent().getExtras().getBoolean("untoken");
        Spannable sp = new SpannableString(getString(R.string.loginTitel));
        sp.setSpan(new AbsoluteSizeSpan(20, true), 0, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSuperColor)),
                0, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sp.setSpan(new AbsoluteSizeSpan(14, true), 15, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                15, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvLoginTitle.setText(sp);

        Spannable sp1 = new SpannableString(getString(R.string.loginTitel2));
        sp1.setSpan(new AbsoluteSizeSpan(20, true), 0, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSuperColor)),
                0, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sp1.setSpan(new AbsoluteSizeSpan(14, true), 10, sp1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                10, sp1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mLoginTitle.setText(sp1);

        //设置缓存的登录 账号 密码
        editUser.setText(SpfUtils.getSpfSaveStr("username"));
        if (SpfUtils.getSpfSaveBoolean("isPassword")) {
            editPass.setText(SpfUtils.getSpfSaveStr("password"));
            editUser.setSelection(editUser.getText().length());
            cBoxPass.setChecked(true);
        }

        editUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mIvAccountDelete.setVisibility(View.VISIBLE);
                } else {
                    mIvAccountDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mIvPasswordDelete.setVisibility(View.VISIBLE);
                } else {
                    mIvPasswordDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    /**
     * 检查权限
     */
    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                } else {
                    //权限获取失败
                    permissionChecker.showDialog(1);
                }
                break;
        }
    }

    @OnClick({R.id.tvLogin, R.id.iv_account_delete, R.id.iv_password_delete})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvLogin:
                login();
                break;
            case R.id.iv_account_delete:
                editUser.setText("");
                KeyBoardUtils.openKeyBoard(LoginActivity.this);
                break;
            case R.id.iv_password_delete:
                editPass.setText("");
                KeyBoardUtils.openKeyBoard(LoginActivity.this);
                break;
        }
    }

    private void login() {
        mUserName = editUser.getText().toString().trim();
        mPassWord = editPass.getText().toString().trim();
        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassWord)) {
            T.showLong(R.string.numPass);
            return;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.user_login);
        mConnService.loginToApp(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<LoginBean>(progressDialog) {
                    @Override
                    protected void onSuccess(LoginBean loginBean) {

                        L.e("login_成功");
                        if (null != loginBean) {
                            mCache.put("LoginBean", loginBean);

                            //获取最新的token
                            ConstantUtils.token = loginBean.getToken();
                            ConstantUtils.role  = loginBean.getRole();
                            mCache.put("token", ConstantUtils.token, 14400);//token 超时时间
                            mCache.put("role", ConstantUtils.role);//role 角色

                            if (null != loginBean.getStudent()) {
                                //缓存学生信息 和 单独换粗学生id , 单独学生头像
                                mCache.put(ConstantUtils.student, loginBean.getStudent());
                                ConstantUtils.studentID = loginBean.getStudent().getStudentid();
                                ConstantUtils.head_portrait = loginBean.getStudent().getTouxiangurl();
                                SpfUtils.saveIntToSpf("studentId", ConstantUtils.studentID);
                                SpfUtils.saveStrToSpf("touxiangurl", ConstantUtils.head_portrait);
                            }

                            if (mUntoken) { //token失效
                                String username = SpfUtils.getSpfSaveStr("username");
                                String string = editUser.getText().toString();
                                //切换账号了
                                if (!string.equals(username)) {

                                    //校园圈自动刷新 保证是当前学生的能看到的信息  和 体质接口
                                    EventBus.getDefault().post(RxConstants.LOGIN_SUCCEED);

                                    SpfUtils.saveBooleanToSpf("loginShowHome", true);

                                    //缓存 登录账号
                                    SpfUtils.saveStrToSpf("username", mUserName);
                                    SpfUtils.saveStrToSpf("password", mPassWord);
                                    if (cBoxPass.isChecked()) {//是否缓存 登录密码
                                        SpfUtils.saveBooleanToSpf("isPassword", true);
                                    }
                                    ActJump.jumpRole(mContext, ConstantUtils.role);
                                } else {
                                    JumpUtils.exitActivity(LoginActivity.this);
                                }
                            } else {
                                EventBus.getDefault().post(RxConstants.LOGIN_SUCCEED);

                                SpfUtils.saveBooleanToSpf("loginShowHome", true);

                                //缓存 登录账号
                                SpfUtils.saveStrToSpf("username", mUserName);
                                SpfUtils.saveStrToSpf("password", mPassWord);
                                if (cBoxPass.isChecked()) {//是否缓存 登录密码
                                    SpfUtils.saveBooleanToSpf("isPassword", true);
                                }
                                ConstantUtils.isfirstOpenApp = "userIsFirstOpenApp";

                                ActJump.jumpRole(mContext, ConstantUtils.role);
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (editUser.getText().toString().trim().length() > 0) {
            mIvAccountDelete.setVisibility(View.VISIBLE);
        } else {
            mIvAccountDelete.setVisibility(View.GONE);
        }
        if (editPass.getText().toString().trim().length() > 0) {
            mIvPasswordDelete.setVisibility(View.VISIBLE);
        } else {
            mIvPasswordDelete.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onPermission();
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
