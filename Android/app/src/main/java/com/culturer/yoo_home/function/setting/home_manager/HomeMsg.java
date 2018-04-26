package com.culturer.yoo_home.function.setting.home_manager;

public class HomeMsg {
	
	public static final int MDFY_HOME_NAME = 0;
	public static final int MDFY_HOME_NTFY = 1;
	public static final int MDFY_HOME_MSG = 2;
	public static final int ADD_HOME_PERSON = 3;
	public static final int RVM_HOME_PEROSON = 4;
	public static final int CHANGE_HOME = 5;
	public static final int GRANT_HOME = 6;
	
	private int HomeMsgType;
	
	private int Id;
	
	private String FamilyName;
	
	private String FamilyNotifyTitle;
	private String FamilyNotifyContent;
	private String CreateTime;
	
	private String Msg;
	
	private String userTel;
	
	public HomeMsg() {
	
	}
	
	public HomeMsg(int homeMsgType, int id, String familyName, String familyNotifyTitle, String familyNotifyContent, String createTime, String msg) {
		HomeMsgType = homeMsgType;
		Id = id;
		FamilyName = familyName;
		FamilyNotifyTitle = familyNotifyTitle;
		FamilyNotifyContent = familyNotifyContent;
		CreateTime = createTime;
		Msg = msg;
	}
	
	public HomeMsg(int homeMsgType, int id,String userTel) {
		HomeMsgType = homeMsgType;
		Id = id;
		this.userTel = userTel;
	}
	
	public int getHomeMsgType() {
		return HomeMsgType;
	}
	
	public void setHomeMsgType(int homeMsgType) {
		HomeMsgType = homeMsgType;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getFamilyName() {
		return FamilyName;
	}
	
	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}
	
	public String getFamilyNotifyTitle() {
		return FamilyNotifyTitle;
	}
	
	public void setFamilyNotifyTitle(String familyNotifyTitle) {
		FamilyNotifyTitle = familyNotifyTitle;
	}
	
	public String getFamilyNotifyContent() {
		return FamilyNotifyContent;
	}
	
	public void setFamilyNotifyContent(String familyNotifyContent) {
		FamilyNotifyContent = familyNotifyContent;
	}
	
	public String getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	public String getMsg() {
		return Msg;
	}
	
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public String getUserTel() {
		return userTel;
	}
	
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
}
