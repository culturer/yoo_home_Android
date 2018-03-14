package com.culturer.yoo_home.function.home.home_album;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.ALBUMS_URL;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class HomeAlbumRemoteDatasource extends BaseRemoteDataSource {
    public HomeAlbumRemoteDatasource(Context context) {
        super(context);
    }
    public void delAlbum(HttpParams params, HttpCallback callback){
        new RxVolley.Builder()
                .url(ALBUMS_URL)
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
