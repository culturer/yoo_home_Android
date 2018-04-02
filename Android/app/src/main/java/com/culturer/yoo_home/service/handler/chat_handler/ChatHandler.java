package com.culturer.yoo_home.service.handler.chat_handler;

import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;


public class ChatHandler {
	
	Gson gson;
	
	public ChatHandler() {
		initHandler();
	}
	
	private void initHandler() {
		gson = new Gson();
	}
	
	//处理接收到的新消息
	public void handle(ChatMsg msg) {
		//更新UI显示
		msg.setStatus(ChatMsg.Chat_Msg_Success);
		CacheData.chatMsgs.add(msg);
		EventBus.getDefault().post(msg);
		//发送消息反馈，改变发送方消息状态
		ChatMsg statusMsg = new ChatMsg(msg.getId(),msg.getStatus());
		String strMsg = gson.toJson(statusMsg);
		MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.CHAT_MSG_Status,strMsg);
		EventBus.getDefault().post(mqttMsg);
	}
	
	//处理消息的状态如发送成功，失败，已读，未读
	public void handleStatus(ChatMsg msg){
		//接收到消息反馈后更新UI显示
		if (msg.getStatus() == ChatMsg.Chat_Msg_Success){
			for (int i=0 ; i< CacheData.chatMsgs.size(); i++){
			
			}
		}
		if (msg.getStatus() == ChatMsg.Chat_Msg_Fail){

		}
	}
}
