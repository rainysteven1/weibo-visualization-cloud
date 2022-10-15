package com.rainy.spider.common;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SysConstant {
    // 默认字符集
    public static final String DEFAULT_CHARSET = "utf-8";
    // 默认协议
    public static final String HTTPS_PROTOCOL = "https:";
    // 默认日期格式
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM--dd HH:mm:ss";
    // 微博热搜榜URL
    public static final String HOT_SEARCH_RANKING_URL = "https://s.weibo.com/top/summary";
    // 微博热搜URL
    public static final String HOT_SEARCH_PREFIX_URL = "https://s.weibo.com/weibo?q=";

    public static String COOKIE = "SINAGLOBAL=1686655246524.2402.1660828692836; SCF=AvYj27nh4M63X4YXlmI-fEyVqDx6y6pFot1_azgM84Dd5eR_P5nRbPMndpjE5zZtb10MUNtqYcPuV3HBgYgvkqU.; _s_tentry=login.sina.com.cn; UOR=login.sina.com.cn,weibo.com,login.sina.com.cn; Apache=9098289851746.814.1665803564504; ULV=1665803564507:2:1:1:9098289851746.814.1665803564504:1660828692838; XSRF-TOKEN=29jqaLaN1dJ-Y2qlS3MugTFy; PC_TOKEN=b44b506cb2; WBStorage=4d96c54e|undefined; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WWO5PJ.XSDbh1GrbB1Pw0Il5JpVF020eoM4e0M4eoq7; SUB=_2AkMUFqpBdcPxrAFSmf8RxGzhZIhH-jynw8O3An7uJhMyAxh87kgvqSVutBF-XJfDcO2s0GTNz6oH2Vtay4BVFYQU; WBPSESS=Dt2hbAUaXfkVprjyrAZT_IJPP9QHL25DRWJg6NKU8sKpI_Z3yTC9ALPoVJueJjoDTNC-hmF3Jys_YGtdJFOLTXaxGwKJZlhLhDX6FmCECLnPrDZt0iFEG_UUqEh9dm7mOalnBvBewpkK1j7Yig-1ig==";

    @NotNull
    public static HashMap<String, String> setHeader() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put(":authority", "weibo.com");
        headerMap.put(":method", "GET");
        headerMap.put(":scheme", "https");
        headerMap.put("accept", "application/json, text/plain, */*");
        headerMap.put("accept-encoding", "gzip, deflate, br");
        headerMap.put("accept-language", "zh-CN,zh;q=0.9");
        headerMap.put("cookie", COOKIE);
        headerMap.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
        return headerMap;
    }

}
