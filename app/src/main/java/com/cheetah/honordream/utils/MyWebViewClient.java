package com.cheetah.honordream.utils;

import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * MyWebViewClient
 *
 * Created by wondertwo on 2017/7/27.
 */

public class MyWebViewClient extends WebViewClient {

    private Context context;
    private String URL;

    public MyWebViewClient(Context context, String URL) {
        this.context = context;
        this.URL = URL;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals(this.URL)) {
            //...
        }

        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return super.shouldInterceptRequest(view, url);
    }
}
