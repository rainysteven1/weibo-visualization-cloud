package com.rainy.spider.util;

import com.rainy.spider.common.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtil {

    private final static String GET_METHOD = "GET";
    private final static String POST_METHOD = "POST";

    private static String commonMethod(Map<String, String> headers, CloseableHttpClient client, HttpRequestBase base) {
        String result = "";

        if (headers != null && headers.size() > 0) {
            log.debug("Header\n");
            for (Map.Entry<String, String> header : headers.entrySet()) {
                base.addHeader(header.getKey(), header.getValue());
                log.debug(header.getKey() + " : " + header.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(base);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
            }
        } catch (IOException e) {
            log.error("网络请求出错，请检查原因");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                log.error("网络关闭错误");
            }
        }
        return result;
    }

    /**
     * GET请求
     *
     * @param url     请求url
     * @param headers 头部
     * @param params  参数
     * @return
     */
    public static String sendGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        StringBuilder reqUrl = new StringBuilder(url);
        String result = "";
        /*
         * 设置param参数
         */
        if (params != null && params.size() > 0) {
            reqUrl.append("?");
            for (Map.Entry<String, String> param : params.entrySet()) {
                reqUrl.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }
            url = reqUrl.subSequence(0, reqUrl.length() - 1).toString();
        }
        log.debug("[url:" + url + ",method:" + GET_METHOD + "]");
        HttpGet httpGet = new HttpGet(url);

        return commonMethod(headers, client, httpGet);
    }

    /**
     * POST请求
     *
     * @param url     请求url
     * @param headers 头部
     * @param params  参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        /*
          设置参数
         */
        if (params != null && params.size() > 0) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> param : params.entrySet()) {
                paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            log.debug("[url: " + url + ",method: " + POST_METHOD + "]");
            // 模拟表单提交
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, SysConstant.DEFAULT_CHARSET);
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                log.error("不支持的编码");
            }
        }

        return commonMethod(headers, client, httpPost);
    }

    /**
     * post请求发送json
     *
     * @param url     请求url
     * @param json    json文件
     * @param headers 头部
     * @return
     */
    public static String senPostJson(String url, String json, Map<String, String> headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        log.debug("[url: " + url + ",method: " + POST_METHOD + ", json: " + json + "]");

        return commonMethod(headers, client, httpPost);
    }

}
