package com.culturer.yoo_home.function.login.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.culturer.yoo_home.base.mvpbase.BaseRespository;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;


/**
 * Created by Administrator on 2017/11/16.
 */

public class LoginRespository extends BaseRespository<LoginLocalDataSource,LoginRemoteDataSource> {

    private static final String TAG = "LoginRespository";
    private static Gson gson = new Gson();


    public LoginRespository(@NonNull LoginLocalDataSource localDataSource, @NonNull LoginRemoteDataSource remoteDataSource, @NonNull Context context) {
        super(localDataSource, remoteDataSource, context);
    }

    //向服务器发起登陆请求
    public void login(String tel, String password , HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put("tel",tel);
        params.put("password",password);
        remoteDataSource.login(callback,params);
    }

    //登录参数保存
    public void saveDataParams(String token ,String time,String user, String family,String familyUsers){
        localDataSource.saveToken(token);
        localDataSource.saveFamily(family);
        localDataSource.saveLoginTime(time);
        localDataSource.saveUser(user);
        localDataSource.saveFamilyUsers(familyUsers);
    }

}
