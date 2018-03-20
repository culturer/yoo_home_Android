package com.culturer.yoo_home.function.login.load;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.base.mvpbase.BaseLocalDataSource;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;

import com.culturer.yoo_home.database.DaoSession;
import com.culturer.yoo_home.database.TActivity;
import com.culturer.yoo_home.database.TActivityItem;
import com.culturer.yoo_home.database.TAlbum;
import com.culturer.yoo_home.database.TArrangement;
import com.culturer.yoo_home.database.TPhoto;
import com.culturer.yoo_home.util.DatabaseUtil;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadLocalDataSource extends BaseLocalDataSource {

    private static final byte[] lock = new byte[0];

    private static final String  TAG = "HomeMainLocalDataSource";
    public LoadLocalDataSource(Context context) {
        super(context);
    }

    //存数据库
    public void saveAlbum(Album album ){
        TAlbum tAlbum = album.str2TAlbum();
        Log.i(TAG, "saveAlbum: "+tAlbum.toString());
        synchronized (lock){
            DaoSession daoSession = DatabaseUtil.getDaoSession();
            daoSession.insertOrReplace(tAlbum);
        }
    }

    public void savePhoto(Photo photo){
        TPhoto tPhoto = photo.str2TPhoto();
        Log.i(TAG, "savePhoto: "+tPhoto.toString());
        synchronized (lock){
            DaoSession daoSession = DatabaseUtil.getDaoSession();
            daoSession.insertOrReplace(tPhoto);
        }
    }

    public void saveHomeActivity(Activity activity){
        TActivity tActivity = activity.str2TActivity();
        Log.i(TAG, "saveHomeActivity: "+tActivity.toString());
        synchronized (lock){
            DaoSession daoSession = DatabaseUtil.getDaoSession();
            daoSession.insertOrReplace(tActivity);
        }
    }

    public void saveActivityItem(ActivityItem activityItem){
//        TActivityItem tActivityItem = activityItem.str2TActivityItem();
//        Log.i(TAG, "saveActivityItem: "+tActivityItem.toString());
//        synchronized (lock){
//            DaoSession daoSession = DatabaseUtil.getDaoSession();
//            daoSession.insertOrReplace(tActivityItem);
//        }
    }

    public void saveArrangement(Arrangement arrangement){
        TArrangement tArrangement = arrangement.str2TArrangement();
        Log.i(TAG, "saveArrangement: "+arrangement.toString());
        synchronized (lock){
            DaoSession daoSession = DatabaseUtil.getDaoSession();
            daoSession.insertOrReplace(tArrangement);
        }
    }

    public void saveArticle(Article article){

    }

    public void saveComment(Comment comment){

    }
}
