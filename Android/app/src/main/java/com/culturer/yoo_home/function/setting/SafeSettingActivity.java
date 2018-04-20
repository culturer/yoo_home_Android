package com.culturer.yoo_home.function.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class SafeSettingActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_safe_setting);
		init();
	}
	
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
	
	}
	
	private void initView(){
	
	}
	
	private void initNavigation() {
		LinearLayout topNavigation = findViewById(R.id.container);
		HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
		builder.setCenterHomeTopic("Yoo+")
				.setCenterHomeTitle("安全设置")
				.create().
				build();
	}
	
}
