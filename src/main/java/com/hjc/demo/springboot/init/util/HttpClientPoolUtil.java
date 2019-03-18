package com.hjc.demo.springboot.init.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class HttpClientPoolUtil {

    public static PoolingHttpClientConnectionManager cm = null;

    public static CloseableHttpClient httpClient = null;

    /**
     * 默认content 类型
     */
    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    /**
     * 默认请求超时时间30s
     */
    private static final int DEFAUL_TTIME_OUT = 30;
    // 路由最大连接数
    private static final int count = 5;

    private static final int totalCount = 10;
//60000
    private static final int Http_Default_Keep_Time = 60000;

    /**
     * 初始化连接池
     */
    public static synchronized void initPools() {
        if (httpClient == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(count);
            // 最大连接数
            cm.setMaxTotal(totalCount);
            //请求失败时,进行请求重试
            HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
                    if (i > 3){
                        //重试超过3次,放弃请求
                        System.out.println("retry has more than 3 time, give up request");
                        return false;
                    }
                    if (e instanceof NoHttpResponseException){
                        //服务器没有响应,可能是服务器断开了连接,应该重试
                        System.out.println("receive no response from server, retry");
                        return true;
                    }
                    if (e instanceof SSLHandshakeException){
                        // SSL握手异常
                        System.out.println("SSL hand shake exception");
                        return false;
                    }
                    if (e instanceof InterruptedIOException){
                        //超时
                        System.out.println("InterruptedIOException");
                        return false;
                    }
                    if (e instanceof UnknownHostException){
                        // 服务器不可达
                        System.out.println("server host unknown");
                        return false;
                    }
                    if (e instanceof ConnectTimeoutException){
                        // 连接超时
                        System.out.println("Connection Time out");
                        return false;
                    }
                    if (e instanceof SSLException){
                        System.out.println("SSLException");
                        return false;
                    }

                    HttpClientContext context = HttpClientContext.adapt(httpContext);
                    HttpRequest request = context.getRequest();
                    if (!(request instanceof HttpEntityEnclosingRequest)){
                        //如果请求不是关闭连接的请求
                        return true;
                    }
                    return false;
                }
            };
            httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy).setConnectionManager(cm).setRetryHandler(handler).build();
        }
    }

    /**
     * Http connection keepAlive 设置
     */
    public static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            int keepTime = Http_Default_Keep_Time;
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return keepTime * 1000;
        }
    };

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
        return cm;
    }

    /**
     * 执行http post请求 默认采用Content-Type：application/json，Accept：application/json
     *
     * @param uri 请求地址
     * @param data  请求数据
     * @return
     */
    public static String execute(String uri, String data) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = (HttpEntityEnclosingRequestBase) getRequest(uri, HttpPost.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
            method.setEntity(new StringEntity(data));
//            HttpPost httpPost = new HttpPost(url);
//            List<NameValuePair> values = new ArrayList<NameValuePair>();
//            values.add(new BasicNameValuePair("a", "value-1"));
//            values.add(new BasicNameValuePair("b", "开始-2"));
//            method.setEntity(new UrlEncodedFormEntity(values, "UTF-8"));
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method);//, context);
            httpEntity = httpResponse.getEntity();
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(httpResponse.getEntity());// 返回json格式：
                Object response = JSONObject.toJSON(result);
            }
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            }

        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            System.out.print(
                    "execute post request exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print(
                            "close response exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):"
                                    + (System.currentTimeMillis() - startTime));
                }
            }
        }
        return responseBody;
    }

    /**
     * 创建请求
     *
     * @param uri 请求url
     * @param methodName 请求的方法类型
     * @param contentType contentType类型
     * @param timeout 超时时间
     * @return
     */
    public static HttpRequestBase getRequest(String uri, String methodName, String contentType, int timeout) {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase method = null;
        if (timeout <= 0) {
            timeout = DEFAUL_TTIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000).setExpectContinueEnabled(false).build();

        if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPut(uri);
        } else if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPost(uri);
        } else if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpGet(uri);
        } else {
            method = new HttpPost(uri);
        }
        if (StringUtils.isBlank(contentType)) {
            contentType = DEFAULT_CONTENT_TYPE;
        }
        method.addHeader("Content-Type", contentType);
        method.addHeader("Accept", contentType);
        method.setConfig(requestConfig);
        return method;
    }

    /**
     * 执行GET 请求
     *
     * @param uri
     * @return
     */
    public static String execute(String uri) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = getRequest(uri, HttpGet.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.print("请求URL: "+uri+"+  返回状态码："+httpResponse.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            System.out.print("execute get request exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
                    + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("close response exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
                }
            }
        }
        return responseBody;
    }
}