package com.culturer.yoo_home.event;

import com.culturer.yoo_home.bean.Album;

/**
 * Created by Administrator on 2018/1/28 0028.
 */

public class Album_Add_Event {

    int album_type;
    Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(int album_type) {
        this.album_type = album_type;
    }

    public Album_Add_Event(int album_type, Album album) {
        this.album_type = album_type;
        this.album = album;
    }

}
