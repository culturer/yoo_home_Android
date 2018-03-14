package com.culturer.yoo_home.function.login.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.culturer.yoo_home.config.ParamConfig;
import com.kymjs.rxvolley.client.HttpCallback;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LoginPresenter extends BasePresenter<ILoginView,LoginRespository> {

    private static final String TAG= "LoginPresenter";



    public LoginPresenter(ILoginView view, LoginRespository respository, Context context) {
        super(view, respository, context);
    }

    /**
     * 在View中的onResume中调用，每次唤醒页面启动
     */
    @Override
    public void start() {

    }

    /**
     * 登录处理业务逻辑
     * @param tel
     * @param password
     */
    public void login(final String tel , final String password){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "onSuccess: "+t);
                if (loginIncicate(t)){
                    loginSuccess(t);
                }else {
                    Toast.makeText(context,"账号或密码错误请重新登陆",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo"+errorNo+"||errMsg"+strMsg);
                Toast.makeText(context,"网络连接异常，请检查网络后重新登陆",Toast.LENGTH_LONG).show();
            }
        };
        respository.login(tel,password,callback);
    }

    /**
     *判读是否登录成功
     * @param msg
     * @return
     */
    private boolean loginIncicate(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int status = jsonObject.getInt(ParamConfig.HTTP_STATUS);
            if (status == ParamConfig.HTTP_STATUS_SUCCESS){
                Log.i(TAG, "indicate success");
                return true ;
            }else {
                Log.i(TAG, "indicate fail --- code:"+status);
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 登录成功时调用
     */
    private void loginSuccess(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String loginTime = jsonObject.getString(ParamConfig.TIME);
            String message = jsonObject.getString(ParamConfig.MSG);
            String token = jsonObject.getString(ParamConfig.TOKEN);
            String user = jsonObject.getString(ParamConfig.User);
            String family = jsonObject.getString(ParamConfig.Family);
            String familyUsers = jsonObject.getString(ParamConfig.FamilyUsers);
            Log.i(TAG, "loginSuccess: msg --- "+message);
            respository.saveDataParams(token,loginTime,user,family,familyUsers);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.loginSuccess();
    }

}
