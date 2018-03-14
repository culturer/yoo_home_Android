package com.culturer.yoo_home.function.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.home.home_circle.HomeCirclesFragment;
import com.culturer.yoo_home.function.home.home_main.HomeMainFragment;
import com.culturer.yoo_home.function.home.home_family.FamilyFragment;
import com.culturer.yoo_home.function.home.home_smart.SmartFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View contentView;
    //标签
    List<String> pagerList = new ArrayList<>();
    //内容
    List<Fragment> fragmentList= new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (contentView == null){
            contentView = inflater.inflate(R.layout.fragment_home, container, false);
            initData();
            initPager();
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if ( parent!=null ){
            parent.removeView(contentView);
        }

        return contentView;
    }

//    初始化ViewPager 滑动页面
    private void initPager(){
        TabLayout home_tab = (TabLayout) findViewById(R.id.home_tab);
        ViewPager home_pager = (ViewPager) findViewById(R.id.home_pager);
        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        home_tab.setTabMode(TabLayout.MODE_FIXED);
        home_tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
        for (int i=0 ;i<pagerList.size() ;i++ ){
            home_tab.addTab(home_tab.newTab().setText(pagerList.get(i)));
        }
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getActivity().getSupportFragmentManager(), fragmentList, pagerList);
        home_pager.setAdapter(pagerAdapter);
        home_tab.setupWithViewPager(home_pager);
    }

    private void initData(){
        initPagerData();
    }

    private void initPagerData(){
        fragmentList.add( new HomeMainFragment() );
        fragmentList.add(HomeCirclesFragment.newInstance("",""));
        fragmentList.add(SmartFragment.newInstance("",""));
        fragmentList.add(FamilyFragment.newInstance("",""));
        pagerList.add("家庭");
        pagerList.add("娱乐");
        pagerList.add("智能");
        pagerList.add("家族");
    }

    private View findViewById(int id){
        return contentView.findViewById(id);
    }
}
