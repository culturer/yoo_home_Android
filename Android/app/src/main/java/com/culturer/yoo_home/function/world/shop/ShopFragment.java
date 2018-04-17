package com.culturer.yoo_home.function.world.shop;

import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.mine.*;
import com.culturer.yoo_home.widget.directionViewPager.DirectionalViewPager;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	private String mParam1;
	private String mParam2;
	
	private View contentView;
	
	private ListView shop_menu;
	private DirectionalViewPager shop_page;
	
	private List<Fragment> pages ;
	private PagerAdapter pagerAdapter;
	private List<String> menus ;
	MenuAdapter menuAdapter ;
	
	public ShopFragment() {
		// Required empty public constructor
	}
	
	public static ShopFragment newInstance(String param1, String param2) {
		ShopFragment fragment = new ShopFragment();
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		contentView =  inflater.inflate(R.layout.fragment_shop, container, false);
		init();
		return contentView;
	}
	
	private void init(){
		initData();
		initView();
	}
	
	private void initData(){
		initMenuData();
		initPageData();
	}
	
	private void initView(){
		initBaseView();
		initMenuView();
		initPageView();
	}
	
	private void initMenuData(){
		menus = new ArrayList<>();
		for (int i=0 ;i<5 ;i++){
			menus.add("testMenu_"+i);
		}
		menuAdapter = new MenuAdapter(menus,getContext());
	}
	
	private void initPageData(){
		pages = new ArrayList<>();
		for ( int i=0 ; i<menus.size();i++){
			pages.add(ShopPageFragment.newInstance("",""));
		}
		pagerAdapter = new PagerAdapter(getChildFragmentManager(),pages);
	}
	
	private void initBaseView(){
		shop_menu = contentView.findViewById(R.id.shop_menu);
		shop_page = contentView.findViewById(R.id.shop_page);
	}
	
 	private void initMenuView(){
	    shop_menu.setAdapter(menuAdapter);
	    shop_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			    shop_page.setCurrentItem(i);
		    }
	    });
	}
	
	private void initPageView(){
		shop_page.setOrientation(DirectionalViewPager.VERTICAL);
		shop_page.setAdapter(pagerAdapter);
		shop_page.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			
			@Override
			public void onPageSelected(int position) {
			
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
			
			}
		});
	}
	
}
