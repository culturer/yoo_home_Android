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

/**
 * Created by Administrator on 2017/11/16.
 */

public class HomeMainPresenter extends BasePresenter<IHomeMainView,HomeMainRespository> {

    private static final String TAG= "HomeMainPresenter";
    private Gson gson = new Gson();

    public HomeMainPresenter(IHomeMainView view, HomeMainRespository respository, Context context) {
        super(view, respository, context);
    }

    /**
     * 在View中的onResume中调用，每次唤醒页面启动
     */
    @Override
    public void start() {

    }

    public void loadFamily(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo---"+errorNo+" errMsg---"+strMsg);
                view.loadFail();
            }

            @Override
            public void onSuccess(String t) {
                //处理返回数据
                //1.检查返回数据是否正常
                //2.刷新页面缓存
                //3.刷新缓存
                //4.页面显示
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    int status = jsonObject.getInt("status");
                    if (status == 200 ){
                        //获取数据成功
                        view.loadSuccess(null);
                    }else {
                        //获取数据失败
                        view.loadFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                view.loadSuccess(null);
            }
        };
        respository.loadFamily(callback);
    }

    public void updateFamily(String notify,String time){
        final Family family = BaseMsg.getFamily();
        family.setFamilyNotifyTitle(notify);
        family.setFamilyNotifyContent(time);
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
                    int status = jsonObject.getInt(STATUS);
                    if ( status == 200 ){
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
