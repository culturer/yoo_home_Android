package com.culturer.yoo_home.function.home.home_album;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.culturer.yoo_home.util.HttpUtil;
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
        HttpUtil.send(callback,params,ALBUMS_URL);
    }
}
