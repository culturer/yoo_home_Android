package com.culturer.yoo_home.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2017/9/10.
 */

/**
 * 使用方法：
 * 1.初始化 PreferenceUtil.init(context);
 * 2.增 PreferenceUtil.commitString(key,value);
 * 3.查 PreferenceUtil.getString(key,defaultValue);
 * 4.删 PreferenceUtil.removeByKey(key)
 *      PreferenceUtil.removeAll();
 */

public abstract class PreferenceUtil {

    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;
    /**
     * 日志是否禁止自动删除<br>
     * int类型：1表示不自动删除，0为默认自动删除
     */
    public static String LOG_CLEAR = "log_clear";

    public static void init(Context context){
        if (sharedPreferences == null){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void removeByKey(String key){
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removeAll(){
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void commitString(String key , String value){
        editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getString(String key , String failValue){
        return sharedPreferences.getString(key,failValue);
    }

    public static void commitInt(String key , int value){
        editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static int getInt(String key , int failValue){
        return sharedPreferences.getInt(key,failValue);
    }

    public static void commitLong(String key , long value){
        editor = sharedPreferences.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    public static long getLong(String key , long failValue){
        return sharedPreferences.getLong(key,failValue);
    }

    public static void commitBoolean(String key , boolean value){
        editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean getBoolean(String key , boolean failValue){
        return sharedPreferences.getBoolean(key,failValue);
    }

}
