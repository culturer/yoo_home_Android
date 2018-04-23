package com.culturer.yoo_home.function.chat;


import com.culturer.yoo_home.R;
import com.culturer.yoo_home.util.TimeUtil;

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
	//消息已读
	public static final int Chat_Msg_Read = 3;
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
	//聊天信息种类 --- 文本，图片，音频，视频
	private int Chat_Type;
//	//是否是群消息
//	private boolean isGroup;
//	//群消息已读人数
//	private int readCount;
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
	
	private String createTime;
	
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
		this.createTime = TimeUtil.getCurrentTime();
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
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	static String getRelStatus(ChatMsg msg){
		String strMsg = "";
		if (msg.getStatus() == Chat_Msg_Fail){
			strMsg = "失败";
		}
		if (msg.getStatus() == Chat_Msg_Success){
			strMsg = "送达";
		}
		if (msg.getStatus() == Chat_Msg_Sending){
			strMsg = "发送中";
		}
		if (msg.getStatus() == Chat_Msg_Read){
			strMsg = "已读";
		}
		return strMsg;
	}
	
	static int getBackground(ChatMsg msg){
		if (msg.getStatus() == Chat_Msg_Sending){
			return R.drawable.chat_status_sending_bg;
		}
		if (msg.getStatus() == Chat_Msg_Success){
			return R.drawable.chat_status_success_bg;
		}
		if (msg.getStatus() == Chat_Msg_Read){
			return R.drawable.chat_status_read_bg;
		}
		if (msg.getStatus() == Chat_Msg_Fail){
			return R.drawable.chat_status_fail_bg;
		}
		return 0;
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
