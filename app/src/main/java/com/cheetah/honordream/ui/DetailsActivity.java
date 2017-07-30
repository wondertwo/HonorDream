package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cheetah.honordream.R;
import com.cheetah.honordream.constant.WebURLS;

/**
 * 闲置详情页
 *
 * Created by wondertwo on 2017/7/27.
 */

public class DetailsActivity extends Activity {

    private WebView mDetailsWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //解析Bundle数据
        Bundle bundle = getIntent().getExtras();
        String mDetailsURL = bundle.getString("DETAILS_URL");

        //获取WebView实例
        mDetailsWebView = (WebView) findViewById(R.id.activity_details_web_view);
        initializeWebView(mDetailsWebView, mDetailsURL);
    }

    private void initializeWebView(WebView mWebView, String URL) {
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
                //回退到主页
                if (url.equals(WebURLS.HOME_PAGE_URL)) {
                    Intent intent = new Intent(DetailsActivity.this, ContainerActivity.class);
                    startActivity(intent);
                }

                //马上换按钮
                if (url.equals(WebURLS.MESSAGE_URL)) {
                    Intent intent = new Intent(DetailsActivity.this, MessageActivity.class);
                    startActivity(intent);
                }

                view.loadUrl(url);
                return true; //在WebView中打开新链接
            }
        });

        //设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient());

        //加载网页
        mWebView.loadUrl(URL);
    }
}
