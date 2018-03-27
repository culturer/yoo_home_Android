package com.culturer.yoo_home.function.login.load;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.base.mvpbase.BaseRespository;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.Config;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.culturer.yoo_home.config.ParamConfig.HTTP_OPTIONS;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoadRespository extends BaseRespository<LoadLocalDataSource,LoadRemoteDataSource> {

    private static final String TAG = "HomeMainRespository";
    private static Gson gson = new Gson();


    public LoadRespository(@NonNull LoadLocalDataSource localDataSource, @NonNull LoadRemoteDataSource remoteDataSource, @NonNull Context context) {
        super(localDataSource, remoteDataSource, context);
    }

    //加载用户相册信息
    public void loadUerAlbums(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put("userId",BaseMsg.getUser().getId());

        params.put("albumType","true");
        remoteDataSource.loadUerAlbums(callback,params);
    }

    //加载相册信息
    public void loadFamilyAlbums(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        if (BaseMsg.getFamily()!= null){
            params.put("familyId",BaseMsg.getFamily().getId());
        }
        params.put("albumType","false");
        remoteDataSource.loadFamilyAlbums(callback,params);
    }

    //加载相片信息
    public void loadPhoto(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.put("userId",BaseMsg.getUser().getId());
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        if (BaseMsg.getUser()!= null){
            params.put("userId",BaseMsg.getUser().getId());
        }
        remoteDataSource.loadPhoto(callback,params);
    }

    //加载家庭活动
    public void loadHomeActivities(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put("familyId",BaseMsg.getFamily().getId());
        params.put("activityType","true");
        remoteDataSource.loadHomeActivities(callback,params);
    }

    //加载活动详情
    public void loadActivityItems(HttpCallback callback,boolean activityType){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put(HTTP_OPTIONS,0);
        params.put("query_type ",1);
        if (BaseMsg.getFamily()!=null){
            params.put("familyId",BaseMsg.getFamily().getId()+"");
        }else {
            params.put("familyId","-1");
        }
        remoteDataSource.loadActivityItems(callback,params);

    }

    //加载日程安排
    public void loadArrangement(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put(Config.USER_ID,BaseMsg.getUser().getId());
        params.put(Config.ARRANGEMENT_OPYIONS,Config.ARRANGEMENT_OPTIONS_GET);
        remoteDataSource.loadArrangement(callback,params);
    }

    public void loadArticles(HttpCallback callback){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put(Config.USER_ID,BaseMsg.getUser().getId());
        params.put(Config.ARTICLE_OPTIONS,Config.ARTICLE_OPTIONS_GETALL);

    }


    public void saveUserAlbums(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jAlbums = jsonObject.getJSONArray("albums");
            for (int i=0 ; i<jAlbums.length();i++){
                Album album = gson.fromJson(jAlbums.getString(i),Album.class);
                //存缓存
                CacheData.userAlbums.add(album);
                //存数据库
                localDataSource.saveAlbum(album);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveFamilyAlbums(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jAlbums = jsonObject.getJSONArray("albums");
            for (int i=0 ; i<jAlbums.length();i++){
                Album album = gson.fromJson(jAlbums.getString(i),Album.class);
                CacheData.familyAlbums.add(album);
                localDataSource.saveAlbum(album);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void savePhotos(String msg){
        Log.i(TAG, "savePhotos: "+msg);
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jPhotos = jsonObject.getJSONArray("photos");
            if (jPhotos != null ){
                for (int i=0 ; i< jPhotos.length();i++){
                    Photo photo = gson.fromJson(jPhotos.getString(i),Photo.class);
                    CacheData.photos.add(photo);
                    localDataSource.savePhoto(photo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveHomeActivities(String msg){
        try {
            Log.i(TAG, "saveHomeActivities: "+msg);
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jActivieties = jsonObject.getJSONArray("activities");
            if (jActivieties != null){
                for (int i=0 ;i<jActivieties.length();i++){
                    Activity activity = gson.fromJson(jActivieties.getString(i),Activity.class);
                    CacheData.homeActivities.add(activity);
                    localDataSource.saveHomeActivity(activity);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveActivityItems(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jActivityItems = jsonObject.getJSONArray("activityitems");
            if (jActivityItems!=null){
                for (int i=0 ;i<jActivityItems.length();i++){
                    ActivityItem activityItem = gson.fromJson(jActivityItems.getString(i),ActivityItem.class);
                    CacheData.homeActivityItems.add(activityItem);
                    localDataSource.saveActivityItem(activityItem);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void saveArrangements(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jArrangements = jsonObject.getJSONArray("arrangements");
            if (jArrangements!=null){
                for (int i=0 ; i<jArrangements.length() ; i++){
                    Arrangement arrangement = gson.fromJson(jArrangements.getString(i),Arrangement.class);
                    CacheData.arrangements.add(arrangement);
                    localDataSource.saveArrangement(arrangement);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveArticles(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jArticles = jsonObject.getJSONArray("articles");
            JSONArray jComments = jsonObject.getJSONArray("comments");
            if (jArticles!=null){
                for (int i=0 ; i<jArticles.length() ; i++){
                    Article article = gson.fromJson(jArticles.getString(i),Article.class);
                    CacheData.articles.add(article);
                    localDataSource.saveArticle(article);
                }
            }
            if (jComments!=null){
                for (int i=0 ; i<jComments.length() ; i++){
                    Comment comment = gson.fromJson(jComments.getString(i),Comment.class);
                    CacheData.comments.add(comment);
                    localDataSource.saveComment(comment);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
