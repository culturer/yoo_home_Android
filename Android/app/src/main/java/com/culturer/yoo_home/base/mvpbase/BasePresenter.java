package com.culturer.yoo_home.base.mvpbase;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/20.
 */

public abstract class BasePresenter<V extends IView,R extends BaseRespository> {
    protected V view;
    protected R respository;
    protected Context context;

    public BasePresenter(V view, R respository, Context context) {
        this.view = view;
        this.respository = respository;
        this.context = context;
    }

    public abstract void start();
}
