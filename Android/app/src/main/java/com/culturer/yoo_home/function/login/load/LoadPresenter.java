package com.culturer.yoo_home.function.login.load;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.culturer.yoo_home.cahce.CacheData;
import com.kymjs.rxvolley.client.HttpCallback;


/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadPresenter extends BasePresenter<ILoadView,LoadRespository> {

    private static final String TAG= "LoadPresenter";

    //加载数据的条数,用于登陆进度条的控制
    private static final int LOAD_SUCCESS = 7;
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
    public synchronized void checkLoad(String tag){
        ++loadMsgCount;
        Log.i(TAG, "checkLoad: "+tag+" --- "+loadMsgCount);
        view.setprogress(((float)loadMsgCount)/((float)LOAD_SUCCESS));
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
                checkLoad("UserAlbums");
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail(strMsg);
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
                    checkLoad("FamilyAlbums");
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                    view.loadFail(strMsg);
                }
            };
            respository.loadFamilyAlbums(callback);
        }else {
            Log.i(TAG, "loadFamilyAlbums: family is null !!!");
            checkLoad("FamilyAlbums");
        }
    }

    //加载相片信息
    private void loadPhoto(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                respository.savePhotos(t);
                Log.i(TAG, "loadPhoto: "+t);
                checkLoad("Photo");
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail(strMsg);
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
                    checkLoad("HomeActivities");
                    Log.i(TAG, "loadHomeActivities: "+t);
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                    view.loadFail(strMsg);
                }
            };
            respository.loadHomeActivities(callback);
        }else {
            checkLoad("HomeActivities");
        }

    }

    //加载活动详情
    private void loadActivityItems(){

        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "loadActivityItems: "+t);
                respository.saveActivityItems(t);
                checkLoad("ActivityItems");
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo---"+errorNo+"  errMsg"+strMsg);
                view.loadFail(strMsg);
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
                checkLoad("Arrangement");
                Log.i(TAG, "loadArrangement: "+t);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errorNo --- "+errorNo+" errMsg --- "+strMsg);
                view.loadFail(strMsg);
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
                checkLoad("Articles");
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "loadArticles err: errNo --- "+errorNo+" errMsg --- "+strMsg);
                view.loadFail(strMsg);
            }
        };
        respository.loadArticles(callback);
    }

}
