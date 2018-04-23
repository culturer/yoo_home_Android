package com.culturer.yoo_home.function.world;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.function.world.office.OfficeFragment;

import com.culturer.yoo_home.function.world.recommend.RecommendFragment;
import com.culturer.yoo_home.function.world.shop.ShopFragment;
import com.culturer.yoo_home.function.world.shows.ShowsFragment;

import java.util.ArrayList;
import java.util.List;

public class WorldFragment extends Fragment {

    private View contentView;

    public WorldFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (contentView == null){
            contentView = inflater.inflate(R.layout.fragment_world, container, false);
            initPager();
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if ( parent!=null ){
            parent.removeView(contentView);
        }
        return contentView;
    }

    private void initPager(){
        TabLayout world_tab = (TabLayout) findViewById(R.id.world_tab);
        ViewPager world_pager = (ViewPager) findViewById(R.id.world_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(OfficeFragment.newInstance("",""));
        fragmentList.add(ShowsFragment.newInstance("",""));
        fragmentList.add(new RecommendFragment());
        fragmentList.add(ShopFragment.newInstance("",""));
        
        List<String> pagerList = new ArrayList<>();
        pagerList.add("官方");
        pagerList.add("频道");
        pagerList.add("推荐");
        pagerList.add("商城");

        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        world_tab.setTabMode(TabLayout.MODE_FIXED);
        world_tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
        world_tab.addTab(world_tab.newTab().setText(pagerList.get(0)));
        world_tab.addTab(world_tab.newTab().setText(pagerList.get(1)));

        WorldPagerAdapter pagerAdapter = new WorldPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList, pagerList);
        world_pager.setAdapter(pagerAdapter);
        world_tab.setupWithViewPager(world_pager);
    }

    private View findViewById(int id){
        return contentView.findViewById(id);
    }

}
