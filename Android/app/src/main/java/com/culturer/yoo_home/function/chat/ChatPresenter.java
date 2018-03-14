package com.culturer.yoo_home.function.chat;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BasePresenter;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class ChatPresenter extends BasePresenter<IChatView,ChatRespository> {
    public ChatPresenter(IChatView view, ChatRespository respository, Context context) {
        super(view, respository, context);
    }

    @Override
    public void start() {

    }
}
