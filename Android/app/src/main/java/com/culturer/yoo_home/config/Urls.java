package com.culturer.yoo_home.config;

public interface Urls {

    String HEADER = "http://";
//    String HOST = "192.168.0.144";
    String HOST = "192.168.10.237";

    String PORT = "8080";
    String MQTT_PORT = "1883";
    String BASE_URL = HOST+":"+PORT;

    //登录接口
    String LOGIN_URL = HEADER + BASE_URL + "/login";
    //注册接口
    String REGISTER_URL = HEADER + BASE_URL + "/register";
    //验证码接口
    String CAPTCHA_URL =  HEADER + BASE_URL + "/captcha";
    //相册接口
    String ALBUMS_URL = HEADER + BASE_URL + "/albums";
    //图片接口
    String PHOTOS_URL = HEADER + BASE_URL + "/photos";
    //活动接口
    String ACTIVITIES_URL = HEADER + BASE_URL + "/activities";
    //活动详情接口
    String ACTIVITIES_ITEM_URL = HEADER + BASE_URL + "/activityitems";
    //日程安排接口
    String ARRANGEMENTS_URL = HEADER + BASE_URL + "/arrangement";
    //家庭信息接口
    String FAMILY_URL = HEADER + BASE_URL + "/family";
    //文章内容接口
    String ARTICLE_URL = HEADER + BASE_URL + "/article";
    //附件上传接口
    String FILES_URL = HEADER + BASE_URL + "/files";
    //即时通信接口
    String MQ_URL = "tcp://" + "120.78.153.27" +":"+MQTT_PORT;

}
