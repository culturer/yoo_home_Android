package com.culturer.yoo_home.function.home.home_album;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.kymjs.rxvolley.client.HttpCallback;


import java.util.ArrayList;
import java.util.List;

public class HomeAlbumPresenter extends BasePresenter<IHomeAlbumView,HomeAlbumRespository> {

    private static final String TAG = "HomeAlbumPresenter";

    public HomeAlbumPresenter(IHomeAlbumView view, HomeAlbumRespository respository, Context context) {
        super(view, respository, context);
    }

    @Override
    public void start() {

    }

    public void showPhotos(Long albumId){
        List<String> photos = new ArrayList<>();
        if (respository.getPhotos(albumId)!=null){
            photos = respository.getPhotos(albumId);
        }
        view.showPhotos(photos);
    }

    public void delAlbum(long albumId){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "delAlbum: "+t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "delAlbum: errNo"+errorNo+" || errMsg"+strMsg);
            }
        };
        respository.delAlbum(callback,albumId);
    }
}
