package com.culturer.yoo_home.function.setting.home_manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class HomeManagerActivity extends AppCompatActivity {
	
	private static final String TAG = "HomeManagerActivity" ;
	
	private HomeManagerHandler handler;
	
	private TextView home_mdfy_name;
	private TextView home_add_person;
	private TextView home_remove_person;
	private TextView home_create;
	private TextView home_change;
	private TextView home_grant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_manager);
		init();
	}
	
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
		handler = new HomeManagerHandler();
	}
	
	private void initView(){
		initBaseView();
		initNavigation();
	}
	
	private void initBaseView(){
		home_mdfy_name = findViewById(R.id.home_mdfy_name);
		home_add_person = findViewById(R.id.home_add_person);
		home_remove_person = findViewById(R.id.home_remove_person);
		home_create = findViewById(R.id.home_create);
		home_change = findViewById(R.id.home_change);
		home_grant = findViewById(R.id.home_grant);
		
		mdfyHomeName();
		addPerson();
		removePerson();
		createHome();
		changeHome();
		grantHome();
	}
	
	private void initNavigation() {
		LinearLayout topNavigation = findViewById(R.id.container);
		HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
		builder.setCenterHomeTopic("Yoo+")
				.setCenterHomeTitle("家庭管理")
				.create().
				build();
	}
	
	private void mdfyHomeName(){
		home_mdfy_name.setOnClickListener(view -> {
			View contenView = LayoutInflater.from(HomeManagerActivity.this).inflate(R.layout.home_manager_mdfyname,null);
			AlertDialog.Builder  builder = new AlertDialog.Builder(HomeManagerActivity.this)
					.setTitle("修改家庭名称")
					.setView(contenView)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							EditText home_name = contenView.findViewById(R.id.home_name);
							String strName = home_name.getText().toString();
							handler.mdfyHomeName(strName);
						}
					});
			builder.create().show();
		});
	}
	
	private void addPerson(){
		home_add_person.setOnClickListener(view -> {
			View contenView = LayoutInflater.from(HomeManagerActivity.this).inflate(R.layout.home_manager_addperson,null);
			AlertDialog.Builder  builder = new AlertDialog.Builder(HomeManagerActivity.this)
					.setTitle("添加家庭成员")
					.setView(contenView)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							EditText home_tel = contenView.findViewById(R.id.home_tel);
							String tel = home_tel.getText().toString();
							handler.addPerson(tel);
						}
					});
			builder.create().show();
		});
	}
	
	private void removePerson(){
		home_remove_person.setOnClickListener(new View.OnClickListener() {
			View contenView = LayoutInflater.from(HomeManagerActivity.this).inflate(R.layout.home_manager_removeperson,null);
			@Override
			public void onClick(View view) {
				AlertDialog.Builder  builder = new AlertDialog.Builder(HomeManagerActivity.this)
						.setTitle("移除家庭成员")
						.setView(contenView)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								EditText home_tel = contenView.findViewById(R.id.home_tel);
								String tel = home_tel.getText().toString();
								handler.removePerson(tel);
							}
						});
				builder.create().show();
			}
		});
	}
	
	private void createHome(){
		home_create.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				handler.createHome(HomeManagerActivity.this);
			}
		});
	}
	
	private void changeHome(){
		home_change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				View contenView = LayoutInflater.from(HomeManagerActivity.this).inflate(R.layout.home_manager_change,null);
				AlertDialog.Builder  builder = new AlertDialog.Builder(HomeManagerActivity.this)
						.setTitle("加入新的家庭~")
						.setView(contenView)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								EditText home_id = contenView.findViewById(R.id.home_id);
								String homeId = home_id.getText().toString();
								handler.changeHome(homeId);
							}
						});
				builder.create().show();
			}
		});
	}
	
	private void grantHome(){
		home_grant.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			
			}
		});
	}
	
	//1.修改家庭名字
	//2.添加家庭成员
	//3.移除家庭成员
	//4.创建家庭
	//5.更换家庭
	//6.智能设备授权
	//A.公共设施授权
	//B.个人房间授权
	//C.共享所有权限
	
}
