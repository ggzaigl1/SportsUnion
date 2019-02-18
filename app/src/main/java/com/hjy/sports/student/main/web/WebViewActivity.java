package com.hjy.sports.student.main.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2018/1/15 0015.
 * WebView  测试demo 相关内容
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.showError)
    TextView showError;

    public static final String URL = "http://kmjkzx.kmwlyy.com/shopApi/o2o/index/userAuthenticationGet";//正式
    public static final String TIME_OUT_URL = "http://testkmjkzx.kmwlyy.com/web/shop/wx/" + "userAuthentication?orgId=WHXS&openId=test2&" +
            "sex=1&" + "tel=13751096562&" + "groupId=test2&" + "email=1810293364@0qq.com&" + "birthday=1984-09-15&" + "trueName=maituwang&" + "nickName=maituwang&" +
            "city=深圳&" + "address=广东湛江&" + "sign=xxxx";

    @Override
    protected int getContentView() {
        return R.layout.activity_webview;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void init(Bundle savedInstanceState) {
        initview();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initview() {
        //加快HTML网页加载完成的速度，等页面finish再加载图片
        if(Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        mWebView.loadUrl(TIME_OUT_URL);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView支持javascript脚本
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);          //允许缩放
        webSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 5.0 以后的WebView加载的链接为Https开头，但是链接里面的内容，比如图片为Http链接，加载图片方法
        /**
         * MIXED_CONTENT_ALWAYS_ALLOW 允许从任何来源加载内容，即使起源是不安全的；
         *MIXED_CONTENT_NEVER_ALLOW 不允许Https加载Http的内容，即不允许从安全的起源去加载一个不安全的资源；
         *MIXED_CONTENT_COMPLTIBILITY_MODE 当涉及到混合式内容时，WebView会尝试去兼容最新Web浏览器的风格；
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两者都可以
            webSettings.setMixedContentMode(webSettings.getMixedContentMode());
            //mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            //用网页的标题来设置自己的标题栏
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (null != title) {
                    tvTitle.setText(title);
                } else {
                    tvTitle.setText("资讯新闻");
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                mWebView.setVisibility(View.GONE);
                showError.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }

        });
//
    }

    @OnClick({R.id.showError})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showError:
                mWebView.setVisibility(View.VISIBLE);
                showError.setVisibility(View.GONE);
                mWebView.reload();
                break;
        }
    }

    /**
     * （1） 为什么WebView打开一个页面，播放一段音乐，退出Activity时音乐还在后台播放？
     */
    //销毁WebView
    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.removeAllViews();
            //在5.1上如果不加上这句话就会出现内存泄露。这是5.1的bug
            // mComponentCallbacks导致的内存泄漏
            mWebView.setTag(null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();//返回上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出整个应用程序
    }

    /**
     * WebView 开启硬件加速
     * 下面看一下硬件加速， 硬件加速分为四个级别：
     Application级别
     <application android:hardwareAccelerated="true"...>
     Activity级别
     <activity android:hardwareAccelerated="true"...>
     */
}
