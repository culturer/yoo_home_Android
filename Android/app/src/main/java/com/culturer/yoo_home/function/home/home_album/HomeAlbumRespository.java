package com.culturer.yoo_home.function.home.home_album;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.culturer.yoo_home.base.mvpbase.BaseRespository;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.ParamConfig;
import com.culturer.yoo_home.util.PreferenceUtil;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import java.util.ArrayList;
import java.util.List;

import static com.culturer.yoo_home.config.Urls.BASE_URL;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class HomeAlbumRespository extends BaseRespository<HomeAlbumLocalDatasource,HomeAlbumRemoteDatasource> {

    private static final String TAG = "HomeAlbumRespository" ;

    public HomeAlbumRespository(@NonNull HomeAlbumLocalDatasource localDataSource, @NonNull HomeAlbumRemoteDatasource remoteDataSource, @NonNull Context context) {
        super(localDataSource, remoteDataSource, context);
    }

    //1.从缓存里面读取数据
    //2.分类
    public List<String> getPhotos(Long albumId){
        List<Photo> photos = CacheData.photos;
        List<String> strPhotos = new ArrayList<>();
        for (int i=0 ;i<photos.size();i++){
            if (photos.get(i).getAlbumId() == albumId){
                strPhotos.add("http://"+BASE_URL+"/"+photos.get(i).getFileUrl());
                Log.i(TAG, "getPhotos: "+BASE_URL+"/"+photos.get(i).getFileUrl());

//                拼出图片地址
//                例:
//                http://127.0.0.1:8080/photos/2/Screenshot_2018-02-01-16-24-42-856_com.hytera.pstsapp.png

            }
        }
        return strPhotos;
    }

    public void delAlbum(HttpCallback callback, long albumItemId){
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", PreferenceUtil.getString(ParamConfig.TOKEN,""));
        params.put("userId", BaseMsg.getUser().getId());
        params.put("albumItemId",albumItemId+"");
        params.put("options",2);
        remoteDataSource.delAlbum(params,callback);
        localDataSource.delAlbum(albumItemId);
    }
}
