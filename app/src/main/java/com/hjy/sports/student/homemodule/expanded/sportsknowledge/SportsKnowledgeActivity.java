package com.hjy.sports.student.homemodule.expanded.sportsknowledge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.T;
import com.hjy.sports.R;
//import com.kmwlyy.core.net.HttpCode;
//import com.kmwlyy.imchat.TimApplication;
//import com.kmwlyy.login.sdk.H5WebViewActivity;
//import com.kmwlyy.login.sdk.SdkCallBack;
//import com.kmwlyy.login.sdk.WLYYSDK;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 专家咨询
 * Created by QKun on 2018/1/24.
 */

public class SportsKnowledgeActivity extends BaseActivity {
    @BindView(R.id.btn_h5)
    Button mBtnH5;

    @Override
    protected int getContentView() {
        return R.layout.activity_sports_knowledge;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        TimApplication.setListener();
    }

    @OnClick({R.id.btn_h5})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn_h5:
//                WLYYSDK.getInstance().startConsult(mContext, "441203198703231956", "", "", "", "","", new SdkCallBack() {
//                    @Override
//                    public void onError(int code, String msg) {
//                        //服务机构代码失效时不需要提示登录失败
//                        if (code != HttpCode.ORG_EXPIRE){
//                            T.showShort("登录失败，请稍后再试");
//                        }
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                        H5WebViewActivity.startH5WebViewActivity(mContext, "441203198703231956", "", "", "", "","");
//                    }
//                });
                break;
        }
    }
}
