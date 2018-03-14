package com.culturer.yoo_home.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/6.
 */

public class TimeUtil {

    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date current = new Date(System.currentTimeMillis());
        String str = format.format(current);
        return str;
    }

    public static String getDataToString(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        return format.format(date);
    }
}
