package com.hjy.sports.student.modify.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.hjy.sports.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  修改密码
 * Created by fangs on 2018/3/20 0020.
 */
public class ModifyPassActivity extends BaseActivity {

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.editOldPass)
    EditText editOldPass;
    @BindView(R.id.editNewPass)
    EditText editNewPass;
    @BindView(R.id.editConfirmPass)
    EditText editConfirmPass;


    @Override
    protected int getContentView() {
        return R.layout.activity_modify_pass;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText(R.string.modifyPass);

        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
        if (null != studentInfo) {
            tvUserName.setText(studentInfo.getName());
        }
    }

    @OnClick({R.id.tvConfirm})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvConfirm://确认按钮
//                String oldPass = SpfUtils.getSpfSaveStr("password");
//                if (!oldPass.equals(editOldPass.getText().toString().trim())){
//                    T.showLong("原密码输入错误！");
//                    return;
//                }

                String oldPass = editOldPass.getText().toString().trim();
                String newPass = editNewPass.getText().toString().trim();
                String confirmPass = editConfirmPass.getText().toString().trim();
                if (!newPass.equals(confirmPass)){
                    T.showLong("新密码和确认密码输入不一致！");
                    return;
                }

                if (TextUtils.isEmpty(newPass) || TextUtils.isEmpty(confirmPass)){
                    T.showLong("密码不能为空！");
                    return;
                }

                modifyPass(oldPass, newPass);
                break;
        }
    }

    private void modifyPass(String oldPass, String newPass){
        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);

        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("xuejihao", studentInfo.getXuejihao());
        param.put("oldPswd", oldPass);
        param.put("newPswd", newPass);

        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.loading_modify);
        mConnService.upadatePswdToApp(param)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    protected void onSuccess(Object bean) {
                        SpfUtils.saveStrToSpf("password", newPass);
                        JumpUtils.exitActivity(mContext);
                    }
                    @Override
                    protected void updataLayout(int flag) {}
                });
    }
}
