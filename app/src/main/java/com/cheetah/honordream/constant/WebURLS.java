package com.cheetah.honordream.constant;

/**
 * WEB 页面所有 URL
 *
 * Created by wondertwo on 2017/7/27.
 */

public class WebURLS {

    //根据主机服务器地址配置
    private final static String WEB_URLS_BASE_IP = "http://10.20.240.37:8080";
    //应用主页
    public final static String HOME_PAGE_URL = WEB_URLS_BASE_IP + "/index.html";
    //物品详情
    public final static String DETAILS_URL = WEB_URLS_BASE_IP + "/views/details.html";
    //搜索结果
    public final static String SEARCH_RESULT_URL = WEB_URLS_BASE_IP + "/views/result.html";
    //消息页面
    public final static String MESSAGE_URL = WEB_URLS_BASE_IP + "/views/message.html";
    //勾选页面
    public final static String CHECK_ITEMS_URL = WEB_URLS_BASE_IP + "/views/exchange.html";
    //交易页1
    public final static String EXCHANGE_URL = WEB_URLS_BASE_IP + "/views/swap.html";
    //交易页2
    public final static String ENSURE_URL = WEB_URLS_BASE_IP + "/views/deal.html";
    //交易页3
    public final static String COMPLETE_URL = WEB_URLS_BASE_IP + "/views/complete.html";
    //个人店铺(自己)
    public final static String SELF_SHOP_URL = WEB_URLS_BASE_IP + "/views/self.html";
    //个人店铺(对方)
    public final static String OTHER_SHOP_RUL = WEB_URLS_BASE_IP + "/views/personal.html";
}
