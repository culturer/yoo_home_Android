package com.culturer.yoo_home.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.function.login.welcome.WelcomeActivity;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.culturer.yoo_home.test.TestChatActivity;
import com.culturer.yoo_home.util.DirUtil;
import com.culturer.yoo_home.util.GlideAlbumLoader;
import com.culturer.yoo_home.util.PreferenceUtil;
import com.kymjs.okhttp3.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;

import com.vondear.rxtools.RxTool;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.Locale;


import okhttp3.OkHttpClient;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication" ;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        //初始化
        init();
        //初始化浮动按钮
//        initFloatWindow();
    }

    private void init(){
        EventBus.getDefault().register(this);
        //使用okhttp代替httpurlconnection
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(RxVolley.CACHE_FOLDER, new OkHttpStack(new OkHttpClient())));
        //初始化Preference工具
        PreferenceUtil.init(this);
        //初始化基础数据中心
        BaseMsg.init();
        //初始化数据库
//        DatabaseUtil.init();
        //初始化数据库调试工具
        initSqliteStudio();
        //初始化Album
        initAlbum();
        //初始化缓存
        CacheData.init();
        //初始化工具类
        RxTool.init(this);
    }

    //初始化可视化数据库管理工具
    private void initSqliteStudio(){
        Log.i(TAG, "initSqliteStudio: ");
        SQLiteStudioService.instance().start(this);
    }

    private void initAlbum(){
        Album.initialize(
                AlbumConfig.newBuilder(this)
                        .setAlbumLoader(new GlideAlbumLoader()) // 设置Album加载器。
                        .setLocale(Locale.CHINA) // 比如强制设置在任何语言下都用中文显示。
                        .build()
        );
    }
    
    @Subscribe
    public void NewMsg(MQTTMsg msg){
//        Log.i(TAG, "NewMsg: "+msg.getMsg());
    }

    public static Context getContext(){
        return context;
    }

}

