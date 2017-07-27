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

}
