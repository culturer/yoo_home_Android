package com.culturer.yoo_home.function.home.home_main;

import com.culturer.yoo_home.base.mvpbase.IView;
import com.culturer.yoo_home.bean.Family;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface IHomeMainView extends IView<HomeMainPresenter> {
    void loadFail();
    void loadSuccess(Family family);
}
