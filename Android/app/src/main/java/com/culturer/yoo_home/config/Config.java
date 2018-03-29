package com.culturer.yoo_home.config;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface Config {

    String USER_ID = "userId";
    String STATUS = "status";

    //状态标识
    String MSG_STATE = "msg_state";
    int MSG_STATE_SUCCESS = 200 ;
    int MSG_STATE_FAIL = 400 ;
    String FAMILY_OPTIONS = "options";
    int FAMILY_OPTIONS_GET = 0;
    int FAMILY_OPTIONS_ADD = 1;
    int FAMILY_OPTIONS_DEL = 2;
    int FAMILY_OPTIONS_UPDATE = 3;
    int FAMILY_OPTIONS_DELUSERRELATION = 3;
    String FAMILY_ID = "familyId";
    String FAMILY_NAME = "familyName";
    String FAMILY_NOTIFY_TITLE = "familyNotifyTitle";
    String FAMILY_NOTIFY_CONTENT = "familyNotifyContent";
    String FAMILY_NOTIFY_MSG = "msg";

    String ARRANGEMENT_OPYIONS = "options" ;
    int ARRANGEMENT_OPTIONS_GET = 0;
    int ARRANGEMENT_OPTIONS_ADD = 1;
    int ARRANGEMENT_OPTIONS_DEL = 2;
    int ARRANGEMENT_OPTIONS_UPDATE = 3;

    String ALBUM_OPYIONS = "options" ;
    int ALBUM_OPYIONS_GET = 0;
    int ALBUM_OPYIONS_ADD = 1;
    int ALBUM_OPYIONS_DEL = 2;
    int ALBUM_OPYIONS_UPDATE = 3;
    String ALBUM_USER_ID = "userId";
    String ALBUM_FAMILY_ID = "familyId";
    String ALBUM_ITEM_NAME = "albumItemName";
    String ALBUM_ITEM_ID ="albumItemId";
    String ALBUM_TYPE = "albumType";

    String ALBUM_TYPE_PERSON = "true";
    String ALBUM_TYPE_FAMILY = "false";

    String ARTICLE_OPTIONS = "options" ;
    int ARTICLE_OPTIONS_GETALL = 0;
    int ARTICLE_OPTIONS_GETONE = 1;
    int ARTICLE_OPTIONS_ADD = 2;
    int ARTICLE_OPTIONS_DEL = 3;
    int ARTICLE_OPTIONS_ADDCOMM = 4;
    int ARTICLE_OPTIONS_DELCOMM = 5;
}
