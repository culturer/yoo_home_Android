package com.culturer.yoo_home.test;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.samanlan.lib_permisshelper.PermissionsListener;
import com.samanlan.lib_permisshelper.PermissionsUtils;

public class TestPerActivity extends AppCompatActivity {
	
	PermissionsUtils mPermissionsUtils=new PermissionsUtils();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_per);
		
		Button grantOne = findViewById(R.id.grantOne);
		grantOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPermissionWithTip(grantOne);
			}
		});
		
		Button grantGroup = findViewById(R.id.grantGroup);
		grantGroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPermissionsWithTip(grantGroup);
			}
		});
		
		Button grantOneD = findViewById(R.id.grantOneD);
		grantOneD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPermission(grantOneD);
			}
		});
		
		Button grantGroupD = findViewById(R.id.grantGroupD);
		grantGroupD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPermissions(grantGroupD);
			}
		});
	}
	
	/**
	 * 一次申请一个权限（使用拒绝弹框）点击事件
	 */
	public void getPermissionWithTip(View view) {
		if (mPermissionsUtils == null)
			mPermissionsUtils = new PermissionsUtils();
		mPermissionsUtils
				.setPermissionsListener(new PermissionsListener() {
					@Override
					public void onDenied(String[] deniedPermissions) {
						for (int i = 0; i < deniedPermissions.length; i++) {
							Toast.makeText(TestPerActivity.this, deniedPermissions[i] + " 权限被拒绝", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onGranted() {
						Toast.makeText(TestPerActivity.this, "所有权限都被同意", Toast.LENGTH_SHORT).show();
					}
				})
				.withActivity(this)
				.getPermissionsWithTips(TestPerActivity.this,100,new String[]{"人家想开开相机玩玩嘛"}, Manifest.permission.CAMERA);
	}
	
	/**
	 * 一次申请一组权限（使用拒绝弹框）点击事件
	 */
	public void getPermissionsWithTip(View view) {
		if (mPermissionsUtils == null)
			mPermissionsUtils = new PermissionsUtils();
		mPermissionsUtils
				.setPermissionsListener(new PermissionsListener() {
					@Override
					public void onDenied(String[] deniedPermissions) {
						for (int i = 0; i < deniedPermissions.length; i++) {
							Toast.makeText(TestPerActivity.this, deniedPermissions[i] + " 权限被拒绝", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onGranted() {
						Toast.makeText(TestPerActivity.this, "所有权限都被同意", Toast.LENGTH_SHORT).show();
					}
				})
				.withActivity(this)
				.getPermissionsWithTips(TestPerActivity.this
						,100
						, new String[]{"人家要录音","我们需要读取你的信息，磨磨唧唧的","我要上网","我要读内存卡","我要看日历","我要定位"}
						, Manifest.permission.RECORD_AUDIO
						, Manifest.permission.READ_SMS
						, Manifest.permission.INTERNET
						, Manifest.permission.READ_EXTERNAL_STORAGE
						, Manifest.permission.READ_CALENDAR
						, Manifest.permission.ACCESS_FINE_LOCATION);
	}
	
	/**
	 * 一次申请一个权限点击事件
	 */
	public void getPermission(View view) {
		if (mPermissionsUtils == null)
			mPermissionsUtils = new PermissionsUtils();
		mPermissionsUtils
				.setPermissionsListener(new PermissionsListener() {
					@Override
					public void onDenied(String[] deniedPermissions) {
						for (int i = 0; i < deniedPermissions.length; i++) {
							Toast.makeText(TestPerActivity.this, deniedPermissions[i] + " 权限被拒绝", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onGranted() {
						Toast.makeText(TestPerActivity.this, "所有权限都被同意", Toast.LENGTH_SHORT).show();
					}
				})
				.withActivity(this)
				.getPermissions(TestPerActivity.this,100, Manifest.permission.READ_CONTACTS);
	}
	
	/**
	 * 一次申请一组权限点击事件
	 */
	public void getPermissions(View view) {
		if (mPermissionsUtils == null)
			mPermissionsUtils = new PermissionsUtils();
		mPermissionsUtils
				.setPermissionsListener(new PermissionsListener() {
					@Override
					public void onDenied(String[] deniedPermissions) {
						for (int i = 0; i < deniedPermissions.length; i++) {
							Toast.makeText(TestPerActivity.this, deniedPermissions[i] + " 权限被拒绝", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onGranted() {
						Toast.makeText(TestPerActivity.this, "所有权限都被同意", Toast.LENGTH_SHORT).show();
					}
				})
				.withActivity(this)
				.getPermissions(TestPerActivity.this
						, 100
						, Manifest.permission.INTERNET
						, Manifest.permission.READ_EXTERNAL_STORAGE
						, Manifest.permission.READ_CALENDAR
						, Manifest.permission.ACCESS_FINE_LOCATION);
	}
	
}
