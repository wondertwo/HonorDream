package com.cheetah.honordream.utils;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cheetah.honordream.constant.WebURLS;

/**
 *
 *
 * Created by wondertwo on 2017/7/29.
 */

public class WebViewBox {

    private WebView mWebView;
    private String mNextURL;

    public WebViewBox(WebView webView, String nextURL) {
        this.mWebView = webView;
        this.mNextURL = nextURL;
    }

    public void configWebView() {
        WebSettings settings = mWebView.getSettings();
        //启用支持javascript
        settings.setJavaScriptEnabled(true);
        //不使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //访问文件
        settings.setAllowFileAccess(true);
        //支持缩放
        settings.setBuiltInZoomControls(false);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);

        /*mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //return false;
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                        //表示按返回键
                        mWebView.goBack(); //后退 //.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });*/

        //设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //回退到主页
                if (url.equals(WebURLS.HOME_PAGE_URL)) {
                    // TODO
                }

                //马上换按钮
                if (url.equals(WebURLS.MESSAGE_URL)) {
                    // TODO
                }

                view.loadUrl(url);
                return true; //在WebView中打开新链接
            }
        });

        //设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient());

        //加载网页
        mWebView.loadUrl(mNextURL);
    }
}
