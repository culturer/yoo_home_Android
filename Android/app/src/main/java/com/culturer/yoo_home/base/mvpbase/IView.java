package com.culturer.yoo_home.base.mvpbase;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface IView<T> {
    void setPresenter(T presenter);
    T createPresenter();
}
