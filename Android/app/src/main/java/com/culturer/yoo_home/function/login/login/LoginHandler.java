package com.culturer.yoo_home.function.login.login;

import android.content.Context;
import android.util.Log;

import com.culturer.yoo_home.cahce.BaseMsg;

public class LoginHandler {
	
	private static final String TAG = "LoginHandler" ;
	
	private Context context;
	
	public LoginHandler(Context context) {
		this.context = context;
	}
	
	public void handle(LoginMsg msg){
		if (msg.getUserId() == BaseMsg.getUser().getId()){
			Log.i(TAG, "handle: 我在异地登录，强制下线！");
		}
	}
	
}
