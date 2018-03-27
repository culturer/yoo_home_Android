package com.culturer.yoo_home.function.home.home_main;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.culturer.yoo_home.util.HttpUtil;
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

    public void updateFamily(HttpCallback callback, HttpParams params){
        HttpUtil.send(callback,params,FAMILY_URL);
    }
}
