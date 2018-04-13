package com.culturer.yoo_home.util;

import android.media.MediaPlayer;

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
	
	public void stopPlaying(){
		if (mediaPlayer!=null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	
}
