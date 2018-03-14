package com.culturer.yoo_home.function.home.home_main;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.base.mvpbase.BaseLocalDataSource;
import com.culturer.yoo_home.config.ParamConfig;

import com.culturer.yoo_home.util.PreferenceUtil;

/**
 * Created by Administrator on 2017/11/16.
 */

public class HomeMainLocalDataSource extends BaseLocalDataSource {

    private static final byte[] lock = new byte[0];

    private static final String  TAG = "HomeMainLocalDataSource";
    public HomeMainLocalDataSource(Context context) {
        super(context);
    }

    public void saveFamily(String family){
        Log.i(TAG, "saveFamily: "+family);
        PreferenceUtil.commitString(ParamConfig.Family,family);
    }
}
