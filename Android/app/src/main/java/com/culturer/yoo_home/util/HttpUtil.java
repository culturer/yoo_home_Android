package com.culturer.yoo_home.util;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.ACTIVITIES_ITEM_URL;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class HttpUtil {
    public static void send(HttpCallback callback , HttpParams params,String url){
        new RxVolley.Builder()
                .url(url)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }
}
