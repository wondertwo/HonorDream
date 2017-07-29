package com.cheetah.honordream.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cheetah.honordream.R;
import com.cheetah.honordream.constant.WebURLS;

/**
 * 个人店铺(对方)
 *
 * Created by wondertwo on 2017/7/29.
 */

public class OtherShopActivity extends Activity {

    private WebView mOtherShopWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_shop);

        //拿到对象
        mOtherShopWebView = (WebView) findViewById(R.id.activity_other_shop_web_view);
        initializeWebView(mOtherShopWebView);
    }

    private void initializeWebView(WebView mWebView) {
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

        //设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true; //在WebView中打开新链接
            }
        });

        //设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient());

        //加载网页
        mWebView.loadUrl(WebURLS.OTHER_SHOP_RUL);
    }
}
