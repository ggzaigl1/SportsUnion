package com.hjy.sports.student.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.GsonUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.baselibrary.utils.SecurityUtils;
import com.fy.baselibrary.utils.cache.ACache;
import com.githang.statusbar.StatusBarCompat;
import com.hjy.sports.R;
import com.hjy.sports.student.main.StudentMainActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Html页面
 * Created by Administrator on 2017/12/12.
 */
public class FragmentThree extends BaseFragment {
    //    public static final String URL = "http://kmjkzx.kmwlyy.com/shopApi/o2o/index/userAuthenticationGet";//正式
    public static final String URL = "http://testkmjkzx.kmwlyy.com/web/shop/o2o/index/userAuthenticationGet";//测试
//        public static final String URL = "http://testkmjkzxapi.kmwlyy.com/o2o/index/userAuthenticationGet";//测试1

//        public static final String URL = "http://test2kmjkzx.kmwlyy.com/web/shop/o2o/index/userAuthenticationGet";//测试2

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvMenu)
    TextView tvMenu;

    @BindView(R.id.showError)
    TextView showError;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void baseInit() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MdStatusBarCompat.setOrdinaryToolBar(getActivity(), R.color.appHeadBg);
            MdStatusBarCompat.StatusBarLightMode(getActivity());
            getActivity().getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            StatusBarCompat.setStatusBarColor(getActivity(), R.drawable.layer_title_bg);
        }

        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("");

        init();
    }


    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", "WHXS");
        params.put("time", System.currentTimeMillis() + "");

        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) ACache
                .get(BaseApplication.getApplication())
                .getAsObject(ConstantUtils.student);

        if (null != studentInfo) {
            params.put("openId", studentInfo.getStudentid());
            params.put("sex", studentInfo.getSex());
            params.put("telPhone", studentInfo.getJiazhangphone());
            params.put("trueName", studentInfo.getName());
        }

        return params;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView支持javascript脚本
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setSupportZoom(true);          //允许缩放
        webSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
        if (NetUtils.isConnected(getActivity())) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        showError.setOnClickListener(v -> {
            mWebView.setVisibility(View.VISIBLE);
            showError.setVisibility(View.GONE);
            mWebView.reload();
        });
        mWebView.setWebChromeClient(new WebChromeClient());
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
                if (url == null) return false;
                try {
                    //其他自定义的scheme
                    if (url.startsWith("weixin://") || url.startsWith("alipays://") || url.startsWith("mailto://") || url.startsWith("tel://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
                //处理http和https开头的url
                mWebView.loadUrl(url);

//                //支付宝
//                if (url.contains("alipays://platformapi/startApp?")) {
//                    startAlipayActivity(url);
//                    // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
//                } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M) && (url.contains("platformapi") && url.contains("startapp"))) {
//                    startAlipayActivity(url);
//                } else {
//                    mWebView.loadUrl(url);
//                }
//                //微信
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    //H5微信支付要用，不然说"商家参数格式有误"
                    Map<String, String> extraHeaders = new HashMap<String, String>();
                    //测试环境  根据服务器地址不同切换不同的域名 切记！！！
                    extraHeaders.put("Referer", "http://test2kmjkzx.kmwlyy.com");
                    //正式环境
                    //extraHeaders.put("Referer", "http://kmjkzx.kmwlyy.com");
                    view.loadUrl(url, extraHeaders);

                    //商家存在未配置的参数,请联系商家解决
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                mWebView.setVisibility(View.GONE);
//                showError.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });

        String params = GsonUtils.mapToJsonStr(getParams());

        try { //get请求
            mWebView.loadUrl(URL + "?data=" + URLEncoder.encode(SecurityUtils.aesEncrypt(params), "UTF-8"));//get
            L.e("Url", URL + "?data=" + URLEncoder.encode(SecurityUtils.aesEncrypt(params), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 调起支付宝并跳转到指定页面
    private void startAlipayActivity(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tvBack})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                StudentMainActivity activity = (StudentMainActivity) getActivity();
                activity.clickFr();
                break;
        }
    }

    /**
     * 判断网页是否 存在历史项目;
     * 返回键返回上一网页
     */
    public boolean onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            //在5.1上如果不加上这句话就会出现内存泄露。这是5.1的bug
            // mComponentCallbacks导致的内存泄漏
            mWebView.setTag(null);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView.clearCache(false);
            mWebView = null;
        }
        super.onDestroy();
    }

    //    /**
//     * 返回键返回上一网页
//     */
//    @Override
//    public void onBackPressed() {
//        if (mWebView.canGoBack()) {
//            if (mWebView.getTitle().equals("首页")) {
//                JumpUtils.jump(this, StudentMainActivity.class, null);
//                SpfUtils.saveBooleanToSpf("loginShowHome", true);
//            } else {
//                mWebView.goBack();
//            }
//        } else {
//            JumpUtils.jump(this, StudentMainActivity.class, null);
//            SpfUtils.saveBooleanToSpf("loginShowHome", true);
//        }
//    }
}