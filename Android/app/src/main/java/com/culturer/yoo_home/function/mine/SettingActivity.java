package com.culturer.yoo_home.function.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class SettingActivity extends AppCompatActivity {
	
	private View contentView;
	//顶部背景图
	private ImageView setting_top_background;
	//家庭编号
	private EditText setting_et_familyId;
	//签名
	private EditText setting_et_nmsg;
	//昵称
	private EditText setting_et_username;
	//标签
	private EditText setting_et_label;
	//地址点击布局
	private View setting_address;
	//地址显示
	private TextView setting_tv_address;
	//电话点击布局
	private View setting_tel;
	//电话显示
	private TextView setting_tv_tel;
	//邮箱点击布局
	private View setting_email;
	//邮箱显示
	private TextView setting_tv_email;
	//家庭管理
	private TextView setting_manage_family;
	//更换伴侣
	private TextView setting_manage_mate;
	//隐私设置
	private TextView setting_manage_self;
	//安全设置
	private TextView setting_manage_safe;
	//修改密码
	private TextView setting_manage_password;
	//修改电话
	private TextView setting_manage_tel;
	//修改邮箱
	private TextView setting_manage_email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentView = LayoutInflater.from(this).inflate(R.layout.activity_setting,null);
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
		initNavigation(contentView);
		initBaseView();
	}
	
	private void initNavigation(View contentView) {
		LinearLayout topNavigation = contentView.findViewById(R.id.container);
		HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
		builder.setCenterHomeTopic("Yoo+")
				.setCenterHomeTitle("设置")
				.create().
				build();
	}
	
	private void initBaseView(){
		//顶部背景图
		setting_top_background = contentView.findViewById(R.id.setting_top_background);
		//家庭编号
		setting_et_familyId = contentView.findViewById(R.id.setting_et_familyId);
		//签名
		setting_et_nmsg = contentView.findViewById(R.id.setting_et_nmsg);
		//昵称
		setting_et_username = contentView.findViewById(R.id.setting_et_username);
		//标签
		setting_et_label = contentView.findViewById(R.id.setting_et_label);
		//地址点击布局
		setting_address = contentView.findViewById(R.id.setting_address);
		//地址显示
		setting_tv_address = contentView.findViewById(R.id.setting_tv_address);
		//电话点击布局
		setting_tel = contentView.findViewById(R.id.setting_tel);
		//电话显示
		setting_tv_tel = contentView.findViewById(R.id.setting_tv_tel);
		//邮箱点击布局
		setting_email = contentView.findViewById(R.id.setting_email);
		//邮箱显示
		setting_tv_email = contentView.findViewById(R.id.setting_tv_email);
		//家庭管理
		setting_manage_family = contentView.findViewById(R.id.setting_manage_family);
		//更换伴侣
		setting_manage_mate = contentView.findViewById(R.id.setting_manage_mate);
		//隐私设置
		setting_manage_self = contentView.findViewById(R.id.setting_manage_self);
		//安全设置
		setting_manage_safe = contentView.findViewById(R.id.setting_manage_safe);
		//修改密码
		setting_manage_password = contentView.findViewById(R.id.setting_manage_password);
		//修改电话
		setting_manage_tel = contentView.findViewById(R.id.setting_manage_tel);
		//修改邮箱
		setting_manage_email = contentView.findViewById(R.id.setting_manage_email);
	}
	
}
