package com.culturer.yoo_home.cahce;

import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.bean.User;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class BaseMsg {

    private static final String TAG = "BaseMsg" ;

    private static Gson gson ;
    
    private static String token = "";
    private static String loginTime = "";
    private static Family family;
    private static User user;
    private static List<User> familyUsers;
    
    public static void init(){
       gson = new Gson();
    }

    public static String getToken(){
        if (token.equals("")){
            token = PreferenceUtil.getString(ParamConfig.TOKEN,"");
        }
        return token;
    }

    public static String getLoginTime(){
        if (loginTime.equals("")){
            loginTime = PreferenceUtil.getString(ParamConfig.TIME,"");
        }
        return loginTime;
    }

    public static User getUser(){
        if (user == null){
            String strUser = PreferenceUtil.getString(ParamConfig.User,"");
            user = gson.fromJson(strUser,User.class);
        }
        return user;
    }

    public static Family getFamily(){
        if (family == null){
            String strFamily = PreferenceUtil.getString(ParamConfig.Family,"");
            family = gson.fromJson(strFamily,Family.class);
        }
        return family;
    }

    public static void updateFamily(Family nFamily){
        family = nFamily;
        String strFamily = gson.toJson(nFamily,Family.class);
        PreferenceUtil.commitString(ParamConfig.Family,strFamily);
    }

    public static List<User> getFamilyUsers(){
        if (familyUsers == null || familyUsers.size()<=0){
            String strFamilyUser = PreferenceUtil.getString(ParamConfig.FamilyUsers,"");
            if (!strFamilyUser.equals("")){
                Log.i(TAG, "getFamilyUsers: "+strFamilyUser);
                try {
                    JSONArray jsonObject = new JSONArray(strFamilyUser);
                    familyUsers = new ArrayList<>();
                    for (int i=0 ;i<jsonObject.length();i++){
                        User user = gson.fromJson(jsonObject.get(i).toString(),User.class);
                        familyUsers.add(user);
                    }
                    return familyUsers;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(TAG, "getFamilyUsers: familyUsers is null");
        return null;
    }
}
