package com.culturer.yoo_home.function.home.home_arrangement;

/**
 * Created by Administrator on 2017/12/5.
 */

//临时文件，替换后删掉

public class HomeArrangementDataItem {
    int usericon;
    String username;
    String arrangement;
    String arrangement_time;

    public HomeArrangementDataItem(int usericon, String username, String arrangement, String arrangement_time) {
        this.usericon = usericon;
        this.username = username;
        this.arrangement = arrangement;
        this.arrangement_time = arrangement_time;
    }

    @Override
    public String toString() {
        return "username["+username+"]"+
                "arrangement["+arrangement+"]"+
                "arrangement_time["+arrangement_time+"]";
    }
}
