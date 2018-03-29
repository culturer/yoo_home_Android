package com.culturer.yoo_home.function.login.login;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BaseRemoteDataSource;
import com.culturer.yoo_home.util.HttpUtil;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.LOGIN_URL;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoginRemoteDataSource extends BaseRemoteDataSource {

    //初始化
    LoginRemoteDataSource(Context context) {
        super(context);
    }

    //发起登录请求
    public void login(HttpCallback callback, HttpParams params){
        HttpUtil.send(callback,params,LOGIN_URL);
    }

}
