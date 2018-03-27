package com.culturer.yoo_home.function.home.home_main;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.culturer.yoo_home.config.Config.STATUS;
import static com.culturer.yoo_home.config.ParamConfig.HTTP_STATUS_SUCCESS;

/**
 * Created by Administrator on 2017/11/16.
 */

public class HomeMainPresenter extends BasePresenter<IHomeMainView,HomeMainRespository> {

    private static final String TAG= "HomeMainPresenter";

    public HomeMainPresenter(IHomeMainView view, HomeMainRespository respository, Context context) {
        super(view, respository, context);
    }

    /**
     * 在View中的onResume中调用，每次唤醒页面启动
     */
    @Override
    public void start() {

    }

    public void updateFamily(String notify,String time){
        final Family family = BaseMsg.getFamily();
        family.setFamilyNotifyTitle(notify);
        family.setFamilyNotifyContent(time);
        Gson gson = new Gson();
        final String strFamily = gson.toJson(family,Family.class);
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo---"+errorNo+" errMsg---"+strMsg);
                view.loadFail();
            }

            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "onSuccess: "+t);
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    if ( jsonObject.getInt(STATUS) == HTTP_STATUS_SUCCESS ){
                        respository.saveFamily(family,strFamily);
                        view.loadSuccess(family);
                    }else {
                        view.loadFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        respository.updateFamily(callback,notify);
    }

}
