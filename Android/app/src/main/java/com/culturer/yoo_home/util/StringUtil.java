package com.culturer.yoo_home.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	private static final String TAG = "StringUtil" ;

	public static String getDateEN() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;// 2012-10-03 23:41:31
	}

	public static String getStandardDate(String date){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("MM-dd HH:mm");
		String datetime = "";
		try {
			Date datecount = format1.parse(date);
			datetime = format2.format(datecount);
			return datetime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datetime;
	}

	public static String getDataMsg(String msg){
		String[] datas = msg.split(",");
		return datas[datas.length-1];
	}

	public static Bitmap stringtoBitmap(String msg){
		//将字符串转换成Bitmap类型
		Bitmap bitmap=null;
		try {
			byte[]bitmapArray;
			bitmapArray= Base64.decode(msg, Base64.DEFAULT);
			bitmap= BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
		} catch (Exception e) {
			Log.i(TAG, "stringtoBitmap: "+e.getMessage());
			e.printStackTrace();
		}
		return bitmap;
	}
}
