package com.culturer.yoo_home.base.mvpbase;

/**
 * Created by Administrator on 2017/9/20.
 */

import android.content.Context;


/**
 * 负责远程数据操作的基类
 */
public abstract class BaseRemoteDataSource implements IData {
    protected Context context;

    public BaseRemoteDataSource(Context context) {
        this.context = context;
    }
}
