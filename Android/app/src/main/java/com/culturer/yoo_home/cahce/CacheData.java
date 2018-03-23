package com.culturer.yoo_home.cahce;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.culturer.yoo_home.base.BaseApplication;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;
import com.culturer.yoo_home.util.ACache;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存操作类，用于操作缓存数据
 */
public class CacheData {

	/**
	 *  内存缓存
	 */

	//用户相册缓存
	public static List<Album> userAlbums = new ArrayList<>();
	//家庭相册缓存
	public static List<Album> familyAlbums = new ArrayList<>();
	//图片路径缓存
	public static List<Photo> photos = new ArrayList<>();
	//家庭活动缓存
	public static List<Activity> homeActivities = new ArrayList<>();
	//活动项缓存
	public static List<ActivityItem> homeActivityItems = new ArrayList<>();
	//日程安排缓存
	public static List<Arrangement> arrangements = new ArrayList<>();
	//亲友圈缓存
	public static List<Article> articles = new ArrayList<>();
	//评论缓存
	public static List<Comment> comments = new ArrayList<>();
	//家庭成员缓存
	public static List<User> familyUsers = new ArrayList<>();
	//新建家庭活动时的家庭活动临时缓存
	public static Activity tmp_activity = new Activity(-1l, (long) BaseMsg.getFamily().getId(),"","活动开始啦",-1l);
	//新建家庭活动时的活动项临时缓存
	public static List<ActivityItem> tmp_activity_item = new ArrayList<>();

	/**
	 *  本地缓存
	 */

	ACache mCache = ACache.get(BaseApplication.getContext());

	//本地缓存 --- 用户相册
	public void put_userAlbums(JSONArray userAlbums){
		mCache.put("userAlbums",userAlbums);
	}

	public JSONArray get_userAlbums(){
		return mCache.getAsJSONArray("userAlbums");
	}

	//本地缓存 --- 家庭相册
	public void put_familyAlbums(JSONArray familyAlbums){
		mCache.put("familyAlbums",familyAlbums);
	}

	public JSONArray get_familyAlbums(){
		return mCache.getAsJSONArray("familyAlbums");
	}

	//本地缓存图片路径
	public void put_photos(JSONArray photos){
		mCache.put("photos",photos);
	}

	public JSONArray get_photos(){
		return mCache.getAsJSONArray("photos");
	}

	//本地缓存家庭活动
	public void put_homeActivities(JSONArray homeActivities){
		mCache.put("homeActivities",homeActivities);
	}

	public JSONArray get_homeActivities(){
		return mCache.getAsJSONArray("homeActivities");
	}

	//本地缓存家庭活动项
	public void put_homeActivityItems(JSONArray homeActivityItems){
		mCache.put("homeActivityItems",homeActivityItems);
	}

	public JSONArray get_homeActivityItems(){
		return mCache.getAsJSONArray("homeActivityItems");
	}

	//本地缓存日程安排
	public void put_arrangements(JSONArray arrangements){
		mCache.put("arrangements",arrangements);
	}

	public JSONArray get_arrangements(){
		return mCache.getAsJSONArray("arrangements");
	}

	//本地缓存亲友圈
	public void put_articles(JSONArray articles){
		mCache.put("articles",articles);
	}

	public JSONArray get_articles(){
		return mCache.getAsJSONArray("articles");
	}

	//本地缓存评论
	public void put_comments(JSONArray comments){
		mCache.put("comments",comments);
	}

	public JSONArray get_comments(){
		return mCache.getAsJSONArray("comments");
	}

	//本地缓存家庭乘员
	public void put_familyUsers(JSONArray familyUsers){
		mCache.put("familyUsers",familyUsers);
	}

	public JSONArray get_familyUsers(){
		return mCache.getAsJSONArray("familyUsers");
	}

	//本地缓存临时活动
	public void put_tmp_activity(JSONObject tmp_activity){
		mCache.put("tmp_activity",tmp_activity);
	}

	public JSONObject get_tmp_activity(){
		return mCache.getAsJSONObject("tmp_activity");
	}

	//本地缓存临时活动项
	public void put_tmp_activity_item(JSONArray tmp_activity_item){
		mCache.put("tmp_activity_item",tmp_activity_item);
	}

	public JSONArray get_tmp_activity_item(){
		return mCache.getAsJSONArray("tmp_activity_item");
	}

	//缓存图片
	public void put_Bitmap(String key , Bitmap bitmap){
		mCache.put(key, bitmap);
	}

	public Bitmap get_Bitmap(String key){
		return mCache.getAsBitmap(key);
	}
}
