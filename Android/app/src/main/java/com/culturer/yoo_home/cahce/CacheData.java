package com.culturer.yoo_home.cahce;


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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
		userAlbums = new ArrayList<>();
		familyAlbums = new ArrayList<>();
		photos = new ArrayList<>();
		homeActivities = new ArrayList<>();
		homeActivityItems = new ArrayList<>();
		arrangements = new ArrayList<>();
		articles = new ArrayList<>();
		comments = new ArrayList<>();
		familyUsers = new ArrayList<>();
		tmp_activity = new Activity(-1l, (long) BaseMsg.getFamily().getId(),"","活动开始啦",-1l);
		tmp_activity_item = new ArrayList<>();
		mCache = ACache.get(BaseApplication.getContext());
	}

	//将内存缓存存入本地缓存
	public static void saveDatas() throws JSONException {
			JSONArray jUserAlbums = list2JsonArray(userAlbums);
			JSONArray jFamilyAlbums = list2JsonArray(familyAlbums);
			JSONArray jPhotos = list2JsonArray(photos);
			JSONArray jHomeActivities = list2JsonArray(homeActivities);
			JSONArray jHomeActivityItems = list2JsonArray(homeActivityItems);
			JSONArray jArrangements = list2JsonArray(arrangements);
			JSONArray jArticles = list2JsonArray(articles);
			JSONArray jComments = list2JsonArray(comments);
			JSONArray jFamilyUsers = list2JsonArray(familyUsers);
			JSONObject jTmp_activity = object2JsonObject(tmp_activity);
			JSONArray jTmp_activity_item = list2JsonArray(tmp_activity_item);

			put_userAlbums(jUserAlbums);
			put_familyAlbums(jFamilyAlbums);
			put_photos(jPhotos);
			put_homeActivities(jHomeActivities);
			put_homeActivityItems(jHomeActivityItems);
			put_arrangements(jArrangements);
			put_articles(jArticles);
			put_comments(jComments);
			put_familyUsers(jFamilyUsers);
			put_tmp_activity(jTmp_activity);
			put_tmp_activity_item(jTmp_activity_item);
	}
	//将本地缓存读入内存缓存
	public static void getDatas() throws JSONException {
		userAlbums = jsonArray2List(get_userAlbums(),Album.class);
		familyAlbums = jsonArray2List(get_familyAlbums(),Album.class);
		photos = jsonArray2List(get_photos(),Photo.class);
		homeActivities = jsonArray2List(get_homeActivities(),Activity.class);
		homeActivityItems = jsonArray2List(get_homeActivityItems(),ActivityItem.class);
		arrangements = jsonArray2List(get_arrangements(),Arrangement.class);
		articles = jsonArray2List(get_articles(),Article.class);
		comments = jsonArray2List(get_comments(),Comment.class);
		familyUsers = jsonArray2List(get_familyUsers(),User.class);
		tmp_activity = (Activity) jsonObject2Object(get_tmp_activity(),Activity.class);
		tmp_activity_item = jsonArray2List(get_tmp_activity_item(),ActivityItem.class);
	}

	//将List解析成JsonArray
	private static JSONArray list2JsonArray( List datas  ) throws JSONException {
		Gson gson = new Gson();
		if (datas == null || datas.size() == 0){
			return null;
		}
		JSONArray jItems = new JSONArray();
		for (int i=0;i<datas.size();i++){
			JSONObject jItem = new JSONObject(gson.toJson(datas.get(i))) ;
			jItems.put(jItem);
		}
		return jItems ;
	}

	//将Object解析成JsonObject
	private static JSONObject object2JsonObject(Object data) throws JSONException {
		if (data == null){
			return null;
		}
		Gson gson = new Gson();
		JSONObject result = new JSONObject(gson.toJson(data)) ;
		return result;
	}

	//这里使用泛型优化是不是更好?
	//将JsonArray解析成List
	private static List jsonArray2List(JSONArray jsonArray, Type type) throws JSONException {
		if (jsonArray == null){
			return null;
		}
		Gson gson = new Gson();
		List<Object> results = new ArrayList();
		for (int i=0;i<jsonArray.length();i++){
			Object object =  gson.fromJson(jsonArray.getString(i),type);
			results.add(object);
		}
		return results;
	}

	//将JsonObject解析成Object
	private static Object jsonObject2Object(JSONObject data,Type type){
		if (data == null){
			return null;
		}
		Gson gson = new Gson();
		Object result = gson.fromJson(data.toString(),type);
		return result;
	}

	/**
	 *  本地缓存
	 */

	private static ACache mCache ;

	//本地缓存 --- 用户相册
	public static void put_userAlbums(JSONArray userAlbums){
		mCache.put("userAlbums",userAlbums);
	}

	public static JSONArray get_userAlbums(){
		return mCache.getAsJSONArray("userAlbums");
	}

	//本地缓存 --- 家庭相册
	public static void put_familyAlbums(JSONArray familyAlbums){
		mCache.put("familyAlbums",familyAlbums);
	}

	public static JSONArray get_familyAlbums(){
		return mCache.getAsJSONArray("familyAlbums");
	}

	//本地缓存图片路径
	public static void put_photos(JSONArray photos){
		mCache.put("photos",photos);
	}

	public static JSONArray get_photos(){
		return mCache.getAsJSONArray("photos");
	}

	//本地缓存家庭活动
	public static void put_homeActivities(JSONArray homeActivities){
		mCache.put("homeActivities",homeActivities);
	}

	public static JSONArray get_homeActivities(){
		return mCache.getAsJSONArray("homeActivities");
	}

	//本地缓存家庭活动项
	public static void put_homeActivityItems(JSONArray homeActivityItems){
		mCache.put("homeActivityItems",homeActivityItems);
	}

	public static JSONArray get_homeActivityItems(){
		return mCache.getAsJSONArray("homeActivityItems");
	}

	//本地缓存日程安排
	public static void put_arrangements(JSONArray arrangements){
		mCache.put("arrangements",arrangements);
	}

	public static JSONArray get_arrangements(){
		return mCache.getAsJSONArray("arrangements");
	}

	//本地缓存亲友圈
	public static void put_articles(JSONArray articles){
		mCache.put("articles",articles);
	}

	public static JSONArray get_articles(){
		return mCache.getAsJSONArray("articles");
	}

	//本地缓存评论
	public static void put_comments(JSONArray comments){
		mCache.put("comments",comments);
	}

	public static JSONArray get_comments(){
		return mCache.getAsJSONArray("comments");
	}

	//本地缓存家庭乘员
	public static void put_familyUsers(JSONArray familyUsers){
		mCache.put("familyUsers",familyUsers);
	}

	public static JSONArray get_familyUsers(){
		return mCache.getAsJSONArray("familyUsers");
	}

	//本地缓存临时活动
	public static void put_tmp_activity(JSONObject tmp_activity){
		mCache.put("tmp_activity",tmp_activity);
	}

	public static JSONObject get_tmp_activity(){
		return mCache.getAsJSONObject("tmp_activity");
	}

	//本地缓存临时活动项
	public static void put_tmp_activity_item(JSONArray tmp_activity_item){
		mCache.put("tmp_activity_item",tmp_activity_item);
	}

	public static JSONArray get_tmp_activity_item(){
		return mCache.getAsJSONArray("tmp_activity_item");
	}

}
