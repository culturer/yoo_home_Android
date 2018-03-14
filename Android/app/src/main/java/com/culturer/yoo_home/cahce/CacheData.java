package com.culturer.yoo_home.cahce;


import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;

import java.util.ArrayList;
import java.util.List;

public class CacheData {

	public static List<Album> userAlbums = new ArrayList<>();
	public static List<Album> familyAlbums = new ArrayList<>();
	public static List<Photo> photos = new ArrayList<>();
	public static List<Activity> homeActivities = new ArrayList<>();
	public static List<ActivityItem> homeActivityItems = new ArrayList<>();
	public static List<Arrangement> arrangements = new ArrayList<>();
	public static List<Article> articles = new ArrayList<>();
	public static List<Comment> comments = new ArrayList<>();
	public static List<User> familyUsers = new ArrayList<>();

	public static Activity tmp_activity = new Activity(-1l, (long) BaseMsg.getFamily().getId(),"","活动开始啦",-1l);
	public static List<ActivityItem> tmp_activity_item = new ArrayList<>();

}
