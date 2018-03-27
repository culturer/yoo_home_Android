package com.culturer.yoo_home.function.home.home_main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.base.mvpbase.BaseRespository;

import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.config.Config;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by Administrator on 2017/11/16.
 */

public class HomeMainRespository extends BaseRespository<HomeMainLocalDataSource,HomeMainRemoteDataSource> {

    private static final String TAG = "HomeMainRespository";
    private static Gson gson = new Gson();

    public HomeMainRespository(@NonNull HomeMainLocalDataSource localDataSource, @NonNull HomeMainRemoteDataSource remoteDataSource, @NonNull Context context) {
        super(localDataSource, remoteDataSource, context);
    }

    public void updateFamily(HttpCallback callback, String familyNotifyTitle){
        Log.i(TAG, "updateFamily: "+BaseMsg.getFamily().toString());
        HttpParams params = new HttpParams();
        params.put(Config.FAMILY_NOTIFY_TITLE,familyNotifyTitle);
        params.put(Config.USER_ID,BaseMsg.getUser().getId());
        params.put(Config.FAMILY_ID,BaseMsg.getUser().getFamilyId()+"");
        params.put(Config.FAMILY_OPTIONS,Config.FAMILY_OPTIONS_UPDATE);
        params.putHeaders("Authorization", BaseMsg.getToken());
        remoteDataSource.updateFamily(callback,params);
    }

    
    public void saveFamily(Family family,String strFamily){
        localDataSource.saveFamily(strFamily);
        //更新缓存中数据
        BaseMsg.updateFamily(family);
    }
}
