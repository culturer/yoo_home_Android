package com.culturer.yoo_home.base.mvpbase;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/9/20.
 */

/**
 *  MVP中的model基类
 */

public abstract class BaseRespository<L extends BaseLocalDataSource,R extends BaseRemoteDataSource> implements IData {

    @NonNull
    protected final L localDataSource;
    @NonNull
    protected final R remoteDataSource;

    protected final Context context;

    public BaseRespository(@NonNull L localDataSource, @NonNull R remoteDataSource, @NonNull Context context) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.context = context;
    }
}
