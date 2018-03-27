package com.culturer.yoo_home.function.login.login;

import com.culturer.yoo_home.base.mvpbase.IView;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface ILoginView extends IView<LoginPresenter> {
    void loginSuccess();
    void loginFail();
    void logining();
}
