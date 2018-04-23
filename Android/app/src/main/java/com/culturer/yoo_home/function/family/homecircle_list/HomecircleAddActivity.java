package com.culturer.yoo_home.function.family.homecircle_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class HomecircleAddActivity extends AppCompatActivity {
	
	
	private View contentView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentView = LayoutInflater.from(this).inflate(R.layout.activity_homecircle_add,null);
		setContentView(contentView);
		init();
	}
	
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
	
	}
	
	private void initView(){
		initBaseView();
		initNavigation(contentView);
	}
	
	private void initBaseView(){
	
	}
	
	//初始化导航条
	private void initNavigation(View contentView) {
		LinearLayout topNavigation = contentView.findViewById(R.id.container);
		HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
		builder.setCenterHomeTopic("Yoo+")
				.setCenterHomeTitle("心若向阳，无畏悲伤")
				.create().
				build();
	}
}
