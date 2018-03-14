package com.culturer.yoo_home.function.home.home_album;

import com.culturer.yoo_home.base.mvpbase.IView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public interface IHomeAlbumView extends IView<HomeAlbumPresenter> {
    void showPhotos(List<String> photos);
}
