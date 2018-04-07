package com.culturer.yoo_home.function.home.home_circle.homecircle_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class HomecircleListActivity extends AppCompatActivity {
	
	private View contentView;
	private TextView homecircle_ntf;
	private ImageView homecircle_add;
	private RecyclerView homecircle_list;
	
	private List<String> datas = new ArrayList<>();
	HomecircleAdapter adapter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentView = LayoutInflater.from(this).inflate(R.layout.activity_homecircle_list,null);
		setContentView(contentView);
		init();
	}
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
		for (int i=0 ;i<20;i++){
			datas.add("第["+i+"]个隔壁老宋宋啊！");
		}
		adapter = new HomecircleAdapter(datas,this);
	}
	
	private void initView(){
		initBaseView();
		initNavigation(contentView);
		initListView();
	}
	
	private void initBaseView() {
		homecircle_ntf = findViewById(R.id.homecircle_ntf);
		homecircle_add = findViewById(R.id.homecircle_add);
		homecircle_list = findViewById(R.id.homecircle_list);
	}
	
	private void initListView(){
		homecircle_list.setAdapter(adapter);
		homecircle_list.setLayoutManager(new LinearLayoutManager(this));
	}
	
	private void initNavigation(View contentView) {
		LinearLayout topNavigation = contentView.findViewById(R.id.container);
		HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
		builder.setCenterHomeTopic("Yoo+")
				.setCenterHomeTitle("亲友圈")
				.create().
				build();
	}
}
