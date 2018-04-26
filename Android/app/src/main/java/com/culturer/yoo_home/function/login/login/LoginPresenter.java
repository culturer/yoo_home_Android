package com.culturer.yoo_home.function.login.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.culturer.yoo_home.config.ParamConfig;
import com.kymjs.rxvolley.client.HttpCallback;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



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
        
        view.logining();
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "onSuccess: "+t);
                if (loginIncicate(t)){
                    loginSuccess(t);
                }else {
                    Toast.makeText(context,"账号或密码错误请重新登陆",Toast.LENGTH_LONG).show();
                    view.loginFail();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo"+errorNo+"||errMsg"+strMsg);
                Toast.makeText(context,"网络连接异常，请检查网络后重新登陆",Toast.LENGTH_LONG).show();
                view.loginFail();
            }
        };
        respository.login(tel,password,callback);
//        doLogin(tel,password);
        
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
    

    
    public void register(){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.netease.im/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);
        
        String appKey = "1bb52e6d39346c535ca7194474a0f511";
        String appSecret = "123456789012";
        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
//        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
        String checkSum = appSecret+nonce +curTime;//参考 计算CheckSum的java代码
        
        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", "helloworld"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        // 执行请求
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 打印执行结果
        try {
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
