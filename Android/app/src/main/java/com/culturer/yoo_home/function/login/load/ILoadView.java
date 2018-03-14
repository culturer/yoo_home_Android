package com.culturer.yoo_home.function.login.load;

import com.culturer.yoo_home.base.mvpbase.IView;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface ILoadView extends IView<LoadPresenter> {
    void loadFail();
    void loadSuccess();
}
