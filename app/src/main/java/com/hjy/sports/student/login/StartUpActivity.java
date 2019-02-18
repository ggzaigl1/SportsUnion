package com.hjy.sports.student.login;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.hjy.sports.R;
import com.hjy.sports.president.main.PresidentMainActivity;
import com.hjy.sports.student.main.StudentMainActivity;
import com.hjy.sports.util.ActJump;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 体育联盟 主界面
 * Created by fangs on 2017/12/12.
 */
public class StartUpActivity extends BaseActivity {

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start_up);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ConstantUtils.token = mCache.getAsString("token");
        ConstantUtils.role = mCache.getAsString("role");
        ConstantUtils.studentID = SpfUtils.getSpfSaveInt("studentId");

        hideLoadView();
    }

    //延迟200 毫秒 隐藏 加载图片控件
    private void hideLoadView() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    //验证token 为空 则进入登录界面
                    if (TextUtils.isEmpty(ConstantUtils.token)) {
                        JumpUtils.jump(mContext, LoginActivity.class, null);
                    } else {
                        Checktoken();
                    }
                });
    }

    private void Checktoken() {
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        mConnService.checktoken(param).compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        ActJump.jumpRole(mContext, ConstantUtils.role);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
}
