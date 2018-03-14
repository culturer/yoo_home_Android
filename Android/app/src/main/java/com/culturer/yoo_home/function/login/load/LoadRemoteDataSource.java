package com.culturer.yoo_home.function.login.load;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.ACTIVITIES_ITEM_URL;
import static com.culturer.yoo_home.config.Urls.ACTIVITIES_URL;
import static com.culturer.yoo_home.config.Urls.ALBUMS_URL;
import static com.culturer.yoo_home.config.Urls.ARRANGEMENTS_URL;
import static com.culturer.yoo_home.config.Urls.ARTICLE_URL;
import static com.culturer.yoo_home.config.Urls.LOGIN_URL;
import static com.culturer.yoo_home.config.Urls.PHOTOS_URL;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadRemoteDataSource extends BaseRemoteDataSource {

    //初始化
    public LoadRemoteDataSource(Context context) {
        super(context);
    }

    //发起登录请求

    public void login(HttpCallback callback, HttpParams params){
        new RxVolley.Builder()
                .url(LOGIN_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载用户相册
    public void loadUerAlbums(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ALBUMS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载家庭相册
    public void loadFamilyAlbums(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ALBUMS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载家庭活动相册
    public void loadHomeActivities(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ACTIVITIES_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载日程安排
    public void loadArrangement(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ARRANGEMENTS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载相片
    public void loadPhoto(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(PHOTOS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载活动详情
    public void loadActivityItems(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ACTIVITIES_ITEM_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    //加载亲友圈数据
    public void loadArticles(HttpCallback callback , HttpParams params){
        new RxVolley.Builder()
                .url(ARTICLE_URL)
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
