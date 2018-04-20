package com.culturer.yoo_home.function.world.shop;

import android.app.AlertDialog;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csy.menu.satellitemenulib.view.SatelliteMenu;

public class ShopPageFragment extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	private View contentView;
	private GridView products;
	
	private SatelliteMenu satelliteMenu;
	
	List<Map<String,Object>> dataList;
	private SimpleAdapter adapter;
	
	
	public ShopPageFragment() {
		// Required empty public constructor
	}
	
	public static ShopPageFragment newInstance(String param1, String param2) {
		ShopPageFragment fragment = new ShopPageFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contentView = inflater.inflate(R.layout.fragment_shop_page, container, false);
		init();
		return contentView;
	}
	
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
		initGridData();
	}
	
	private void initView(){
		initBaseView();
		initMenu();
		initGridView();
	}
	
	private void initGridData(){
		//图标
		int icno[] = { R.mipmap.ic_launcher, R.mipmap.ic_launcher,  R.mipmap.ic_launcher,
				R.mipmap.ic_launcher,  R.mipmap.ic_launcher,  R.mipmap.ic_launcher,  R.mipmap.ic_launcher,
				R.mipmap.ic_launcher,  R.mipmap.ic_launcher, R.mipmap.ic_launcher,  R.mipmap.ic_launcher,  R.mipmap.ic_launcher
				,  R.mipmap.ic_launcher ,  R.mipmap.ic_launcher ,  R.mipmap.ic_launcher ,  R.mipmap.ic_launcher };
		//图标下的文字
		String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷","嘻嘻","哈哈","一二三","四五六"};
		dataList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i <icno.length; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("img", icno[i]);
			map.put("text",name[i]);
			dataList.add(map);
		}
		
		
		String[] from={"img","text"};
		int[] to={R.id.product_picture,R.id.product_title};
		adapter=new SimpleAdapter(getContext(), dataList, R.layout.shop_product_item, from, to);
		
	}
	
	private void initBaseView(){
		satelliteMenu = contentView.findViewById(R.id.satelliteMenu);
		products = contentView.findViewById(R.id.products);
	}
	private void initMenu(){
		
		List<Integer> menus = new ArrayList<>();
		for (int i=0 ; i<5;i++){
			menus.add(R.drawable.logo_black);
		}
		List<String> menuTitle = new ArrayList<>();
		for (int i=0;i<menus.size();i++){
			menuTitle.add("第["+i+"]个菜单");
		}
		
		satelliteMenu.getmBuilder()
				.setMenuImage(R.drawable.logo_black)
				.setMenuItemImageResource(menus)
				.setMenuItemNameTexts(menuTitle)
				.setOnMenuItemClickListener(new SatelliteMenu.OnMenuItemClickListener() {
					@Override
					public void onClick(View view, int postion) {
						Toast.makeText(getContext(),"菜单["+postion+"] 被点击了！",Toast.LENGTH_LONG).show();
					}
				}).creat();
	}
	private void initGridView(){
		products.setAdapter(adapter);
		
		products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			                        long arg3) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
				builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
			}
		});
	}
}
