package com.culturer.yoo_home.function.login.login;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class LoginMsg {
	
	private String time;
	private int userId;
	
	public LoginMsg(String time, int userId) {
		this.time = time;
		this.userId = userId;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
