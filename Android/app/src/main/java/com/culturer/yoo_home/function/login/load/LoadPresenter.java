package com.culturer.yoo_home.function.login.load;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.kymjs.rxvolley.client.HttpCallback;


/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadPresenter extends BasePresenter<ILoadView,LoadRespository> {

    private static final String TAG= "LoginPresenter";

    //加载数据的条数,用于登陆进度条的控制
    private static final int LOAD_SUCCESS = 6;
    private static volatile int loadMsgCount = 0 ;


    public LoadPresenter(ILoadView view, LoadRespository respository, Context context) {
        super(view, respository, context);
    }

    /**
     * 在View中的onResume中调用，每次唤醒页面启动
     */
    @Override
    public void start() {

    }

    public void loadMsg(){
        loadUserAlbums();
        loadFamilyAlbums();
        loadHomeActivities();
        loadArrangement();
        loadPhoto();
        loadActivityItems();
        loadArticles();
    }

    public void clear(){
        //清空进度条
        loadMsgCount = 0;
    }

    /**
     *检查数据是否加载完毕
     * loadMsgCount 可以当进度条显示来用
     */
    public synchronized void checkLoad(){
        ++loadMsgCount;
        Log.i(TAG, "checkLoad: "+loadMsgCount);
        if (loadMsgCount == LOAD_SUCCESS ){
            clear();
            view.loadSuccess();
        }
    }


    //加载用户相册信息
    private void loadUserAlbums(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "loadUserAlbums: "+t);
                respository.saveUserAlbums(t);
                checkLoad();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail();
            }
        };
        respository.loadUerAlbums(callback);
    }

    //加载家庭相册信息
    private void loadFamilyAlbums(){
        if (BaseMsg.getFamily() != null){
            HttpCallback callback = new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    respository.saveFamilyAlbums(t);
                    Log.i(TAG, "loadFamilyAlbums: "+t);
                    checkLoad();
                }

                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                    view.loadFail();
                }
            };
            respository.loadFamilyAlbums(callback);
        }else {
            Log.i(TAG, "loadFamilyAlbums: family is null !!!");
            checkLoad();
        }
    }

    //加载相片信息
    private void loadPhoto(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                respository.savePhotos(t);
                Log.i(TAG, "loadPhoto: "+t);
                checkLoad();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail();
            }
        };
        respository.loadPhoto(callback);
    }

    //加载家庭活动
    private void loadHomeActivities(){
        if (BaseMsg.getFamily()!=null){
            HttpCallback callback = new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    respository.saveHomeActivities(t);
                    checkLoad();
                    Log.i(TAG, "loadHomeActivities: "+t);
                }

                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                    view.loadFail();
                }
            };
            respository.loadHomeActivities(callback);
        }else {
            checkLoad();
        }

    }

    //加载活动详情
    private void loadActivityItems(){

        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "loadActivityItems: "+t);
                respository.saveActivityItems(t);
                checkLoad();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail();
            }
        };
        respository.loadActivityItems(callback,false);
    }

    //加载日程安排
    private void loadArrangement(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {

                respository.saveArrangements(t);
                checkLoad();
                Log.i(TAG, "loadArrangement: "+t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo --- "+errorNo+" errMsg --- "+strMsg);
                view.loadFail();
            }
        };
        respository.loadArrangement(callback);
    }

    //加载亲友圈内容
    private void loadArticles(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "loadArticles: "+t);
                respository.saveArticles(t);
                checkLoad();
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "loadArticles err: errNo --- "+errorNo+" errMsg --- "+strMsg);
                view.loadFail();
            }
        };
        respository.loadArticles(callback);
    }

}
