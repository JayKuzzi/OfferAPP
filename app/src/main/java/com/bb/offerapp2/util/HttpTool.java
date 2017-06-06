package com.bb.offerapp2.util;

/**
 * Created by bb on 2017/4/7.
 */

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/* 创建一个新的类 HttpTool，将公共的操作抽象出来
 * 为了避免调用sendRequest方法时需实例化，设置为静态方法
 * 传入HttpCallbackListener对象为了方法回调
 * 因为网络请求比较耗时，一般在子线程中进行，
 * 为了获得服务器返回的数据，需要使用java的回调机制 */

public class HttpTool {
    public static void sendRequestWithHttpURLConnection(final String address,
                                   final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    // 调用URL对象的openConnection方法获取HttpURLConnection的实例
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    // 设置请求方式，GET或POST
                    connection.setRequestMethod("GET");
                    // 设置连接超时、读取超时的时间，单位为毫秒（ms）
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    // 设置是否使用缓存  默认是true
                    connection.setUseCaches(true);
                    //设置请求头里面的属性
                    //connection.setRequestProperty();
                    // 开始连接
                    Log.i("HttpURLConnection.GET","开始连接");
                    connection.connect();
                    if (connection.getResponseCode() == 200) {
                        Log.i("HttpURLConnection.GET", "请求chenggong");
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        if (listener != null) {
                            // 回调方法 onFinish()
                            listener.onFinish(response.toString());
                        }
                    } else {
                        Log.i("HttpURLConnection.GET", "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        // 回调方法 onError()
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendRequestWithHttpCient(final String address,
                                   final HttpCientCallback listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(address);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        byte[] data = EntityUtils.toByteArray(httpResponse
                                .getEntity());
                        if (listener != null) {
                            // 回调方法 onFinish()
                            listener.onFinish(data);
                        }
                    } else {
                        Log.i("HttpURLConnection.GET", "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        // 回调方法 onError()
                        listener.onError(e);
                    }
                } finally {
                }
            }
        }).start();
    }


//    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(address)
//                .build();
//        Response response =client.newCall(request).execute();
//          StringBuilder result = new StringBuilder();
//          result.append(response.body().string());

//    }
}
