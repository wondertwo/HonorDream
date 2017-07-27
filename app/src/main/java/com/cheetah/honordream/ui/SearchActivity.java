package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cheetah.honordream.R;
import com.cheetah.honordream.constant.WebURLS;
import com.cheetah.honordream.utils.KeyboardChangeListener;

/**
 * 搜索页面
 *
 * Created by wondertwo on 2017/7/24.
 */

public class SearchActivity extends Activity {

    private ImageButton mSearchBack;
    private LinearLayout mSearchBar;
    private EditText mSearchText;
    private ImageButton mSearchMessage;

    private WebView mSearchWebView;

    private KeyboardChangeListener mKeyboardChangeListener; //监听软键盘

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeUI(); //界面初始化
        initializeWebView(); //初始化WebView

        mKeyboardChangeListener = new KeyboardChangeListener(SearchActivity.this);
        mKeyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow) return;
                //加载网页
                mSearchWebView.loadUrl(WebURLS.SEARCH_RESULT_URL);
            }
        });
    }

    private void initializeWebView() {
        //获取WebView实例
        mSearchWebView = (WebView) findViewById(R.id.activity_search_web_view);
        WebSettings settings = mSearchWebView.getSettings();

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

        mSearchWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //return false;
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mSearchWebView.canGoBack()) {
                        //表示按返回键
                        mSearchWebView.goBack(); //后退 //.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        //设置WebViewClient
        mSearchWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(WebURLS.DETAILS_URL)) {
                    //跳转到物品详情页
                    Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("DETAILS_URL", WebURLS.DETAILS_URL);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    return true; //
                }

                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient
        mSearchWebView.setWebChromeClient(new WebChromeClient());
    }

    private void initializeUI() {
        mSearchBack = (ImageButton) findViewById(R.id.activity_search_toolbar_back);
        mSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ContainerActivity.class);
                startActivity(intent);
            }
        });

        mSearchText = (EditText) findViewById(R.id.activity_search_toolbar_search_text);
        mSearchBar = (LinearLayout) findViewById(R.id.activity_search_toolbar_bar);
        mSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchText.setFocusable(true);
                mSearchText.setCursorVisible(true);
                //强制弹出软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mSearchText, InputMethodManager.SHOW_FORCED);
            }
        });

        mSearchMessage = (ImageButton) findViewById(R.id.activity_search_toolbar_message);
        mSearchMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MsgListActivity.class);
                startActivity(intent);
            }
        });
    }

}

// settings.setAllowUniversalAccessFromFileURLs(true);
// settings.setJavaScriptCanOpenWindowsAutomatically(true);
        /*mHomeWebView.addJavascriptInterface(new JSJsonDataInterface() {
            @JavascriptInterface
            @Override
            public String getJSON() {
                return webJson;
            }
        }, "android");*/

//mHomeWebView.loadUrl("file:///android_asset/index.html");
//mHomeWebView.loadDataWithBaseURL("file:///android_assets", "index.html", "text/html", "UTF-8", null);
