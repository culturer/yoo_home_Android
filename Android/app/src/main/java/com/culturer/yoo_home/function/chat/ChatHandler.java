package com.culturer.yoo_home.function.chat;

import android.util.Log;

import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ChatHandler {
	
	private static final String TAG = "ChatHandler";
	
	
	public ChatHandler() {
		initHandler();
	}
	
	private void initHandler() {
	
	}
	
	//处理接收到的新消息
	public void handle(ChatMsg msg) {
		Log.i(TAG, "handle: "+msg.toString());
		if (indicate(msg)){
			Gson gson = new Gson();
			//处理新的聊天消息
			if (msg.getStatus() == ChatMsg.Chat_Msg_Sending){
				Log.i(TAG, "handle: 处理新消息");
				//发送消息反馈，改变发送方消息状态
				ChatMsg statusMsg = new ChatMsg(msg.getId(),ChatMsg.Chat_Msg_Success);
				String strMsg = gson.toJson(statusMsg);
				Log.i(TAG, "handle: 发送收到确认接收到消息通知");
				MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.CHAT_MSG,strMsg);
				EventBus.getDefault().post(mqttMsg);
				//更新UI显示
				CacheData.chatMsgs.add(msg);
				EventBus.getDefault().post(msg);
			}
			//处理聊天消息已接收状态变化
			if(msg.getStatus() == ChatMsg.Chat_Msg_Success){
				Log.i(TAG, "handle: 处理消息送达通知");
				if (CacheData.chatMsgs!=null  && CacheData.chatMsgs.size()>0){
					for (int i=0;i<CacheData.chatMsgs.size();i++){
						if (CacheData.chatMsgs.get(i).getId() .equals( msg.getId())){
							CacheData.chatMsgs.get(i).setStatus(ChatMsg.Chat_Msg_Success);
							EventBus.getDefault().post(msg);
							break;
						}
					}
				}else {
					Log.i(TAG, "handle: CacheData.chatMsgs is null");
				}
			}
			//处理消息的已读未读状态
			if (msg.getStatus() == ChatMsg.Chat_Msg_Read){
				Log.i(TAG, "handle: 处理消息已读通知");
				if ( CacheData.chatMsgs != null && CacheData.chatMsgs.size()>0 ){
					for (int i=0;i<CacheData.chatMsgs.size();i++){
						if (CacheData.chatMsgs.get(i).getId() .equals( msg.getId())){
							CacheData.chatMsgs.get(i).setStatus(ChatMsg.Chat_Msg_Read);
							EventBus.getDefault().post(msg);
							break;
						}
					}
				}else {
					Log.i(TAG, "handle: CacheData.chatMsgs is null");
				}
				
			}
		}
	}
	
	//权限验证
	private boolean indicate(ChatMsg chatMsg){
		boolean flag = false;
		//验证消息是不是自己发的
		if (chatMsg.getUserId()!= BaseMsg.getUser().getId()) {
			//验证消息是不是发给自己的
			List<Integer> users = chatMsg.getUsers();
			if (users!=null){
				for (int i=0;i<users.size();i++){
					if (users.get(i) == BaseMsg.getUser().getId()){
						flag = true;
						break;
					}
				}
				Log.i(TAG, "indicate: the msg is not sent to me ");
			}
			Log.i(TAG, "indicate: users is null");
			//方便调试，先关闭用户验证
			flag = true;
		}else {
			Log.i(TAG, "indicate: the msg is sent by myself --- "+chatMsg.toString());
		}
		return flag;
	}
}
