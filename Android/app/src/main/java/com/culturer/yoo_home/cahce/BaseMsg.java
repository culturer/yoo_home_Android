package com.culturer.yoo_home.cahce;

import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.bean.User;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;

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

    public static void init(){
       gson = new Gson();
    }

    public static String getToken(){
        return PreferenceUtil.getString(ParamConfig.TOKEN,"");
    }

    public static String getLoginTime(){
        return PreferenceUtil.getString(ParamConfig.TIME,"");
    }

    public static User getUser(){
        String strUser = PreferenceUtil.getString(ParamConfig.User,"");
        User user = gson.fromJson(strUser,User.class);
        return user;
    }

    public static Family getFamily(){
        String strFamily = PreferenceUtil.getString(ParamConfig.Family,"");
        Family family = gson.fromJson(strFamily,Family.class);
        return family;
    }

    public static void updateFamily(Family family){
        String strFamily = gson.toJson(family,Family.class);
        PreferenceUtil.commitString(ParamConfig.Family,strFamily);
    }

    public static List<User> getFamilyUsers(){
        String strFamilyUser = PreferenceUtil.getString(ParamConfig.FamilyUsers,"");
        Log.i(TAG, "getFamilyUsers: "+strFamilyUser);
        try {
            JSONObject jsonObject = new JSONObject(strFamilyUser);
            List<User> familyUser = new ArrayList<>();
            return familyUser;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
