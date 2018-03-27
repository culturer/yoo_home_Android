package com.culturer.yoo_home.function.chat;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
