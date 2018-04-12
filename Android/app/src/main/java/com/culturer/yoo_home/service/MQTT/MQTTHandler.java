package com.culturer.yoo_home.service.MQTT;

import android.util.Log;

import com.culturer.yoo_home.function.chat.ChatHandler;
import com.culturer.yoo_home.function.chat.ChatMsg;
import com.culturer.yoo_home.util.ThreadUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

class MQTTHandler {
	
	private static final String TAG = "MQTTHandler" ;
	
	private Gson gson;
	private ChatHandler chatHandler;
	
	MQTTHandler() {
		initHandler();
	}
	
	private void initHandler() {
		gson = new Gson();
		chatHandler = new ChatHandler();
	}
	
	//将消息分类处理
	void handle(String msg) throws JSONException {
		Log.i(TAG, "handle: "+msg);
		JSONObject jMsg = new JSONObject(msg);
		int type = jMsg.getInt("type");
		String mMsg = jMsg.getString("msg");
		if (type == MQTTMsg.CHAT_MSG) {
			ChatMsg chatMsg = gson.fromJson(mMsg, ChatMsg.class);
			chatHandler.handle(chatMsg);
		}
	}
	
}
