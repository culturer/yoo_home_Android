package com.culturer.yoo_home.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.culturer.yoo_home.util.DatabaseUtil;
import com.culturer.yoo_home.util.GlideAlbumLoader;
import com.culturer.yoo_home.util.PreferenceUtil;


import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.squareup.okhttp.OkHttpClient;
import com.vondear.rxtools.RxTool;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;



import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


/**
 * Created by Administrator on 2017/11/17.
 */

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

    private void initFloatWindow() {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.this,"click",Toast.LENGTH_SHORT).show();
            }
        });

        //效果图1
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width,0.2f)
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.8f)
                .setY(Screen.height,0.3f)
                .setMoveType(MoveType.slide)
                .setMoveStyle(500,new BounceInterpolator())
                .setDesktopShow(true)
                .build();
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
        DatabaseUtil.init();
        //初始化数据库调试工具
        initSqliteStudio();
        //初始化Album
        initAlbum();
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
        Log.i(TAG, "NewMsg: "+msg.getMsg());
    }

    public static Context getContext(){
        return context;
    }
}

