package com.kymjs.okhttp;

import android.os.Looper;
import android.test.AndroidTestCase;

import com.kymjs.common.Log;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.RequestQueue;
import com.kymjs.rxvolley.toolbox.FileUtils;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * Created by kymjs on 1/21/16.
 */
public class PostRequestTest extends AndroidTestCase {

    HttpCallback callback;

    @Before
    public void setUp() throws Exception {
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(RxVolley.CACHE_FOLDER,
                new OkHttpStack(new OkHttpClient())));
        
        callback = new HttpCallback() {
            @Override
            public void onPreStart() {
                Log.d("=====onPreStart");
                // 测试类是运行在异步的,所以此处断言会异常
                // assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onPreHttp() {
                Log.d("=====onPreHttp");
                assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onSuccessInAsync(byte[] t) {
                assertNotNull(t);
                Log.d("=====onSuccessInAsync" + new String(t));
                //onSuccessInAsync 一定是运行在异步
                assertFalse(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onSuccess(String t) {
                Log.d("=====onSuccess" + t);
                assertNotNull(t);
                assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                assertNotNull(t);
                Log.d("=====onSuccessWithHeader" + headers.size() + new String(t));
                assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.d("=====onFailure" + strMsg);
                assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.d("=====onFinish");
                assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
            }
        };
    }

    /**
     * 不知道为什么,在测试类中写,log会打印不完整,但是在activity中调用,日志就是完整的
     */
    @Test
    public void testUploadProgress() throws Exception {
        HttpParams params = new HttpParams();

        params.putHeaders("cookie", "aliyungf_tc=AQAAAOEM/UExEAsAUAYscy4Da0FfTWqX;" +
                "oscid=vv%2BiiKldi6wRaKbbRig0DDvMcIURmo56ZCZD2bfC83AsmxdhUxEVnr3ORNGz7BjiFlkpGQHUKJoRTzVAwy3oVtcO7JsM4nRIjEl6ZgM%2BmZgplCH0foAIiQ%3D%3D;");

        params.put("uid", 863548);
        params.put("msg", "睡觉");
        params.put("img", new File(FileUtils.getSDCardPath() + "/request.png"));

        RxVolley.post("http://192.168.1.11/action/api/tweet_pub", params,
                new ProgressListener() {
                    @Override
                    public void onProgress(long transferredBytes, long totalSize) {
                        //防止日志太多刷屏
                        if (transferredBytes % 10000 == 0) {
                            Log.d(transferredBytes + "=====" + totalSize);
                        }
                        assertTrue(Thread.currentThread() == Looper.getMainLooper().getThread());
                    }
                }, callback);
    }

    @Test
    public void testPost() throws Exception {
        HttpParams params = new HttpParams();

        params.putHeaders("cookie", "aliyungf_tc=AQAAAOEM/UExEAsAUAYscy4Da0FfTWqX;" +
                "oscid=vv%2BiiKldi6wRaKbbRig0DDvMcIURmo56ZCZD2bfC83AsmxdhUxEVnr3ORNGz7BjiFlkpGQHUKJoRTzVAwy3oVtcO7JsM4nRIjEl6ZgM%2BmZgplCH0foAIiQ%3D%3D;");

        params.put("uid", 863548);
        params.put("msg", "睡觉");
        params.put("img", new File(FileUtils.getSDCardPath() + "/request.png"));

        RxVolley.post("http://192.168.1.11/action/api/tweet_pub", params, callback);
    }
}
