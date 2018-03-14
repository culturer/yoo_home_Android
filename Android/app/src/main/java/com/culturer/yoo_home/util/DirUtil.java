/**
 * 文件名称 : DirUtil.java
 * <p>
 * 作者信息 : yxj
 * <p>
 * 创建时间 : 2014-7-18, 下午5:34:30
 * <p>
 * 版权声明 : Copyright (c) 2010-2016 Fangle Ltd. All rights reserved
 * <p>
 * 评审记录 :
 * <p>
 */

package com.culturer.yoo_home.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 请在这里增加文件描述
 * <p>
 */
public final class DirUtil
{
    /**
     * 根目录
     */
    public final static String ROOT = Environment.getExternalStorageDirectory().getPath() + File.separator
            + "YOOPLUS";

    /**
     * 图片目录
     */
    public final static String IMG_PATH = ROOT + File.separator + "Files/img";
    /**
     * 视频目录
     */
    public final static String VIDEO_PATH = ROOT + File.separator + "Files/video/";
    
    /**
     * 音頻目录
     */
    public final static String AUDIO_PATH = ROOT + File.separator + "Files/audio";

//    public static final String savePath = Environment.getExternalStorageDirectory() + "/"+"0APUC/Files/";//

    /**
     * 日志保存目录
     */
    public final static String LOG_FILE_PATH = ROOT+ File.separator+"log";
    public final static String LOG_CRASH_FILE_PATH = ROOT+ File.separator+"Crash"+ File.separator;
    /**
     * 临时文件路径， 进入应用时创建，退出时删除
     */
    public final static String TEMP_PATH = ROOT + File.separator + "temp";
    /**
     * 上传照片目录
     */
    public final static String UPLOAD_IMG_PATH = TEMP_PATH + File.separator + "uploadImg";
    
    /**
     * 上传多媒体文件目录
     */
    public final static String UPLOAD_MEDIA_PATH = TEMP_PATH + File.separator + "uploadMedia";
    
    /**
     * 下载事件照片目录
     */
    public final static String DOWNLOAD_IMG_PATH = TEMP_PATH + File.separator + "downImg";
    
    /**
     * 下载视频目录
     */
    public final static String DOWNLOAD_MEDIA_PATH = TEMP_PATH + File.separator + "downMedia";
    /**
     * 语音识别音频的保存路径
     */
    public final static String STT_VODIO_PATH = TEMP_PATH + File.separator + "stt";
    
    /**
     * 保存图相关json资源文件的目录
     */
    public final static String RES = ROOT + File.separator + "ParkResource";
    
    public static void createDir()
    {
        Log.d("DirUtil", "DirUtil-----------create dir");
//        FileUtil.createDir(ROOT);
//        FileUtil.createDir(IMG_PATH);
//        FileUtil.createDir(VIDEO_PATH);
//        FileUtil.createDir(AUDIO_PATH);
//        FileUtil.createDir(AREA_DATA_PATH);
//        FileUtil.createDir(KEYWORS_PATH);
//        FileUtil.createDir(TEMP_PATH);
//        FileUtil.createDir(UPLOAD_IMG_PATH);
//        FileUtil.createDir(UPLOAD_MEDIA_PATH);
//        FileUtil.createDir(DOWNLOAD_IMG_PATH);
//        FileUtil.createDir(DOWNLOAD_MEDIA_PATH);
//        FileUtil.createDir(LOG_FILE_PATH);
//        FileUtil.createDir(RES);
    }    
    
    /**
     * 删除临时目录
     */
    public static void removeTempDir(){
    }
    //获取文件夹大小
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fList = file.listFiles();
            for(int i=0;i<fList.length;i++) {
                if(fList[i].isDirectory())
                    size = size + getFolderSize(fList[i]);
                else
                    size = size + fList[i].length();
            }
        }
        catch(Exception ex) { ex.printStackTrace(); }
        return size / (1024*1024); //输出xM
    }

    public static String saveBitmap(String path1, Bitmap b) {
//        DST_FOLDER_NAME = dir;
        String path = initPath(path1);
        long dataTake = System.currentTimeMillis();
        String jpegName = path + File.separator + "picture_" + dataTake + ".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return jpegName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String initPath(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        return path;
    }
}
