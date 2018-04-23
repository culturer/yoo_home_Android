package com.culturer.yoo_home.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.culturer.yoo_home.R;

import java.util.ArrayList;
import java.util.List;

import csy.menu.satellitemenulib.view.SatelliteMenu;

public class TestMenuActivity extends AppCompatActivity {
	
	private SatelliteMenu satelliteMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_menu);
		satelliteMenu = findViewById(R.id.satelliteMenu);
		
		List<Integer> menus = new ArrayList<>();
		List<String> menuTitle = new ArrayList<>();
		
		for (int i=0 ; i<3;i++){
			menus.add(R.mipmap.ic_launcher_round);
		}
		
		for (int i=0;i<menus.size();i++){
			menuTitle.add("第["+i+"]个菜单");
		}
		
		Log.i("TestMenuActivity", "menu_size: "+menus.size());
		
		satelliteMenu.getmBuilder()
				.setMenuImage(R.drawable.logo_black)
				.setMenuItemImageResource(menus)
				.setMenuItemNameTexts(menuTitle)
				.setOnMenuItemClickListener(new SatelliteMenu.OnMenuItemClickListener() {
					@Override
					public void onClick(View view, int postion) {
						Toast.makeText(TestMenuActivity.this,"菜单["+postion+"] 被点击了！",Toast.LENGTH_LONG).show();
					}
				}).creat();
		
	}
}
