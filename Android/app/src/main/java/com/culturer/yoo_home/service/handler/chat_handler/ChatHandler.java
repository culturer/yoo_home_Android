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
		//处理新的聊天消息
		if (msg.getStatus() == ChatMsg.Chat_Msg_Sending){
			//更新UI显示
			CacheData.chatMsgs.add(msg);
			EventBus.getDefault().post(msg);
			//发送消息反馈，改变发送方消息状态
			ChatMsg statusMsg = new ChatMsg(msg.getId(),ChatMsg.Chat_Msg_Success);
			String strMsg = gson.toJson(statusMsg);
			MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.CHAT_MSG,strMsg);
			EventBus.getDefault().post(mqttMsg);
		}
		//处理聊天消息已接收状态变化
		if(msg.getStatus() == ChatMsg.Chat_Msg_Success){
			for (int i=0;i<CacheData.chatMsgs.size();i++){
				if (CacheData.chatMsgs.get(i).getId() == msg.getId()){
					CacheData.chatMsgs.get(i).setStatus(ChatMsg.Chat_Msg_Success);
					EventBus.getDefault().post(msg);
					break;
				}
			}
		}
	}
	
}
