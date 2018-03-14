package com.culturer.yoo_home.function.login.login;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.base.mvpbase.BaseLocalDataSource;
import com.culturer.yoo_home.bean.User;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoginLocalDataSource extends BaseLocalDataSource {

    private static final byte[] lock = new byte[0];

    private static final String  TAG = "LoginLocalDataSource";
    public LoginLocalDataSource(Context context) {
        super(context);
    }

    public void saveLoginTime(String loginTime){
        Log.i(TAG, "saveLoginTime: "+loginTime);
        PreferenceUtil.commitString(ParamConfig.TIME,loginTime);
    }

    public void saveToken(String token){
        Log.i(TAG, "saveToken: "+token);
        PreferenceUtil.commitString(ParamConfig.TOKEN,token);
    }

    public void saveUser(String user){
        Log.i(TAG, "saveUser: "+user);
        PreferenceUtil.commitString(ParamConfig.User,user);
    }

    public void saveFamily(String family){
        Log.i(TAG, "saveFamily: "+family);
        PreferenceUtil.commitString(ParamConfig.Family,family);
    }

    public void saveFamilyUsers(String familyUsers){
        Log.i(TAG, "saveFamilyUsers: "+familyUsers);
        PreferenceUtil.commitString(ParamConfig.FamilyUsers,familyUsers);
        try {
            JSONArray jFamilyUsers = new JSONArray(familyUsers);
            Gson gson = new Gson();
            for (int i=0 ; i<jFamilyUsers.length() ; i++){
                User user = gson.fromJson(jFamilyUsers.getString(i),User.class);
                CacheData.familyUsers.add(user);
                Log.i(TAG, "saveFamilyUsers: "+user.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "saveFamilyUsers: "+CacheData.familyUsers.size());
    }
}
