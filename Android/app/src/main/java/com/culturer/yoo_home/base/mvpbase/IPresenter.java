package com.culturer.yoo_home.base.mvpbase;

/**
 * Created by Administrator on 2017/9/20.
 */

/**
 * 在新的MVP中把业务逻辑放在Presenter中
 * 把数据库等基本的操作放在Respository中
 * 另外取消了Respository对Presenter的依赖
 * @param <T>
 */
public interface IPresenter<T extends IView> {

}
