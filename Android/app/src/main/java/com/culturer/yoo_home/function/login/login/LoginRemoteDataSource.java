package com.culturer.yoo_home.function.login.login;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.LOGIN_URL;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoginRemoteDataSource extends BaseRemoteDataSource {

    //初始化
    public LoginRemoteDataSource(Context context) {
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


}
