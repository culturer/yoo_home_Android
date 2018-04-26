package com.culturer.yoo_home.function.setting.home_manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.function.setting.create_home.CreateHomeActivity;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.culturer.yoo_home.util.HttpUtil;
import com.google.gson.Gson;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import static com.culturer.yoo_home.config.Urls.FAMILY_URL;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeManagerHandler {
	
	private static final String TAG = "HomeManagerHandler";
	
	private Gson gson;
	
	void mdfyHomeName(String homeName){
		
		//发送http请求更新服务器数据
		HttpParams params = new HttpParams();
		params.put("familyName",homeName);
		params.put("userId",BaseMsg.getUser().getId());
		params.put("familyId",BaseMsg.getFamily().getId());
		params.put("options",3);
		HttpCallback callback = new HttpCallback() {
			@Override
			public void onSuccess(String t) {
				Log.i(TAG, "onSuccess: "+t);
			}
			
			@Override
			public void onFailure(int errorNo, String strMsg) {
				Log.i(TAG, "onFailure: errNo --- "+errorNo+",errMsg --- "+strMsg);
			}
		};
		HttpUtil.send(callback,params,FAMILY_URL);
		
		//修改本地缓存
		Family family = BaseMsg.getFamily();
		family.setFamilyName(homeName);
		BaseMsg.updateFamily(family);
		
		//发送推送信息
		HomeMsg homeMsg = new HomeMsg(HomeMsg.MDFY_HOME_NAME
				,BaseMsg.getFamily().getId()
				,homeName
				,BaseMsg.getFamily().getFamilyNotifyTitle()
				,BaseMsg.getFamily().getFamilyNotifyContent()
				,BaseMsg.getFamily().getCreateTime()
				,BaseMsg.getFamily().getMsg());
		if (gson ==null){
			gson = new Gson();
		}
		String msg = gson.toJson(homeMsg);
		MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.HOME_MSG,msg);
		
	}
	
	void addPerson(String tel){
		
		//发送推送消息
		HomeMsg homeMsg = new HomeMsg(HomeMsg.ADD_HOME_PERSON
								,BaseMsg.getFamily().getId()
								,tel);
		if (gson!=null){
			gson = new Gson();
		}
		String msg = gson.toJson(homeMsg);
		MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.HOME_MSG,msg);
		
	}
	
	void removePerson(String tel){
		//发送http
		
		//更新缓存
		
		//发送推送消息
		HomeMsg homeMsg = new HomeMsg(HomeMsg.RVM_HOME_PEROSON
				,BaseMsg.getFamily().getId()
				,tel);
		if (gson!=null){
			gson = new Gson();
		}
		String msg = gson.toJson(homeMsg);
		MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.HOME_MSG,msg);
	}
	
	void createHome(Context context){
		Intent intent = new Intent(context,CreateHomeActivity.class);
		context.startActivity(intent);
	}
	
	void changeHome(String homeId){
		//发送http
		
		//更新缓存
		
		//发送推送消息
	}
	
	public void grantHome(){
	
	}
}
