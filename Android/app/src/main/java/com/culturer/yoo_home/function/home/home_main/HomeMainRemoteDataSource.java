package com.culturer.yoo_home.function.home.home_main;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.FAMILY_URL;

/**
 * Created by Administrator on 2017/11/16.
 */

public class HomeMainRemoteDataSource extends BaseRemoteDataSource {

    //初始化
    public HomeMainRemoteDataSource(Context context) {
        super(context);
    }

    public void loadFamily(HttpCallback callback, HttpParams params){
        new RxVolley.Builder()
                .url(FAMILY_URL)             //该接口需要联调
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }
    public void updateFamily(HttpCallback callback, HttpParams params){
        new RxVolley.Builder()
                .url(FAMILY_URL)             //该接口需要联调
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }
}
