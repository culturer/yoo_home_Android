package com.culturer.yoo_home.function.login.load;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.culturer.yoo_home.util.HttpUtil;
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


    //加载用户相册
    public void loadUerAlbums(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ALBUMS_URL);
    }

    //加载家庭相册
    public void loadFamilyAlbums(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ALBUMS_URL);
    }

    //加载家庭活动相册
    public void loadHomeActivities(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ACTIVITIES_URL);
    }

    //加载日程安排
    public void loadArrangement(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ARRANGEMENTS_URL);
    }

    //加载相片
    public void loadPhoto(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,PHOTOS_URL);
    }

    //加载活动详情
    public void loadActivityItems(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ACTIVITIES_ITEM_URL);
    }

    //加载亲友圈数据
    public void loadArticles(HttpCallback callback , HttpParams params){
        HttpUtil.send(callback,params,ARTICLE_URL);
    }
}
