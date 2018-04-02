package com.culturer.yoo_home.service.handler.chat_handler;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class ChatMsg {
	
	//消息发送中
	public static final int Chat_Msg_Sending = 0 ;
	//消息发送成功
	public static final int Chat_Msg_Success = 1;
	//消息发送失败
	public static final int Chat_Msg_Fail = 2;
	//文本
	public static final int Chat_Msg_Text = 0;
	//图片
	public static final int Chat_Msg_Photo = 1;
	//视频
	public static final int Chat_Msg_Video = 2;
	//音频
	public static final int Chat_Msg_Audio = 3;
	//表情
	public static final int Chat_Msg_Emoji = 4;
	//链接
	public static final int Chat_Msg_URL = 5;
	
	//消息编号
	private String id;
	//消息状态
	private int status;
	//聊天信息种类
	private int Chat_Type;
	//消息发送方Id
	private int userId;
	//用户名
	private String username;
	//用户头像
	private String userIcon;
	//消息
	private String msg;
	//附件链接
	private String url;
	//接收消息的用户Id
	private List<Integer> users;
	
	public ChatMsg() {
	}
	
	//用于处理消息状态
	public ChatMsg(String id, int status) {
		this.id = id;
		this.status = status;
	}
	
	//用于构建消息体
	public ChatMsg(int status, int chat_Type, int userId, String username, String userIcon, String msg, String url, List<Integer> users) {
		this.id = getMyUUID();
		this.status = status;
		Chat_Type = chat_Type;
		this.userId = userId;
		this.username = username;
		this.userIcon = userIcon;
		this.msg = msg;
		this.url = url;
		this.users = users;
	}
	
	//生成id
	private String getMyUUID(){
		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		return uniqueId;
	}
	
	public String getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getChat_Type() {
		return Chat_Type;
	}
	
	public void setChat_Type(int chat_Type) {
		Chat_Type = chat_Type;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserIcon() {
		return userIcon;
	}
	
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Integer> getUsers() {
		return users;
	}
	
	public void setUsers(List<Integer> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return "ChatMsg{" +
				"Chat_Type=" + Chat_Type +
				", userId=" + userId +
				", username='" + username + '\'' +
				", userIcon='" + userIcon + '\'' +
				", msg='" + msg + '\'' +
				", url='" + url + '\'' +
				", users=" + users +
				'}';
	}
}
