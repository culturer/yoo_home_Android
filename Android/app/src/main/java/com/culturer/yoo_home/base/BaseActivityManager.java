package com.culturer.yoo_home.base;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import java.util.Stack;


public class BaseActivityManager {
    private final String TAG = "ActivityManager";
    private static BaseActivityManager instance;
    public Stack<Activity> stack = new Stack<Activity>();
    Handler handler = new Handler();

    private BaseActivityManager() {
    }

    public static BaseActivityManager getInstance() {
        if (instance == null) {
            instance = new BaseActivityManager();
        }
        return instance;
    }

    //得到活动中Activity
    public Activity getFrontActivity() {
        Activity activity = null;
        if (stack.size() > 0) {
            activity = stack.lastElement();
            if (activity != null)
                Log.i(TAG, "psts activity stack front:" + activity.getPackageName() + ",stack.size:" + stack.size());
        }
        return activity;

    }

    //栈内增加activity
    public void add(Activity activity) {
        stack.add(activity);
        if (activity != null)
            Log.i(TAG, "psts activity stack add:" + activity.toString() + ",stack.size:" + stack.size());
    }

    //栈内减少activity
    public void remove(Activity activity) {
        String atvName = activity.toString();
        if (stack.size() > 0) {
            if (stack.contains(activity)) {
                stack.remove(activity);
                Log.i(TAG, "psts activity stack remove:" + atvName + ",stack.size:" + stack.size());
            }
        }
    }

    //关闭所有Activity
    public void finishAllActivity() {
        while (stack.size() > 0) {
            Activity activity = getFrontActivity();
            if (activity != null) {
                activity.finish();
                remove(activity);
            }
        }
    }

    public void finishOtherActivity(Activity atv) {
        while (stack.size() > 0) {
            Activity activity = getFrontActivity();
            if (activity != null) {
                if (activity != atv)
                    activity.finish();
                remove(activity);
            }
        }
        stack.add(atv);
    }


}