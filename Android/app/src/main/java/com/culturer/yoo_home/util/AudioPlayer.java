package com.culturer.yoo_home.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

//注意耗时操作尽量不要在主线程中进行
public class AudioPlayer {
	
	private String fileName;
	private MediaPlayer mediaPlayer;
	private  MediaPlayer.OnCompletionListener listener;
	private MediaPlayer.OnErrorListener errListener;
	
	public AudioPlayer(String fileName,MediaPlayer.OnCompletionListener listener,MediaPlayer.OnErrorListener errListener) {
		this.fileName = fileName;
		this.listener = listener;
		this.errListener = errListener;
	}
	
	//播放本地音频文件
	public void startPlaying(){
		if (mediaPlayer == null ){
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnCompletionListener(listener);
			mediaPlayer.setOnErrorListener(errListener);
		}
		if (!fileName.equals("")){
			try {
				mediaPlayer.setDataSource(fileName);
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//播放网络音频文件
	public void startPlaying(Context context ,String fileURL){
		if (mediaPlayer == null ){
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnCompletionListener(listener);
			mediaPlayer.setOnErrorListener(errListener);
		}
		if (!fileName.equals("")){
			try {
//				mediaPlayer.setDataSource(context, Uri.parse("http://localhost:8080/music"));
				mediaPlayer.setDataSource(context, Uri.parse(fileURL));
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopPlaying(){
		if (mediaPlayer!=null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	
}
