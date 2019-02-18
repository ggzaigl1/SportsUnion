package com.hjy.sports.student.homemodule.expanded.healthknowledge;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

/**
 * Created by Gab on 2018/3/6 0006.
 * 健康常识 详情
 */

public class HealthKnowledgeParticularsActivity extends BaseActivity {

    public String URL = "";
    AgentWeb mAgentWeb;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_knowledge_particulars;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideMenu();
        tvTitle.setText("健康常识详情");
        URL = getIntent().getStringExtra("URL");
        LinearLayout mLinearLayout = findViewById(R.id.ll_web_view);
        mAgentWeb = AgentWeb.with(this)//
                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        if (TextUtils.isEmpty(title)){
                            tvTitle.setText(title);
                        }
                    }
                })//设置 Web 页面的 title 回调
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(URL);
    }

    //WebViewClient
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

    };
    //WebChromeClient
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }
    };

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause(); //暂停应用内所有 WebView ， 需谨慎。
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
