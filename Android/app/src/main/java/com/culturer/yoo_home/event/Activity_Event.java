package com.culturer.yoo_home.event;

import com.culturer.yoo_home.bean.Activity;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class Activity_Event {

    public static final int HomeActivity_NEW = 0;

    public int type ;
    public Activity activity;

    public Activity_Event(int type, Activity activity) {
        this.type = type;
        this.activity = activity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
