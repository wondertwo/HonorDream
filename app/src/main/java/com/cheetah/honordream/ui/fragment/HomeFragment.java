package com.cheetah.honordream.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheetah.honordream.R;
import com.cheetah.honordream.constant.WebURLS;
import com.cheetah.honordream.ui.DetailsActivity;
import com.cheetah.honordream.ui.MsgListActivity;
import com.cheetah.honordream.ui.SearchActivity;

/**
 * 二货页面
 *
 * Created by wondertwo on 2017/7/24.
 */

public class HomeFragment extends Fragment {

    private ImageButton mToolbarMenu;
    private LinearLayout mToolbarSearch;
    private TextView mToolbarSearchText;
    private ImageButton mToolbarMsgList;

    private WebView mHomeWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 执行一系列初始化操作
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        initializeUI(homeView);
        initializeWebView(homeView);

        return homeView;
    }

    private void initializeWebView(View homeView) {
        //获取WebView实例
        mHomeWebView = (WebView) homeView.findViewById(R.id.fragment_home_web_view);
        WebSettings settings = mHomeWebView.getSettings();

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

        mHomeWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //return false;
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mHomeWebView.canGoBack()) {
                        //表示按返回键
                        mHomeWebView.goBack(); //后退 //.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        //设置WebViewClient
        mHomeWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(WebURLS.DETAILS_URL)) {
                    //跳转到物品详情页
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
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
        mHomeWebView.setWebChromeClient(new WebChromeClient());

        //加载网页
        mHomeWebView.loadUrl(WebURLS.HOME_PAGE_URL);
    }

    private void initializeUI(View homeView) {
        mToolbarMenu = (ImageButton) homeView.findViewById(R.id.toolbar_menu);
        mToolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), ClassificationActivity.class);
                startActivity(intent);*/
            }
        });
        mToolbarSearch = (LinearLayout) homeView.findViewById(R.id.toolbar_search);
        mToolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        mToolbarSearchText = (TextView) homeView.findViewById(R.id.toolbar_search_text);
        mToolbarMsgList = (ImageButton) homeView.findViewById(R.id.toolbar_message);
        mToolbarMsgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MsgListActivity.class);
                startActivity(intent);
            }
        });
    }

}
