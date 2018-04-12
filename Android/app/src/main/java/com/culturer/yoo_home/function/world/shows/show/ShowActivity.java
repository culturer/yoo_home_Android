package com.culturer.yoo_home.function.world.shows.show;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.world.shows.show.pages.PageOneFragment;
import com.culturer.yoo_home.function.world.shows.show.pages.PageThreeFragment;
import com.culturer.yoo_home.function.world.shows.show.pages.PageTwoFragment;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private View contentView;
    private BottomNavigationView bottomNavigationView;
    private ViewPager fragment_container;

    private ShowPagerAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_show,null);
        init();
        setContentView(contentView);
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){
        initFragments();
        adapter = new ShowPagerAdapter(getSupportFragmentManager(),fragments);
    }

    private void initView(){
        initBaseView();
        initNavigation(contentView);
        initBottomNavigation();
        initViewPager();
    }

    private void initBaseView(){
        bottomNavigationView = contentView.findViewById(R.id.bottomNavigationView);
        fragment_container = contentView.findViewById(R.id.fragment_container);
    }

    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("心若向阳无畏悲伤")
                .create()
                .build();
    }

    private void initBottomNavigation(){
         BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = item -> {
                    switch (item.getItemId()) {
                        case R.id.show_one_page:
                            fragment_container.setCurrentItem(0);
                            return true;
                        case R.id.show_two_page:
                            fragment_container.setCurrentItem(1);
                            return true;
                        case R.id.show_three_page:
                            fragment_container.setCurrentItem(2);
                            return true;
                    }
                    return false;
                };
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void switchTab(int position){
        if (position == 0){
            bottomNavigationView.setSelectedItemId(R.id.show_one_page);
        }
        else if (position == 1){
            bottomNavigationView.setSelectedItemId(R.id.show_two_page);
        }
        else if (position == 2){
            bottomNavigationView.setSelectedItemId(R.id.show_three_page);
        }
    }

    private void initFragments(){
        fragments  = new ArrayList<>();
        fragments = new ArrayList<>();
        fragments.add(PageOneFragment.newInstance("",""));
        fragments.add(PageTwoFragment.newInstance("",""));
        fragments.add(PageThreeFragment.newInstance("",""));
    }

    private void initViewPager(){
        fragment_container.setCurrentItem(0);
        fragment_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchTab(position%fragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragment_container.setAdapter(adapter);
        switchTab(0);
    }
}
