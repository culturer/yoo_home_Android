package com.culturer.yoo_home.config;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface HomeMainConfig {

    //popWindow参数
    int HOMEMAIN_POP_NOTIFY = 0 ;
    int HOMEMAIN_POP_ACTIVITY = 1 ;
    int HOMEMAIN_POP_FAMILYACTIVITY = 2 ;
    int HOMEMAIN_POP_ARRANGEMENT = 3 ;

    //popwindow偏移量
    int X_OFF = 0;
    int Y_OFF = 0;

    //聊天类型
    String CHAT_TYPE = "chat_type";
    //页面跳转传输数据包
    String CHAT_DATA = "data";
    //聊天对象字段
    String CHAT_RECEIVER = "receiver";

    //聊天页面类型
    boolean CHAT_TYPE_HOME = false;
    boolean CHAT_TYPE_USER = true;

    //相册页面类型
    String ALBUM_TYPE = "album_type" ;
    int ALBUM_TYPE_USER = 0 ;
    int ALBUM_TYPE_HOME = 1 ;

    //家庭公告内容
    String FAMILY_NOTIFY_CONTENT = "family_notify_content";
    //家庭公告事件
    String FAMILY_NOTIFY_TIME = "family_notify_time";
}
