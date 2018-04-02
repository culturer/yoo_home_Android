package com.culturer.yoo_home.service.MQTT;

import com.culturer.yoo_home.service.handler.chat_handler.ChatHandler;
import com.culturer.yoo_home.service.handler.chat_handler.ChatMsg;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class MQTTHandler {
	
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
		JSONObject jMsg = new JSONObject(msg);
		int type = jMsg.getInt("type");
		String mMsg = jMsg.getString("msg");
		if (type == MQTTMsg.CHAT_MSG_New) {
			ChatMsg chatMsg = gson.fromJson(mMsg, ChatMsg.class);
			chatHandler.handle(chatMsg);
		}
		if (type == MQTTMsg.CHAT_MSG_Status){
			ChatMsg chatMsg = gson.fromJson(mMsg, ChatMsg.class);
			chatHandler.handleStatus(chatMsg);
		}
	}
	
}
