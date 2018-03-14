package com.culturer.yoo_home.function.chat;

import android.content.Context;
import android.support.annotation.NonNull;

import com.culturer.yoo_home.base.mvpbase.BaseRespository;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class ChatRespository extends BaseRespository<ChatLocalDataSource,ChatRemoteDataSource> {
    public ChatRespository(@NonNull ChatLocalDataSource localDataSource, @NonNull ChatRemoteDataSource remoteDataSource, @NonNull Context context) {
        super(localDataSource, remoteDataSource, context);
    }
}
