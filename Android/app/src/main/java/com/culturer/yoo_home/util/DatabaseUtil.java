package com.culturer.yoo_home.util;

import com.culturer.yoo_home.base.BaseApplication;
import com.culturer.yoo_home.database.DaoMaster;
import com.culturer.yoo_home.database.DaoSession;


import org.greenrobot.greendao.database.Database;


/**
 * Created by Administrator on 2017/9/25.
 */

public class DatabaseUtil {

    private static final boolean ENCRYPTED = false;

    private static DaoSession daoSession;

    public static void init(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(),ENCRYPTED ? "yooplus-encrypted":"yooplus-db");
        Database database = ENCRYPTED ? helper.getEncryptedWritableDb("yooplus"):helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
