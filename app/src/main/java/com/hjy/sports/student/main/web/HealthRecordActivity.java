package com.hjy.sports.student.main.web;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.JiangKanBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.githang.statusbar.StatusBarCompat;
import com.hjy.sports.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by QKun on 2018/3/13.
 * 健康档案 360
 */

public class HealthRecordActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.showError)
    TextView showError;
    String mUrl;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_record;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        rlHead.setVisibility(View.GONE);
        getHealthUrl();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MdStatusBarCompat.setOrdinaryToolBar(this, R.color.appHeadBg);
            MdStatusBarCompat.StatusBarLightMode(this);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            StatusBarCompat.setStatusBarColor(this, R.drawable.layer_title_bg);
        }
    }

    private void getHealthUrl() {
        int mStudentId = SpfUtils.getSpfSaveInt("studentId");
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        param.put("studentid", mStudentId);
        mConnService.getjiangkanurl(param)
                .doOnSubscribe(disposable -> mCompositeDisposable.add(disposable))
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<JiangKanBean>() {
                    @Override
                    protected void onSuccess(JiangKanBean t) {
                        mUrl = t.getUrl();
//                        mWebView.loadUrl(mUrl);
                        init();
                        L.e("Taaaaa", t.getUrl());
                    }

                    @Override
                    protected void updataLayout(int flag) {
//                        InitData(mUrl);
                    }
                });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        mWebView.requestFocus();
//        mWebView.loadUrl("http://www.wanandroid.com/index");
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView支持javascript脚本
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        showError.setOnClickListener(v -> {
            mWebView.setVisibility(View.VISIBLE);
            showError.setVisibility(View.GONE);
            mWebView.reload();
        });
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        mWebView.addJavascriptInterface(new JavaScriptObject(mContext), "health");
        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar1 == null) {
                    return;
                }
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (null != title) {
                    tvTitle.setText(title);
                } else {
                    tvTitle.setText("");
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // handler.cancel();// Android默认的处理方式
                // handleMessage(Message msg);// 进行其他处理
                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID || error.getPrimaryError() == SslError.SSL_EXPIRED
                        || error.getPrimaryError() == SslError.SSL_INVALID
                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                mWebView.setVisibility(View.GONE);
                showError.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });
        mWebView.loadUrl(mUrl);
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
     * JS通过WebView调用 Android 代码
     */
    class JavaScriptObject {

        private AppCompatActivity act;

        public JavaScriptObject(AppCompatActivity act) {
            this.act = act;
        }

        @android.webkit.JavascriptInterface
        public void goBackHealth() {//goToBatHome
            act.runOnUiThread(() -> JumpUtils.exitActivity(act));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            //在5.1上如果不加上这句话就会出现内存泄露。这是5.1的bug
            // mComponentCallbacks导致的内存泄漏
            mWebView.setTag(null);
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.destroy();
            mWebView = null;
        }
    }
}
