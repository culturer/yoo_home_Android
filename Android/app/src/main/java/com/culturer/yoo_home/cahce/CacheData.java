package com.culturer.yoo_home.cahce;


import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;
import com.culturer.yoo_home.service.handler.chat_handler.ChatMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存操作类，用于操作缓存数据
 */
public class CacheData {

	/**
	 *  内存缓存
	 */
	
	//聊天消息缓存
	public static List<ChatMsg> chatMsgs;
	//用户相册缓存
	public static List<Album> userAlbums;
	//家庭相册缓存
	public static List<Album> familyAlbums ;
	//图片路径缓存
	public static List<Photo> photos ;
	//家庭活动缓存
	public static List<Activity> homeActivities ;
	//活动项缓存
	public static List<ActivityItem> homeActivityItems ;
	//日程安排缓存
	public static List<Arrangement> arrangements;
	//亲友圈缓存
	public static List<Article> articles  ;
	//评论缓存
	public static List<Comment> comments;
	//家庭成员缓存
	public static List<User> familyUsers ;
	//新建家庭活动时的家庭活动临时缓存
	public static Activity tmp_activity ;
	//新建家庭活动时的活动项临时缓存
	public static List<ActivityItem> tmp_activity_item ;

	//初始化缓存
	public static void init(){
		chatMsgs = new ArrayList<>();
		userAlbums = new ArrayList<>();
		familyAlbums = new ArrayList<>();
		photos = new ArrayList<>();
		homeActivities = new ArrayList<>();
		homeActivityItems = new ArrayList<>();
		arrangements = new ArrayList<>();
		articles = new ArrayList<>();
		comments = new ArrayList<>();
		familyUsers = new ArrayList<>();
		tmp_activity = new Activity(-1l, true,(long) BaseMsg.getFamily().getId(),"","活动开始啦",-1l);
		tmp_activity_item = new ArrayList<>();
	}

	//将内存缓存存入本地缓存
	public static void saveDatas(){

	}

	//将本地缓存读入内存缓存
	public static void getDatas(){

	}

}
